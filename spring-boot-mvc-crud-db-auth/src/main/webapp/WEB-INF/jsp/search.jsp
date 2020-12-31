<%--
  Created by IntelliJ IDEA.
  User: Jagdeep Jain
  Date: 10/17/20
  Time: 08:42 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
  <title>Contact List</title>
</head>
<body>
  <h1>Contact Management</h1>

  <input type="button" value="Add Contact" onclick="window.location.href='${pageContext.request.contextPath}/contact/addContactForm'; return false;"/>
  <br>
  <form:form action="search" modelAttribute="contact" method="POST">
    <table>
      <tbody>
      <tr>
        <td>
          <label>Search Contact: </label>
        </td>
        <td>
          <input type="text" name="firstName">
        </td>
        <td>
          <input type="submit" value="Search"/>
        </td>
      </tr>
      </tbody>
    </table>
  </form:form>

  <table border="1">
    <thead bgcolor="gray">
      <tr>
      <th>First Name</th>
      <th>Last Name</th>
      <th>Email</th>
        <th>Action</th>
      </tr>
    </thead>

    <c:forEach var = "tcontact" items="${contact}">

      <c:url var="updateLink" value="${pageContext.request.contextPath}/contact/updateForm">
        <c:param name="id" value="${tcontact.id}"/>
      </c:url>

      <c:url var="deleteLink" value="${pageContext.request.contextPath}/contact/delete">
        <c:param name="id" value="${tcontact.id}"/>
      </c:url>

      <tr>
        <td> ${tcontact.firstName}</td>
        <td> ${tcontact.lastName}</td>
        <td> ${tcontact.email}</td>
        <td>
          <a href="${updateLink}">Update</a>
          <a href="${deleteLink}" onClick="if (!(confirm('Sure Delete?'))) return false">Delete</a>
        </td>
      </tr>
    </c:forEach>
  </table>

  <form:form action="${pageContext.request.contextPath}/logout" method="POST">
    <input type="submit" value="Logout" />
  </form:form>

</body>
</html>
