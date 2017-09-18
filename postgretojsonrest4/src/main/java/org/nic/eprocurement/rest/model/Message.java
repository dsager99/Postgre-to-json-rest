package org.nic.eprocurement.rest.model;

import java.util.Date;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

//@XmlRootElement
public class Message {
	

	private String name;
	private String password;
	
public Message() {
    	
    }
    
    public Message(String name, String password) {
    	
    	this.name = name;
    	this.password = password;
    	
    }
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	/*public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}*/
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	private Map<String, Object> db;

	public Map<String, Object> getDb() {
		return db;
	}

	public void setDb(Map<String, Object> db) {
		this.db = db;
	}
}
