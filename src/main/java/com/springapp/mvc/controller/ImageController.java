package com.springapp.mvc.controller;

/*
 *Created By Intellij IDEA
 *User:Jade.Xiao
 *Date:2018/7/16
 *Time:11:32
 */


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;



@Controller
public class ImageController {
    @RequestMapping(value = "ImageIndex")
    public ModelAndView index(){
        return new ModelAndView("test/Image");
    }
}
