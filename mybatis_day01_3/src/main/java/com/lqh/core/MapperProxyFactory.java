package com.lqh.core;

import com.lqh.annotation.Select;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;


//增强业务逻辑
public class MapperProxyFactory implements InvocationHandler {

    private FirstSqlSession firstSqlSession;

    public MapperProxyFactory(FirstSqlSession firstSqlSession) {
        this.firstSqlSession = firstSqlSession;
    }


    /**--------------------------要增强的方法-----------------
     *
     * @param proxy 即方法newProxyInstance()方法返回的代理对象，该对象一般不要在invoke方法中使用。
     * @param method 代理对象调用的方法。
     * @param args 代理对象调用方法时传递的参数。
     * @return
     * @throws Throwable
     *
     * 完成对数据库的查询
     */
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 1.获取当前执行方法上的注解
        Select select = method.getAnnotation(Select.class);
        // 2.当前执行的sql语句
        String sql = select.sql();
        // 3.封装的对象的返回值类型的全限定名
        String resultType = select.ResultType();
        // 查询
        return firstSqlSession.operatorQuery(sql,resultType);
    }
}
