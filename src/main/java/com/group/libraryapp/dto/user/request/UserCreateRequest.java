package com.group.libraryapp.dto.user.request;

public class UserCreateRequest {

    private String name;
    //age가 int가 아니라 integer인 이유는 null이 들어올 수도 있어서
    private Integer age;

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }
}
