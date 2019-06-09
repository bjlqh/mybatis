package com.lqh;

import com.lqh.domain.QueryVo;
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

    /*条件查询指定用户*/
    @Test
    public void testFindByUser() {
        SqlSession sqlSession = factory.openSession();
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        User user = new User();
        user.setAddress("北京");
        User user1 = userDao.findByUser(user);
        System.out.println(user1);
    }

    /*范围查询*/
    @Test
    public void testFindUserByArrayIds() {
        SqlSession sqlSession = factory.openSession();
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        int[] arr = {31,43,44,45};
        List<User> ids = userDao.findUserByArrayIds(arr);
        for (User id : ids) {
            System.out.println(id);
        }
    }

    @Test
    public void testFindUserByListIds() {
        SqlSession sqlSession = factory.openSession();
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        List<Integer> list = new ArrayList<>();
        list.add(41);
        list.add(42);
        list.add(43);
        List<User> listIds = userDao.findUserByListIds(list);
        for (User listId : listIds) {
            System.out.println(listId);
        }
    }

    @Test
    public void testFindByVo() {
        SqlSession sqlSession = factory.openSession();
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        List<Integer> list = new ArrayList<>();
        list.add(41);
        list.add(42);
        list.add(43);
        QueryVo vo = new QueryVo();
        vo.setList(list);
        List<User> userByVo = userDao.findUserByVo(vo);
        for (User user : userByVo) {
            System.out.println(user);
        }
    }

    @Test
    public void testsave() {
        SqlSession sqlSession = factory.openSession();
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        User user = new User();
        user.setUsername("米");
        user.setSex("啊");
        user.setAddress("数");
        user.setBirthday(new Date());
        userDao.save(user);
        sqlSession.commit();
    }
}
