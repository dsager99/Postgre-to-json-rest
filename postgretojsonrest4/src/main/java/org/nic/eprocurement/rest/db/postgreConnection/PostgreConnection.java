package org.nic.eprocurement.rest.db.postgreConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgreConnection {
	
	private String DB_DRIVER, DB_CONNECTION, DB_USER, DB_PASSWORD;
	
	public PostgreConnection() {
		// get the properties values and assign them to respective variable
		this.DB_DRIVER = PostgreContract.DB_DRIVER;
		this.DB_USER = PostgreContract.DB_USER;		
		this.DB_PASSWORD = PostgreContract.DB_PASSWORD;
	}

	public Connection getdbconnection(String dbName) {
		
		Connection dbcon = null;
		this.DB_CONNECTION = PostgreContract.DB_CONNECTION + dbName;
		
		try {
			Class.forName(this.DB_DRIVER );
		} catch (ClassNotFoundException e) {
			System.out.println("Couldn't find JDBC driver, please include respective JDBC driver");
			System.out.println(e.getMessage());
			e.printStackTrace();
			return dbcon;
		}

		try {
			dbcon = DriverManager.getConnection(this.DB_CONNECTION, this.DB_USER, this.DB_PASSWORD);
			return dbcon;
		} catch (SQLException e) {
			System.out.println("DataBase Connection Failed");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		return dbcon;
	}
}
