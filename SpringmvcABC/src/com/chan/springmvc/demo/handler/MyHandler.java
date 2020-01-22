package com.chan.springmvc.demo.handler;

import com.chan.springmvc.demo.annotation.Component;
import com.chan.springmvc.demo.annotation.RequestMapping;

/**
 * 自定义处理器
 *
 * @author Administrator
 */
@Component(id = "myHandler")
public class MyHandler {

    private final String SUCCESS = "success";

    @RequestMapping(path = "/hello.do")
    public String hello() {
        System.out.println("hello");
        return SUCCESS;
    }

    @RequestMapping(path = "/love.do")
    public String love() {
        System.out.println("love");
        return SUCCESS;
    }
}
