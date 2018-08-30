package com.revature.util;

import java.sql.*;
import java.io.*;
import java.util.Properties;


public class ConnectionUtil {
	public static Connection getConnection() throws SQLException, IOException {
		Properties prop = new Properties();
		//InputStream in = new FileInputStream("connection.properties");
		InputStream in = new FileInputStream("C:\\Users\\Christian\\eclipse-workspace\\Project1\\connection.properties");
		prop.load(in);
		
		String url = prop.getProperty("url");
		String user = prop.getProperty("user");
		String password = prop.getProperty("password");

		Driver d = new oracle.jdbc.OracleDriver();
		DriverManager.registerDriver(d);
		return DriverManager.getConnection(url, user, password);
	}


}
