/**
 * Javascript library for QlikWebApp
 */

$(document).ready(function() {
	
	// Prevent form submission on enter key
	$(window).keydown(function(event){
		if(event.keyCode == 13) {
			event.preventDefault();
			return false;
		}
	});
	
	// Submit button click event
	$("#submitmessagebutton").bind("click", function() {
		var submitMessageTextBox = $('#submitMessageTextBox');
		var message = jQuery.trim(submitMessageTextBox.val());

		// Validate message text box
		if (message.length > 0) {
			$.ajax({
				type : 'GET',
				url : "submitmessage?message=" + message,
				contentType : "application/json",
				success : function(data) {
					var response = $.parseJSON(data);//parse JSON
					var status = response.status;
					
					if(status == "SUCCESS") {
						showApplicationModal('Message successfully entered intodatabase. <br>' 
								+ 'Message id: ' + response.submittedQlikMessage.id);
						clearSearchFilter();
						loadMessages();
					} else if(status == "ERROR") {
						showApplicationModal('Something went wrong in submitting your message. <br>' 
								+ 'Error: ' + response.submittedQlikMessage.error);
					}
				},
				error : function(e) {
					showApplicationModal('Some error occured in the application. Please try again later.');
				}
			});

		} else {
			showApplicationModal('Please enter message.');
		}
	});
	
	$("#submitsearchbutton").bind("click", function() {
		var searchMessageTextBox = $('#searchMessageTextBox');
		var message = jQuery.trim(searchMessageTextBox.val());

		// Validate message text box
		if (message.length > 0) {
			$.ajax({
				type : 'GET',
				url : "getmessage?queryby=message&message=" + message,
				contentType : "application/json",
				success : function(data) {
					var response = $.parseJSON(data);//parse JSON
					var status = response.status;
					
					if(status == "SUCCESS") {
						var totalmessages = response.totalmessages;
						// Populate list of all messages in table
						if (totalmessages > 0) {
							setSearchFilter(message);
							populateMessagesOnPage(response.qlikmessages);
						} else {
							showApplicationModal('No messages found in database!');
						}
					} else if(status == "ERROR") {
						showApplicationModal('Something went wrong in listing the messages. <br>' 
								+ 'Error: ' + response.submittedQlikMessage.error);
					}
				},
				error : function(e) {
					showApplicationModal('Some error occured in the application. Please try again later.');
				}
			});
		} else {
			showApplicationModal('Please enter message.');
		}
	});
	
	// Refresh button click
	$("#refreshbutton").bind("click", function() {
		// Get all messages from database by listmessages web service
		clearSearchFilter();
		loadMessages();
	});
	
	loadMessages();
});

function searchMessage(messagetext) {
	$.ajax({
		type : 'GET',
		url : "getmessage?queryby=message&message=" + messagetext,
		contentType : "application/json",
		success : function(data) {
			var response = $.parseJSON(data);//parse JSON
			var status = response.status;
			
			if(status == "SUCCESS") {
				var totalmessages = response.totalmessages;
				// Populate list of all messages in table
				if (totalmessages > 0) {
					
					populateMessagesOnPage(response.qlikmessages);
				} else {
					showApplicationModal('No messages found in database!');
				}
			} else if(status == "ERROR") {
				showApplicationModal('Something went wrong in listing the messages. <br>' 
						+ 'Error: ' + response.submittedQlikMessage.error);
			}
		},
		error : function(e) {
			showApplicationModal('Some error occured in the application. Please try again later.');
		}
	});
}

function loadMessages() {
	$.ajax({
		type : 'GET',
		url : "listmessages",
		contentType : "application/json",
		success : function(data) {
			var response = $.parseJSON(data);//parse JSON
			var status = response.status;
			
			if(status == "SUCCESS") {
				var totalmessages = response.totalmessages;
				// Populate list of all messages in table
				if (totalmessages > 0) {
					populateMessagesOnPage(response.qlikmessages);
				} else {
					showApplicationModal('No messages found in database!');
				}
			} else if(status == "ERROR") {
				showApplicationModal('Something went wrong in listing the messages. <br>' 
						+ 'Error: ' + response.submittedQlikMessage.error);
			}
		},
		error : function(e) {
			showApplicationModal('Some error occured in the application. Please try again later.');
		}
	});
}

function populateMessagesOnPage(messages) {
	var messagestablebody = $('#messagestablebody');
	messagestablebody.empty();
	
	for(var message in messages) {
	     var id = messages[message].id;
	     var messagetext = messages[message].messagetext;
	     var ispalindrome = messages[message].ispalindrome;
	     var createddatetime = messages[message].createddatetime;
	     
	     var messagerow = '<tr id="message-' + id + '">' + 
		    		 '<td>' + id + '</td>' + 
		    		 '<td>' + messagetext + '</td>' + 
		    		 '<td>' + ispalindrome + '</td>' + 
		    		 '<td>' + createddatetime + '</td>' +
		    		 '<td><button type="button" class="btn btn-danger" onclick="deleteMessage(\'' + id + '\');">Delete</button></td>' +
	    		 '</tr>';
	     messagestablebody.append(messagerow);
	     console.log(messagerow);
	}
}

function deleteMessage(id) {
	$.ajax({
		type : 'GET',
		url : "deletemessage?queryby=id&id=" + id,
		contentType : "application/json",
		success : function(data) {
			var response = $.parseJSON(data);//parse JSON
			var status = response.status;
			
			if(status == "SUCCESS") {
				var messagesdeleted = response.messagesdeleted;
				// Populate list of all messages in table
				if (messagesdeleted > 0) {
					$('#message-' + id).remove();
					showApplicationModal('Message deleted with id ' + id);
				} else {
					showApplicationModal('No messages found in database!');
				}
			} else if(status == "ERROR") {
				showApplicationModal('Something went wrong in listing the messages. <br>' 
						+ 'Error: ' + response.error);
			}
		},
		error : function(e) {
			showApplicationModal('Some error occured in the application. Please try again later.');
		}
	});
}

function setSearchFilter(keyword) {
	$('#searchKeyworkLabel').empty();
	$('#searchKeyworkLabel').append("Search filter set for keyword: " + keyword);
}

function clearSearchFilter() {
	$('#searchKeyworkLabel').empty();
}

function showApplicationModal(message) {
	$('#applicationMessageModalMessageArea').empty();
	$('#applicationMessageModalMessageArea').append(message);
	$("#applicationMessageModal").modal();
}