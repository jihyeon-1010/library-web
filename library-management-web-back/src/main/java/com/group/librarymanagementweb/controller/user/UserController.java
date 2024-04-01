package com.group.librarymanagementweb.controller.user;

import com.group.librarymanagementweb.dto.user.request.UserCreateRequest;
import com.group.librarymanagementweb.dto.user.request.UserUpdateRequest;
import com.group.librarymanagementweb.dto.user.response.UserLoanResponse;
import com.group.librarymanagementweb.dto.user.response.UserResponse;
import com.group.librarymanagementweb.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    // 생성
    @PostMapping("/user")
    public void saveUser(@RequestBody UserCreateRequest request) {
        userService.saveUser(request);
    }

    // 전체 조회
    @GetMapping("/user")
    public List<UserResponse> getUser() {
        return userService.getUser();
    }

    // 검색 조회
    @GetMapping("/user/member")
    public List<UserResponse> getSearchUser(@RequestParam String query) {
        return userService.getSearchUser(query);
    }

    // 대여 조회
    @GetMapping("/user/{id}")
    public List<UserLoanResponse> getUserLoanBook(@PathVariable long id) {
        return userService.getUserLoanBook(id);
    }

    // 업데이트
    @PutMapping("/user")
    public void updateUser(@RequestBody UserUpdateRequest request) {
        userService.updateUser(request);
    }

    // 삭제
    @DeleteMapping("/user")
    public void deleteUser(@RequestParam long id) {
        userService.deleteUser(id);
    }

}
