package com.lqh.idao;

import com.lqh.domain.Account;
import com.lqh.domain.User;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface AccountDao {
    /**
     * 查询所有账户
     * @return
     */
    @Select("select * from account")
    /**
     * 配置一对一的延迟加载
     */
    @Results(value = {
            @Result(id = true ,property = "id",column = "id"),    //主键
            @Result(property = "uid",column = "uid"),    //普通字段
            @Result(property = "money",column = "money"),
            /**
             * 通过@Result配置一对一关系
             *      property: 关联对象的属性名
             *      javaType：关联对象的字节码对象
             *      column：查询关联对象的参数来源（字段）
             *      one：配置一对一关系
             *          接受@One注解
             * @One：配置一对一关系
             *    select：查询关联对象的方法名（dao接口的全类名.方法）
             *    fetchType : 关联对象的查询方式
             *      立即加载(EAGER)
             *      延迟加载(LAZY) ：延迟加载
             *      默认(DEFAULT) : 参考全局配置
             */
            @Result(property = "user" ,javaType = User.class ,column = "uid",
                    one = @One(select = "com.lqh.idao.UserDao.findUserById" ,fetchType = FetchType.LAZY))
    })
    public List<Account> findAllAccount();
}
