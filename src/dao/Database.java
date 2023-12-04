package dao;

 
import java.sql.Connection;
import java.sql.DriverManager;
 
public class Database {
	
    public static Connection getConnection() { 
        try {
            Class.forName("org.postgresql.Driver");

            String connectionString = System.getenv("DB_CONNECTION_STRING");
            Connection con = DriverManager.getConnection(connectionString,
                    System.getenv("DB_USER"), System.getenv("DB_PASSWORD"));
            return con;
        } catch (Exception ex) {
            System.out.print("Database.getConnection() Error --> ");
            ex.printStackTrace();
            return null;
        }
    }
    
 
    public static void close(Connection con) {
        try {
            con.close();
        } catch (Exception ex) {
        }
    }
}