package com.chan.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * HelloWorld控制器
 *
 * @author Administrator
 */
@Controller(value = "helloWorld")
public class HelloWorld {

    /**
     * 1. @RequestMapping注解映射请求URL和要执行的方法
     * 2. 返回值会通过视图处理器解析为下一步请求，进行转发操作
     *
     * @return String
     */
    @RequestMapping(value = "hello")
    public String hello() {
        System.out.println("Hello World!");
        return "success";
    }
}
