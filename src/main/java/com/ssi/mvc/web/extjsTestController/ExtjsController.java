package com.ssi.mvc.web.extjsTestController;

/*
 * Created by Intellij IDEA
 * User:Jade.xiao
 * Date:2018/1/31
 * Time:16:27
*/

import com.google.common.collect.Maps;
import com.ssi.mvc.domains.user.ExtUserService;
import com.ssi.mvc.domains.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

import static com.ssi.mvc.web.WebForwardConstant.*;

@Controller
public class ExtjsController {

    @Autowired
    private ExtUserService userService;

    @RequestMapping("extjsone")
    public ModelAndView extjsone(){
        return new ModelAndView("extjsOne");
    }

    @RequestMapping("extjstwo")
    public ModelAndView extjstwo(){
        return new ModelAndView("extjsTwo");
    }



    @RequestMapping(FWD_SYSTEM_EXT_USER)
    public ModelAndView extjsuser(){
        return new ModelAndView(FWD_SYSTEM_EXT_USER);
    }

    @RequestMapping(value = FWD_SYSTEM_EXT_ID, method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> searchById(final HttpServletRequest request, final HttpServletResponse response) {
        final Map<String, Object> map = Maps.newHashMap();
//        获取查询的id
        final String id=request.getParameter("userId");
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
    @RequestMapping(value = FWD_SYSTEM_EXT_INSERT, method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> insertUser(final HttpServletRequest request, final HttpServletResponse response) {
        User user=new User();
//        System.out.println("message："+request.getParameter("userId")+request.getParameter("userName")+request.getParameter("userAge"));
//        获取要插入的数据
        user.setId(request.getParameter("userId"));
        user.setName(request.getParameter("userName"));
        user.setAge(Integer.parseInt(request.getParameter("userAge")));
        response.setCharacterEncoding("utf-8");
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

    //    根据id删除用户
    @RequestMapping(value = FWD_SYSTEM_EXT_DELETE, method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> deleteById(final HttpServletRequest request, final HttpServletResponse response) {
//        获取删除的id的集合。
        String[] ids=request.getParameter("ids").split(",");
        Map<String, Object> map = Maps.newHashMap();
        int deleteFlag=0;
        if (ids.length==1){
            String id=ids[0];
            deleteFlag=userService.delete(id);
        }else if (ids.length!=0){
            deleteFlag = userService.deleteIds(ids);
        }
        if (deleteFlag==1){
            map.put("deleteflag", "success");
            map.put("success","true");
        }else {
            map.put("deleteflag", "faile");
            map.put("success","false");
        }
        return map;
    }

    //    根据id修改用户信息
    @RequestMapping(value =FWD_SYSTEM_EXT_UPDATE, method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> updateUser(final HttpServletRequest request, final HttpServletResponse response) {
        User user=new User();
        user.setId(request.getParameter("userId"));
        user.setName(request.getParameter("userName"));
        user.setAge(Integer.parseInt(request.getParameter("userAge")));
        response.setCharacterEncoding("utf-8");
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
}
