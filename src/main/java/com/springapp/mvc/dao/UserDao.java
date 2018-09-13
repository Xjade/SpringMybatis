package com.springapp.mvc.dao;

/*
 * Created by Intellij IDEA
 * User:Jade.xiao
 * Date:2018/1/16
 * Time:9:03
*/

import com.springapp.mvc.pojo.User;

import java.util.List;

public interface UserDao {
   List<User> selectAllUsers();

   List<User> selectUserById(String id);

   void insertUser(User user);

   void updateUser(User user);

   void deleteUser(String id);

}
