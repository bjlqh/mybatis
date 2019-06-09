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
import java.util.ArrayList;
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

    @Test
    public void testSaveUser() {
        SqlSession sqlSession = factory.openSession();
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        User user = new User("迪丽热巴","女",new Date(),"北京");
        userDao.saveUser(user);
        sqlSession.commit();
        sqlSession.close();
        System.out.println("保存的id是: "+user.getId());
    }

    @Test
    public void testUpdateUser() {
        SqlSession sqlSession = factory.openSession();
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        User user = new User();
        user.setId(42);
        user.setUsername("薇薇");
        userDao.updateUser(user);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testDeleteUser() {
        SqlSession sqlSession = factory.openSession();
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        User user = new User();
        user.setId(51);
        userDao.deleteUser(user);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testFindAllUser() {
        SqlSession sqlSession = factory.openSession();
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        List<User> allUser = userDao.findAllUser();
        for (User user : allUser) {
            System.out.println(user);
        }
    }

    @Test
    public void testFindUserById() {
        SqlSession sqlSession = factory.openSession();
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        User user= userDao.findUserById(58);
        System.out.println(user);
    }

    @Test
    public void testFindUserLikeX() {
        SqlSession sqlSession = factory.openSession();
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        User user = new User();
        user.setAddress("北京");
        List<User> likeX = userDao.findUserLikeX(user);
        for (User x : likeX) {
            System.out.println(x);
        }
    }

    @Test
    public void testFindCount() {
        SqlSession sqlSession = factory.openSession();
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        User user = new User();
        user.setAddress("北京");
        int count = userDao.findCount(user);
        System.out.println(count);
    }

    @Test
    public void testFindUsers() {
        SqlSession sqlSession = factory.openSession();
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        User user = new User();
        user.setSex("女");
        user.setUsername("迪丽热巴");
        List<User> users = userDao.findUsers(user);
        for (User user1 : users) {
            System.out.println(user1);
        }
    }

    @Test
    public void testFindUserByListId() {
        SqlSession sqlSession = factory.openSession();
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        List<Integer> list = new ArrayList();
        for (int i = 40; i <=50 ; i++) {
            list.add(i);
        }
        List<User> userList = userDao.findUserByListId(list);
        for (User user : userList) {
            System.out.println(user);
        }
    }
}
