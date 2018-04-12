<%--
  Created by IntelliJ IDEA.
  User: volodya.khachatryan
  Date: 3/15/2018
  Time: 3:10 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Registration</title>
</head>
<body>
<h1>Register</h1>
<form action="registerUser" method="post">
    <label>First Name</label><br>
    <input type="text" name="firstName" required><br>
    <label>Last Name</label><br>
    <input type="text" name="lastName" required><br>
    <label>Age</label><br>
    <input type="number" name="age" required min="0"><br>
    <label>Username</label><br>
    <input type="text" name="username" required><br>
    <label>Password</label><br>
    <input type="password" name="password" required><br>

    <input type="submit" value="Register">

</form>
<form action="loginPage.jsp" method="post">
    <input type="submit" value="Login">
</form>

</body>
</html>
