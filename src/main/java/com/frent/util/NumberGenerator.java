/**
 * @author odeng
 *
 * Copyright © 2017 All Rights Reserved
 *
 */
package com.frent.util;

import java.util.Random;

public class NumberGenerator {

	final static String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
	
	public static int genreateNumeric() {
		Random random = new Random(System.currentTimeMillis());
		return ((1 + random.nextInt(2)) * 10000000 + random.nextInt(10000000));
	}
	
	public static String getSaltString(int length) {
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < length) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;
    }
	
	public static int genreateNumeric(int cen) {
		String numberString = "1";
		for (int i = 0; i < cen; i++) {
			numberString += "0";
		}
		Random random = new Random(System.currentTimeMillis());
		return ((1 + random.nextInt(2)) * (Integer.parseInt(numberString)) + random.nextInt(Integer.parseInt(numberString)));
	}

	public static void main(String[] strings) {
		int result = genreateNumeric();
		System.out.println(genreateNumeric(2));
		System.out.println(result);
		System.out.println(Integer.toHexString(result));
		System.out.println(String.format("%08x", result).toUpperCase());
	}

}
