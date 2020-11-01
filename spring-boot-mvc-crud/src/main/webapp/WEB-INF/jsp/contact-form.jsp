<%--
  Created by IntelliJ IDEA.
  User: Jagdeep Jain
  Date: 10/16/20
  Time: 11:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
  <title>Add Contact</title>
</head>
  <body>
  <h1>Contact Management</h1>
  <h2>Add Contact</h2>
  <form:form action="save" modelAttribute="contact" method="POST">
    <form:hidden path="id" />
    <table>
    <tbody>
    <tr>
      <td>
        <label>First Name: </label>
      </td>
      <td>
        <form:input path="firstName"/>
      </td>
    </tr>

    <tr>
      <td>
        <label>Last Name: </label>
      </td>
      <td>
        <form:input path="lastName"/>
      </td>
    </tr>

    <tr>
      <td>
        <label>Email: </label>
      </td>
      <td>
        <form:input path="email"/>
      </td>
    </tr>

    <tr>
      <td>
      </td>
      <td>
        <input type="submit" value="Save"/>
      </td>
    </tr>

    </tbody>
  </table>
  </form:form>

  <form:form action="${pageContext.request.contextPath}/logout" method="POST">
    <input type="submit" value="Logout" />
  </form:form>

</body>
</html>
