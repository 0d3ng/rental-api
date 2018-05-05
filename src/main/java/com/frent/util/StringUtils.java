/**
 * @author odeng
 *
 * Copyright © 2017 All Rights Reserved
 *
 */
package com.frent.util;

import java.util.Date;

public class StringUtils {

	public static long substractTime(Date date, Date date2) {
		long diff = date2.getTime() - date.getTime();
		return diff / 1000;
	}

}
