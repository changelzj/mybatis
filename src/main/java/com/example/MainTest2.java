package com.example;

import com.example.entity.Dept;
import com.example.entity.Employee;
import com.example.entity.User;
import com.example.mapper.DeptMapper;
import com.example.mapper.EmployeeMapper;
import com.example.mapper.UserMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class MainTest2 {

    /*Map*/
    @Test
    public void testParam() {
        SqlSession sqlSession = MainTest.getSQLSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        Map<String, Object> map = new HashMap<>();
        map.put("name", "lzj");
        map.put("id", 10);
        User user = userMapper.getByIdAndName(map);
        System.out.println(user);
    }

    @Test
    public void testKeyMap() {
        SqlSession sqlSession = MainTest.getSQLSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        Map<Integer, User> map = userMapper.getKeyMap(1);
        System.out.println(map);
    }

    @Test
    public void testEmpDept() {
        SqlSession sqlSession = MainTest.getSQLSession();
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        Employee employee = mapper.getEmpAndDept(100);
        System.out.println(employee);
    }

    @Test
    public void testLazy() throws InterruptedException {
        SqlSession sqlSession = MainTest.getSQLSession();
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        Employee step = mapper.getEmpAndDeptStep(200);
        step.getEmail();
        Thread.sleep(5000);
        step.getDept();
    }

    @Test
    public void testCollection() {
        SqlSession sqlSession = MainTest.getSQLSession();
        DeptMapper mapper = sqlSession.getMapper(DeptMapper.class);
        Dept dept = mapper.getDeptAndEmployees(80);
        System.out.println(dept);
    }

    @Test
    public void testLazyCollection() {
        SqlSession sqlSession = MainTest.getSQLSession();
        DeptMapper mapper = sqlSession.getMapper(DeptMapper.class);
        Dept dept = mapper.getDeptEmployeesStep(80);
        System.out.println(dept.getDeptName());
        System.out.println(dept.getEmployees());
    }

}


