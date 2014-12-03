package com.imaginea.venkatesh.main;

import static org.junit.Assert.assertEquals;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashSet;
import java.util.Set;
import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;
import com.imaginea.venkatesh.apacheconfig.ApacheCrawlerConfig;
import com.imaginea.venkatesh.downloader.URLDownloder;
import com.imaginea.venkatesh.utils.Crawler;

@SuppressWarnings("unused")
public class ApacheMailCrawlerTest {
	
	private Crawler crawler;
	private URLDownloder urlDownloader;
	private ApacheCrawlerConfig apacheCrawlerConfig;
	private ApacheMailCrawler apacheMailCrawler;
	
	@Before
	public void setUp(){
		crawler = new Crawler();
		apacheCrawlerConfig = new ApacheCrawlerConfig();
		urlDownloader = EasyMock.createMock(URLDownloder.class);
		apacheMailCrawler = new ApacheMailCrawler(crawler, urlDownloader, apacheCrawlerConfig);
	}
	
	@Test
	public void apacheMailDownloaderTrueTestCase() throws IOException{
		String crawlUrl = "http://mail-archives.apache.org/mod_mbox/maven-users/";
		apacheCrawlerConfig.setYearHrefPattern("\\s*2014\\d{2}.mbox/thread");
		apacheMailCrawler.apacheMailDownload(crawlUrl, "2014");
		EasyMock.expectLastCall().times(1);
		
	}
	
	@Test(expected = MalformedURLException.class)
	public void apacheMailDownloaderFalseTestCase() throws IOException{
		String crawlUrl = "testurl.com";
		apacheCrawlerConfig.setYearHrefPattern("\\s*2014\\d{2}.mbox/thread");
		apacheMailCrawler.apacheMailDownload(crawlUrl, "2014");
	}
	
}
