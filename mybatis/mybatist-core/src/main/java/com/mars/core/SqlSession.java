package com.mars.core;


import java.util.List;

public interface SqlSession {

    <E> List<E> listAll(String statementId, Object ...params) throws Exception;

    <T> T getOne(String statementId, Object ...params) throws Exception;

    // 为Mapper 接口生成代理类
    <T> T getMapper(Class<?> mapperClass);
}
