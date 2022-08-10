package com.aca.leaflit.dao;


import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MariaDbUtil {

	private static String connectionUrl =
			"jdbc:mariadb://localhost:3306/myplants?user=root&password=********";
	
	public static Connection getConnection() {
		Connection connection = null;
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			connection = DriverManager.getConnection(connectionUrl);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		return connection;
	}
	
	public static void main(String[] args) throws SQLException {
		Connection conn = MariaDbUtil.getConnection();
		if (null != conn) {
			System.out.println("Got my first Java connection to my DB!!!");
			
			DatabaseMetaData metaData = conn.getMetaData();
			ResultSet rs = metaData.getTables(null, null, "%", null);
			
			while (rs.next()) {
				System.out.println(rs.getString("table_name"));
			}
			
		} else {
			System.out.println();
		}
	}
}

