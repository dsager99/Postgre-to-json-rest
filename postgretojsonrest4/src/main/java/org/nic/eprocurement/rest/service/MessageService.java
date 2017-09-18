package org.nic.eprocurement.rest.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Array;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import javax.json.JsonValue;

import org.nic.eprocurement.rest.compressor.ZipCompressor;
import org.nic.eprocurement.rest.database.DatabaseClass;
import org.nic.eprocurement.rest.db.postgreServices.PostgreHelper;
import org.nic.eprocurement.rest.db.postgreServices.RetrieveFromPostGre;
import org.nic.eprocurement.rest.model.Message;


public class MessageService {
	
	private static ResultSetMetaData rsmd;
	private static int numOfColumns;
	
	Map<String, ?> config = null;
	JsonBuilderFactory factory = Json.createBuilderFactory(config);
	
	PostgreHelper help = new PostgreHelper();
	RetrieveFromPostGre getData = new RetrieveFromPostGre();
	ZipCompressor zipCompressor = new ZipCompressor();
	
/*	private Map<Long, Message> messages = DatabaseClass.getMessages();
	
	public List<Message> getAllMessages() {
		
		Message m1 = new Message();
		Message m2 = new Message();
		
		m1.setId(1);
		m1.setName("sagar");
		m2.setId(2);
		m2.setName("dasari");
		
		messages.put(1L, m1);
		messages.put(2L, m2);
		
		List<Message> list = new ArrayList<Message>(messages.values());
		
		return list;
	}*/
	
/*	public List<Message> getAllMessages() {
		
		
		
		RetrieveFromPostGre getData = new RetrieveFromPostGre();

		JsonObjectBuilder rowJsonObject = factory.createObjectBuilder();
		JsonArrayBuilder tableJsonArray = factory.createArrayBuilder();

		ResultSet rs = getData.selectRows("db1", "schema1", "table1");
		List<Message> list = new ArrayList<Message>();
		rsmd=rs.getMetaData();  
		numOfColumns = rsmd.getColumnCount();
		
		try {
			while(rs.next()) { 
				String numberType[] = {"BOOLEAN", "INTEGER", "FLOAT", "LONG", "DOUBLE"};
				String stringType[] = {"STRING", "DATE"};
				Map<String, Object> map = new HashMap<String, Object>();
				Message m1 = new Message();
				for (int i=1; i<=numOfColumns; i++) {                    
					
					Object o = rs.getObject(i);
					
					if(Arrays.asList(stringType).contains(help.getDataType(rsmd.getColumnType(i)))) {
						map.put(rsmd.getColumnName(i), rs.getString(i));
					}
					else if(Arrays.asList(numberType).contains(help.getDataType(rsmd.getColumnType(i)))) {
						map.put(rsmd.getColumnName(i), rs.getBigDecimal(i));
					}
					else {
						map.put(rsmd.getColumnName(i), rs.getObject(i));
					}
				}
				m1.setDb(map);
				list.add(m1);
		    }
		} catch (Exception e) {
			System.err.println( e.getClass().getName()+": "+ e.getMessage() );
			e.printStackTrace();
			System.exit(0);
		}
		
//		Message m1 = new Message();
//		Message m2 = new Message();
//		
//		m1.setId(1);
//		m1.setName("sagar");
//		m2.setId(2);
//		m2.setName("dasari");
//		
//		List<Message> list = new ArrayList<Message>();
//		
//		list.add(m1);
//		list.add(m2);
		
		return list;
	}*/
	
/*	public List<Object> getAllMessages() {
		
		Map<String, Object> m1 = new HashMap<String, Object>();
		Map<String, Object> m2 = new HashMap<String, Object>();
		m1.put("id", 1);
		m1.put("name", "sagar");
		m2.put("id", 2);
		m2.put("name", "dasari");
		
		//List<Map<String, Object>> list = new ArrayList<>();
		List<Object> list = new ArrayList<>();
		list.add((Object)m1);
		list.add((Object)m2);
		
		return list;
	}*/
	
/*	public Message getAllMessages() {
		Message m1 = new Message();
		Message m2 = new Message();
		
		m1.setId(1);
		m1.setName("sagar");
		m2.setId(2);
		m2.setName("dasari");
		
		return m1;
	}*/

	public int getTableCountDirect() throws SQLException {
		
		getData.setDataBaseElements("db1", "schema1", "table1");
		
		ResultSet rs = getData.retrieveCount();
		
		rs.next();
		
		int count = rs.getInt(1);
		
		return count;
	}
	
	public ResultSet getTableCount(RetrieveFromPostGre dbElements) throws SQLException {
		
		getData.setDataBaseElements(dbElements.getDatabaseName(), dbElements.getSchemaName(),dbElements.getTableName());
		
		ResultSet rs = getData.retrieveCount(); 
		
		return rs;
	}
	
	public String getPartialRowsDirect(int page, int size) throws SQLException {
		
		getData.setDataBaseElements("db1", "schema1", "table1");
		
		ResultSet rs = getData.retrieveFewRows(page,size);
		
		String jsonString = resultSetToJsonString(rs);
		
		return jsonString;
	}

