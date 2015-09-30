package com.felix.demo;

public class Util {
	public static String getSystemId(String userId) {
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
