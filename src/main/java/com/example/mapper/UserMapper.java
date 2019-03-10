package com.example.mapper;

import com.example.entity.User;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserMapper {

    User findById(Integer id);

    /*mybatis 允许增删改返回 integer long boolean,integer long代表行数，boolean超过零行就是真否则为假*/
    Integer add(User user);

    Long update(User user);

    Boolean  delete(Integer id);

    List<User> getList();

    User getByIdAndName(@Param("name") String name, @Param("id") Integer id);

    User getByIdAndName(Map<String, Object> paramMap);

    /*组合参数*/
    User getByIdAndUser(Integer id, User user);





    User findByIds(List<Integer> ids);

    /*封装map时使用哪个属性作为键*/
    @MapKey("id")
    Map<Integer, User> getKeyMap(Integer id);

}


