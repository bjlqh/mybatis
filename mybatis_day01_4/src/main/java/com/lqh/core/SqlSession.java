package com.lqh.core;

import java.util.List;

public interface SqlSession {
    //泛型方法，获取结果集
    public <T> List<T> selectList(String mapperId) throws Exception;
}

