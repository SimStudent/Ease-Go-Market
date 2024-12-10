package JDBC;
import java.sql.Connection;

public class LocalhostJDBC {
    private static String DB_URL = "jdbc:mysql://localhost:3306/clothing";
    private static String DB_USER = "root";
    private static String DB_PASSWORD = "123456";
    private static final String DB_DRIVER = "com.mysql.jdbc.Driver";

    public LocalhostJDBC(){
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public LocalhostJDBC(String DB,String acc,String pwd){
        DB_URL = "jdbc:mysql://localhost:3306/"+ DB + "?characterEncoding=UTF-8";
        DB_USER = acc;
        DB_PASSWORD = pwd;
    }

    public Connection getConnection(){
        try {
            return java.sql.DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    

}
