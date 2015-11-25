package com.qlikauditionproject.db.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;
import com.qlikauditionproject.Utility;
import com.qlikauditionproject.db.dto.QlikMessageDto;

/**
 * Data Access Object layer for database query transactions.
 *
 * @author Richa Pandey
 *
 */
public class QlikMessageDao {
	
	/**
	 * Database constants
	 */
	String url = "jdbc:mysql://localhost:3306/QlikAuditionDB";
	String user = "richa";
	String password = "richa";
	
	final String sql_insertMessage = "INSERT INTO QlikMessage(messagetext,ispalindrome,createddatetime) values(?, ?, current_timestamp())";
	final String sql_getMessagesFromDatabase = "SELECT * FROM QlikMessage ORDER BY ID DESC";
	final String sql_getMessageFromDatabaseById = "SELECT * FROM QlikMessage WHERE id = ? ORDER BY ID DESC";
	final String sql_getMessageFromDatabaseByMessageText = "SELECT * FROM QlikMessage WHERE BINARY messagetext LIKE ? ORDER BY ID DESC";
	final String sql_deleteMessageFromDatabaseById = "DELETE FROM QlikMessage WHERE id = ?";
	final String sql_deleteMessageFromDatabaseByMessageText = "DELETE FROM QlikMessage WHERE BINARY messagetext LIKE ?";

	public QlikMessageDao() {
	}
	
	/**
	 * Method to insert message in database.
	 * @param message
	 * @return
	 */
	public QlikMessageDto insertMessageInDatabase(String message) {
		QlikMessageDto qlikMessage = null;

		long insertedRowId = 0;

		try {
			Class.forName("com.mysql.jdbc.Driver");

			Connection conn = DriverManager.getConnection(url, user, password);

			PreparedStatement statement = conn.prepareStatement(sql_insertMessage, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, message);
			statement.setBoolean(2, Utility.isPalindrome(message));

			int rowsInserted = statement.executeUpdate();
			if (rowsInserted > 0) {
				qlikMessage = new QlikMessageDto();
				qlikMessage.setMessagetext(message);
				qlikMessage.setIspalindrome(Utility.isPalindrome(message));
			}

			// Get inserted row id
			ResultSet rs = statement.getGeneratedKeys();
			if (rs.next()) {
				insertedRowId = rs.getInt(1);
				qlikMessage.setId(insertedRowId);
			}
			conn.close();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return qlikMessage;
	}

	/**
	 * This method returns the Message table row by id
	 * 
	 * @param id
	 * @return
	 */
	public ArrayList<QlikMessageDto> getMessageFromDatabaseById(long messageid) {
		ArrayList<QlikMessageDto> qlikMessages = new ArrayList<QlikMessageDto>();

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, user, password);

			PreparedStatement statement = conn.prepareStatement(sql_getMessageFromDatabaseById, Statement.RETURN_GENERATED_KEYS);
			statement.setLong(1, messageid);
			
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String messagetext = rs.getString("messagetext");
				boolean ispalindrome = rs.getBoolean("ispalindrome");
				String createddatetime = rs.getString("createddatetime");

				qlikMessages.add(new QlikMessageDto(id, messagetext, ispalindrome, createddatetime));
			}
			conn.close();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return qlikMessages;
	}

	/**
	 * This method returns the Message table row by message text
	 * 
	 * @param message
	 * @return
	 */
	public ArrayList<QlikMessageDto> getMessageFromDatabaseByMessageText(String message) {
		ArrayList<QlikMessageDto> qlikMessages = new ArrayList<QlikMessageDto>();

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, user, password);

			PreparedStatement statement = conn.prepareStatement(sql_getMessageFromDatabaseByMessageText, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, message);
			
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String messagetext = rs.getString("messagetext");
				boolean ispalindrome = rs.getBoolean("ispalindrome");
				String createddatetime = rs.getString("createddatetime");

				qlikMessages.add(new QlikMessageDto(id, messagetext, ispalindrome, createddatetime));
			}
			conn.close();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return qlikMessages;
	}

	/**
	 * Method to get all messages from database.
	 * 
	 * @return
	 */
	public List<QlikMessageDto> getMessagesFromDatabase() {
		ArrayList<QlikMessageDto> qlikMessages = new ArrayList<QlikMessageDto>();

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, user, password);

			PreparedStatement statement = conn.prepareStatement(sql_getMessagesFromDatabase, Statement.RETURN_GENERATED_KEYS);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String messagetext = rs.getString("messagetext");
				boolean ispalindrome = rs.getBoolean("ispalindrome");
				String createddatetime = rs.getString("createddatetime");

				qlikMessages.add(new QlikMessageDto(id, messagetext, ispalindrome, createddatetime));
			}
			conn.close();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return qlikMessages;
	}
	
	/**
	 * This method returns the Message table row by id
	 * 
	 * @param id
	 * @return
	 */
	public int deleteMessageFromDatabaseById(long id) {
		int recordsDeleted = 0;

		try {
			Class.forName("com.mysql.jdbc.Driver");

			Connection conn = DriverManager.getConnection(url, user, password);

			PreparedStatement statement = conn.prepareStatement(sql_deleteMessageFromDatabaseById, Statement.RETURN_GENERATED_KEYS);
			statement.setLong(1, id);

			recordsDeleted = statement.executeUpdate();
			conn.close();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return recordsDeleted;
	}

	/**
	 * This method returns the Message table row by message text
	 * 
	 * @param message
	 * @return
	 */
	public int deleteMessageFromDatabaseByMessageText(String message) {
		int recordsDeleted = 0;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, user, password);

			PreparedStatement statement = conn.prepareStatement(sql_deleteMessageFromDatabaseByMessageText, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, message);

			recordsDeleted = statement.executeUpdate();
			conn.close();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return recordsDeleted;
	}

}
