package com.lqh.core;

import com.lqh.domain.Configuration;
import com.lqh.domain.Mapper;
import com.lqh.utils.Operator;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class FirstSqlSession implements SqlSession {
    private Configuration cfg;
    private Operator operator;

    public FirstSqlSession(Configuration cfg) {
        this.cfg = cfg;
        operator = new Operator(cfg);
    }

    /**
     * 查询全部
     *
     * @param mapperId 唯一标识namespace.id
     *                 封装jdbc
     */
    public <T> List<T> selectList(String mapperId) throws Exception {
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
    }


}
