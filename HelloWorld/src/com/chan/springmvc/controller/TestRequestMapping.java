package com.chan.springmvc.controller;

import com.chan.springmvc.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

/**
 * 测试RequestMapping
 *
 * @author Administrator
 */
@SessionAttributes(value = {"user"}, types = {String.class})
@RequestMapping(value = "springmvc")
@Controller
public class TestRequestMapping {
    private static final String SUCCESS = "success";

    @RequestMapping(value = "testMethod", method = RequestMethod.POST)
    public String testRequestMethod() {
        System.out.println("测试请求方法");
        return SUCCESS;
    }

    @RequestMapping(value = "testParams", params = {"username!=admin", "password"})
    public String testRequestParams() {
        System.out.println("测试请求参数");
        return SUCCESS;
    }

    @RequestMapping(value = "testHeaders", headers = {"Accept-Language=zh-CN,zh;q=0.9,en;q=0.8"})
    public String testRequestHeaders() {
        System.out.println("测试请求头");
        return SUCCESS;
    }

    /**
     * 测试路径占位符
     *
     * @param id 映射路径URL的占位符
     * @return String
     */
    @RequestMapping(value = "testVariable/{id}")
    public String testVariable(@PathVariable("id") Integer id) {
        System.out.println("路径变量=" + id);
        return SUCCESS;
    }

    @RequestMapping(value = "testRest/{id}", method = RequestMethod.GET)
    public String testRestGet(@PathVariable Integer id) {
        System.out.println("测试Rest Get请求，id=" + id);
        return SUCCESS;
    }

    @RequestMapping(value = "testRest/{id}", method = RequestMethod.POST)
    public String testRestPost(@PathVariable Integer id) {
        System.out.println("测试Rest Post请求，id=" + id);
        return SUCCESS;
    }

    @RequestMapping(value = "testRest/{id}", method = RequestMethod.DELETE)
    public String testRestDelete(@PathVariable Integer id) {
        System.out.println("测试Rest Delete请求，id=" + id);
        return SUCCESS;
    }

    @RequestMapping(value = "testRest/{id}", method = RequestMethod.PUT)
    public String testRestPut(@PathVariable Integer id) {
        System.out.println("测试Rest Put请求，id=" + id);
        return SUCCESS;
    }

    @RequestMapping(value = "testParam")
    public String testRequestParam(@RequestParam(value = "username") String username,
                             @RequestParam(value = "age", required = false) Integer age) {
        System.out.println("username=" + username);
        System.out.println("age=" + age);
        return SUCCESS;
    }

    /**
     * SpringMVC会根据实体的属性名自动装配，支持级联属性
     *
     * @param user User
     */
    @RequestMapping("testPojo")
    public void testPojo(User user,
                           HttpServletRequest request, HttpServletResponse response,
                           Writer out) throws IOException {
        System.out.println(user);
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");

        out.write(user.toString());
        out.close();
    }

    /**
     * 返回ModelAndView类型
     * 包含了视图和模型数据，模型数据默认被放到request域中
     *
     * @return ModelAndView
     */
    @RequestMapping("testModelAndView")
    public ModelAndView testModelAndView() {
        ModelAndView mv = new ModelAndView(SUCCESS);
        mv.addObject("time", new Date());
        return mv;
    }

    /**
     * Map数据会添加到返回模型中
     *
     * @param map Map
     * @return String
     */
    @RequestMapping("testMap")
    public String testMap(Map<String, Object> map) {
        map.put("username", Arrays.asList("tom", "jerry", "jack"));
        return SUCCESS;
    }

    /**
     * 在控制器类中加上SessionAttributes注解，
     * 对应模型属性会被放入到session域中
     *
     * @param map Map
     * @return String
     */
    @RequestMapping("testSessionAttr")
    public String testSessionAttributes(Map<String, Object> map) {
        User user = new User("admin", "123");
        map.put("user", user);
        map.put("school", "北京大学");
        return SUCCESS;
    }

    /**
     * ModelAttribute注解表示模型属性
     */
    @ModelAttribute
    public void getUser(@RequestParam(value = "id", required = false) Integer id,
                        Map<String, Object> map) {
        if (id == 1) {
            //模拟从数据库中查询到对应对象
            User userAdmin = new User(1, "admin", "123", "admin@springmvc.com");
            System.out.println("从数据库中获取到对象：" + userAdmin);
            map.put("userAdmin", userAdmin);
        }
    }

    /**
     * 1. 执行带有ModelAttribute注解的方法，从数据库中取出对象，放入map中，键为user
     * 2. SpringMVC将表单提交的参数重新赋值给map中的user键对应的User对象
     * 3. 将修改好的User对象传入请求方法
     *
     * @return String
     */
    @RequestMapping(value = "testModelAttribute")
    public String testModelAttribute(@ModelAttribute("userAdmin") User user) {
        System.out.println("修改对象：" + user);
        return SUCCESS;
    }
}
