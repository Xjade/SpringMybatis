package com.springapp.mvc.JunitTest;

/*
 * Created by Intellij IDEA
 * User:Jade.xiao
 * Date:2018/1/16
 * Time:8:58
*/




import com.springapp.mvc.pojo.User;
import com.springapp.mvc.service.impl.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration("file:../../../src/main/webapp/WEB-INF/mvc-dispatcher-servlet.xml")
public class TestClass {

    @Autowired
    UserServiceImpl userService ;

    @Before
    public void test(){
        System.out.println(userService);
    }

    @Test
    public void test1(){

        List<User> users=new ArrayList<User>();
        users=userService.getAllUsers();
        System.out.println(users.size());

    }
}
