package com.qlikauditionproject;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Utility class for implementing helper methods used in application.
 * 
 * @author Richa Pandey
 *
 */
public class Utility {

	/**
	 * Method used to get current time stamp. 
	 * Used in setting timestamp while database operations.
	 * 
	 * @return
	 */
	public static String getCurrentTimeStamp() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());
	}

	/**
	 * Method used to detect whether a message is palindrome or not.
	 * 
	 * @param message
	 * @return
	 */
	public static boolean isPalindrome(String message) {
		if (message == null) {
			return false;
		}
		
		String reverse = "";
		int length = message.length();

		for (int i = length - 1; i >= 0; i--) {
			reverse = reverse + message.charAt(i);
		}

		if (message.equals(reverse)) {
			return true;
		} else {
			return false;
		}
	}

}
