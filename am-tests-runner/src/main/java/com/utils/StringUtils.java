package com.utils;

public class StringUtils {

	public static String arrayToString(String[] array, String splitter) {
		StringBuilder build = new StringBuilder();
		for (String s : array) {
			build.append(s);
			build.append(splitter);
		}
		return build.toString();
	}

}
