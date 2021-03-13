package com.mars.pojo;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author jsq
 *
 */
public class Configuration {

    /**
     * 数据源
     */
    private DataSource dataSource;

    /**
     * key: MappedStatement.id value MappedStatement
     * 用于保存  解析 Mapper.xml 和 MapperNameSpace.id 的映射信息
     */
    private Map<String, MappedStatement> mappedStatementMap = new HashMap<String, MappedStatement>();

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Map<String, MappedStatement> getMappedStatementMap() {
        return mappedStatementMap;
    }

    public void setMappedStatementMap(Map<String, MappedStatement> mappedStatementMap) {
        this.mappedStatementMap = mappedStatementMap;
    }
}
