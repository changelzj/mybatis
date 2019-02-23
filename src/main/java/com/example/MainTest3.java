package com.example;

import com.example.entity.Employee;
import com.example.mapper.DeptMapper;
import com.example.mapper.EmployeeMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;


public class MainTest3 {
    /**
     * 动态SQL
     */
    @Test
    public void testLazyCollection() {
        SqlSession sqlSession = MainTest.getSQLSession();
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        Employee employee = new Employee();
        employee.setEmail("JLIVINGS");
        List<Employee> employees = mapper.queryList(employee);
        for (Employee temp : employees) {
            System.out.println(temp);
        }
    }

    /**
     * 一级缓存默认开启，一个sqlsession 对应一个一级缓存
     *  一级缓存失效的四种情况：
     *  sqlsession 不同，缓存失效
     *  sqlsession相同，查询条件（参数）不同，缓存失效（因为每个增删改标签默认flushCache=true，执行后清空缓存）
     *  ！！ flushCache在查询标签默认是false
     *  sqlsession相同，两次查询中间，执行增删改，缓存失效
     *  sqlsession相同，中途手动清空一级缓存，缓存失效
     *
     *
     */
    @Test
    public void testCache() {
        SqlSession sqlSession = MainTest.getSQLSession();
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        Employee employee = new Employee();
        employee.setEmail("JLIVINGS");
        List<Employee> employees = mapper.queryList(employee);
        for (Employee temp : employees) {
            System.out.println(temp);
        }
        // 清空缓存，只影响一级缓存
        sqlSession.clearCache();

        List<Employee> employees2 = mapper.queryList(employee);
        for (Employee temp : employees2) {
            System.out.println(temp);
        }
    }

    /**
     * 二级缓存，默认也是开启的，但是需要在mapper文件指定
     * namespace级别，一个名称空间对应一个缓存
     * 一次查询的结果会放在一个会话的一级缓存中，会话关闭后，数据会被保存在二级缓存中，新的会话，会参照二级缓存
     * 关闭二级缓存，还可以通过mapper内的select标签 useCache=false
     * 查询间执行了增删改也会被清除缓存
     */
    @Test
    public void testCacheLev2() {
        InputStream in = null;
        try {
            in = Resources.getResourceAsStream("mybatis-config.xml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);

        SqlSession sqlSession1 = factory.openSession(true);
        SqlSession sqlSession2 = factory.openSession(true);

        EmployeeMapper mapper1 = sqlSession1.getMapper(EmployeeMapper.class);
        EmployeeMapper mapper2 = sqlSession2.getMapper(EmployeeMapper.class);

        Employee employee = new Employee();
        employee.setEmail("JLIVINGS");

        List<Employee> employees1 = mapper1.queryList(employee);
        System.out.println(employees1);
        sqlSession1.close();

        List<Employee> employees2 = mapper2.queryList(employee);
        System.out.println(employees2);
        sqlSession2.close();

    }



    @Test
    public void testEhcache() {
        InputStream in = null;
        try {
            in = Resources.getResourceAsStream("mybatis-config.xml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);

        SqlSession sqlSession1 = factory.openSession(true);
        SqlSession sqlSession2 = factory.openSession(true);

        DeptMapper mapper1 = sqlSession1.getMapper(DeptMapper.class);
        DeptMapper mapper2 = sqlSession2.getMapper(DeptMapper.class);

        List<Map<String, Object>> list = mapper1.getList();
        System.out.println(list.size());
        sqlSession1.close();
        List<Map<String, Object>> list1 = mapper2.getList();
        System.out.println(list1.size());
        sqlSession2.close();

    }
}



