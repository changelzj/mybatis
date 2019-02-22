package com.example.mapper;

import com.example.entity.Dept;

public interface DeptMapper {
    Dept getDeptById(Integer id);

    Dept getDeptAndEmployees(Integer id);

    Dept getDeptEmployeesStep(Integer id);
}




