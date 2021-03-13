package com.mars.core;


import com.mars.pojo.Configuration;

import java.sql.SQLException;
import java.util.List;

public interface Executor {

    /**
     * 查询
     * @param configuration
     * @param statementId
     * @param objects
     * @param <E>
     * @return
     * @throws Exception
     */
    <E> List<E> query(Configuration configuration, String statementId, Object... objects) throws Exception;

    /**
     * 更新
     * @param configuration
     * @param statementId
     * @param objects
     * @return
     */
    int update(Configuration configuration, String statementId, Object... objects) throws Exception;


}
