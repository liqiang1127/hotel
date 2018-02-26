package com.lq.webUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSON;

public class HttpHelper {
	// 发送json请求的工具，并且接受到返回的数据
	public static String sendHttpJsonRequest(String requestUrl, String JsonStr) {
		Map<String, Object> resMap = new HashMap<>();
		StringBuffer buffer = new StringBuffer();
		InputStream inputStream = null;
		String jsonString = null;
		try {
			URL url = new URL(requestUrl);
			HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();
			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			httpUrlConn.setInstanceFollowRedirects(true);
			httpUrlConn.setRequestProperty("Content-Type", "application/json");
			httpUrlConn.setRequestMethod("POST");
			httpUrlConn.connect();
			// 当有数据需要提交时
			if (null != JsonStr) {
				OutputStream outputStream = httpUrlConn.getOutputStream();
				// 注意编码格式，防止中文乱码
				outputStream.write(JsonStr.getBytes("UTF-8"));
				outputStream.close();
			}
			// 将返回的输入流转换成字符串
			inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// 释放资源
			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();
			jsonString = buffer.toString();
		} catch (ConnectException ce) {
			resMap.put("errorNo",-1);
			resMap.put("errorInfo", "连接错误");
			return JSON.toJSONString(resMap);
		} catch (IOException e) {
			resMap.put("errorNo", -2);
			resMap.put("errorInfo", "读写错误");
			return JSON.toJSONString(resMap);
		} finally {
			try {
				if (inputStream != null) {
					inputStream.close();
				}
			} catch (IOException e) {
				resMap.put("errorNo", -2);
				resMap.put("errorInfo", "读写错误");
				return JSON.toJSONString(resMap);
			}
		}
		return jsonString;
	}

	// 服务器接收Json的工具
	public static String getRequestJsonString(HttpServletRequest request) {
		String submitMehtod = request.getMethod();
		// GET目前不支持
		if (submitMehtod.equals("GET")) {
			return null;
			// POST
		} else {
			StringBuffer buffer = new StringBuffer();
			try {
				ServletInputStream inputStream = request.getInputStream();
				InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
				BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
				String str = null;
				while ((str = bufferedReader.readLine()) != null) {
					buffer.append(str);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			return buffer.toString();
		}
	}
}
