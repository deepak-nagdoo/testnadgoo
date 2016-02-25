package shopnow.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Util {
	private static String driver = "com.mysql.jdbc.Driver";
	private static String url = "jdbc:mysql://localhost/shopnow";
	private static String user = "root";
	private static String password = "root";
	private static Connection connection=null;
/*	private static PreparedStatement preparedStatement=null;
	private static ResultSet resultSet=null;*/
	
	public static Connection getConnection(){
		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(url,user,password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

		return connection;
	}
	
	public static void closeConnection(){
			try {
				if(connection!=null)
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

}
