package com.pegasus.feigong.service;

import com.pegasus.feigong.pojo.User;

import java.util.List;


public interface UserService {


    List<User> search(String q);

    List<User> orderBy(String field, String sort);

    List<User> safeOrderBy(String field, String sort);

}
