package com.paladin.hrms.crawler;

import java.io.IOException;
import java.net.URLDecoder;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;

public class FileCrawler extends HrmsCrawler {

	public FileResult getFile(String url) throws IOException {

		Connection connection = Jsoup.connect(url);
		wrapConnection(connection, false);
		setCookies(connection);
		// 执行请求
		Response response = connection.ignoreContentType(true).method(Method.GET).execute();

		String dis = response.header("Content-Disposition");
		String filename = dis.substring(dis.lastIndexOf("=") + 1);
		filename = URLDecoder.decode(filename, "utf-8");

		byte[] data = response.bodyAsBytes();

		FileResult result = new FileResult();
		result.data = data;
		result.filename = filename;
		return result;
	}

	public static class FileResult {
		private String filename;
		private byte[] data;

		public String getFilename() {
			return filename;
		}

		public void setFilename(String filename) {
			this.filename = filename;
		}

		public byte[] getData() {
			return data;
		}

		public void setData(byte[] data) {
			this.data = data;
		}

	}
}
