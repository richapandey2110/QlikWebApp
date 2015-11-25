package com.qlikauditionproject.db;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.qlikauditionproject.Utility;
import com.qlikauditionproject.db.dao.QlikMessageDao;
import com.qlikauditionproject.db.dto.QlikMessageDto;

/**
 * jUnit unit test class for Data Access Layer tests
 *
 * @author Richa Pandey
 *
 */
public class DatabaseTests {

	QlikMessageDao qlikMessageDao = null;

	@Before
	public void setUp() {
		qlikMessageDao = new QlikMessageDao();
	}

	@Test
	public void insertMessageInDatabaseTest() throws IOException {
		System.out.println("Running database insert test");

		String message = "message " + Utility.getCurrentTimeStamp();
		QlikMessageDto insertedMessageDto = qlikMessageDao.insertMessageInDatabase(message);

		assertNotNull(insertedMessageDto);
		assertTrue(insertedMessageDto.getId() > 0);
	}

	@Test
	public void deleteMessageFromDatabaseByIdTest() throws IOException {
		System.out.println("Running database deleteMessageFromDatabaseById() test");

		int messageid = 12;
		
		int deletedRecords = 0;
		deletedRecords = qlikMessageDao.deleteMessageFromDatabaseById(messageid);

		assertTrue(deletedRecords > 0);
	}

	@Test
	public void listMessageFromDatabaseTest() throws IOException {
		System.out.println("Running database listMessageFromDatabase() test");

		List<QlikMessageDto> noOfMessages = null;
		noOfMessages = qlikMessageDao.getMessagesFromDatabase();

		assertTrue(noOfMessages != null);
	}

	@Test
	public void getMessageFromDatabaseByIdTest() throws IOException {
		System.out.println("Running databasegetMessageFromDatabaseById() test");

		QlikMessageDto insertedMessage = qlikMessageDao.insertMessageInDatabase("test");
		long expectedMessageId = insertedMessage.getId();
		
		List<QlikMessageDto> qlikMessages = qlikMessageDao.getMessageFromDatabaseById(expectedMessageId);

		assertNotNull(qlikMessages);
		assertTrue(qlikMessages.size() > 0);
	}

	@Test
	public void getMessageFromDatabaseByMessageTest() throws IOException {
		System.out.println("Running databasegetMessageFromDatabaseByMessageTest() test");

		String messagetext = "POP";
		
		qlikMessageDao.insertMessageInDatabase(messagetext);

		List<QlikMessageDto> qlikMessages = qlikMessageDao.getMessageFromDatabaseByMessageText(messagetext);

		assertNotNull(qlikMessages);
		assertTrue(qlikMessages.size() > 0);
	}

}
