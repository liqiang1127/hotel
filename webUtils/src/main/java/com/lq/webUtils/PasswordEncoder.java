package com.lq.webUtils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.alibaba.fastjson.util.Base64;

import sun.misc.BASE64Encoder;

public class PasswordEncoder {
	public static String passwordEncode(String password){
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			BASE64Encoder encode = new BASE64Encoder();
			return encode.encode(md5.digest(password.getBytes("utf-8")));
		} catch (Exception e) {
			return "";
		}
	}
}
