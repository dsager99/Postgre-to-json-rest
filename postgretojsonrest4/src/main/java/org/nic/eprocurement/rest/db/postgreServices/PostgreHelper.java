package org.nic.eprocurement.rest.db.postgreServices;

public class PostgreHelper {
	
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
