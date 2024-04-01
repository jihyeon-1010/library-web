package com.group.librarymanagementweb.domain.user;

import com.group.librarymanagementweb.domain.book.Book;
import com.group.librarymanagementweb.domain.user.loanhistory.UserLoanHistory;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(nullable = false, length = 25)
    private String name;
    @Column(nullable = false)
    private String birthDate;
    @Column(nullable = false, length = 11)
    private String phoneNumber;
    @Column(length = 40)
    private String address;
    @Column(nullable = false)
    private LocalDate regDate;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserLoanHistory> userLoanHistories = new ArrayList<>();

    public User(String name, String birthDate, String phoneNumber, String address, LocalDate regDate) {
        this.name = name;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.regDate = regDate;
    }

    public void updateName(String name) {
        this.name = name;
    }

    public void updatePhone_number(String phone_number) {
        this.phoneNumber = phone_number;
    }

    public void updateAddress(String address) {
        this.address = address;
    }

    public void loanBook(Book book, LocalDate loanDate) {
        this.userLoanHistories.add(new UserLoanHistory(this, book, loanDate));
    }

    public void returnBook(long bookId) {
        // userLoanHistories에서 해당 bookId를 가진 모든 기록 찾기
        List<UserLoanHistory> targetHistories = this.userLoanHistories.stream()
                .filter(history -> history.getBook().getId() == bookId)
                .collect(Collectors.toList());

        // 모든 대출 기록 반납
        targetHistories.forEach(UserLoanHistory::doReturn);
    }
}
