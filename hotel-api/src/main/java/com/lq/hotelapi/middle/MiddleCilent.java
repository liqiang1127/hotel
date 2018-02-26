package com.lq.hotelapi.middle;

import com.lq.webUtils.HttpHelper;

public class MiddleCilent {
	public static String sendHttpJsonRequest(String functionUrl,String JsonString){
		return HttpHelper.sendHttpJsonRequest(functionUrl, JsonString);
	}
}
