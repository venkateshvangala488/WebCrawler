package com.imaginea.venkatesh.utils;

import static org.junit.Assert.*;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;

public class CrawlerTest {
	
	private Crawler crawler;
	
	@Before
	public void setUp(){
		crawler = new Crawler();
	}
	
	@Test
	public void crawlForLinksWithPatternTestCase() throws IOException{
		String startUrl = "http://mail-archives.apache.org/mod_mbox/maven-users/";
		Set<String> crawledLinks = crawler.crawlForLinks(startUrl, "\\s*2014\\d{2}.mbox/thread");
		assertEquals(crawledLinks.size(), 12);
	}
	
	@Test
	public void crawlForLinksWithoutPatternTestCase() throws IOException{
		String startUrl = "http://mail-archives.apache.org/mod_mbox/maven-users/";
		Set<String> crawledLinks = crawler.crawlForLinks(startUrl, null);
		assertTrue(crawledLinks.size() > 12);
	}
	
	@Test(expected = NullPointerException.class)
	public void crawlForLinksFalseResourceTestCase() throws IOException{
		String startUrl = getClass().getResource("resource.html").toString();
		Set<String> crawledLinks = crawler.crawlForLinks(startUrl, null);
		assertEquals(crawledLinks.size(), 9);
	}
	
	@Test
	public void crawlForLinksWithCollectionUsingFileTestCase() throws IOException{
		Set<String> links = new HashSet<String>();
		String testUrl = getClass().getResource("/testResource.html").toString();
		links.add(testUrl);
		Set<String> crawledLinks = crawler.crawlForLinks(links, "");
		assertEquals(crawledLinks.size(), 7);
	}
	
	@Test
	public void crawlForLinksInStepMannerTestCase() throws IOException{
		Set<String> links = new HashSet<String>();
		String startUrl = "http://mail-archives.apache.org/mod_mbox/maven-users/";
		links.add(startUrl);
		Set<String> crawledLinks = crawler.crawlForLinksInStepManner(links, "\\s*2013\\d{2}.mbox/thread");
		assertEquals(crawledLinks.size(), 12);
	}
	
}
