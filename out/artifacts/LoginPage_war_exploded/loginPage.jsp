<%--
  Created by IntelliJ IDEA.
  User: volodya.khachatryan
  Date: 3/15/2018
  Time: 3:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<h1>Login</h1>

<form action="checkLoginPassword" method="post">
    <label>Username</label><br>
    <input type="text" name="username"><br>
    <label>Password</label><br>
    <input type="password" name="password"><br>
    <input type="submit" value="Login">
</form>
<form action="registrationPage.jsp">
    <input type="submit" value = "Register">
</form>
</body>
</html>
