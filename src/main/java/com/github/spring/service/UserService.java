package com.github.spring.service;

import com.github.spring.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public Map userInfo() throws Exception {
        return null;
    }

}