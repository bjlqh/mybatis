package com.lqh.idao;

import com.lqh.domain.User;


public interface UserDao {
    public User findUserById(int id);
}
