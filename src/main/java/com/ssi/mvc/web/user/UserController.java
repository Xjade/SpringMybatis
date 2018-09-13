package com.ssi.mvc.web.user;

/*
 * Created by Intellij IDEA
 * User:Jade.xiao
 * Date:2018/1/31
 * Time:13:26
*/

import com.google.common.collect.Maps;


import com.ssi.mvc.domains.user.UserService;
import com.ssi.mvc.domains.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static com.ssi.mvc.web.WebForwardConstant.*;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    //    显示初始页面的查询
    @RequestMapping(value = FWD_SYSTEM_HOME)
    public ModelAndView index() {
        List<User> users = new ArrayList<User>();
        users = userService.getAllUsers();
        return new ModelAndView(FWD_SYSTEM_HOME, "userList", users);
    }

    //    根据id查询用户
    @RequestMapping(value = FWD_SYSTEM_ID, method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> ById(@RequestParam("userId") String id) {
        final Map<String, Object> map = Maps.newHashMap();
        List<User> users;
        if (id != null && id != "") {
            users = userService.getUserById(id);
        } else {
            users = userService.getAllUsers();
        }
        map.put("users", users);
        return map;
    }


    //    插入数据
    @RequestMapping(value = FWD_SYSTEM_INSERT, method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> insertUser(User user) {
        Map<String,Object> map=Maps.newHashMap();
        int insertFlag=0;
        try{
            insertFlag =  userService.insert(user);
        }catch (Exception e){
            map.put("insertflag","Exception faile");
            return map;
        }

        if (insertFlag==1){
            map.put("insertflag","success");
            return map;

        }else {
            map.put("insertflag","faile");
            return map;
        }
    }

    //    根据id修改用户信息
    @RequestMapping(FWD_SYSTEM_UPDATE)
    @ResponseBody
    public Map<String,Object> updateUser(User user) {
        Map<String,Object> map=Maps.newHashMap();
        int updateFlag=0;
        try {
          updateFlag =  userService.update(user);
        } catch (Exception e) {
            map.put("updateflag","Exception faile");
            return map;
        }
        if (updateFlag==1){
            map.put("updateflag","success");
            return map;

        }else {
            map.put("updateflag","faile");
            return map;
        }
    }

    //    根据id删除用户
    @RequestMapping(value = FWD_SYSTEM_DELETE, method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> deleteById(@RequestParam("userId") String userId) {
        Map<String, Object> map = Maps.newHashMap();
        int deleteFlag;
        try {
           deleteFlag = userService.delete(userId);
        } catch (Exception e) {
            map.put("deleteflag", "faile");
            return map;
        }
        if (deleteFlag==1){
            map.put("deleteflag", "success");
            map.put("success","true");
        }
        return map;
    }


}
