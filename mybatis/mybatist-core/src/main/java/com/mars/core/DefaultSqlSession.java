package com.mars.core;

import com.mars.pojo.Configuration;

import java.lang.reflect.*;
import java.util.List;

public class DefaultSqlSession implements SqlSession {

    private Configuration configuration;

    public DefaultSqlSession(Configuration configuration) {
        this.configuration = configuration;
    }

    public <E> List<E> listAll(String statementId, Object... params) throws Exception {
        SimpleExecutor simpleExecutor = new SimpleExecutor();
        return simpleExecutor.query(configuration,statementId,params);
    }

    public <T> T getOne(String statementId, Object... params) throws Exception {
        if(listAll(statementId,params).size()==1){
            return (T) listAll(statementId,params).get(0);
        }else {
            throw new RuntimeException("select one but found"+listAll(statementId,params).size());
        }
    }

    public <T> T getMapper(Class<?> mapperClass) {
        // JDK 动态代理为Mapper接口生成代理类
       Object proxyInstance =  Proxy.newProxyInstance(DefaultSqlSession.class.getClassLoader(), new Class[]{mapperClass}, new InvocationHandler() {
            public Object invoke(Object proxy, Method method, Object[] params) throws Throwable {
                String statementId = method.getDeclaringClass().getName()+"."+method.getName();
                Type genericReturnType = method.getGenericReturnType();
                // 判断是否进行类型参数化
                if(genericReturnType instanceof ParameterizedType) {
                    return listAll(statementId,params);
                }
                return getOne(statementId, params);
            }
        });
        return (T) proxyInstance;
    }
}
