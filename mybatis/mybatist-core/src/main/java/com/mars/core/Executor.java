package com.mars.core;


        import com.mars.pojo.Configuration;

        import java.sql.SQLException;
        import java.util.List;

public interface Executor {

   <E> List<E> query(Configuration configuration, String statementId, Object... objects) throws SQLException, Exception;
}
