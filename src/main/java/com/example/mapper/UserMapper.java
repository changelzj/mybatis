package com.example.mapper;

import com.example.entity.User;

import java.util.List;

public interface UserMapper {
    void add(User user);
    List<User> getList();
}


