package com.imaginea.venkatesh.apacheconfig;

public class ApacheCrawlerConfig {
	private String yearHrefPattern = "\\s*{year}\\d{2}.mbox/thread";
	private String paginationPattern =  yearHrefPattern + "?";
	private String mailHrefPattern = "\\s*({year}\\d{2}.mbox/%3c)|({year}\\d{2}.mbox/ajax/%3c)";
	
	public String getYearHrefPattern() {
		return yearHrefPattern;
	}
	
	public void setYearHrefPattern(String yearHrefPattern) {
		this.yearHrefPattern = yearHrefPattern;
	}
	
	public String getPaginationPattern() {
		return paginationPattern;
	}
	
	public void setPaginationPattern(String paginationPattern) {
		this.paginationPattern = paginationPattern;
	}
	
	public String getMailHrefPattern() {
		return mailHrefPattern;
	}
	
	public void setMailHrefPattern(String mailHrefPattern) {
		this.mailHrefPattern = mailHrefPattern;
	}
}
