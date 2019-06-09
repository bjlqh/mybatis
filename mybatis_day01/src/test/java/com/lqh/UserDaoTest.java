package com.lqh;

import com.lqh.domain.User;
import com.lqh.idao.UserDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.List;

public class UserDaoTest {
    @Test
    public void getAll() throws IOException {
        //1.加载核心配置文件
        //InputStream is = UserDaoTest.class.getClassLoader().getResourceAsStream("");
        InputStream is = Resources.getResourceAsStream("SqlMapConfig.xml");
        //Reader reader = Resources.getResourceAsReader("SqlMapConfig.xml");
        //2.创建sqlSessionFactoryBuilder  解析配置文件,封装配置类
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        //3. 创建factory 为了生产sqlSesion对象
        SqlSessionFactory factory = builder.build(is);
        // 4.创建 sqlSession解析核心配置文件,操作数据库
        SqlSession sqlSession = factory.openSession();
        // 5.基于动态代理的形式使用mybatis
        //---获取dao层接口的动态代理对象


        UserDao userDao = sqlSession.getMapper(UserDao.class);
        List<User> list = userDao.getAll();
        for (User user : list) {
            System.out.println(user);
        }
    }

}
