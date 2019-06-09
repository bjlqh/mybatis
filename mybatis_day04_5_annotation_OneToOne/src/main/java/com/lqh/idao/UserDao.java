package com.lqh.idao;

import com.lqh.domain.User;
import org.apache.ibatis.annotations.Select;

public interface UserDao {

    // 根据用户的id 查询
    @Select("select * from user where id =#{id}")
    public User findUserById(int id);
}
