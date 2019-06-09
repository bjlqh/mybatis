package com.lqh.core;

public interface SqlSession {

    public <T> T getMapper(Class aClass);
}

