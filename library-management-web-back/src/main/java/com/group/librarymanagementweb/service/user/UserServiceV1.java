//package com.group.librarymanagementweb.service.user;
//
//import com.group.librarymanagementweb.dto.user.request.UserCreateRequest;
//import com.group.librarymanagementweb.dto.user.request.UserUpdateRequest;
//import com.group.librarymanagementweb.dto.user.response.UserResponse;
//import com.group.librarymanagementweb.repository.user.UserJdbcRepository;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class UserServiceV1 implements UserService{
//
//    private final UserJdbcRepository userJdbcRepository;
//
//    public UserServiceV1(UserJdbcRepository userJdbcRepository) {
//        this.userJdbcRepository = userJdbcRepository;
//    }
//
//    // 저장
//    @Override
//    public void saveUser(UserCreateRequest request) {
//        userJdbcRepository.saveUser(request);
//    }
//
//    // 조회
//    @Override
//    public List<UserResponse> getUser() {
//        return userJdbcRepository.getUser();
//    }
//
//    // 업데이트
//    @Override
//    public void updateUser(UserUpdateRequest request) {
//        // 유저가 존재하지 않는 경우 예외 처리
//        if (userJdbcRepository.isUserNotExist(request.getId())) {
//            throw new IllegalArgumentException();
//        }
//
//        userJdbcRepository.updateUser(request);
//    }
//
//    // 삭제
//    @Override
//    public void deleteUser(long id) {
//        // 유저가 존재하지 않는 경우 예외 처리
//        if (userJdbcRepository.isUserNotExist(id)) {
//            throw new IllegalArgumentException();
//        }
//
//        userJdbcRepository.deleteUser(id);
//    }
//
//}
