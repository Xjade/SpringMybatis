package com.springapp.mvc.service;

/*
 * Created by Intellij IDEA
 * User:Jade.xiao
 * Date:2018/1/16
 * Time:9:02
*/

import com.springapp.mvc.pojo.User;

import java.util.List;

public interface UserService {

    /**
     *
     * @return 返回的是User类型的List
     */
    List<User> getAllUsers();

    /**
     *
     * @param id 根据用户的ID查询
     * @return 返回的是User类型的List
     */
    List<User> getUserById(String id);

    /**
     *
     * @param user 直接用User类型作为参数插入数据
     */
    void insert(User user);

    /**
     *
     * @param user 直接用User类型作为参数跟新数据
     */
    void update(User user);

    /**
     *
     * @param id 根据用户id删除数据
     */
    void delete(String id);

    /**
     *
     * @param user1 直接用User类型作为参数插入数据
     * @param user2 直接用User类型作为参数插入数据
     */
    void insertTest(User user1,User user2);
}
