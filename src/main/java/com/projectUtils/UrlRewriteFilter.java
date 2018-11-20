package com.projectUtils;

/*
 *Created By Intellij IDEA
 *User:Jade.Xiao
 *Date:2018/11/14
 *Time:14:15
 *
 */

import com.springapp.mvc.service.BaseService;
import com.springapp.mvc.service.UserService;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.context.support.XmlWebApplicationContext;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.DriverManager;

public class UrlRewriteFilter implements Filter {



    private BaseService baseService;

    private FilterConfig config;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        config = filterConfig;
        ServletContext sc=filterConfig.getServletContext();
        XmlWebApplicationContext cxt=(XmlWebApplicationContext)WebApplicationContextUtils.getWebApplicationContext(sc);
        if(cxt != null && cxt.getBean(BaseService.class) != null && baseService == null)
            baseService = (BaseService) cxt.getBean(BaseService.class);
    }


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        filterChain.doFilter(servletRequest,servletResponse);


//        HttpServletRequest request = (HttpServletRequest) servletRequest;
//        String requestURI = request.getRequestURI();
//        if (true){
//            filterChain.doFilter(servletRequest,servletResponse);
//            return;
//        }
//        if (false){
//            String newURI = ""+requestURI;
//            servletRequest.getRequestDispatcher(newURI).forward(servletRequest, servletResponse);
//        }
    }

    @Override
    public void destroy() {
    }
}
