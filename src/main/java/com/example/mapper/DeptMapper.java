package com.example.mapper;

import com.example.entity.Dept;

import java.util.List;
import java.util.Map;

public interface DeptMapper {
    Dept getDeptById(Integer id);

    Dept getDeptAndEmployees(Integer id);

    Dept getDeptEmployeesStep(Integer id);

    List<Map<String, Object>> getList();
}




