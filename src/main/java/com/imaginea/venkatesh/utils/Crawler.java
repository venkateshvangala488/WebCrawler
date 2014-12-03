package com.imaginea.venkatesh.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Crawler {

	private static final Logger LOGGER = LoggerFactory.getLogger(Crawler.class);

	public Set<String> crawlForLinksInStepManner(Set<String> links, String... hrefPatterns) throws IOException {
		for (String hrefPattern : hrefPatterns) {
			links = crawlForLinks(links, hrefPattern);
		}
		return links;
	}

	/**
	 * @param crawlLink
	 * @param hrefPattern
	 * @return all the links from the Given URL that matches the Given Pattern
	 * @throws IOException
	 */
	public Set<String> crawlForLinks(String crawlLink, String hrefPattern) throws IOException {
		Set<String> selectedUrls = new HashSet<String>();
		try {
			InputStream inputStream = new URL(crawlLink).openStream();
			Document document = Jsoup.parse(inputStream, null, crawlLink);
			Elements elements = document.select("a");

			for (Element element : elements) {
				String href = element.absUrl("href");
				if (hrefPattern != null && hrefPattern != "") {
					Pattern pattern = Pattern.compile(hrefPattern);
					Matcher matcherLink = pattern.matcher(href);
					while (matcherLink.find()) {
						selectedUrls.add(href);
					}
				} else {
					selectedUrls.add(href);
				}
			}
		} catch (IOException ie) {
			LOGGER.error(ie.getMessage());
			throw ie;
		}
		return selectedUrls;
	}

	public Set<String> crawlForLinks(Set<String> sourceLinks, String hrefPattern) throws IOException {
		Set<String> extractedLinks = new HashSet<String>();
		for (String sourceLink : sourceLinks) {
			extractedLinks.addAll(this.crawlForLinks(sourceLink, hrefPattern));
		}
		return extractedLinks;
	}

}
