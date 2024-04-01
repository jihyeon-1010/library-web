package com.group.librarymanagementweb.service.book;

import com.group.librarymanagementweb.domain.book.Book;
import com.group.librarymanagementweb.domain.book.BookRepository;
import com.group.librarymanagementweb.domain.user.User;
import com.group.librarymanagementweb.domain.user.UserRepository;
import com.group.librarymanagementweb.domain.user.loanhistory.UserLoanHistoryRepository;
import com.group.librarymanagementweb.dto.book.request.BookCreateRequest;
import com.group.librarymanagementweb.dto.book.request.BookLoanRequest;
import com.group.librarymanagementweb.dto.book.request.BookReturnRequest;
import com.group.librarymanagementweb.dto.book.response.BookResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserLoanHistoryRepository userLoanHistoryRepository;

    // 책 생성
    @Transactional
    public void saveBook(BookCreateRequest request) {
        Book book = request.toEntity();

        if (book.getName() == null || book.getName().isBlank()) {
            throw new IllegalArgumentException("책 이름은 필수로 입력되어야 합니다.");
        }

        bookRepository.save(book);
    }

    // 책 조회
    @Transactional
    public List<BookResponse> getSearchBook(String query) {
        if (query == null || query.isBlank()) {
            throw new IllegalArgumentException("검색 조건을 입력해주세요.");
        }

        List<Book> books = bookRepository.findAllByNameContaining(query);

        if (books == null || books.isEmpty()) {
            throw new IllegalArgumentException("해당 책을 찾을 수 없습니다.");
        }

        return books.stream()
                .map(BookResponse::new)
                .collect(Collectors.toList());
    }

    // 대출
    @Transactional
    public void loanBook(BookLoanRequest request) {
        // 1. 대출기록 정보를 확인해서 대출중인지 확인
        // 2. 만약 대출중이라면 예외 발생
        if (userLoanHistoryRepository.existsByBookIdAndIsReturn(request.getBookId(), false)) {
            throw new IllegalArgumentException("이미 대출중인 책입니다.");
        }

        // 3. 유저 정보 가져오기
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(IllegalArgumentException::new);

        // 4. 도서 정보 가져오기
        Book book = bookRepository.findById(request.getBookId())
                .orElseThrow(IllegalArgumentException::new);

        user.loanBook(book, request.getLoanDate());
    }

    // 반납
    @Transactional
    public void returnBook(BookReturnRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(IllegalArgumentException::new);

        user.returnBook(request.getBookId());

    }
}
