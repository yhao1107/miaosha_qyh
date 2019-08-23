package com.wojbk.miaosha.dao;


import com.wojbk.miaosha.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/*
@date: 2019-5-7 19:40
@author: Qinyonghao
@action: creat
 */

@Mapper
public interface UserDao {
    @Select("select * from t_user where id=#{id}")
    User getById(@Param("id") long id);

//    @Insert("insert into t_user(id,name) values(#{id},#{name})")
//    void insert(User u1);
}
