package com.group.libraryapp.repository.user;

import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserJdbcRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //putmapping 분리
    public boolean isUserNotExist(long id){
        String readSql = "select * from user where id=?";
        return jdbcTemplate.query(readSql, (rs, rowNum)->0, id).isEmpty();
    }

    public void updateUserName(UserUpdateRequest request){
        String sql = "update user set name=? where id=?";
        jdbcTemplate.update(sql, request.getName(), request.getId());
    }

    //deletemapping 분리
    public boolean isUserNotExist(String name){
        String readSql = "select * from user where name=?";
        return jdbcTemplate.query(readSql, (rs, rowNum)->0, name).isEmpty();
    }

    public void deleteUserName(String name){
        String deleteSql = "delete from user where name=?";
        jdbcTemplate.update(deleteSql, name);
    }

    //postmapping 분리
    public void createUser(UserCreateRequest request){
        String sql = "insert into user (name,age) values (?,?)";
        jdbcTemplate.update(sql, request.getName(), request.getAge());
    }

    //getmapping 분리
    public List<UserResponse> readUser(){
        String sql = "select * from user";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            long id = rs.getLong("id");
            String name = rs.getString("name");
            int age = rs.getInt("age");
            return new UserResponse(id, name, age);
        });
    }
}
