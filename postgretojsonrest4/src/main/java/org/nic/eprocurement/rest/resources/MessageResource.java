package org.nic.eprocurement.rest.resources;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.nic.eprocurement.rest.db.postgreServices.RetrieveFromPostGre;
import org.nic.eprocurement.rest.model.Message;
import org.nic.eprocurement.rest.service.MessageService;
import org.nic.eprocurement.rest.service.Test;
import org.nic.eprocurement.rest.service.Test1;

@Path("/getdbinfo")
public class MessageResource {
	
	MessageService msgService = new MessageService();
	
	@POST
	@Path("/count")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResultSet getCount(RetrieveFromPostGre dbElements) throws SQLException {
		ResultSet rs = msgService.getTableCount(dbElements);
		return rs;
	}
	
	@POST
	@Path("/retrieverows")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResultSet getRows(RetrieveFromPostGre dbElements,
							@QueryParam("page") int page,
							@QueryParam("size") int size) throws SQLException {
		
		ResultSet rs;
		
		if(page!=0 && size!=0) {
			rs = msgService.getPartialRows(dbElements, page, size);
		}
		else {
			rs = msgService.getAllRows(dbElements);
		}
		
		return rs;
	}
	
	@POST
	@Path("/retrievezip")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces("application/zip")
	public Response getZip(RetrieveFromPostGre dbElements,
							@QueryParam("page") int page,
						@QueryParam("size") int size) throws SQLException, IOException {
		
		System.out.println("request accepted");
		String filePath = null;
		
		SimpleDateFormat time_formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		String current_time_str = time_formatter.format(System.currentTimeMillis());
		
		if(page!=0 && size!=0) {
			filePath = msgService.getPartialRowsZip(dbElements, page, size);
		}
		else {
			filePath = msgService.getAllRowsZip(dbElements);
		}
		
		File file = new File(filePath);
		ResponseBuilder response = Response.ok((Object) file);
		response.header("Content-Disposition","attachment; filename="+ dbElements.getDatabaseName() + "_" + dbElements.getSchemaName()
		+ "_" + dbElements.getTableName() + "_" + "page-" + page + "(size-" + size + ")_" + current_time_str + ".zip\"");
		
		return response.build();
	}
}
