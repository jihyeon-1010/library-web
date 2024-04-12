package com.group.librarymanagementweb.dto.book.response;

import com.group.librarymanagementweb.domain.book.Book;
import lombok.Getter;

@Getter
public class BookResponse {
    private final long id;
    private final String name;
    private final String author;
    private final String publisher;

    public BookResponse(long id, String name, String author, String publisher) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.publisher = publisher;
    }

    public BookResponse(Book book) {
        this.id = book.getId();
        this.name = book.getName();
        this.author = book.getAuthor();
        this.publisher = book.getPublisher();
    }
}
