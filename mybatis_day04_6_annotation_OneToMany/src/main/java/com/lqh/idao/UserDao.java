package com.lqh.idao;

import com.lqh.domain.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface UserDao {

    /**
     * 查询所有用户
     * @return
     */
    @Select("select * from user")
    /**
     * 配置一对多的延迟加载
     */
    @Results(value = {
            @Result(id = true ,property = "id",column = "id"),    //主键
            @Result(property = "username",column = "username"),    //普通字段
            @Result(property = "sex",column = "sex"),
            @Result(property = "address",column = "address"),
            @Result(property = "birthday",column = "birthday"),
            /**
             * 通过@Result配置一对一关系
             *      property: 关联对象的属性名
             *      javaType：关联对象的字节码对象
             *      column：查询关联对象的参数来源（字段）
             *      many：接受@Many注解，配置一对多关系
             *          接受@many注解
             @Many：配置一对多关系
              *      select：查询关联对象的方法名(接口的全类名.方法)
              *      fetchType：查询关联对象的方法
             */
            @Result(property = "accounts" ,javaType = List.class ,column = "id",
                    many = @Many(select = "com.lqh.idao.AccountDao.findAccountById" ,fetchType = FetchType.DEFAULT))
    })
    public List<User> findAllUser();

    public void saveUser(User user);
}
