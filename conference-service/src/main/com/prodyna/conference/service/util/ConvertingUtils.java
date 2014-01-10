package com.prodyna.conference.service.util;

import java.util.ArrayList;
import java.util.List;

public class ConvertingUtils {

	private ConvertingUtils() {

	}

	public static <T> List<T> constructList(final Iterable<T> iterable) {
		List<T> list = new ArrayList<T>();
		for (T i : iterable) {
			list.add(i);
		}
		return list;
	}
}
