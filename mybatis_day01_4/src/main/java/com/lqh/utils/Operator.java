package com.lqh.utils;

import com.lqh.domain.Configuration;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//执行器的类，专门用于执行SQL语句
public class Operator {

    private Configuration cfg;

    public Operator(Configuration cfg) {
        this.cfg = cfg;
    }

    //执行查询语句
    public <E> List<E> operatorQuery(String sql, String resultType) throws Exception {
        Connection conn = null;
        try {
            // 1.注册驱动
            Class.forName(cfg.getDriver());
            // 2.创建连接
            conn = DriverManager.getConnection(cfg.getUrl(), cfg.getUsername(), cfg.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
        }

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            // 3.创建statement
            ps = conn.prepareStatement(sql);
            // 4.执行sql语句获取结果集
            rs = ps.executeQuery();



            // 5. 封装结果集
            List list = new ArrayList();
            // 5.1反射(创建对象,反射调用set方法)
            Class aClass = Class.forName(resultType);
            Method[] methods = aClass.getDeclaredMethods();

            // 5.2 获取数据库查询结果的所有字段
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();  //列的数量
            // 5.3 获取所有字段名称
            List<String> columnNames = new ArrayList<String>();
            for (int i = 1; i <= columnCount; i++) {
                columnNames.add(metaData.getColumnName(i));
            }

            while (rs.next()) {
                //1.创建对象
                Object o = aClass.newInstance();
                //2.反射赋值   字段名(Username)----set方法 public void setUsername(String username){this.username=username}
                for (String columnName : columnNames) {
                    for (Method method : methods) {
                        if (method.getName().equalsIgnoreCase("set" + columnName)) {
                            method.invoke(o, rs.getObject(columnName));
                        }
                    }
                }
                //存入list集合
                list.add(o);
            }
            return list;
        } finally {
            rs.close();
            ps.close();
            conn.close();
        }
    }
}
