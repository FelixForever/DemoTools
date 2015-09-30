package com.felix.demo;

import java.security.MessageDigest;

public class Util {
	
	/*
	 * MD5两次加密
	 */
	public final static String toMD5(final String str)
	{
		return MD5(MD5(str));
	}
	private final static String MD5(String s) {
		final char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'A', 'B', 'C', 'D', 'E', 'F' };
		try {
			byte[] btInput = s.getBytes();
			// 获得MD5摘要算法的 MessageDigest 对象
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			// 使用指定的字节更新摘要
			mdInst.update(btInput);
			// 获得密文
			byte[] md = mdInst.digest();
			// 把密文转换成十六进制的字符串形式
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			byte byte0;
			for (int i = 0; i < j; i++) {
				byte0= md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/*
	 * 获得系统唯一id
	 */
	public static String getSystemId(){
		return getSystemId("");
	}
	public static String getSystemId(final String userId) {
		StringBuilder sb = new StringBuilder();
		long time = System.currentTimeMillis();
		short mode = 0;
		while (true) {
			if (time <= 0)
				break;
			mode = (short) (time & 077);
			if (mode <= 9)
				sb.insert(0, (char) (mode + 48));
			else if (mode <= 35)
				sb.insert(0, (char) (mode + 55));
			else if (mode <= 61)
				sb.insert(0, (char) (mode + 61));
			else if (mode == 62)
				sb.insert(0, (char) (mode - 16));
			else
				sb.insert(0, (char) (mode + 32));
			time = time >> 6;
		}
		sb.insert(0, userId);
		return sb.toString();
	}
}
