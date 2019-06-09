package com.lqh.core;

import com.lqh.domain.Configuration;
import com.lqh.utils.XmlConfigBuilder;
import org.dom4j.DocumentException;

import java.io.InputStream;

/**
 * 1.解析配置文件创建配置类对象
 * 2.创建sqlSessionFactory
 */
public class SqlSessionFactoryBuilder {

    public SqlSessionFactory build(InputStream is) throws DocumentException {

        Configuration configuration = XmlConfigBuilder.buildConfigration(is);

        SqlSessionFactory sqlSessionFactory = new SqlSessionFactory(configuration);
        return sqlSessionFactory;
    }
}


