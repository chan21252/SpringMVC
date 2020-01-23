package com.chan.springmvc.handler;

import com.chan.springmvc.dao.DepartmentDao;
import com.chan.springmvc.dao.EmployeeDao;
import com.chan.springmvc.entity.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/emp/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable(value = "id")Integer id) {
        employeeDao.delete(id);
        return "redirect:/emps";
    }

    @RequestMapping(value = "/emp/{id}", method = RequestMethod.GET)
    public String input(@PathVariable Integer id, Map<String, Object> map) {
        map.put("departments", departmentDao.getDepartments());
        map.put("employee", employeeDao.getEmployee(id));
        return "input";
    }

    @ModelAttribute(name = "employee")
    public void  getEmployee(@RequestParam(name = "id", required = false) Integer id,
                                Map<String, Object> map) {
        if (id != null) {
            map.put("employee", employeeDao.getEmployee(id));
        }
    }

    @RequestMapping(value = "/emp", method = RequestMethod.PUT)
    public String update(@ModelAttribute("employee") Employee employee) {
        employeeDao.update(employee);
        return "redirect:/emps";
    }
}
