package com.lqh.domain;

public class Mapper {
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
