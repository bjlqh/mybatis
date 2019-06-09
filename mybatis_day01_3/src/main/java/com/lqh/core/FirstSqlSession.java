package com.lqh.core;

import com.lqh.domain.Configuration;
import com.lqh.domain.Mapper;
import com.lqh.utils.Operator;

import java.lang.reflect.Proxy;
import java.util.List;
import java.util.Map;

public class FirstSqlSession implements SqlSession {

    private Configuration cfg;
    private Operator operator;

    public FirstSqlSession(Configuration cfg) {
        this.cfg = cfg;
        operator = new Operator(cfg);
    }

     /* 查询全部
     *
     * @param //mapperId 唯一标识namespace.id
     *                 封装jdbc
     */
    /*public <T> List<T> selectList(String mapperId) throws Exception {
        // 1. 获取持久层
        Map<String, Mapper> mappers = cfg.getMappers();
        // 2. 获取持久层唯一模块
        Mapper mapper = mappers.get(mapperId);
        // 3.1 获取模块中相应功能的方法
        String sql = mapper.getQuerySql();
        // 3.2 获取封装对象的全限定类名
        String resultType = mapper.getResultType();
        // 4. 执行器执行并获取返回结果
        List<T> list = operator.operatorQuery(sql, resultType);
        return list;
    }*/

    //根据sql和select查询数据库列表
    public <T> List<T> operatorQuery(String sql,String resultTye) throws Exception {
        return operator.operatorQuery(sql,resultTye);
    }

    //根据dao接口的字节码对象（class），创建接口的动态代理对象


    public <T> T getMapper(Class aClass) {

        MapperProxyFactory proxy = new MapperProxyFactory(this);

        return (T) Proxy.newProxyInstance(
                        FirstSqlSession.class.getClassLoader(),
                        new Class[]{aClass},
                        proxy
        );
    }
}
