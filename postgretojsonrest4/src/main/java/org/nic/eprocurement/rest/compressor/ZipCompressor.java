package org.nic.eprocurement.rest.compressor;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.nic.eprocurement.rest.db.postgreServices.RetrieveFromPostGre;

public class ZipCompressor {
	
	public String getZipFilePath(RetrieveFromPostGre dbElements, String jsonData, int page, int size) throws IOException {
		
		SimpleDateFormat time_formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		String current_time_str = time_formatter.format(System.currentTimeMillis());
		
		String filePath = "/home/nicsi/" + dbElements.getDatabaseName() + "_" + dbElements.getSchemaName()
						+ "_" + dbElements.getTableName() + "_" + "_" + "page-" + page + "(size-" + size + ")_" + current_time_str + ".zip";
		
		File f = new File(filePath);
		
		ZipOutputStream out = new ZipOutputStream(new FileOutputStream(f));
		ZipEntry e = new ZipEntry("mytext.txt");
		out.putNextEntry(e);

		byte[] data = jsonData.getBytes();
		out.write(data, 0, data.length);
		out.closeEntry();

		out.close();
		
		System.out.println(f.getPath());
		
		return f.getPath();
	}
}
