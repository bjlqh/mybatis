package com.lqh.domain;

import java.io.Serializable;

public class Mapper implements Serializable {
    private static final long serialVersionUID = 2212399669472588955L;
    private String querySql;    //sql语句
    private String resultType;  //返回值类型的全限定名

    public String getQuerySql() {
        return querySql;
    }

    public void setQuerySql(String querySql) {
        this.querySql = querySql;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    @Override
    public String toString() {
        return "Mapper{" +
                "querySql='" + querySql + '\'' +
                ", resultType='" + resultType + '\'' +
                '}';
    }
}
