package org.nic.eprocurement.rest.database;

import java.util.HashMap;
import java.util.Map;

import org.nic.eprocurement.rest.model.Message;
import org.nic.eprocurement.rest.model.Profile;


public class DatabaseClass {

	private static Map<Long, Message> messages = new HashMap<Long, Message>();
	private static Map<Long, Profile> profiles = new HashMap<Long, Profile>();
	
	public static Map<Long, Message> getMessages() {
		return messages;
	}
	public static void setMessages(Map<Long, Message> messages) {
		DatabaseClass.messages = messages;
	}
	public static Map<Long, Profile> getProfiles() {
		return profiles;
	}
	public static void setProfiles(Map<Long, Profile> profiles) {
		DatabaseClass.profiles = profiles;
	}
}
