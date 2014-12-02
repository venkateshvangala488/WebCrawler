package com.imaginea.venkatesh.main;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import org.apache.commons.validator.routines.UrlValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.imaginea.venkatesh.apacheconfig.ApacheConfig;
import com.imaginea.venkatesh.downloader.FileBaseDownLoadHandler;
import com.imaginea.venkatesh.downloader.URLDownloder;
import com.imaginea.venkatesh.utils.Crawler;

public class ApacheMailCrawler {

	private static final Logger LOGGER = LoggerFactory.getLogger(ApacheMailCrawler.class);
	private Crawler crawler;
	private URLDownloder urlDownloader;

	public Crawler getCrawler() {
		return crawler;
	}

	public void setCrawler(Crawler crawler) {
		this.crawler = crawler;
	}

	public URLDownloder getUrlDownloader() {
		return urlDownloader;
	}

	public void setUrlDownloader(URLDownloder urlDownloader) {
		this.urlDownloader = urlDownloader;
	}

	private Boolean isValidURL(String url) {
		UrlValidator urlValidator = new UrlValidator();
		return urlValidator.isValid(url);
	}

	public void apacheMailDownload(String crawlUrl, String year) throws IOException {
		Set<String> urlSet = new HashSet<String>();
		urlSet.add(crawlUrl);
		ApacheConfig config = new ApacheConfig();
		Set<String> apacheMailLinks = crawler.crawlForLinksInStepManner(urlSet, config.getYearHrefPattern().replace("{year}", year), config.getPaginationPattern().replace("{year}", year), config.getMailHrefPattern().replace("{year}", year));
		urlDownloader.download(apacheMailLinks);
	}

	public static void main(String args[]) throws IOException {
		String startUrl = "http://mail-archives.apache.org/mod_mbox/maven-users/";
		String downloadDir = args.length >= 1 ? args[0] : "DownLoadedMails";
		String year = args.length > 1 ? args[1] : "2014";
		
		ApacheMailCrawler apacheMailCrawler = new ApacheMailCrawler();
		LOGGER.info("Apache Crawler Intialized with URL..{url}".replace("{url}", startUrl));
		if (!apacheMailCrawler.isValidURL(startUrl)) {
			LOGGER.info("Given {url} URL is not Valid..".replace("{url}", startUrl));
			return;
		}
		
		URLDownloder urlDownloader = new URLDownloder(new FileBaseDownLoadHandler(downloadDir));
		apacheMailCrawler.setUrlDownloader(urlDownloader);
		Crawler crawler = new Crawler();
		apacheMailCrawler.setCrawler(crawler);
		apacheMailCrawler.apacheMailDownload(startUrl, year);
	}
}
