package com.chan.springmvc.test;

import com.chan.springmvc.dao.EmployeeDao;
import com.chan.springmvc.entity.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

/**
 * 测试SpringMVC
 *
 * @author Administrator
 */
@Controller
public class TestSpringMVC {

    @Resource(name = "employeeDao")
    private EmployeeDao employeeDao;

    @RequestMapping(value = "/testConversionService", method = RequestMethod.POST)
    public String testConvert(@RequestParam("employee") Employee employee) {
        employeeDao.save(employee);
        return "redirect:/emps";
    }
}
