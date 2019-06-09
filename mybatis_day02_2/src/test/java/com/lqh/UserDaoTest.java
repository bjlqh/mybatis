package com.lqh;

import com.lqh.dao.UserDaoImpl;
import com.lqh.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class UserDaoTest {

    private SqlSessionFactory factory;
    @Before
    public void init() throws IOException {
        //1.加载核心配置文件
        InputStream is = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.创建sqlSessionFactoryBuilder  解析配置文件,封装配置类
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        //3. 创建factory 为了生产sqlSesion对象
        factory = builder.build(is);
    }

    /*查询所有用户*/
    @Test
    public void select() {
        UserDaoImpl userDao = new UserDaoImpl(factory);
        userDao.select();
    }

    /*条件查询指定用户*/
    @Test
    public void selectOne() {
        UserDaoImpl userDao = new UserDaoImpl(factory);
        User user = userDao.selectOne("科比");
    }

    /*模糊查询用户*/
    @Test
    public void selectSomeome() {
        UserDaoImpl userDao = new UserDaoImpl(factory);
        List<User> list = userDao.selectSomeone("北京");
        for (User user1 : list) {
            System.out.println(user1);
        }
    }

    /*添加用户*/
    @Test
    public void saveUser() {
        User user = new User();
        UserDaoImpl userDao = new UserDaoImpl(factory);
        user.setUsername("rose33");
        user.setBirthday(new Date());
        user.setAddress("UK");
        user.setSex("女");
        userDao.saveUser(user);
        System.out.println(user);
    }

    @Test
    public void addUser() {
        User user = new User();
        UserDaoImpl userDao = new UserDaoImpl(factory);
        user.setUsername("kobe55");
        user.setBirthday(new Date());
        user.setAddress("USA");
        user.setSex("男");
        userDao.addUser(user);
        System.out.println(user);
    }



    /*修改用户*/
    @Test
    public void updateUser() {
        User user = new User();
        UserDaoImpl userDao = new UserDaoImpl(factory);
        user.setAddress("顺义");
        user.setSex("男");
        user.setId(45);
        userDao.updateUser(user);
    }


    /*删除用户*/
    @Test
    public void deleteUser() {
        UserDaoImpl userDao = new UserDaoImpl(factory);
        userDao.deleteById(57);
    }

    /*查询用户数量*/
    @Test
    public void findAll() {
        UserDaoImpl userDao = new UserDaoImpl(factory);
        int count = userDao.findCount();
        System.out.println(count);
    }
}
