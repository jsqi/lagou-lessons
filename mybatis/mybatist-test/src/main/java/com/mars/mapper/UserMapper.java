package com.mars.mapper;

import com.mars.bean.User;

import java.util.List;

public interface UserMapper {

     List<User> listAll() throws Exception;

     User getUserById(User user) throws Exception;

}
