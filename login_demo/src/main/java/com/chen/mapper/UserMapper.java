package com.chen.mapper;


import com.chen.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {
    @Select("select * from tb_user where username = #{username} and password = #{password}")
    User select(@Param("username") String username, @Param("password") String password);

    @Insert("insert into tb_user(username, password) values (#{username}, #{password})")
    int insert(User user);

    @Select("select * from tb_user where username = #{username}")
    User selectByUsername(String username);


}
