package com.pegasus.feigong.mapper;

import com.pegasus.feigong.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {



    /**
     * 传统的xml配置
     */
    List<User> orderBy(String field, String sort);

    List<User> safeOrderBy(String field, String sort);


    // 模糊搜索，直接'%#{q}%' 会报错
    // 安全代码：@Select("select * from users where user like concat('%',#{q},'%')")
    @Select("select * from users where username like '%${q}%'")
    List<User> search(String q);


}
