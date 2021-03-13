package com.mars.mapper;

import com.mars.bean.User;

import java.util.List;

/**
 * @author jsq
 */
public interface UserMapper {

     /**
      * 查询用户列表
      * @return
      * @throws Exception
      */
     List<User> listAll() throws Exception;

     /**
      * 根据Id查询用户信息
      * @param user
      * @return
      * @throws Exception
      */
     User getUserById(User user) throws Exception;

     /**
      * 根据Id 更新用户数据
      * @param user
      * @return
      */
     int upDateUserById(User user);

}
