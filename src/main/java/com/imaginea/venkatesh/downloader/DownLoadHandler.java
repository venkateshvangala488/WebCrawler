package com.imaginea.venkatesh.downloader;

import java.io.IOException;
import java.io.InputStream;

public interface DownLoadHandler {
	public void handle(String url, InputStream inputStream, String fileName) throws IOException;
}
