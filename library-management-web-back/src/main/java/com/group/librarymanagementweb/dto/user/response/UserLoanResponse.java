package com.group.librarymanagementweb.dto.user.response;

import com.group.librarymanagementweb.domain.user.loanhistory.UserLoanHistory;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class UserLoanResponse {
    private final long id;
    private final long bookId;
    private final String name;
    private final String author;
    private final String publisher;
    private final LocalDate loanDate;
    private final boolean isReturn;

    public UserLoanResponse(UserLoanHistory userLoanHistory) {
        this.id = userLoanHistory.getId();
        this.bookId = userLoanHistory.getBook().getId();
        this.name = userLoanHistory.getBook().getName();
        this.author = userLoanHistory.getBook().getAuthor();
        this.publisher = userLoanHistory.getBook().getPublisher();
        this.loanDate = userLoanHistory.getLoanDate();
        this.isReturn = userLoanHistory.isReturn();
    }

    public boolean isReturn() {
        return isReturn;
    }
}
