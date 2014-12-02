package com.imaginea.venkatesh.downloader;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

public class URLDownloderTest {
	
	private URLDownloder downloader;
	
	@Before
	public void setUp(){
		downloader = EasyMock.createMock(URLDownloder.class);
	}
	
	@Test
	public void downloadUrlTestCase() throws IOException{
		String downloadUrl = "http://mail-archives.apache.org/mod_mbox/maven-users/";
		downloader.download(downloadUrl);
	}
	
	@Test
	public void downloadUrlsTestCase() throws IOException{
		Set<String> urls = new HashSet<String>();
		String downloadUrl = "http://mail-archives.apache.org/mod_mbox/maven-users/";
		urls.add(downloadUrl);
		downloader.download(urls);
	}
}
