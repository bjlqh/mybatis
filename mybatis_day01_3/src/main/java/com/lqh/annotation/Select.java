package com.lqh.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Select {
    //替换sql配置文件中的sql语句和返回值类型
        String sql();
        String ResultType();
}
