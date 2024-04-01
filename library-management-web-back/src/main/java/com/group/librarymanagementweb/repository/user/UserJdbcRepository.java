package com.group.librarymanagementweb.repository.user;

import com.group.librarymanagementweb.dto.user.request.UserCreateRequest;
import com.group.librarymanagementweb.dto.user.request.UserUpdateRequest;
import com.group.librarymanagementweb.dto.user.response.UserResponse;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserJdbcRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // 유저 id가 존재하는지 확인
    public boolean isUserNotExist(long id) {
        String readSql = "select * from user where id = ?";
        return jdbcTemplate.query(readSql, (rs, rowNum) -> 0, id).isEmpty();
    }

    // 유저 저장
    public void saveUser(UserCreateRequest request) {
        String sql = "insert into user(name, birth_date, phone_number, address, reg_date) values(?, ?, ?, ?, ?)";
        jdbcTemplate.update(
                sql,
                request.getName(),
                request.getBirth_date(),
                request.getPhone_number(),
                request.getAddress(),
                request.getReg_date()
        );
    }

    // 유저 조회
    public List<UserResponse> getUser() {
        String sql = "select * from user";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            long id = rs.getLong("id");
            String name = rs.getString("name");
            String birth_date = rs.getString("birth_date");
            String phone_number = rs.getString("phone_number");
            String address = rs.getString("address");
            LocalDate reg_date = rs.getDate("reg_date").toLocalDate();
            return new UserResponse(id, name, birth_date, phone_number, address, reg_date);
        });
    }

    // 유저 업데이트
    public void updateUser(UserUpdateRequest request) {
        StringBuilder sqlBuilder = new StringBuilder("update user set ");
        List<Object> params = new ArrayList<>();

        if (request.getName() != null) {
            sqlBuilder.append("name = ?, ");
            params.add(request.getName());
        }

        if (request.getPhoneNumber() != null) {
            sqlBuilder.append("phone_number = ?, ");
            params.add(request.getPhoneNumber());
        }

        if (request.getAddress() != null) {
            sqlBuilder.append("address = ?, ");
            params.add(request.getAddress());
        }

        sqlBuilder.delete(sqlBuilder.length() - 2, sqlBuilder.length());
        sqlBuilder.append(" where id = ?");
        params.add(request.getId());

        String sql = sqlBuilder.toString();
        jdbcTemplate.update(sql, params.toArray());
    }

    // 유저 삭제
    public void deleteUser(long id) {
        String sql = "delete from user where id = ?";
        jdbcTemplate.update(sql, id);
    }

}
