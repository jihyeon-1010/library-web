package com.group.librarymanagementweb.dto.user.request;

import lombok.Getter;

@Getter
public class UserUpdateRequest {

    private long id;
    private String name;
    private String phoneNumber;
    private String address;

}
