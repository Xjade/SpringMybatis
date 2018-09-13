package com.ssi.mvc.web;

/*
 * Created by Intellij IDEA
 * User:Jade.xiao
 * Date:2018/2/8
 * Time:17:02
*/

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class webTestController {
    @RequestMapping(value = "/jqueryTest",method = RequestMethod.GET)
    public ModelAndView jqueryTest() {
        return new ModelAndView("jqueryTest");
    }
}
