package util;


//数据库连接是成功的
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.administrator;

public class DBConnection {
	static String ip = "127.0.0.1";
	static int port = 3306;
	static String database = "e_bank2";
	static String encoding = "UTF-8";
	static String loginName = "root";
	static String password = "admin";

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver"); //注册驱动
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() throws SQLException {   //获得连接
		String url = String.format("jdbc:mysql://%s:%d/%s?characterEncoding=%s", ip, port, database, encoding);
		return DriverManager.getConnection(url, loginName, password);
	}
	
	public static void main(String[] args) throws SQLException {
		System.out.println(getConnection());		
	}

	

}


