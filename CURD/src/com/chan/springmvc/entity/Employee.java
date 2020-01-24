package com.chan.springmvc.entity;

import com.alibaba.fastjson.annotation.JSONField;
import org.hibernate.validator.constraints.Email;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import java.util.Date;

/**
 * 员工实体类
 *
 * @author Administrator
 */
public class Employee {
    private Integer id;

    @NotNull
    private String lastName;

    @Email
    private String email;

    @Pattern(regexp = "[男女]", message = "性别只能为男或女")
    private String gender;
    private Department department;

    @JSONField(format = "yyyy-MM-dd")
    @Past(message = "生日是过去时间")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date birthday;

    @Min(value = 0, message = "工资必须大于0")
    @NumberFormat(style = NumberFormat.Style.DEFAULT)
    private Double salary;

    public Employee() {
    }

    public Employee(Integer id, String lastName, String email, String gender, Department department) {
        this.id = id;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.department = department;
    }

    public Employee(Integer id, String lastName, String email, String gender, Department department, Date birthday, Double salary) {
        this.id = id;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.department = department;
        this.birthday = birthday;
        this.salary = salary;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                ", department=" + department +
                ", birthday=" + birthday +
                ", salary=" + salary +
                '}';
    }
}
