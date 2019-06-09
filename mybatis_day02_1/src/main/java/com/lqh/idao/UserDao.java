package com.lqh.idao;

import com.lqh.domain.User;

import java.util.List;

public interface UserDao {
    public List<User> select();

    public int saveUser(User user);

    public User selectOne(String s);

    public List<User> selectSomeone(String s);

    public void updateUser(User user);

    public void deleteById(int id);

    public int findCount(String s);
}
