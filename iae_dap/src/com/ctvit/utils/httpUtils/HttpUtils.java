package com.ctvit.utils.httpUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

/**
 * @作者 guoxiaojie@ctvit.com.cn
 * @日期 2013-4-1
 */
public class HttpUtils {
	/**
	 * 默认HTTP的Get请求
	 * 
	 * @param url
	 *            请求URL地址包含参数
	 * @return 服务器响应的结果集
	 */
	public static String httpGet(String url) {
		String returnValue = "";
		HttpClient httpClient = null;
		HttpGet gets = null;
		try {
			httpClient = new DefaultHttpClient();
			gets = new HttpGet(url);
			HttpResponse httpResponse = httpClient.execute(gets);
			HttpEntity entity = httpResponse.getEntity();
			if (entity != null) {
				InputStream inputStream = entity.getContent();
				returnValue = IOUtils.toString(inputStream);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			httpClient.getConnectionManager().shutdown();
		}
		return returnValue;
	}

	/**
	 * 默认Http的Post请求
	 * 
	 * @param url
	 *            请求的URL地址
	 * @param param
	 *            请求参数
	 * @return 服务器响应结果
	 * @throws URISyntaxException
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	public static String httpPost(String url, Map<String, String> param)
			throws URISyntaxException, ClientProtocolException, IOException {
		String returnValue = null;
		URI uri = null;
		DefaultHttpClient httpClient = null;
		HttpPost httpPost = null;
		InputStream inputStream = null;
		try {
			uri = new URI(url);
			httpClient = new DefaultHttpClient();
			httpPost = new HttpPost(uri);
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			if (param != null) {
				Set<String> keySet = param.keySet();
				for (String key : keySet) {
					params.add(new BasicNameValuePair(key, param.get(key)));
				}
			} else {
				return null;
			}
			httpPost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
			HttpResponse httpResponse = httpClient.execute(httpPost);
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				HttpEntity entity = httpResponse.getEntity();
				String charset = EntityUtils.getContentCharSet(entity);
				if (entity != null) {
					inputStream = entity.getContent();
					IOUtils.toString(inputStream, charset);
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
		} finally {
			inputStream.close();
			httpClient.getConnectionManager().shutdown();
		}
		return returnValue;
	}
}
