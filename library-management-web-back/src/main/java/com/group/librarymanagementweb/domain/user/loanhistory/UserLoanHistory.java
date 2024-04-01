package com.group.librarymanagementweb.domain.user.loanhistory;

import com.group.librarymanagementweb.domain.book.Book;
import com.group.librarymanagementweb.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
public class UserLoanHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;

    @ManyToOne(fetch = FetchType.LAZY)
    private Book book;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    private LocalDate loanDate;
    private boolean isReturn;

    public UserLoanHistory(User user, Book book, LocalDate loanDate) {
        this.user = user;
        this.book = book;
        this.loanDate = loanDate;
        this.isReturn = false;
    }

    public boolean isReturn() {
        return isReturn;
    }

    public void doReturn() {
        this.isReturn = true;
        this.loanDate = LocalDate.now();
    }

}
