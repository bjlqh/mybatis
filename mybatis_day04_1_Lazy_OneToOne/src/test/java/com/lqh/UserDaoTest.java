package com.lqh;

import com.lqh.domain.Account;
import com.lqh.domain.User;
import com.lqh.idao.AccountDao;
import com.lqh.idao.UserDao;
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

    // 操作主体
   @Test
    public void testFindAllAccounts() {
       SqlSession sqlSession = factory.openSession();
       AccountDao accountDao = sqlSession.getMapper(AccountDao.class);
       List<Account> accounts = accountDao.findAllAccounts();
       for (Account account : accounts) {
           System.out.println(account.getMoney()+":"+account.getUser().getId());
       }
   }

   // 操作关联对象
    @Test
    public void testFindAllUser() {
        SqlSession sqlSession = factory.openSession();
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        User user = userDao.findUserById(41);
        System.out.println(user);
    }


}
