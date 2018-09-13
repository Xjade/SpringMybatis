package com.springapp.mvc.dao.impl;

/*
 * Created by Intellij IDEA
 * User:Jade.xiao
 * Date:2018/1/16
 * Time:9:20
*/




import com.springapp.mvc.dao.UserDao;
import com.springapp.mvc.pojo.User;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public final class UserDaoMybatis extends SqlSessionDaoSupport implements UserDao {

//    自动注入setSqlSessionFactory
    @Autowired
    @Override
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

//    自动注入setSqlSessionTemplate
    @Autowired
    @Override
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }

//    查询全部用户
    @Override
    public List<User> selectAllUsers() {
        return this.getSqlSession().selectList("selectAllUsers");
    }

//    根据ID查找用户
    @Override
    public List<User> selectUserById(String id) {
        return this.getSqlSession().selectList("selectUserById",id);
    }

//    新增用户
    @Override
    public void insertUser(User user) {
        this.getSqlSession().insert("insertUser",user);
    }

//    根据用户ID修改用户信息
    @Override
    public void updateUser(User user) {
        this.getSqlSession().update("updateUser",user);
    }

    @Override
    public void deleteUser(String id) {
        this.getSqlSession().delete("deleteUser",id);
    }
}
