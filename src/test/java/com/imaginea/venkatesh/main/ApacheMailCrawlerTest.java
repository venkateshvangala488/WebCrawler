package com.imaginea.venkatesh.main;

import static org.junit.Assert.assertEquals;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashSet;
import java.util.Set;
import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;
import com.imaginea.venkatesh.apacheconfig.ApacheConfig;
import com.imaginea.venkatesh.downloader.URLDownloder;
import com.imaginea.venkatesh.utils.Crawler;

@SuppressWarnings("unused")
public class ApacheMailCrawlerTest {
	
	private Crawler crawler;
	private ApacheMailCrawler apacheMailCrawler;
	private URLDownloder downloader;
	
	@Before
	public void setUp(){
		apacheMailCrawler = new ApacheMailCrawler();
		crawler = new Crawler();
		downloader = EasyMock.createMock(URLDownloder.class);
	}
	
	@Test
	public void apacheMailDownloaderTrueTestCase() throws IOException{
		String crawlUrl = "http://mail-archives.apache.org/mod_mbox/maven-users/";
		Set<String> urlSet = new HashSet<String>();
		urlSet.add(crawlUrl);
		
		ApacheConfig config = new ApacheConfig();
		config.setYearHrefPattern("\\s*2014\\d{2}.mbox/thread");
		
		Set<String> downloadUrls = crawler.crawlForLinksInStepManner(urlSet, config.getYearHrefPattern());
		assertEquals(downloadUrls.size(), 12);
		downloader.download(downloadUrls);
	}
	
	@Test(expected = MalformedURLException.class)
	public void apacheMailDownloaderFalseTestCase() throws IOException{
		String crawlUrl = "testurl.com";
		Set<String> urlSet = new HashSet<String>();
		urlSet.add(crawlUrl);
		
		ApacheConfig config = new ApacheConfig();
		config.setYearHrefPattern("\\s*2014\\d{2}.mbox/thread");
		
		Set<String> downloadUrls = crawler.crawlForLinksInStepManner(urlSet, config.getYearHrefPattern());
		assertEquals(downloadUrls.size(), 12);
		downloader.download(downloadUrls);
	}
	
}
