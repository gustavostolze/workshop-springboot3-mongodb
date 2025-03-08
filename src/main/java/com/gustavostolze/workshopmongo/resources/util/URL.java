package com.gustavostolze.workshopmongo.resources.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class URL {

	public static String decodeParam(String text) {
		try {
			return URLDecoder.decode(text, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}

	public static Instant decodeDate(String date, Instant defaultValue) {
		try {
			date = date + " 00:00";
			DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
			Instant instant = LocalDateTime.parse(date, fmt).atZone(ZoneId.of("GMT")).toInstant();
			return instant;
		} catch (DateTimeParseException e) {
			return defaultValue;
		}
	}
}
