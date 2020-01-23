package com.chan.springmvc.dao;

import com.chan.springmvc.entity.Department;
import com.chan.springmvc.entity.Employee;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * 员工数据操作类
 *
 * @author Administrator
 */
@Repository(value = "employeeDao")
public class EmployeeDao {
    private static Map<Integer, Employee> employees;

    @Resource(name = "departmentDao")
    private DepartmentDao departmentDao;

    static {
        employees = new HashMap<Integer, Employee>();
        employees.put(1001, new Employee(1001, "E-AA", "aa@spring.com", "男", new Department(101, "D-AA")));
        employees.put(1002, new Employee(1002, "E-BB", "bb@spring.com", "女", new Department(102, "D-BB")));
        employees.put(1003, new Employee(1003, "E-CC", "cc@spring.com", "男", new Department(103, "D-CC")));
        employees.put(1004, new Employee(1004, "E-DD", "dd@spring.com", "男", new Department(104, "D-DD")));
        employees.put(1005, new Employee(1005, "E-EE", "ee@spring.com", "女", new Department(105, "D-EE")));
    }

    /**
     * 获取所有员工信息
     *
     * @return Collection
     */
    public Collection<Employee> getEmployees() {
        return employees.values();
    }

    /**
     * 获取某个员工的信息
     *
     * @param id id
     * @return Employee
     */
    public Employee getEmployee(Integer id) {
        return employees.get(id);
    }

    /**
     * 保存员工信息
     *
     * @param employee Employee
     */
    public void save(Employee employee) {
        //id自增1
        employee.setId(getMaxId() + 1);

        //设置部门名称
        Department employeeDepartment = employee.getDepartment();
        Department department = departmentDao.geDepartment(employeeDepartment.getId());
        employeeDepartment.setDepartmentName(department.getDepartmentName());

        employees.put(getMaxId() + 1, employee);
    }

    private Integer getMaxId() {
        int maxId = 1000;
        for (int id : employees.keySet()) {
            maxId = Math.max(id, maxId);
        }

        return maxId;
    }

    /**
     * 删除对应ID的员工信息
     *
     * @param id int
     */
    public void delete(Integer id) {
        employees.remove(id);
    }

    /**
     * 更新员工信息
     *
     * @param employee Employee
     */
    public void update(Employee employee) {
        employees.put(employee.getId(), employee);
    }
}
