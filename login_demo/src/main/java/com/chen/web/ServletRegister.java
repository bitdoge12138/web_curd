package com.chen.web;

import com.chen.mapper.UserMapper;
import com.chen.pojo.User;
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

@WebServlet("/register")
public class ServletRegister extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);


//      1. 加载MyBatis的核心配置文件，获取SqlSessionFactory

        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

//      2. 获得SqlSession对象用来执行Sql
        SqlSession sqlSession = sqlSessionFactory.openSession();

//      3. 获取Mapper接口的代理对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

//      4. 执行接口方法
        User user1 = userMapper.selectByUsername(username);

        response.setContentType("text/html; charset=utf-8");


        if (user1 == null) {
            int cnt = userMapper.insert(user);
            if (cnt > 0) {
                response.getWriter().write("注册成功");
            }
            else {
                response.getWriter().write("注册失败");
            }
            sqlSession.commit();
            sqlSession.close();
        }
        else {

            response.getWriter().write("用户名已存在，请重新输入");
            sqlSession.close();
        }









        PrintWriter writer = response.getWriter();


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
