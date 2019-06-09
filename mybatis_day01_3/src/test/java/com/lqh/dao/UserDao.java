package com.lqh.dao;

import com.lqh.annotation.Select;
import com.lqh.client.User;

import java.util.List;

public interface UserDao {
    @Select(sql = "select * from user",ResultType = "com.lqh.client.User")
    public List<User> findAlllUser();
}
