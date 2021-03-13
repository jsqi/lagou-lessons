package com.mars.pojo;

/**
 * 用于存放mapper.xml 解析出的数据
 * @author jsq
 */
public class MappedStatement {

    /**
     * mapper.xml 标签的 唯一Id
     */
    private String id;

    /**
     * 参数类型
     */
    private String paramsType;

    /**
     * 返回值类型
     */
    private String resultType;

    /**
     * mapper.xml 获取的原始的sql
     */
    private String sql;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParamsType() {
        return paramsType;
    }

    public void setParamsType(String paramsType) {
        this.paramsType = paramsType;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }
}
