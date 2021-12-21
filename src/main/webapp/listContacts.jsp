<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Contact List</title>
</head>
<body>
	<table border=1>
		<thead>
			<tr>
				<th>Contact ID</th>
				<th>Name</th>
				<th>Email</th>
				<th>Phone</th>
				<th colspan=2>Action</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${listContact}" var="contact">
				<tr>
					<td><c:out value="${contact.id}"/></td>
					<td><c:out value="${contact.name}"/></td>
					<td><c:out value="${contact.email}"/></td>
					<td><c:out value="${contact.phone}"/></td>
					<td><a href="edit?id=<c:out value="${contact.id}"/>">Update</a></td>
					<td><a href="delete?id=<c:out value="${contact.id}"/>">Delete</a></td>

				</tr>
			</c:forEach>
		</tbody>
	</table>
	<p><a href="<%=request.getContextPath()%>/new">Add
					New User</a></p>
</body>
</html>