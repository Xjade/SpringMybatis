package com.ssi.mvc.domains.user.repository;

/*
 * Created by Intellij IDEA
 * User:Jade.xiao
 * Date:2018/1/31
 * Time:13:38
*/


import com.ssi.mvc.domains.user.entity.User;

import java.util.List;

public interface UserRepository {
    List<com.ssi.mvc.domains.user.entity.User> selectAllUsers();

    List<com.ssi.mvc.domains.user.entity.User> selectUserById(String id);

    int insertUser(User user);

    int updateUser(User user);

    int deleteUser(String id);
}
