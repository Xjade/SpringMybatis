package com.Utils;

/*
 *Created By Intellij IDEA
 *User:Jade.Xiao
 *Date:2018/11/14
 *Time:14:15
 *
 */


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class UrlRewriteFilter implements Filter {
//实现filter需要在web.xml文件里面进行配置

//    修改请求的头路径
    private String addUrl="/DXHY";
//    设置请求拦截的条件
    private String perfix="/receipts";
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String requestURI = request.getRequestURI();
        if (requestURI.length()<10){
            return;
        }
        if (perfix.equals(requestURI.substring(0,9))){
            String newURI = addUrl+requestURI;
            servletRequest.getRequestDispatcher(newURI).forward(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
