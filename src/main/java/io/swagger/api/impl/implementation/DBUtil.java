package io.swagger.api.impl.implementation;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBUtil {
	private static final String username = "root";
	private static final String password = "Avipuspa90@";
	private static final String dbUrl = "jdbc:mysql://localhost:3306/leavedb";
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(dbUrl,username,password);
	}
}
