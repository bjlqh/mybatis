package com.lqh;

import com.lqh.domain.Role;
import com.lqh.domain.User;
import com.lqh.idao.RoleDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
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
    public void testfindAllRoles() {
        SqlSession sqlSession = factory.openSession();
        RoleDao roleDao = sqlSession.getMapper(RoleDao.class);
        List<Role> allRoles = roleDao.findAllRoles();
        for (Role allRole : allRoles) {
            System.out.println(allRole);
        }
    }
}
