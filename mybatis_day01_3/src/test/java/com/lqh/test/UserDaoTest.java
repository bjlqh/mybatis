package com.lqh.test;

import com.lqh.client.User;
import com.lqh.core.SqlSession;
import com.lqh.core.SqlSessionFactory;
import com.lqh.core.SqlSessionFactoryBuilder;
import com.lqh.dao.UserDao;
import org.dom4j.DocumentException;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

public class UserDaoTest {
    private SqlSessionFactory factory = null;
    @Before
    public void init() throws DocumentException {
        InputStream is = UserDaoTest.class.getClassLoader().getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        factory = builder.build(is);
    }

    @Test
    public void testFindAllUser() {
        SqlSession sqlSession = factory.openSession();
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        List<User> alllUser = userDao.findAlllUser();
        for (User user : alllUser) {
            System.out.println(user);
        }
    }
}
