package com.group.librarymanagementweb.service.user;

import com.group.librarymanagementweb.domain.user.User;
import com.group.librarymanagementweb.domain.user.UserRepository;
import com.group.librarymanagementweb.domain.user.loanhistory.UserLoanHistory;
import com.group.librarymanagementweb.domain.user.loanhistory.UserLoanHistoryRepository;
import com.group.librarymanagementweb.dto.user.request.UserCreateRequest;
import com.group.librarymanagementweb.dto.user.request.UserUpdateRequest;
import com.group.librarymanagementweb.dto.user.response.UserLoanResponse;
import com.group.librarymanagementweb.dto.user.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Primary
@Service
public class UserServiceV2 implements UserService{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserLoanHistoryRepository userLoanHistoryRepository;

    // 생성
    @Override
    @Transactional
    public void saveUser(UserCreateRequest request) {
        // 1. DTO를 엔티티로 변환
        User user = request.toEntity();

        // 2. 예외 처리
        if (user.getName() == null || user.getName().isBlank()) {
            throw new IllegalArgumentException("이름은 필수로 입력되어야 합니다.");
        }
        if (user.getBirthDate() == null || user.getBirthDate().isBlank()) {
            throw new IllegalArgumentException("생년월일은 필수로 입력되어야 합니다.");
        }
        if (user.getPhoneNumber() == null || user.getPhoneNumber().isBlank()) {
            throw new IllegalArgumentException("전화번호는 필수로 입력되어야 합니다.");
        }
        if (user.getRegDate() == null) {
            throw new IllegalArgumentException("등록일자는 반드시 입력되어야 합니다.");
        }

        // 3. 엔티티를 DB에 저장
        userRepository.save(user);
    }

    // 전체 조회
    @Override
    @Transactional(readOnly = true)
    public List<UserResponse> getUser() {
        return userRepository.findAll()
                .stream()
                .map(UserResponse::new)
                .collect(Collectors.toList());
    }

    // 검색 조회
    @Override
    @Transactional(readOnly = true)
    public List<UserResponse> getSearchUser(String query) {
        if (query == null || query.isBlank()) {
            throw new IllegalArgumentException("검색 조건을 입력해주세요.");
        }

        List<User> userList = userRepository.
                findAllByNameContainingOrPhoneNumberContainingOrBirthDateContaining(query, query, query);

        if (userList == null || userList.isEmpty()) {
            throw new IllegalArgumentException("해당 사용자를 찾을 수 없습니다.");
        }

        return userList.stream()
                .map(UserResponse::new)
                .collect(Collectors.toList());
    }

    // 대여 조회
    @Override
    @Transactional
    public List<UserLoanResponse> getUserLoanBook(long id) {
        // 1. 해당 id를 가진 유저의 대출 기록 가져오기
        List<UserLoanHistory> loans = userLoanHistoryRepository.findAllByUserId(id);

        if (loans == null || loans.isEmpty()) {
            throw new IllegalArgumentException("해당 사용자의 대출 기록이 없습니다.");
        }

        return loans.stream()
                .map(UserLoanResponse::new)
                .collect(Collectors.toList());
    }

    // 업데이트
    @Override
    @Transactional
    public void updateUser(UserUpdateRequest request) {
        // 유저 id가 존재하지 않는 경우 예외 처리
        User user = userRepository.findById(request.getId())
                .orElseThrow(IllegalArgumentException::new);

        if (request.getName() != null) {
            user.updateName(request.getName());
        }

        if (request.getPhoneNumber() != null) {
            user.updatePhone_number(request.getPhoneNumber());
            }

        if (request.getAddress() != null) {
            user.updateAddress(request.getAddress());
        }
    }

    // 삭제
    @Override
    @Transactional
    public void deleteUser(long id) {
        // 유저 id가 존재하지 않는 경우 예외 처리
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 존재하지 않습니다.") );

        userRepository.delete(user);
    }
}
