package com.example;

import com.example.entity.User;
import com.example.mapper.AnnoUserMapper;
import com.example.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MainTest {
    public static SqlSession getSQLSession() {
        InputStream in = null;
        try {
            in = Resources.getResourceAsStream("mybatis-config.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        return factory.openSession(true);
    }


    @Test
    public void test() {
        SqlSession sqlSession = getSQLSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> list = userMapper.getList();
        System.out.println(list);
        sqlSession.close();
    }

    @Test
    public void testAnno() {
        SqlSession sqlSession = getSQLSession();
        AnnoUserMapper userMapper = sqlSession.getMapper(AnnoUserMapper.class);
        List<User> list = userMapper.getList();
        System.out.println(list);
        sqlSession.close();
    }

    @Test
    public void testAdd() {
        //默认不自动提交
        SqlSession sqlSession = getSQLSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = new User(null, "cherry91", "nv", 25);
        Integer res = userMapper.add(user);
        Integer id = user.getId();
        System.out.println(id);

        System.out.println(res);
        sqlSession.commit();

    }

    @Test
    public void testUpdate() {
        //默认不自动提交
        SqlSession sqlSession = getSQLSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        userMapper.update(new User(25, "cherry333", "nv", 25));
        sqlSession.commit();

    }

    @Test
    public void testDelete() {
        //默认不自动提交
        SqlSession sqlSession = getSQLSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        userMapper.delete(3);
        sqlSession.commit();

    }


    @Test
    public void testParam() {
        SqlSession sqlSession = getSQLSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.getByIdAndName("fd", 12);
        System.out.println(user);
    }
}




































































