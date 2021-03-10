package com.mars.config;

import com.mars.pojo.Configuration;
import com.mars.pojo.MappedStatement;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.List;

public class XXLMapperBuilder {

    private Configuration configuration;

    public XXLMapperBuilder(Configuration configuration) {
        this.configuration = configuration;
    }

    public void parse(InputStream inputStream) throws DocumentException {
        Document document = new SAXReader().read(inputStream);
        // 获取到Mapper标签
        Element rootElement = document.getRootElement();
        String nameSpace = rootElement.attributeValue("namespace");
        List<Element> selectNodes = rootElement.selectNodes("//select");
        for (Element element : selectNodes) {
            String id = element.attributeValue("id");
            String resultType = element.attributeValue("resultType");
            String paramType = element.attributeValue("paramType");
            String sql = element.getTextTrim();
            MappedStatement mappedStatement = new MappedStatement();
            mappedStatement.setId(id);
            mappedStatement.setParamsType(paramType);
            mappedStatement.setResultType(resultType);
            mappedStatement.setSql(sql);
            String key = nameSpace + "."+ id;
            configuration.getMappedStatementMap().put(key,mappedStatement);
        }
    }
}
