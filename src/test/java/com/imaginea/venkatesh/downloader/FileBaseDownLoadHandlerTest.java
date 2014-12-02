package com.imaginea.venkatesh.downloader;

import static org.junit.Assert.*;
import java.io.*;
import java.net.*;
import org.apache.commons.io.FileDeleteStrategy;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class FileBaseDownLoadHandlerTest {

	private static FileBaseDownLoadHandler handler;
	private File rootDir;

	@BeforeClass
	public static void initialize() {
		handler = new FileBaseDownLoadHandler("DownloadTestMails");
	}

	@Test
	public void handleTestCase() throws MalformedURLException, IOException {
		rootDir = handler.rootDir;
		String downloadUrl = getClass().getResource("/testResource.html").toString();
		InputStream inputStream = new URL(downloadUrl).openConnection().getInputStream();
		handler.handle(downloadUrl, inputStream, "testFile");
		assertTrue(rootDir.exists() && rootDir.listFiles() != null);
	}

	@AfterClass
	public static void clean() {
		if (handler.rootDir.exists()) {
			FileDeleteStrategy.FORCE.deleteQuietly(handler.rootDir);
			assertFalse(handler.rootDir.exists());
		}
	}

}
