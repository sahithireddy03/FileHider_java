package db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Myconnection {
    public static Connection connection;
    public static Connection getConnection(){

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/filehider1?useSSL=false","root","sahithi");
        } catch (ClassNotFoundException | SQLException e) {
            //throw new RuntimeException(e);
            e.printStackTrace();
        }
        System.out.println("Connection is successful");
        return connection;
    }
    public static void closeConnection(){
        if(connection!=null){
            try{
                connection.close();
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
    }

}
