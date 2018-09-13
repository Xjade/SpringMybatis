package com.ssi.mvc.domains.user.impl;

/*
 * Created by Intellij IDEA
 * User:Jade.xiao
 * Date:2018/1/31
 * Time:14:16
*/

import com.ssi.mvc.domains.user.UserService;
import com.ssi.mvc.domains.user.entity.User;
import com.ssi.mvc.domains.user.repository.mybatis.UserRepositoryMybatis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepositoryMybatis userDaoMybatis;

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
    public int insert(User user) {
        return userDaoMybatis.insertUser(user);
    }

    //    根据用户id删除一条数据
    @Override
    public int delete(String id) {
        int deleteCount = 0;
        try {
            deleteCount = userDaoMybatis.deleteUser(id);
        } catch (RuntimeException e) {
        }
        return deleteCount;
    }

    @Override
    public int deleteIds(String[] ids) {
        int deleteCount = 0;
        try {
            for (String id : ids) {
                System.out.println(id+"---------");
                deleteCount = userDaoMybatis.deleteUser(id);
                if (deleteCount == 0) {
                    throw new RuntimeException();
                }
            }
        } catch (RuntimeException e) {
        }
        return deleteCount;
    }

    //    跟新用户信息，id唯一切不可更新
    @Override
    public int update(User user) {
        return userDaoMybatis.updateUser(user);
    }


}
