package com.chan.springmvc.handler;

import com.chan.springmvc.model.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.Map;

/**
 * 去
 *
 * @author Administrator
 */
@SessionAttributes
@RequestMapping(value = "springmvc")
@Component
public class TestRequestMapping {
    private final static String SUCCESS = "success";

    @ModelAttribute
    public void getUser(@RequestParam(name = "id") Integer id, Map<String, Object> map) {
        if (1 == id) {
            User user = new User(id, "tom", "123", "tom@spring.com", 22);
            System.out.println("从数据库获取对象：" + user);
            map.put("user1", user);
        }
    }

    /**
     * 参数使用@ModelAttribute注解，从implicitMode中获取对象，然后将request参数绑定到对象，传入方法参数
     * 将方法入参添加到模型中
     *
     * @param user User
     * @return String
     */
    @RequestMapping(value = "updateUser")
    public String updateUser(@ModelAttribute(name = "user1") User user) {
        System.out.println("修改对象" + user);
        return SUCCESS;
    }
}
