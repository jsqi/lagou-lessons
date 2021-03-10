import com.mars.bean.User;

import java.sql.*;

public class ConnectionTest {

    public static void main(String[] args) {
        Connection connection = null;
        try {
            // 注册 MySQl驱动
            Class.forName("com.mysql.jdbc.Driver");
            // 建立连接
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mybatis?characterEncoding=utf-8","root","root");
            // 定义SQl 语句
            String sql = "select * from user";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String password = resultSet.getString("password");
                user.setId(id);
                user.setName(name);
                user.setPassword(password);
                System.out.println(user);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if(null != connection){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }


    }
}
