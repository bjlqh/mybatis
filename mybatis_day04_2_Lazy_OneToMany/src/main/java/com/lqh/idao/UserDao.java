package com.lqh.idao;

import com.lqh.domain.Account;
import com.lqh.domain.User;

import java.util.List;


public interface UserDao {
    /*
    查询所有用户
    */
   public List<User> findAllUser();
}


