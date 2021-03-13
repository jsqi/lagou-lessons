package com.mars.core;


import java.util.List;

/**
 * @author jsq
 * SqlSession 接口
 */
public interface SqlSession {

    /**
     * 查询多个
     * @param statementId
     * @param params
     * @param <E>
     * @return List<E>
     * @throws Exception
     */
    <E> List<E> listAll(String statementId, Object ...params) throws Exception;

    /**
     * 查询单个
     * @param statementId
     * @param params
     * @param <T>
     * @return
     * @throws Exception
     */
    <T> T getOne(String statementId, Object ...params) throws Exception;


    /**
     * 根据条件更新
     * @param statementId
     * @param param
     * @return
     */
    int update(String statementId,Object param);


    /**
     * 获取Mapper 接口代理对象
     * @param mapperClass
     * @param <T>
     * @return
     */
    <T> T getMapper(Class<?> mapperClass);
}
