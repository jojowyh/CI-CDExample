package com.test.mapper;

import com.test.pojo.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
* @author 86187
* @description 针对表【user(用户表)】的数据库操作Mapper
* @createDate 2024-06-24 14:59:34
* @Entity com.test.pojo.entity.User
*/

@Mapper
public interface UserMapper {



    User getByCondition(User user);

    @Insert("insert into trade_platform.user (user_name, user_phone, user_pwd,create_time, update_time)" +
            "values (#{userName},#{userPhone},#{userPwd},#{createTime},#{updateTime})")
    void insert(User user);



    void update(User user);

    @Delete("delete from trade_platform.user where user_id = #{userId}")
    void delete(Long userId);

    @Select("select * from trade_platform.user")
    List<User> query();
}




