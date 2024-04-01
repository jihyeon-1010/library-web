package com.group.librarymanagementweb.domain.book;

import com.group.librarymanagementweb.dto.book.response.BookResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findAllByNameContaining(String bookName);
}
