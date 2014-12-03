package com.imaginea.venkatesh.main;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import org.apache.commons.validator.routines.UrlValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.imaginea.venkatesh.apacheconfig.ApacheCrawlerConfig;
import com.imaginea.venkatesh.downloader.FileBaseDownLoadHandler;
import com.imaginea.venkatesh.downloader.URLDownloder;
import com.imaginea.venkatesh.utils.Crawler;

public class ApacheMailCrawler {

	private static final Logger LOGGER = LoggerFactory.getLogger(ApacheMailCrawler.class);
	private final static UrlValidator URLVALIDATOR = new UrlValidator();

	private Crawler crawler;
	private URLDownloder urlDownloader;
	private ApacheCrawlerConfig apacheCrawlerConfig;
	
	public ApacheMailCrawler(Crawler crawler, URLDownloder urlDownloder, ApacheCrawlerConfig apacheCrawlerConfig) {
		this.crawler = crawler;
		this.urlDownloader = urlDownloder;
		this.apacheCrawlerConfig = apacheCrawlerConfig;
	}

	public void apacheMailDownload(String crawlUrl, String year) throws IOException {
		 Set<String> urlSet = new HashSet<String>();
		 urlSet.add(crawlUrl);
		 Set<String> apacheMailLinks = crawler.crawlForLinksInStepManner(urlSet, apacheCrawlerConfig.getYearHrefPattern().replace("{year}", year), apacheCrawlerConfig.getPaginationPattern().replace("{year}", year), apacheCrawlerConfig.getMailHrefPattern().replace("{year}", year));
		 urlDownloader.download(apacheMailLinks);
	}

	public static void main(String args[]) throws IOException {
		String startUrl = "http://mail-archives.apache.org/mod_mbox/maven-users/";
		String downloadDir = args.length >= 1 ? args[0] : "DownLoadedMails";
		String year = args.length > 1 ? args[1] : "2014";

		LOGGER.info("Apache Crawler Intialized with URL..{}", startUrl);
		if (!URLVALIDATOR.isValid(startUrl)) {
			LOGGER.info("Given {url} URL is not Valid..".replace("{url}", startUrl));
			return;
		}
		ApacheMailCrawler apacheMailCrawler = new ApacheMailCrawler(new Crawler(), new URLDownloder(new FileBaseDownLoadHandler(downloadDir)), new ApacheCrawlerConfig());
		apacheMailCrawler.apacheMailDownload(startUrl, year);
	}
}
