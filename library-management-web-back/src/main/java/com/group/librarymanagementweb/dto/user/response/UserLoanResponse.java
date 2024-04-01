package com.group.librarymanagementweb.dto.user.response;

import com.group.librarymanagementweb.domain.user.loanhistory.UserLoanHistory;

import java.time.LocalDate;

public class UserLoanResponse {
    private long id;
    private long bookId;
    private String name;
    private String author;
    private String publisher;
    private LocalDate loanDate;
    private boolean isReturn;

    public UserLoanResponse(UserLoanHistory userLoanHistory) {
        this.id = userLoanHistory.getId();
        this.bookId = userLoanHistory.getBook().getId();
        this.name = userLoanHistory.getBook().getName();
        this.author = userLoanHistory.getBook().getAuthor();
        this.publisher = userLoanHistory.getBook().getPublisher();
        this.loanDate = userLoanHistory.getLoanDate();
        this.isReturn = userLoanHistory.isReturn();
    }

    public long getId() {
        return id;
    }

    public long getBookId() {
        return bookId;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublisher() {
        return publisher;
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public boolean isReturn() {
        return isReturn;
    }
}
