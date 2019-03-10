package com.example;

import com.example.entity.Student;
import com.example.mapper.StudentMapper;
import com.example.mapper.UserMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class MainTest4 {

    @Test
    public void testTrim() {
        SqlSession sqlSession = MainTest.getSQLSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        Student stu = new Student();
        stu.setId(2);
        stu.setName("轻工业直播");
        List<Student> students = mapper.query(stu);
        System.out.println(students);
    }

    @Test
    public void testUpdate() {
        SqlSession sqlSession = MainTest.getSQLSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        Student stu = new Student();
        stu.setId(2);
        stu.setName("轻工业直播");
        mapper.update(stu);
        sqlSession.commit();
    }

    @Test
    public void testForeach() {
        SqlSession sqlSession = MainTest.getSQLSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        List<Student> students = mapper.queryByIds(Arrays.asList(new Integer[]{2, 3}));
        System.out.println(students);
    }

}
