<!DOCTYPE html>
<html lang="en">
<head>
<title>Qlik Audition Project</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</head>
<body>

	<div class="container">
		<div class="page-header" style="margin-top: 20px; padding: 0px">
			<h2>Qlik Audition Project</h2>
		</div>

		<div class="well well-sm">
			<fieldset>
				<legend>Submit Message Form</legend>
				<form id="submitmessageform" class="form-horizontal" role="form">
					<div class="form-group">
						<label class="control-label col-sm-2" for="message">Message:</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="submitMessageTextBox"
								placeholder="Enter message">
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<button id="submitmessagebutton" type="button"
								class="btn btn-default">Submit</button>
						</div>
					</div>
				</form>

			</fieldset>
		</div>

		<h4>Messages</h4>
		<hr style="margin-top: 5px; margin-bottom: 10px;" />
		<form class="form-inline pull-left" role="form">
			<div class="form-group">
				<label for="searchMessageTextBox">Search:</label>&nbsp; <input
					type="text" class="form-control" id="searchMessageTextBox"
					placeholder="Enter message">
				<button id="submitsearchbutton" type="button"
					class="btn btn-default">Search</button>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<label id="searchKeyworkLabel"></label>
			</div>
		</form>
		<div class="pull-right">
			<button id="refreshbutton" type="button" class="btn btn-default">Refresh
				Messages</button>
		</div>
		<table class="table table-hover" style="padding-top: 15px;">
			<thead>
				<tr>
					<th>Id</th>
					<th>Message</th>
					<th>Palindrome</th>
					<th>Created Date</th>
					<th>Delete</th>
				</tr>
			</thead>
			<tbody id="messagestablebody">
				<!-- Sample message -->
				<!--  
				<tr>
					<td>1</td>
					<td>Madam</td>
					<td>No</td>
					<td>16-11-2015 07:10:00</td>
					<td><button type="button" class="btn btn-danger"
							data-toggle="modal" data-target="#deleteModalId">Delete</button></td>
				</tr>
				-->
			</tbody>
		</table>

		<!-- Submit Message Model code here -->
		<div id="applicationMessageModal" class="modal fade" role="dialog">
			<div class="modal-dialog">

				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">Qlik Application Message</h4>
					</div>
					<div class="modal-body">
						<p id="applicationMessageModalMessageArea"></p>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script src="./js/qlikjs.js"></script>
</body>
</html>