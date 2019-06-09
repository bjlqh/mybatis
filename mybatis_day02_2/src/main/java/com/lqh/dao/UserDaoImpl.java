package com.lqh.dao;

import com.lqh.domain.User;
import com.lqh.idao.UserDao;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class UserDaoImpl implements UserDao {
    private SqlSessionFactory factory;
    public UserDaoImpl(SqlSessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public List<User> select() {
        SqlSession sqlSession = factory.openSession();
        List<User> list = sqlSession.selectList("user.select");
        sqlSession.commit();
        sqlSession.close();
        for (User user : list) {
            System.out.println(user);
        }
        return list;
    }

    @Override
    public void saveUser(User user) {
        SqlSession sqlSession = factory.openSession();
        sqlSession.insert("user.saveUser", user);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void addUser(User user) {
        SqlSession sqlSession = factory.openSession();
        sqlSession.insert("user.addUser", user);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public User selectOne(String name) {
        SqlSession sqlSession = factory.openSession();
        User user = sqlSession.selectOne("user.selectOne",name);
        sqlSession.commit();
        sqlSession.close();
        return user;
    }

    @Override
    public List<User> selectSomeone(String home) {
        SqlSession sqlSession = factory.openSession();
        List<User> list = sqlSession.selectList("user.selectSomeone",home);
        return list;
    }

    @Override
    public void updateUser(User user) {
        SqlSession sqlSession = factory.openSession();
        sqlSession.update("user.updateUser2", user);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void deleteById(int id) {
        SqlSession sqlSession = factory.openSession();
        sqlSession.delete("user.deleteById",id);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public int findCount() {
        SqlSession sqlSession = factory.openSession();
        int count = sqlSession.selectOne("user.findCount");
        return count;
    }

    @Override
    public int findCount(String s) {
        return 0;
    }
}
