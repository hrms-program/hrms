package com.paladin.hrms.crawler;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.web.util.HtmlUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.paladin.framework.utils.StringUtil;

public class HrmsCrawler {

	private final static String base_url = "http://58.213.112.246/rlzy";

	private final static String login_url = "http://58.213.112.246/rlzy/security/login";
	private final static String user_name = "320583";
	private final static String password = "ks863596";

	private static Map<String, String> cookies;

	/**
	 * 登录获取cookies
	 * 
	 * @throws IOException
	 */
	public void login() throws IOException {
		Connection connection = Jsoup.connect(login_url);

		wrapConnection(connection, false);
		// 携带登陆信息
		connection.data("loginname", user_name).data("password", password).data("loginfrom", "login2").data("login", "on");

		// 请求url获取响应信息
		Response res = connection.ignoreContentType(true).method(Method.POST).execute();// 执行请求
		// 获取返回的cookie
		cookies = res.cookies();
	}

	/**
	 * 包裹连接
	 */
	protected void wrapConnection(Connection connection, boolean isAjax) {
		// 伪造请求头
		connection.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
				.header("Accept-Encoding", "gzip, deflate").header("Accept-Language", "zh-CN,zh;q=0.9").header("Cache-Control", "max-age=0")
				.header("Connection", "keep-alive").header("Content-Length", "60").header("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
				.header("Host", "58.213.112.246")
				.header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.80 Safari/537.36");

		if (isAjax) {
			connection.header("X-Requested-With", "XMLHttpRequest");
		}
	}

	public Map<String, String> getCookies() throws IOException
	{
		if(cookies == null) {
			synchronized(HrmsCrawler.class) {
				if(cookies == null) {
					login();				
				}
			}
		}
		
		return cookies;
	}

	/**
	 * 设置cookies
	 * 
	 * @param connection
	 * @throws IOException
	 */
	protected void setCookies(Connection connection) throws IOException {
		connection.cookies(getCookies());
	}

	public Document getDocument(String url, Map<String, String> data, Method method, boolean isAjax) throws IOException {
		Response res = getResponse(url, data, method, isAjax);
		return Jsoup.parse(res.body(), base_url);
	}

	public Response getResponse(String url, Map<String, String> data, Method method, boolean isAjax) throws IOException {
		Connection connection = Jsoup.connect(url);
		wrapConnection(connection, isAjax);
		setCookies(connection);

		if (data != null) {
			connection.data(data);
		}
		// 执行请求
		return connection.ignoreContentType(true).method(method).execute();
	}

	private ObjectMapper objectMapper = new ObjectMapper();

	@SuppressWarnings("unchecked")
	public Map<String, Object> getJson(String url, Map<String, String> data, Method method, boolean isAjax) throws IOException {
		Response res = getResponse(url, data, method, isAjax);
		return objectMapper.readValue(res.body(), Map.class);
	}

	/**
	 * 修复html文本
	 * 
	 * @param text
	 * @return
	 */
	public String repairHtmlText(String text) {
		if (text != null && text.length() > 0) {
			text = HtmlUtils.htmlUnescape(text);
			text = StringUtil.strongTrim(text);
		}

		return text;
	}

	public Date toDate(String data) {
		if (StringUtil.isEmpty(data)) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return sdf.parse(data);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

}
