package com.group.librarymanagementweb.dto.user.response;

import com.group.librarymanagementweb.domain.book.Book;
import com.group.librarymanagementweb.domain.user.User;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
public class UserResponse {
    private final long id;
    private final String name;
    private final String birthDate;
    private final String phoneNumber;
    private final String address;
    private final LocalDate regDate;

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

}
