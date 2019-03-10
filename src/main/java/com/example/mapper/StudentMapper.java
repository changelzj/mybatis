package com.example.mapper;

import com.example.entity.Student;

import java.util.List;

public interface StudentMapper {

    List<Student> query(Student student);

    void update(Student student);

    List<Student> queryByIds(List<Integer> ids);

}
