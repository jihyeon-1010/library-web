package com.group.librarymanagementweb.dto.book.request;

import com.group.librarymanagementweb.domain.book.Book;
import lombok.Getter;

@Getter
public class BookCreateRequest {

    private Long id;
    private String name;
    private String author;
    private String publisher;

    public Book toEntity() {
        return new Book(id, name, author, publisher);
    }

}
