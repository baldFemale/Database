package control;

/**
 * Created by Jordan on 2017/11/19.
 */

import java.sql.*;
import java.sql.Connection;

public class DBConnection {
    private static Connection conn=null;
    private static final DBConnection ourInstance = new DBConnection();
    public static DBConnection getInstance() {
        return ourInstance;
    }

    private final String user = "user1";
    private final String password = "u0001";

    private DBConnection() {
        // 驱动程序名
        String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        String driverMySQL="com.mysql.jdbc.Driver";

        // URL指向要访问的数据库名scutcs
        String url = "jdbc:sqlserver://192.168.0.250:1433;DatabaseName=student";
        String databaseName="student";
        String urlMySQL="jdbc:mysql://localhost:3306/"+databaseName;

        try {
        	Class.forName(driver);
            // DriverManager.registerDriver(new Driver());
            conn = DriverManager.getConnection(url, user, password);

            if(!conn.isClosed())
                System.out.println("Succeeded connecting to the Database!");


        } catch(SQLException e) {
            e.printStackTrace();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnection(){
        if (conn == null)
            conn = DBConnection.getInstance().conn;
        return conn;
    }
    public void CloseConnection(){
        try{
            conn.close();
            conn=null;
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
