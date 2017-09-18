package org.nic.eprocurement.rest.service;

import java.io.StringReader;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Array;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Arrays;
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
import javax.json.JsonString;

import org.nic.eprocurement.rest.db.postgreServices.PostgreHelper;
import org.nic.eprocurement.rest.db.postgreServices.RetrieveFromPostGre;

public class Test1 {/*
	
	private static ResultSetMetaData rsmd;
	private static int numOfColumns;
	Map<String, ?> config = null;
	JsonBuilderFactory factory = Json.createBuilderFactory(config);
	
	PostgreHelper help = new PostgreHelper();
	
	public String getAllMessages() throws SQLException {

		RetrieveFromPostGre getData = new RetrieveFromPostGre();

		JsonObjectBuilder rowJsonObject = factory.createObjectBuilder();
		JsonArrayBuilder tableJsonArray = factory.createArrayBuilder();

		rowJsonObject.add("id", 1);
		rowJsonObject.add("name", "sa");
		JsonObject jo = rowJsonObject.build();
		tableJsonArray.add(jo);
		rowJsonObject.add("id", 2);
		rowJsonObject.add("name", "da");
		jo = rowJsonObject.build();
		tableJsonArray.add(jo);
		
	//	getData.selectRows("db1", "schema1", "on_hand");
		ResultSet rs = getData.selectRows("db1", "schema1", "table1");
		
		rsmd=rs.getMetaData();  
		numOfColumns = rsmd.getColumnCount();
		
		int a[]={0,1};
		//JsonArray j = (JsonArray) a;
		
		try {
			while(rs.next()) { 
				rowJsonObject = createJsonObjectOfSingleRow(rs);
		        tableJsonArray.add(rowJsonObject);
		        JsonObject value = Json.createObjectBuilder()
		        		.add("array", Json.createArrayBuilder().add(10).add(20)).build();
		      //  break;
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
		JsonArrayBuilder jsonArray = factory.createArrayBuilder();
		
		String numberType[] = {"BOOLEAN", "INTEGER", "FLOAT", "LONG", "DOUBLE"};
		String stringType[] = {"STRING", "DATE"};
		
		for (int i=1; i<=numOfColumns; i++) {                    
            
			String column_name = rsmd.getColumnName(i);
			System.out.println(help.getDataType(rsmd.getColumnType(i)));
			if(Arrays.asList(numberType).contains(help.getDataType(rsmd.getColumnType(i)))) {
				rowJsonObject.add(column_name, rs.getInt(column_name));
			}
			else if(Arrays.asList(stringType).contains(help.getDataType(rsmd.getColumnType(i)))) {
				rowJsonObject.add(column_name, rs.getString(column_name));
			}
			else if(help.getDataType(rsmd.getColumnType(i)).equalsIgnoreCase("ARRAY")) {
				//System.out.println("in arr");
				//JsonArray jArray = (JsonArray) rs.getArray(column_name);
				//rowJsonObject.add(column_name, jArray);
				Object s[] = (Object[]) rs.getArray(column_name).getArray();
				System.out.println(s.getClass().getSimpleName());
				for(Object s1: s) {
					
					elementJsonArray.add((String)s1);
				}
				rowJsonObject.add("array", elementJsonArray);
			}
			
			Object o = rs.getObject(i);
		//	System.out.println("Class " + rs.getObject(i).getClass()+ (o instanceof Number) + rsmd.getColumnClassName(i) +help.getDataType(rsmd.getColumnType(i)));
			if(Arrays.asList(stringType).contains(help.getDataType(rsmd.getColumnType(i)))) {
				//System.out.println(o.getClass().getSimpleName());
				jsonObject.add(rsmd.getColumnName(i), getDataBaseElementJsonValue(rs.getString(i)));
			}
			else if(o instanceof Number) {
				//System.out.println(o.getClass().getSimpleName());
				jsonObject.add(rsmd.getColumnName(i), getDataBaseElementJsonValue(rs.getBigDecimal(i)));
			}
			else if(o instanceof Array) {
				System.out.println(o.getClass().getSimpleName());
				System.out.println(rs.getArray(i).getResultSet());
				//createJsonObjectOfSingleRow(rs.getArray(i).getResultSet(),elementJsonObject);
				//rowJsonObject.add(rsmd.getColumnName(i), getDataBaseElementJsonValue(rs.getArray(i).getResultSet()));
				getDataBaseElementJsonValue(rs.getArray(i).getResultSet());
				getDataBaseElementJsonValue(rs.getArray(i).getArray());
				rs.getArray(i).getArray();
				//getDataBaseElementJsonValue(rs.getObject(i));
				Object od = rs.getObject(i);
				for(Field f : od.getClass().getDeclaredFields()) {
					
				}
				jsonObject.add(rsmd.getColumnName(i), getDataBaseElementJsonValue(rs.getArray(i).getArray()));
			}
			//rowJsonObject.add
		}
		return jsonObject;
	}

	public String getDataBaseElementJsonValue(String data) {
		return data;
	}
	
	public BigDecimal getDataBaseElementJsonValue(BigDecimal data) {
		return data;
	}
	
	public void getDataBaseElementJsonValue(ResultSet data) {
		try {
			data.next();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			System.out.println(data.getString(2));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(Object arrayElement: data) {
			
			elementJsonArray.add((String)s1);
		}
		
		//return null;
	}
	@SuppressWarnings("unchecked")
	private <T> T castObject(Class<T> clazz, Object object) {
		  return (T) object;
	}
	private JsonObjectBuilder jsonObjectToBuilder(JsonObject jo) {
	    JsonObjectBuilder job = Json.createObjectBuilder();

	    for (Entry<String, JsonValue> entry : jo.entrySet()) {
	        job.add(entry.getKey(), entry.getValue());
	    }

	    return job;
	}
	@SuppressWarnings("unchecked")
	public <T> JsonArray getDataBaseElementJsonValue(Object array) {
		JsonObjectBuilder jsonObject = factory.createObjectBuilder();
		JsonArrayBuilder jsonArray = factory.createArrayBuilder();
		Object s[] = (Object[]) array;
		System.out.println(s.getClass().getSimpleName());
		Type T = s.getClass();
		for(Object s1: s) {
			if(s1 instanceof String)
				jsonArray.add(s1.toString());
			else if(s1 instanceof Number) {
				if(s1 instanceof Integer || s1 instanceof Short)
					jsonArray.add((Integer) s1);
				else if(s1 instanceof Long)
					jsonArray.add((Long) s1);
				else if(s1 instanceof Double || s1 instanceof Float)
					jsonArray.add((Double) s1);
				else if(s1 instanceof BigInteger)
					jsonArray.add((BigInteger) s1);
				else if(s1 instanceof BigDecimal)
					jsonArray.add((BigDecimal) s1);
			}
			else if(s1 instanceof Array)
				jsonArray.add(getDataBaseElementJsonValue(s1));
		}
		return jsonArray.build();
	}
	
*/}