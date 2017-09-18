package org.nic.eprocurement.ES.RequestBuilder;

import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.client.Client;
import org.nic.eprocurement.ES.dao.ESConnection;

public class RequestBuilder {

	public IndexRequestBuilder getIndexRequestBuilder(String index, String type) {
		Client client = ESConnection.getConnection();
		IndexRequestBuilder indexRequest = client.prepareIndex().setIndex(index).setType(type);
		return indexRequest;
	}

	public BulkRequestBuilder getBulkRequestBuilder() {
		// TODO Auto-generated method stub
		Client client = ESConnection.getConnection();
		BulkRequestBuilder bulkRequest = client.prepareBulk();
		return bulkRequest;
	}
}