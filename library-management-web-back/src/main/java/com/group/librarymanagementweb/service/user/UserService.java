package com.group.librarymanagementweb.service.user;

import com.group.librarymanagementweb.domain.book.Book;
import com.group.librarymanagementweb.domain.book.BookRepository;
import com.group.librarymanagementweb.domain.user.loanhistory.UserLoanHistory;
import com.group.librarymanagementweb.dto.book.response.BookResponse;
import com.group.librarymanagementweb.dto.user.request.UserCreateRequest;
import com.group.librarymanagementweb.dto.user.request.UserUpdateRequest;
import com.group.librarymanagementweb.dto.user.response.UserLoanResponse;
import com.group.librarymanagementweb.dto.user.response.UserResponse;

import java.util.List;
import java.util.Optional;

public interface UserService {
    void saveUser(UserCreateRequest request);

    List<UserResponse> getUser();

    List<UserResponse> getSearchUser(String query);

    public List<UserLoanResponse> getUserLoanBook(long id);

    void updateUser(UserUpdateRequest request);

    void deleteUser(long id);
}
