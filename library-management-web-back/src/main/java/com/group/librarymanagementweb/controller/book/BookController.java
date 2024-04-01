package com.group.librarymanagementweb.controller.book;

import com.group.librarymanagementweb.dto.book.request.BookCreateRequest;
import com.group.librarymanagementweb.dto.book.request.BookLoanRequest;
import com.group.librarymanagementweb.dto.book.request.BookReturnRequest;
import com.group.librarymanagementweb.dto.book.response.BookResponse;
import com.group.librarymanagementweb.service.book.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    // 책 생성
    @PostMapping("/book")
    public void saveBook(@RequestBody BookCreateRequest request) {
        bookService.saveBook(request);
    }

    // 검색 조회
    @GetMapping("/book")
    public List<BookResponse> getSearchBook(@RequestParam String query) {
        return bookService.getSearchBook(query);
    }

    // 대출
    @PostMapping("/book/loan")
    public void loanBook(@RequestBody BookLoanRequest request) {
        bookService.loanBook(request);
    }

    // 반납
    @PutMapping("/book/return")
    public void returnBook(@RequestBody BookReturnRequest request) {
        bookService.returnBook(request);
    }

}
