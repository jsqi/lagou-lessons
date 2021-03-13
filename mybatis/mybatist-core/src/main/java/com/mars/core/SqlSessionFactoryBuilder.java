package com.mars.core;

import com.mars.config.XMLConfigBuilder;
import com.mars.pojo.Configuration;

import java.io.InputStream;

public class SqlSessionFactoryBuilder {

    public SqlSessionFactory build(InputStream inputStream) throws Exception {
        // 解析配置文件，将配置文件的数据封装到Configuration中
        XMLConfigBuilder xmlConfigBuilder = new XMLConfigBuilder();
        Configuration configuration = xmlConfigBuilder.parseConfig(inputStream);
        // 创建SqlSessionFactory
        return new DefaultSqlSessionFactory(configuration);
    }
}
