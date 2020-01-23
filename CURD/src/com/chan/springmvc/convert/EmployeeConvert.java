package com.chan.springmvc.convert;

import com.chan.springmvc.entity.Department;
import com.chan.springmvc.entity.Employee;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * 员工信息转换类，字符串转为Employee类型
 *
 * @author Administrator
 */
@Component("employeeConvert")
public class EmployeeConvert implements Converter<String, Employee> {
    @Override
    public Employee convert(String source) {
        if (source == null) {
            throw new RuntimeException("字符串不能为空");
        }

        String[] values = source.split("-");
        if (values.length == 4) {
            String lastName = values[0];
            String email = values[1];
            String gender = values[2];
            String departmentId = values[3];

            Department department = new Department();
            department.setId(Integer.parseInt(departmentId));

            Employee employee = new Employee(null, lastName, email, gender, department);
            System.out.println(source + "--转换为--" + employee);
            return employee;
        } else {
            throw new RuntimeException("字符串格式错误");
        }
    }
}
