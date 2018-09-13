package com.ssi.mvc.domains.user.repository.mybatis;

/*
 * Created by Intellij IDEA
 * User:Jade.xiao
 * Date:2018/1/31
 * Time:13:39
*/


import com.ssi.mvc.domains.user.entity.User;
import com.ssi.mvc.domains.user.repository.UserRepository;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import static com.ssi.mvc.domains.user.UserRepositoryMybatisStatement.*;

import java.util.List;


@Repository
public class UserRepositoryMybatis extends SqlSessionDaoSupport implements UserRepository{

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
        return this.getSqlSession().selectList(STM_GET_ALL_USER);
    }

    //    根据ID查找用户
    @Override
    public List<User> selectUserById(String id) {
        return this.getSqlSession().selectList(STM_GET_USER_BY_ID,id);
    }

    //    新增用户
    @Override
    public int insertUser(User user) {
       return this.getSqlSession().insert(STM_SET_USER,user);
    }

    //    根据用户ID修改用户信息
    @Override
    public int updateUser(User user) {
      return   this.getSqlSession().update(STM_UPDATE_USER,user);
    }

    @Override
    public int deleteUser(String id) {
       return this.getSqlSession().delete(STM_DELETE_USER,id);
    }
}
