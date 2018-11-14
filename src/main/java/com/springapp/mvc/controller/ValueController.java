package com.springapp.mvc.controller;

/*
 *Created By Intellij IDEA
 *User:Jade.Xiao
 *Date:2018/11/14
 *Time:15:09
 *
 */

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

@Controller
public class ValueController {


    @Value("${abc.name}")
    private String name;
}


