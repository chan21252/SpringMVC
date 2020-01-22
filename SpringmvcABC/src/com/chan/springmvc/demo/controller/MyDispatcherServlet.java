package com.chan.springmvc.demo.controller;

import com.chan.springmvc.demo.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * 调度servlet
 * （1）从初始化参数中读取处理器的类名，获取对应的Class对象
 * （2）遍历处理的所有方法对象，
 * - 找到有RequestMapping注解并且path值和请求url一致的方法，执行该方法，返回视图
 * - 如果没有找到，抛出异常
 *
 * @author Administrator
 */
public class MyDispatcherServlet extends HttpServlet {
    private static final String FORWARD_PREFIX = "/WEB-INF/views/";
    private static final String FORWARD_SUFFIX = ".jsp";

    private static Class<?> handlerClazz;
    private static Method[] handlerMethods;

    @Override
    public void init() throws ServletException {
        System.out.println("初始化调度Servlet");
        System.out.println("加载handler的Class对象");
        String handlerClassName = getInitParameter("handler");
        try {
            handlerClazz = Class.forName(handlerClassName);
            handlerMethods = handlerClazz.getMethods();
        } catch (ClassNotFoundException e) {
            System.out.println("未找到类：" + handlerClassName);
        }
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestPath = req.getRequestURI();
        System.out.println("请求路径：" + requestPath);

        try {
            boolean hasHandlerMethod = false;
            //遍历方法
            for (Method method : handlerMethods) {
                //是否有RequestMapping注解
                if (method.isAnnotationPresent(RequestMapping.class)) {
                    //获取注解
                    RequestMapping requestAnn = method.getAnnotation(RequestMapping.class);
                    System.out.println("找到一个RequestMapping注解，可以处理的请求路径为" + requestAnn.path());
                    //找到了请求url对应注解的处理方法
                    if (requestAnn.path().equals(requestPath)) {
                        String result = (String) method.invoke(handlerClazz.newInstance());
                        System.out.println("执行处理方法：" + method.getName() + "，结果：" + result);

                        //转发到返回视图
                        req.getRequestDispatcher(FORWARD_PREFIX + result + FORWARD_SUFFIX).forward(req, resp);

                        hasHandlerMethod = true;
                        break;
                    }
                }
            }
            if (!hasHandlerMethod) {
                System.out.println("未找到对应的处理器方法！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
