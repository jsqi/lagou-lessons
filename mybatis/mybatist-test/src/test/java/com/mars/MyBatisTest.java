package com.mars;

import com.mars.bean.User;
import com.mars.core.SqlSession;
import com.mars.core.SqlSessionFactory;
import com.mars.core.SqlSessionFactoryBuilder;
import com.mars.io.Resources;
import com.mars.mapper.UserMapper;

import java.io.InputStream;
import java.util.List;

public class MyBatisTest {

    public static void test() {

        InputStream inputStream = Resources.getResourceAsInputStream("mybatisConfig.xml");
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        try {
            SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(inputStream);
            SqlSession sqlSession = sqlSessionFactory.openSession();
            User user = new User();
            user.setId(1);
            user.setName("张三");
            UserMapper userMapper =  sqlSession.getMapper(UserMapper.class);
            List<User> userList = userMapper.listAll();
            System.out.println(userList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(inputStream);
    }

    public static void main(String[] args) {
        test();
    }
}
