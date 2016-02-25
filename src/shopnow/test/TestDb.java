package shopnow.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.sql.DataSource;

import shopnow.service.CategoryService;

public abstract class TestDb {
	private static String driver = "com.mysql.jdbc.Driver";
	private static String url = "jdbc:mysql://localhost/shopnow";
	private static String user = "root";
	private static String password = "root";
	private static Connection connection;
	private static PreparedStatement preparedStatement;
	private static ResultSet resultSet;
	
	@Resource(lookup="java:jboss/datasources/SNS")
	private static DataSource dataSource;
	public static void check() throws Exception{
		Class.forName(driver);
		connection = DriverManager.getConnection(url,user,password);
		String query = "Select * from role";
		preparedStatement = connection.prepareStatement(query);
		resultSet = preparedStatement.executeQuery();
		while(resultSet.next()){
			System.out.println(resultSet.getString(1));
		}
	}

	public static void checkDataSource() throws Exception{
		System.out.println(dataSource.getConnection());
	}
	
	public static void main(String[] args) {
		try {
			checkDataSource();
/*			check();
			System.out.println(new CategoryService().getAllCategory());
			Date date= new Date();
			SimpleDateFormat format = new SimpleDateFormat("dd.mm.yyyy");
			System.out.println("date sample-->"+format.format(date));
*/		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
