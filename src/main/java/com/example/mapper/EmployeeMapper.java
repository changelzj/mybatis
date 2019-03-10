package com.example.mapper;

import com.example.entity.Employee;

import java.util.List;

public interface EmployeeMapper {

    Employee getEmpAndDept(Integer id);

    Employee getEmpAndDeptStep(Integer id);

    Employee getEmployeeByDeptId(Integer id);

    List<Employee> queryList(Employee employee);

    void updateEmployee(Employee employee);
}