	public ResultSet getPartialRows(RetrieveFromPostGre dbElements, int page, int size) throws SQLException {
		
		getData.setDataBaseElements(dbElements.getDatabaseName(), dbElements.getSchemaName(),dbElements.getTableName());
		
		ResultSet rs = getData.retrieveFewRows(page,size);
		
		return rs;
	}
	
	public String getPartialRowsZip(RetrieveFromPostGre dbElements, int page, int size) throws SQLException, IOException {
		
		getData.setDataBaseElements(dbElements.getDatabaseName(), dbElements.getSchemaName(),dbElements.getTableName());
		
		ResultSet rs = getData.retrieveFewRows(page,size);
		
		String jsonString = resultSetToJsonString(rs);
		
		String filePath = zipCompressor.getZipFilePath(dbElements, jsonString, page, size);
		
		return filePath;
		
	}	

	public String getAllRowsDirect() throws SQLException {
		
		getData.setDataBaseElements("db1", "schema1", "table1");
	
		ResultSet rs = getData.retrieveAllRows();
		
		String jsonString = resultSetToJsonString(rs);
		
		return jsonString;
	}
	
	public ResultSet getAllRows(RetrieveFromPostGre dbElements) throws SQLException {
		
		getData.setDataBaseElements(dbElements.getDatabaseName(), dbElements.getSchemaName(),dbElements.getTableName());
	
		ResultSet rs = getData.retrieveAllRows();
		
		return rs;
	}
	
	public String getAllRowsZip(RetrieveFromPostGre dbElements) throws SQLException, IOException {
		
		getData.setDataBaseElements(dbElements.getDatabaseName(), dbElements.getSchemaName(),dbElements.getTableName());
		
		ResultSet rs = getData.retrieveAllRows();
		
		String jsonString = resultSetToJsonString(rs);
		
		String filePath = zipCompressor.getZipFilePath(dbElements, jsonString, 0, 0);
		
		return filePath;
	
	}
	
	public String resultSetToJsonString(ResultSet rs) throws SQLException {
		
		JsonObjectBuilder rowJsonObject = factory.createObjectBuilder();
		JsonArrayBuilder tableJsonArray = factory.createArrayBuilder();
		
		rsmd = rs.getMetaData();  
		numOfColumns = rsmd.getColumnCount();
		
		try {
			while(rs.next()) { 
				rowJsonObject = createJsonObjectOfSingleRow(rs);
		        tableJsonArray.add(rowJsonObject);
		    }
		} catch (Exception e) {
			System.err.println( e.getClass().getName()+": "+ e.getMessage() );
			e.printStackTrace();
			System.exit(0);
		}
		
		return tableJsonArray.build().toString();
	}
	
	public JsonObjectBuilder createJsonObjectOfSingleRow(ResultSet rs) throws SQLException {
			
		JsonObjectBuilder jsonObject = factory.createObjectBuilder();
		
		String numberType[] = {"BOOLEAN", "INTEGER", "FLOAT", "LONG", "DOUBLE"};
		String stringType[] = {"STRING", "DATE"};
		
		for (int i=1; i<=numOfColumns; i++) {                    

			Object o = rs.getObject(i);
		
			if(Arrays.asList(stringType).contains(help.getDataType(rsmd.getColumnType(i)))) {
				jsonObject.add(rsmd.getColumnName(i), getDataBaseElementJsonValue(rs.getString(i)));
			}
			else if(Arrays.asList(numberType).contains(help.getDataType(rsmd.getColumnType(i)))) {
				jsonObject.add(rsmd.getColumnName(i), getDataBaseElementJsonValue(rs.getBigDecimal(i)));
			}
			else if(o instanceof Array) {
				jsonObject.add(rsmd.getColumnName(i), getDataBaseElementJsonValue(rs.getArray(i).getArray()));
			}
			else {
				System.out.println("These types of Columns are not being handled");
			}
		}
		return jsonObject;
	}

	public String getDataBaseElementJsonValue(String data) {
		return data;
	}
	
	public BigDecimal getDataBaseElementJsonValue(BigDecimal data) {
		return data;
	}
	
	public JsonArray getDataBaseElementJsonValue(Object array) {
		JsonArrayBuilder jsonArray = factory.createArrayBuilder();
		Object s[] = (Object[]) array;
		
		for(Object s1: s) {
			
			if(s1 instanceof String) {
				jsonArray.add(s1.toString());
			}
			else if(s1 instanceof Number) {
				
				if(s1 instanceof Integer || s1 instanceof Short) {
					jsonArray.add((Integer) s1);
				}
				else if(s1 instanceof Long) {
					jsonArray.add((Long) s1);
				}
				else if(s1 instanceof Double || s1 instanceof Float) {
					jsonArray.add((Double) s1);
				}
				else if(s1 instanceof BigInteger) {
					jsonArray.add((BigInteger) s1);
				}
				else if(s1 instanceof BigDecimal) {
					jsonArray.add((BigDecimal) s1);
				}
			}
			else if(s1.getClass().isArray()) {
				jsonArray.add(getDataBaseElementJsonValue(s1));
			}
		}
		return jsonArray.build();
	}
}
