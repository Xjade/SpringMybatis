package com.projectUtils;

/*
 *Created By Intellij IDEA
 *User:Jade.Xiao
 *Date:2018/11/19
 *Time:14:26
 *
 */

import com.springapp.mvc.service.BaseService;
import com.springapp.mvc.service.impl.BaseServiceImpl;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyServletContextListener implements ServletContextListener {


    private BaseService baseService;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

//        普通java类获取需要使用的bean通过context获取
//        WebApplicationContext context = ContextLoader.getCurrentWebApplicationContext();

        ServletContext sc=servletContextEvent.getServletContext();
        WebApplicationContext cxt=WebApplicationContextUtils.getWebApplicationContext(sc);
        if(cxt != null && cxt.getBean(BaseServiceImpl.class) != null && baseService == null)
            baseService = (BaseService) cxt.getBean(BaseServiceImpl.class);

        System.out.println("创建MyServletContextListener");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

        System.out.println("销毁MyServletContextListener");

    }
}
