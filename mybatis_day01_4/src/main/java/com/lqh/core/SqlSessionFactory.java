package com.lqh.core;

import com.lqh.domain.Configuration;

public class SqlSessionFactory {
    private Configuration cfg;
    public SqlSessionFactory(Configuration cfg) {
        this.cfg = cfg;
    }
    public SqlSessionFactory() {}
    public Configuration getCfg() {
        return cfg;
    }
    public void setCfg(Configuration cfg) {
        this.cfg = cfg;
    }

    //创建sqlSession
    public SqlSession openSession() {
        return new FirstSqlSession(this.cfg);
    }
}
