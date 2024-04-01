package com.group.librarymanagementweb.dto.book.request;

import com.group.librarymanagementweb.domain.book.Book;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class BookLoanRequest {
    private long userId;
    private long bookId;
    private LocalDate loanDate;
}
