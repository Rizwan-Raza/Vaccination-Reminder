package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
//	 private static String username = "system";
//	 private static String password = "oracle";
//	 private static String driver = "oracle.jdbc.driver.OracleDriver";
//	 private static String url = "jdbc:oracle:thin:@localhost:1521:xe";

	 private static String username = "root";
	 private static String password = "root";
	 private static String driver = "com.mysql.jdbc.Driver";
	 private static String url = "jdbc:mysql://localhost:3306/webapp";

	private static Connection conn = null;

	public static Connection getConnection() {
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
}
