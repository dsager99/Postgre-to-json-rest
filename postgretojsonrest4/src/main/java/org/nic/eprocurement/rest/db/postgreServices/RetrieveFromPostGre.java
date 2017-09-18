package org.nic.eprocurement.rest.db.postgreServices;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import org.nic.eprocurement.rest.db.postgreConnection.PostgreConnection;

public class RetrieveFromPostGre {

	PostgreConnection postgreConnection = new PostgreConnection();
	PostgreHelper getColumnsMeta = new PostgreHelper();
	Connection dbConnection;
	private String databaseName, schemaName, tableName;

	public RetrieveFromPostGre() {

	}

	public RetrieveFromPostGre(String databaseName, String schemaName, String tableName) {
		System.out.println("in db");
		this.databaseName = databaseName;
		this.schemaName = schemaName;
		this.tableName = tableName;
	}

	public String getDatabaseName() {
		return databaseName;
	}

	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}

	public String getSchemaName() {
		return schemaName;
	}

	public void setSchemaName(String schemaName) {
		this.schemaName = schemaName;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public void setDataBaseElements(String databaseName, String schemaName, String tableName) {

		this.setDatabaseName(databaseName);
		this.setSchemaName(schemaName);
		this.setTableName(tableName);

		this.dbConnection = this.postgreConnection.getdbconnection(this.getDatabaseName());

		// return dbConnection;
	}

	public ResultSet retrieveCount() throws SQLException {
		String sql;
		sql = "select count(*) from " + this.schemaName + "." + this.tableName;
		ResultSet rs = executeQuery(sql);
		return rs;
	}

	public ResultSet retrieveFewRows(int page, int size) {
		String sql;
		int limit = size;
		int offset = (page - 1) * size;
		sql = "SELECT * " + "FROM " + this.schemaName + "." + this.tableName + " LIMIT " + limit + " OFFSET " + offset;
		ResultSet rs = executeQuery(sql);
		return rs;
	}

	public ResultSet retrieveAllRows() throws SQLException {
		String sql;
		sql = "select * from " + this.schemaName + "." + this.tableName;
		ResultSet rs = executeQuery(sql);
		return rs;
	}

	public ResultSet executeQuery(String sql) {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = this.dbConnection.createStatement();
			rs = stmt.executeQuery(sql);
			return rs;
			// stmt.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			e.printStackTrace();
			System.exit(0);
		}
		return rs;
	}
}
