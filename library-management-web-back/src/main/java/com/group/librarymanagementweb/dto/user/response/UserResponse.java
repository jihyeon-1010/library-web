package com.group.librarymanagementweb.dto.user.response;

import com.group.librarymanagementweb.domain.book.Book;
import com.group.librarymanagementweb.domain.user.User;

import java.time.LocalDate;
import java.util.List;

public class UserResponse {

    private long id;
    private String name;
    private String birthDate;
    private String phoneNumber;
    private String address;
    private LocalDate regDate;

    public UserResponse(long id, String name, String birth_date, String phone_number, String address, LocalDate reg_date) {
        this.id = id;
        this.name = name;
        this.birthDate = birth_date;
        this.phoneNumber = phone_number;
        this.address = address;
        this.regDate = reg_date;
    }

    public UserResponse(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.birthDate = user.getBirthDate();
        this.phoneNumber = user.getPhoneNumber();
        this.address = user.getAddress();
        this.regDate = user.getRegDate();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public LocalDate getRegDate() {
        return regDate;
    }
}
