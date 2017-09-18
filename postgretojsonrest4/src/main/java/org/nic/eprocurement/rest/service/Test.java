package org.nic.eprocurement.rest.service;

import java.io.StringReader;
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
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import javax.json.JsonValue;

import org.nic.eprocurement.rest.db.postgreServices.PostgreHelper;
import org.nic.eprocurement.rest.db.postgreServices.RetrieveFromPostGre;

public class Test {
	
	private static ResultSetMetaData rsmd;
	private static int numOfColumns;
	Map<String, ?> config = null;
	JsonBuilderFactory factory = Json.createBuilderFactory(config);
	
	PostgreHelper help = new PostgreHelper();
	
	public String getAllMessages() throws SQLException {
	
		/*	String personJSONData = 
	            "  {" +
	            "   \"name\": \"Jack\", " +
	            "   \"age\" : 13, " +
	            "   \"isMarried\" : false, " +
	            "   \"address\": { " +
	            "     \"street\": \"#1234, Main Street\", " +
	            "     \"zipCode\": \"123456\" " +
	            "   }, " +
	            "   \"phoneNumbers\": [\"011-111-1111\", \"11-111-1111\"] " +
	            " }";
		JsonReader reader = Json.createReader(new StringReader(rs.getObject(1).toString()));
	    
	    JsonObject personObject = reader.readObject();
	    JsonObject obje = personObject.getJsonObject();
		JsonArray phoneNumbersArray = personObject.getJsonArray("phoneNumbers");
		JsonObjectBuilder rowJsonObject = factory.createObjectBuilder();
		rowJsonObject.add("array", phoneNumbersArray);
		jsonArray.add(rowJsonObject);*/
		return null;
				
	}
}
