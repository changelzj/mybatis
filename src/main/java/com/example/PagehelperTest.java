package com.example;

import com.example.entity.Employee;
import com.example.mapper.EmployeeMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class PagehelperTest {
    
    private static SqlSession getSQLSession() {
        InputStream in = null;
        try {
            in = Resources.getResourceAsStream("mybatis-pagehelper-config.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        return factory.openSession(true);
    }

    /**
     * 使用Page对象得到分页信息
     */
    @Test
    public void test() {
        SqlSession sqlSession = getSQLSession();
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        Page<Object> page = PageHelper.startPage(1, 10);
        List<Employee> accountingInfos = mapper.queryList(new Employee());

        int pages = page.getPages();
        System.out.println("总页数" + pages);
        int pageSize = page.getPageSize();
        System.out.println("每页记录数" + pageSize);
        int pageNum = page.getPageNum();
        System.out.println("当前页"+pageNum);
        long total = page.getTotal();
        System.out.println("总记录数" + total);


    }

    @Test
    public void test2() {
        SqlSession sqlSession = getSQLSession();
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        Page<Object> page = PageHelper.startPage(110, 10);
        List<Employee> list = mapper.queryList(new Employee());
        PageInfo<Employee> pageInfo = new PageInfo<>(list);
        System.out.println(pageInfo.isHasNextPage());
        System.out.println(pageInfo.getTotal());
        System.out.println(pageInfo.isIsFirstPage());

        pageInfo = new PageInfo<>(list, 5);
        // 拟定连续显示的页码数量，确定显示哪些页
        int[] nums = pageInfo.getNavigatepageNums();
        for (int num : nums) {
            System.out.println(num);
        }
    }



}



