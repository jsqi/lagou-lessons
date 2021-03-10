package com.mars.config;

import com.mars.io.Resources;
import com.mars.pojo.Configuration;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.List;
import java.util.Properties;

public class XMLConfigBuilder {

    private Configuration configuration;
    public XMLConfigBuilder() {
        this.configuration = new Configuration();
    }

    public Configuration parseConfig(InputStream inputStream) throws Exception {

        Document document = new SAXReader().read(inputStream);
        Element rootElement = document.getRootElement();
        List<Element> list = rootElement.selectNodes("//property");
        Properties properties = new Properties();
         for (Element element: list){
             String name = element.attributeValue("name");
             String value = element.attributeValue("value");
             properties.setProperty(name,value);
         }

         // 创建数据源连接池
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass(properties.getProperty("driverClass"));
        dataSource.setJdbcUrl(properties.getProperty("url"));
        dataSource.setUser(properties.getProperty("username"));
        dataSource.setPassword(properties.getProperty("password"));
        configuration.setDataSource(dataSource);

        // 解析mapper.xml
       List<Element> mapperList =  rootElement.selectNodes("//mapper");
        for (Element element: mapperList) {
            String mapperPath = element.attributeValue("resource");
            InputStream mapperStream = Resources.getResourceAsInputStream(mapperPath);
            XXLMapperBuilder xxlMapperBuilder = new XXLMapperBuilder(configuration);
            xxlMapperBuilder.parse(mapperStream);
        }
        return configuration;
    }
}
