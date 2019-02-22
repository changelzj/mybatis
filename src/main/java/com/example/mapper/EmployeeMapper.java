package com.example.mapper;

import com.example.entity.Employee;

public interface EmployeeMapper {

    Employee getEmpAndDept(Integer id);

    Employee getEmpAndDeptStep(Integer id);

    Employee getEmployeeByDeptId(Integer id);
}



