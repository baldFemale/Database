package toolkit;

import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JFrame;

import view.Item341Act;
import view.Item342Act;

/**
 * Created by Jordan on 2017/12/12.
 */

final class TestUnit {
    private static Connection connection = null;
    public static void main(String[] a){
        testItem3();
    }
    public static Connection DBC(){

        String driverMySQL="com.mysql.jdbc.Driver";
        String databaseName="student";
        String urlMySQL="jdbc:mysql://localhost:3306/"+databaseName;
        if(connection!=null)return connection;
        try {
            Class.forName(driverMySQL);
            // DriverManager.registerDriver(new Driver());
            connection = DriverManager.getConnection(urlMySQL, "", "");
            if(!connection.isClosed())
                System.out.println("Succeeded connecting to the Database!");
        } catch(Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
    static void testItem3(){
        JFrame jf=new JFrame("test");
        jf.setSize(600,400);
        jf.add(new Item342Act());
        jf.setFont(new Font("ËÎÌו",Font.ITALIC,30));
        Utility.setWindowAtCenter(jf);
        jf.setVisible(true);
    }
}
