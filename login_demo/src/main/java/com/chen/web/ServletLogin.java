package com.chen.web;

import com.chen.mapper.UserMapper;
import com.chen.pojo.User;
import com.chen.util.SqlSessionFactoryUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

@WebServlet ("/login")
public class ServletLogin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");


//      1. 加载MyBatis的核心配置文件，获取SqlSessionFactory

//        String resource = "mybatis-config.xml";
//        InputStream inputStream = Resources.getResourceAsStream(resource);
//        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactoryUtil();

//      2. 获得SqlSession对象用来执行Sql
        SqlSession sqlSession = sqlSessionFactory.openSession();

//      3. 获取Mapper接口的代理对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

//      4. 执行接口方法
        User user = userMapper.select(username, password);

//      5. 释放资源
        sqlSession.close();


        response.setContentType("text/html; charset=utf-8");

        PrintWriter writer = response.getWriter();
        if (user == null) {
            writer.write("登录失败");
        }
        else {
            writer.write("登录成功，欢迎您：" + username);
        }





    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
