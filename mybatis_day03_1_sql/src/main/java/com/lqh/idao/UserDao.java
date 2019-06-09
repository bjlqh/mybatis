package com.lqh.idao;

import com.lqh.domain.QueryVo;
import com.lqh.domain.User;

import java.util.List;

public interface UserDao {

    public User findByUser(User user);

    public List<User> findUserByArrayIds(int[] arr);

    public List<User> findUserByListIds(List list);

    public List<User> findUserByVo(QueryVo vo);

    public void save(User user);
}
