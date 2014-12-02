package com.imaginea.venkatesh.downloader;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class URLDownloder {

	private static final Logger LOGGER = LoggerFactory.getLogger(URLDownloder.class);
	private DownLoadHandler handler;
	private int counter = 0;
	
	public URLDownloder(DownLoadHandler handler){
		this.handler = handler;
	}
	
	public void download(String url) throws IOException {
		try {
			InputStream inputStream = new URL(url).openConnection().getInputStream();
			this.handler.handle(url, inputStream, this.getFileName(url));
		} catch (IOException ie) {
			LOGGER.error(ie.getMessage());
			throw ie;
		}
	}
	
	public void download(Collection<String> downloadUrls) throws IOException{
		for(String url : downloadUrls){
			this.download(url);
		}
	}

	public String getFileName(String link) {
		counter = counter + 1;
		final Pattern DIGIT_PATTERN = Pattern.compile("(\\d+)");
		StringBuffer fileName = new StringBuffer();
		Matcher digit = DIGIT_PATTERN.matcher(link);
		if (digit.find()) {
			fileName.append(digit.group());
		}
		return fileName.append("_" + counter + ".txt").toString();
	}
	
}
