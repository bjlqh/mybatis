package com.lqh;

import com.lqh.domain.User;
import com.lqh.idao.UserDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class UserDaoTest {

    private SqlSessionFactory factory = null;

    @Before
    public void init() throws IOException {
        //1.加载核心配置文件
        InputStream is = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.创建sqlSessionFactoryBuilder  解析配置文件,封装配置类
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        //3. 创建factory 为了生产sqlSesion对象
        factory = builder.build(is);
    }
    /*一级缓存*/
    @Test
    public void testFindAllUser1() {
        SqlSession sqlSession = factory.openSession();
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        User user1 = userDao.findUserById(41);
        System.out.println(user1);
        System.out.println("--------------------------------------");
        User user2 = userDao.findUserById(41);
        System.out.println(user2);
    }

    /*二级缓存*/
    @Test
    public void testFindAllUser2() {
        SqlSession sqlSession = factory.openSession();
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        User user = userDao.findUserById(41);
        System.out.println(user);
        sqlSession.commit();
        System.out.println("-----------------一级缓存已清空------------------------");
        SqlSession sqlSession1 = factory.openSession();
        UserDao userDao1 = sqlSession1.getMapper(UserDao.class);
        User user1 = userDao1.findUserById(41);
        System.out.println(user1);
    }

    @Test
    public void testAddUser() {
        SqlSession sqlSession = factory.openSession();
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        User user = new User();
        user.setUsername("mike1");
        user.setBirthday(new Date());
        user.setSex("男");
        userDao.saveUser(user);
        System.out.println(user);

    }
}
