package com.springapp.mvc.controller;

/*
 *Created By Intellij IDEA
 *User:Jade.Xiao
 *Date:2018/12/4
 *Time:10:02
 *
 */

import com.springapp.mvc.pojo.User;
import com.springapp.mvc.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class TestController {

    private final Logger logger=LoggerFactory.getLogger(TestController.class);

    @Autowired
    private UserService userService;


    @ResponseBody
    @RequestMapping(value = "/testTransaction",method = RequestMethod.POST)
    public Map<String,Object> testTransaction(HttpServletRequest request){

        System.out.println(request.getSession().toString());
        System.out.println();

        User user=new User();
        user.setName("a");
        System.out.println(user.printThis());



        System.out.println(String.valueOf(request.getHeader("user-agent")));
        System.out.println(request.getHeader("Content-Type"));

//        Enumeration<String> list=request.getHeaderNames();
//
//        while (list.hasMoreElements()){
//            System.out.println(list.nextElement());
//        }


        Map<String,Object> returnMap=new HashMap<>();
        User user1=new User();
        User user2=new User();
        user1.setAge(1);
        user1.setId("test1");
        user1.setName("test1");
        user2.setAge(1);
        user2.setId("test1");


        returnMap.put("req",request.getClass());
        return returnMap;
    }
}
