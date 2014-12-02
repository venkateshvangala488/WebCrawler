package com.imaginea.venkatesh.downloader;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileBaseDownLoadHandler implements DownLoadHandler {
	private static final Logger LOGGER = LoggerFactory.getLogger(FileBaseDownLoadHandler.class);
	public File rootDir;

	public FileBaseDownLoadHandler(String rootDir) {
		File makeDir = new File(System.getProperty("java.io.tmpdir") + "/" + rootDir);
		if (!makeDir.exists()) {
			makeDir.mkdir();
		}
		this.rootDir = makeDir;
	}

	public void handle(String url, InputStream inputStream, String fileName) throws IOException {
		String filePath = rootDir + "/" + fileName;
		File file = new File(filePath);
		if (!file.exists()) {
			OutputStream outputStream = new FileOutputStream(file);
			try {
				file.createNewFile();
				IOUtils.copy(inputStream, outputStream);
				
			} catch (IOException io) {
				LOGGER.error(io.getMessage());
				throw io;
			}
			finally{
				outputStream.close();
			}
		}
	}
}
