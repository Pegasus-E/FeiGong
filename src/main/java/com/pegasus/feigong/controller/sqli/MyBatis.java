package com.pegasus.feigong.controller.sqli;

import com.pegasus.feigong.pojo.User;
import com.pegasus.feigong.service.UserService;
import com.pegasus.feigong.service.impl.UserServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Api("SQL注入 - MyBatis")
@RestController
@Slf4j
@RequestMapping("/sqli/MyBatis")
public class MyBatis {

    @Autowired
    private UserServiceImpl userService;


    @ApiOperation(value = "vul：Mybatis order by 注入（xml方式）", notes = "#{} 会将对象转成字符串，形成 order by \"user\" desc 造成错误(无法正常排序)，因此很多研发会采用${}来解决，从而造成SQL注入")
    @GetMapping("/vul/order")
    public List<User> orderBy(String field, String sort) {
        log.info("[vul] mybaits: order by " + field + " " + sort);
        return userService.orderBy(field, sort);
    }

    @ApiOperation(value = "safe：Mybatis order by 做映射")
    @GetMapping("/safe/order")
    public List<User> safeOrderBy(String field,String sort) {
        return userService.safeOrderBy(field,sort);
    }


    @ApiOperation(value = "vul：Mybatis使用${}查询")
    @GetMapping("/vul/search")
    public List<User> search(@RequestParam("q") String q) {
        log.info("[vul] mybaits: " + q);
        return userService.search(q);
    }

    @ApiOperation(value = "vul：Mybatis使用${}查询")
    @GetMapping("/safe/search")
    public List<User> safeSearch(@RequestParam("q") String q) {
        return userService.search(q);
    }





}
