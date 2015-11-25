package com.qlikauditionproject;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Test class for Utility
 * 
 * @author Richa Pandey
 *
 */
public class UtilityTest {
		
	@Test
	public void testIsNotPalindrome() {
		String message = "richa";
		
		boolean expected = false;
		boolean actual = Utility.isPalindrome(message);
		
		assertEquals("String is palindrome.", expected, actual);
	}
	
	@Test
	public void testCaseSensitiveIsNotPalindrome() {
		String message = "mOM";
		
		boolean expected = false;
		boolean actual = Utility.isPalindrome(message);
		
		assertEquals("String is palindrome.", expected, actual);
	}
	
	@Test
	public void testIsPalindrome() {
		String message = "mom";
		
		boolean expected = true;
		boolean actual = Utility.isPalindrome(message);
		
		assertEquals("String is palindrome.", expected, actual);
	}

}
