package com.qlikauditionproject.db.dto;

/**
 * This class is used to transfer data between MySQL database and Java web services
 * 
 * @author Richa Pandey
 *
 */
public class QlikMessageDto {

	private long id;
	private String messagetext;
	private boolean ispalindrome;
	private String createddatetime;

	public QlikMessageDto() {

	}

	public QlikMessageDto(int id, String messagetext, boolean isPalindrome, String createddatetime) {
		super();
		this.id = id;
		this.messagetext = messagetext;
		this.ispalindrome = isPalindrome;
		this.createddatetime = createddatetime;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMessagetext() {
		return messagetext;
	}

	public void setMessagetext(String messagetext) {
		this.messagetext = messagetext;
	}

	public boolean isIspalindrome() {
		return ispalindrome;
	}

	public void setIspalindrome(boolean ispalindrome) {
		this.ispalindrome = ispalindrome;
	}

	public String getCreateddatetime() {
		return createddatetime;
	}

	public void setCreateddatetime(String createddatetime) {
		this.createddatetime = createddatetime;
	}

}
