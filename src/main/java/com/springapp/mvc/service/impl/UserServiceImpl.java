package com.springapp.mvc.service.impl;

/*
 * Created by Intellij IDEA
 * User:Jade.xiao
 * Date:2018/1/16
 * Time:9:02
*/



import com.springapp.mvc.dao.impl.UserDaoMybatis;


import com.springapp.mvc.pojo.User;
import com.springapp.mvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public  class UserServiceImpl implements UserService {

    @Autowired
    private UserDaoMybatis userDaoMybatis;

//    直接查询所有用户并且显示出来
    @Override
    public List<User> getAllUsers() {
        return userDaoMybatis.selectAllUsers();
    }

//    根据用户唯一的ID来查询用户。 这里用list方便后面json返回也可以直接返回User类型
    @Override
    public List<User> getUserById(String id) {
        return userDaoMybatis.selectUserById(id);
    }

//    插入一条数据
    @Override
    public void insert(User user) {
        userDaoMybatis.insertUser(user);
    }

//    根据用户id删除一条数据
    @Override
    public void delete(String id){
        userDaoMybatis.deleteUser(id);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public void insertTest(User user1,User user2) {
        userDaoMybatis.insertUser(user1);
        userDaoMybatis.insertUser(user2);
    }

    //    跟新用户信息，id唯一切不可更新
    @Override
    public void update(User user) {
        userDaoMybatis.updateUser(user);
    }


}
