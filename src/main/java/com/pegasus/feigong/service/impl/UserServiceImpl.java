package com.pegasus.feigong.service.impl;


import com.pegasus.feigong.mapper.UserMapper;
import com.pegasus.feigong.pojo.User;
import com.pegasus.feigong.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    public List<User> search(String q) {
        return userMapper.search(q);
    }

    public List<User> orderBy(String field, String sort) {
        return userMapper.orderBy(field,sort);
    }

    public List<User> safeOrderBy(String field, String sort) {
        return userMapper.safeOrderBy(field,sort);
    }
}
