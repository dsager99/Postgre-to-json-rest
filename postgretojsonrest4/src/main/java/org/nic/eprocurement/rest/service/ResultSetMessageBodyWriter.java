package org.nic.eprocurement.rest.service;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Array;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Map;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

@Provider
@Produces(MediaType.APPLICATION_JSON)
public class ResultSetMessageBodyWriter implements MessageBodyWriter<ResultSet>{

	private static ResultSetMetaData rsmd;
	private static int numOfColumns;
	
	Map<String, ?> config = null;
	JsonBuilderFactory factory = Json.createBuilderFactory(config);
	
	@Override
	public long getSize(ResultSet arg0, Class<?> arg1, Type arg2, Annotation[] arg3, MediaType arg4) {
		// TODO Auto-generated method stub
		return -1;
	}

	@Override
	public boolean isWriteable(Class<?> type, Type arg1, Annotation[] arg2, MediaType arg3) {
		return ResultSet.class.isAssignableFrom(type);
		
	}

	@Override
	public void writeTo(ResultSet rs, Class<?> type, Type type1, Annotation[] antns, MediaType mt,
			MultivaluedMap<String, Object> arg5, OutputStream out) throws IOException, WebApplicationException {
		
		JsonObjectBuilder rowJsonObject = factory.createObjectBuilder();
		JsonArrayBuilder tableJsonArray = factory.createArrayBuilder();
		
		try {
			rsmd=rs.getMetaData();
			numOfColumns = rsmd.getColumnCount();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}  
		
		
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
		out.write(tableJsonArray.build().toString().getBytes());
	}
	
	public JsonObjectBuilder createJsonObjectOfSingleRow(ResultSet rs) throws SQLException {
		
		JsonObjectBuilder jsonObject = factory.createObjectBuilder();
		
		String numberType[] = {"BOOLEAN", "INTEGER", "FLOAT", "LONG", "DOUBLE"};
		String stringType[] = {"STRING", "DATE"};
		
		for (int i=1; i<=numOfColumns; i++) {                    

			Object o = rs.getObject(i);
		
			if(Arrays.asList(stringType).contains(getDataType(rsmd.getColumnType(i)))) {
				jsonObject.add(rsmd.getColumnName(i), getDataBaseElementJsonValue(rs.getString(i)));
			}
			else if(Arrays.asList(numberType).contains(getDataType(rsmd.getColumnType(i)))) {
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
	
	public String getDataType(int i) {
		
		String type="";
		
		switch(i) {
			case java.sql.Types.INTEGER:  
				type="INTEGER";      break;
			case java.sql.Types.BIGINT:  
				type="LONG";      break;
			case java.sql.Types.BOOLEAN:  
				type="BOOLEAN";      break;
			case java.sql.Types.BIT:  
				type="BOOLEAN";      break;
			case java.sql.Types.FLOAT:   
				type="FLOAT";       break;
			case java.sql.Types.REAL:  
				type="DOUBLE";        break;
			case java.sql.Types.DOUBLE: 
				type="DOUBLE";      break;
			case java.sql.Types.NUMERIC:  
				type="INTEGER";     break;
			case java.sql.Types.DECIMAL:  
				type="DOUBLE";     break;
			case java.sql.Types.CHAR:  
				type="STRING";        break;
			case java.sql.Types.NCHAR:  
				type="STRING";        break;
			case java.sql.Types.VARCHAR:  
				type="STRING";    break;
			case java.sql.Types.NVARCHAR:  
				type="STRING";    break;
			case java.sql.Types.LONGVARCHAR: 
				type="STRING"; break;
			case java.sql.Types.DATE: 
				type="DATE";        break;
			case java.sql.Types.TIME: 
				type="DATE";        break;
			case java.sql.Types.TIMESTAMP: 
				type="DATE";   break;
			case java.sql.Types.BINARY:  
			   type="BINARY";     break;
			case java.sql.Types.VARBINARY:  
				type="BINARY";  break;
			case java.sql.Types.LONGVARBINARY:   
				type="BINARY"; break;
			case java.sql.Types.NULL: 
				type="STRING";        break;
			case java.sql.Types.OTHER:  
				type="STRING";       break;
			case java.sql.Types.JAVA_OBJECT:  
				type="OBJECT"; break;
			case java.sql.Types.DISTINCT:  
				type="STRING";    break;
			case java.sql.Types.STRUCT:  
				type="STRING";      break;
			case java.sql.Types.ARRAY:  
				type="ARRAY";      break;
			case java.sql.Types.BLOB:  
				type="STRING";        break;
			case java.sql.Types.CLOB:  
				type="STRING";        break;
			case java.sql.Types.REF:  
				type="STRING";         break;
		} 
		return type;
	}
}
