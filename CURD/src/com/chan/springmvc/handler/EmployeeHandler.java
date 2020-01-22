package com.chan.springmvc.handler;

import com.chan.springmvc.dao.DepartmentDao;
import com.chan.springmvc.dao.EmployeeDao;
import com.chan.springmvc.entity.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 员工信息处理类
 *
 * @author Administrator
 */
@Controller(value = "employeeHandler")
public class EmployeeHandler {
    @Resource(name = "employeeDao")
    private EmployeeDao employeeDao;

    @Resource(name = "departmentDao")
    private DepartmentDao departmentDao;

    @RequestMapping(value = "/emps")
    public String list(Map<String, Object> map) {
        map.put("employees", employeeDao.getEmployees());
        return "list";
    }

    @RequestMapping(value = "/emp", method = RequestMethod.GET)
    public String input(Map<String, Object> map) {
        map.put("departments", departmentDao.getDepartments());
        map.put("employee", new Employee());
        return "input";
    }

    @RequestMapping(value = "/emp", method = RequestMethod.POST)
    public String save(@ModelAttribute("employee") Employee employee) {
        employeeDao.save(employee);
        return "redirect:/emps";
    }
}
