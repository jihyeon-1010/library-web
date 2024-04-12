package com.group.librarymanagementweb.dto.user.request;

import com.group.librarymanagementweb.domain.user.User;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Date;

@Getter
public class UserCreateRequest {
    private String name;
    private String birth_date;
    private String phone_number;
    private String address;
    private LocalDate reg_date;

    public User toEntity() {
        return new User(name, birth_date, phone_number, address, reg_date);
    }
}
