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

    /*查询所有用户*/
    @Test
    public void select() {
        SqlSession sqlSession = factory.openSession();
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        List<User> list = userDao.select();
        for (User user : list) {
            System.out.println(user);
        }
    }

    /*条件查询指定用户*/
    @Test
    public void selectOne() {
        SqlSession sqlSession = factory.openSession();
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        User user = userDao.selectOne("科比");
        System.out.println(user);

    }

    /*模糊查询用户*/
    @Test
    public void selectSomeome() {
        SqlSession sqlSession = factory.openSession();
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        List<User> list = userDao.selectSomeone("77");
        for (User user : list) {
            System.out.println(user);
        }
    }

    /*添加用户*/
    @Test
    public void saveUser() {
        SqlSession sqlSession = factory.openSession();
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        // 1.传入参数
        User user = new User("科比2",new Date(), "男", "los_angeles");
        User user1 = new User("james",new Date(),"男","cleveland");
        User user2 = new User("jack",new Date(),"男","UK");
        userDao.saveUser(user2);
        // 2.提交事务
        sqlSession.commit();
        // 3.关闭释放资源
        sqlSession.close();
        System.out.println("保存用户的id :" + user2.getId());

    }

    /*修改用户*/
    @Test
    public void updateUser() {
        SqlSession sqlSession = factory.openSession();
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        User user = new User();
        user.setId(52);
        user.setUsername("詹猩猩");
        userDao.updateUser(user);
        sqlSession.commit();
        sqlSession.close();
    }

    /*删除用户*/
    @Test
    public void deleteUser() {
        SqlSession sqlSession = factory.openSession();
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        userDao.deleteById(48);
        sqlSession.commit();
        sqlSession.close();
    }

    /*查询用户数量*/
    @Test
    public void findAll() {
        SqlSession sqlSession = factory.openSession();
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        int count = userDao.findCount("%北京%");
        System.out.println(count);
    }
}
