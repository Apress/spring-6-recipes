<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<title>Reservation Query</title>
</head>

<body>
<div>
	<form method="post">
		<label for="courtName">Court Name</label>
		<input type="text" name="courtName" value="${courtName}"/>
		<input type="submit" value="Query"/>
	</form>
</div>
<div>
	<table style="border: 1px black;">
		<tr>
			<th>Court Name</th>
			<th>Date</th>
			<th>Hour</th>
			<th>Player</th>
		</tr>
		<c:forEach items="${reservations}" var="reservation">
			<tr>
				<td>${reservation.courtName}</td>
				<td>${reservation.date}</td>
				<td>${reservation.hour}</td>
				<td>${reservation.player.name}</td>
			</tr>
		</c:forEach>
	</table>
</div>
</body>
</html>
