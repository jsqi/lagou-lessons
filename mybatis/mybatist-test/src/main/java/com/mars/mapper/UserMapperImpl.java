package com.mars.mapper;

import com.mars.bean.User;
import com.mars.core.SqlSession;
import com.mars.core.SqlSessionFactory;
import com.mars.core.SqlSessionFactoryBuilder;
import com.mars.io.Resources;

import java.io.InputStream;
import java.util.List;

@Deprecated
public class UserMapperImpl {


    public List<User> listAllUser() throws Exception {
        InputStream inputStream = Resources.getResourceAsInputStream("mybatisConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        List<User> userList = sqlSession.listAll("user.listAll");
        return userList;
    }

    public User getUserByParam(User user) throws Exception {

        InputStream inputStream = Resources.getResourceAsInputStream("mybatisConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        return  sqlSession.getOne("user.listAll",user);

    }
}
