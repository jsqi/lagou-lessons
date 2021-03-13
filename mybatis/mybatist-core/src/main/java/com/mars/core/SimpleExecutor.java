package com.mars.core;


import com.mars.config.BoundSql;
import com.mars.pojo.Configuration;
import com.mars.pojo.MappedStatement;
import com.mars.utils.GenericTokenParser;
import com.mars.utils.ParameterMapping;
import com.mars.utils.ParameterMappingTokenHandler;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jsq
 * sql 执行器
 */
public class SimpleExecutor implements Executor {


    public <E> List<E> query(Configuration configuration, String statementId, Object... objects) throws Exception {

        MappedStatement  mappedStatement =  configuration.getMappedStatementMap().get(statementId);
        Connection connection =  configuration.getDataSource().getConnection();
        // 获取到Mapper.xml 原始SQl 语句
        String sql = mappedStatement.getSql();
        // 将原始sql 语句进行转化
        BoundSql bondSql =  getBoundSql(sql);
        PreparedStatement preparedStatement = connection.prepareStatement(bondSql.getSqlText());
        // 设置参数
        Class<?> paramsKlass = getClass(mappedStatement.getParamsType());
        Class<?> resultTypeKlass = getClass(mappedStatement.getResultType());

        List<ParameterMapping> parameterMappingList = bondSql.getParameterMappingList();
        List<Object> resultList = new ArrayList<Object>();

        for (int i = 0; i <parameterMappingList.size() ; i++) {
            ParameterMapping parameterMapping = parameterMappingList.get(i);
            String filedName = parameterMapping.getContent();
            // 反射
            Field declaredField = paramsKlass.getDeclaredField(filedName);
            declaredField.setAccessible(true);
            Object o = declaredField.get(objects[0]);
            preparedStatement.setObject(i+1,o);
        }
        // 执行sql
        ResultSet resultSet = preparedStatement.executeQuery();
        // 封装返回结果集
        ResultSetMetaData metaData = resultSet.getMetaData();
        while (resultSet.next()){
            Object resultObj = resultTypeKlass.newInstance();
            for (int i = 1; i <metaData.getColumnCount() ; i++) {
                 // 获取字段名
                String columnName = metaData.getColumnName(i);
                Object value = resultSet.getObject(columnName);
                // 根据对应关系完成返回值的封装
                PropertyDescriptor propertyDescriptor = new PropertyDescriptor(columnName,resultTypeKlass);
                Method writeMethod = propertyDescriptor.getWriteMethod();
                writeMethod.invoke(resultObj,value);
            }
            resultList.add(resultObj);
        }
        return (List<E>) resultList;
    }

    /**
     * 更新
     *
     * @param configuration
     * @param statementId
     * @param objects
     * @return
     */
    public int update(Configuration configuration, String statementId, Object... objects) throws Exception {
        MappedStatement  mappedStatement =  configuration.getMappedStatementMap().get(statementId);
        Connection connection =  configuration.getDataSource().getConnection();
        String sql = mappedStatement.getSql();
        // 将原始sql 语句进行转化
        BoundSql bondSql =  getBoundSql(sql);
        PreparedStatement preparedStatement = connection.prepareStatement(bondSql.getSqlText());
        // 设置参数
        Class<?> paramsKlass = getClass(mappedStatement.getParamsType());
        List<ParameterMapping> parameterMappingList = bondSql.getParameterMappingList();
        for (int i = 0; i <parameterMappingList.size() ; i++) {
            ParameterMapping parameterMapping = parameterMappingList.get(i);
            String filedName = parameterMapping.getContent();
            // 反射
            Field declaredField = paramsKlass.getDeclaredField(filedName);
            declaredField.setAccessible(true);
            Object o = declaredField.get(objects[0]);
            preparedStatement.setObject(i+1,o);
        }
        // 执行sql
        int count  = preparedStatement.executeUpdate(sql);
        return count;
    }

    private Class<?> getClass(String paramsType) throws ClassNotFoundException {
        if(null != paramsType){
           return Class.forName(paramsType);
        }
        return null;
    }

    private BoundSql getBoundSql(String sql){
        // 配合标记解析器完成对占位符的解析处理
        ParameterMappingTokenHandler parameterMappingTokenHandler = new ParameterMappingTokenHandler();
        GenericTokenParser genericTokenParser = new GenericTokenParser("#{","}",parameterMappingTokenHandler);
        // 解析好的 sql
        String pareSql = genericTokenParser.parse(sql);
        // 解析参数名称
        List<ParameterMapping> parameterMappingList = parameterMappingTokenHandler.getParameterMappings();
        return new BoundSql(pareSql,parameterMappingList);
    }

}
