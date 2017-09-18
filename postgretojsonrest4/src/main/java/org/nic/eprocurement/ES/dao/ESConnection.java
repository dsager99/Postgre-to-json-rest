package org.nic.eprocurement.ES.dao;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

public class ESConnection {
	private static String clusterName = "elasticsearch";

	// ElasticSearch http port defaul to 9300
	private static int port = 9300;

	// Elasticsearch client node ip
	private static String hostName = "localhost";

	// close Client
	private static Client client = null;

	// To establish a new Connection
	public static Client getConnection() {
		if (client == null) {
			Settings settings = Settings.builder()
			        .put("cluster.name", clusterName).build();
			try {
				client = new PreBuiltTransportClient(settings)
				        .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(hostName), port));
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}

		}
		return client;
	}

	public static String getClusterName() {
		return clusterName;
	}

	public static void setClusterName(String clusterName) {
		ESConnection.clusterName = clusterName;
	}

	public static int getPort() {
		return port;
	}

	public static void setPort(int port) {
		ESConnection.port = port;
	}

	public static String getHostName() {
		return hostName;
	}

	public static void setHostName(String hostName) {
		ESConnection.hostName = hostName;
	}

	public static void closeConnection() {
		client.close();
	}
}