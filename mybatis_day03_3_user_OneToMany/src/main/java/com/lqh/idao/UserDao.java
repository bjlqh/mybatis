package com.lqh.idao;

import com.lqh.domain.User;

import java.util.List;

public interface UserDao {
    public List<User> findAllUser();
}
