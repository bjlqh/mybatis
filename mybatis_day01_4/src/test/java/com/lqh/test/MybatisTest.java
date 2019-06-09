package com.lqh.test;

import com.lqh.client.User;
import com.lqh.core.SqlSession;
import com.lqh.core.SqlSessionFactory;
import com.lqh.core.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

public class MybatisTest {
    @Test
    public void test1() throws Exception {
        // 1.加载配置文件获取流
        InputStream is = MybatisTest.class.getClassLoader().getResourceAsStream("SqlMapConfig.xml");
        // 2.创建sqlSessionFactoryBuilder
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        // 3.创建sqlSessionFactory
        SqlSessionFactory factory = sqlSessionFactoryBuilder.build(is);
        // 4.创建sqlSession
        SqlSession sqlSession = factory.openSession();
        // 5.执行sql语句获取结果集
        List<User> list = sqlSession.selectList("user.getAll");
        for (User user : list) {
            System.out.println(user);
        }
    }
}
