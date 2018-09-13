package com.springapp.mvc.controller;

/*
 * Created by Intellij IDEA
 * User:Jade.xiao
 * Date:2018/1/16
 * Time:15:44
*/
import com.google.common.collect.Maps;
import com.springapp.mvc.pojo.User;
import com.springapp.mvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

//    显示初始页面的查询
    @RequestMapping(value = "Index")
    public ModelAndView index(){
        List<User> users=new ArrayList<User>();
        users= userService.getAllUsers();
        return new ModelAndView("index","userList",users);
    }

//    根据id查询用户
    @RequestMapping(value = "ID",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> ById(@RequestParam("id")String id){
        final Map<String,Object> map= Maps.newHashMapWithExpectedSize(1);
        List<User> users;
        if (id!=null&&id!=""){
            users=userService.getUserById(id);
        }else {
            users=userService.getAllUsers();
        }
        map.put("users",users);
        return map;
    }


//    插入数据
    @RequestMapping(value = "Insert",method = RequestMethod.GET)
    @ResponseBody
    public  ModelAndView insertUser(User user){
        try {
            userService.insert(user);
        }catch (Exception e){
        }
        List<User> users=userService.getAllUsers();
        return new ModelAndView("redirect:index","userList",users);
    }

//    根据ID修改用户信息
    @RequestMapping("Update")
    @ResponseBody
    public ModelAndView updateUser(User user){
        System.out.print(user.toString());
        try {
            userService.update(user);
        }catch (Exception e){
        }
        List<User> users=userService.getAllUsers();
        return new ModelAndView("redirect:index","userList",users);
    }

//    根据id删除用户
    @RequestMapping(value = "DeleteID",method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> deleteById(@RequestParam("userId") String userId){
        Map<String,Object> map=new HashMap<String,Object>();
        try {
            userService.delete(userId);
            map.put("flag", "success");
        }catch (Exception e){
            map.put("flag","faile");
        }
            return map;
    }

//    弹出添加用户的窗口
    @RequestMapping("showModel")
    public String showModel(){
        return "AddUserForm";
    }

    //    测试页面
    @RequestMapping("test")
    public String test(){
        return "AddUserForm";
    }
}
