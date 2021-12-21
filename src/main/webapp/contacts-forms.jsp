<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Contact Form</title>
</head>
<body>
    <header>
	    <ul>
	    	<li>
	    		<a href="<%=request.getContextPath()%>/list">
	    			Contacts
	    		</a>
	    	</li>
	    </ul>
    </header>
    <br>
    <div>
    	<div>
    		<c:if test="${contact != null}">
    			<form action="update" method="post">
    		</c:if>
    		<c:if test="${contact == null}">
				<form action="insert" method="post">
			</c:if>
			<caption>
				<h2>
					<c:if test="${contact != null}">
            			Edit Contact
            		</c:if>
					<c:if test="${contact == null}">
            			Add New Contact
            		</c:if>
				</h2>
			</caption>
			<c:if test="${contact != null}">
					<input type="hidden" name="id" value="<c:out value="${contact.id}" />" />
			</c:if>
			<fieldset class="form-group">
					<label>Contact Name</label> <input type="text"
						value="<c:out value='${contact.name}' />"
						name="name" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>Contact Email</label> <input type="email"
						value="<c:out value='${contact.email}' />"
						name="email" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>Phone</label> <input type="text"
						value="<c:out value='${contact.phone}' />" 
						name="phone" required="required">
				</fieldset>

				<button type="submit">Save</button>
				</form>

    	</div>
    </div>
</body>
</html>