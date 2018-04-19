<%@ page import="models.User" %>
<%@ page import="models.ShoppingCart" %>
<%@ page import="models.Item" %>
<%@ page import="java.util.Map" %>
<%--
  Created by IntelliJ IDEA.
  User: volodya.khachatryan
  Date: 4/11/2018
  Time: 6:23 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<jsp:useBean id="dao" class="dao.Dao"/>

<div style="display: block; float: right">

    <jsp:useBean id="connection" scope="application" class="dao.Dao"/>

    <%
        User user;
        user = (User) request.getSession().getAttribute("userBean");
        if (user != null) {
            out.println(user.getFirstName() + " " + user.getLastName());
        }
    %>

    <form action="signOut" method="post">
        <input type="submit" value="Sign out">
    </form>

</div>

<%
    ShoppingCart shoppingCart;
    Map<Item, Integer> addedItems;

    if (user != null) {
        shoppingCart = dao.getShoppingCart(user.getUserId());
        addedItems = shoppingCart.getAddedItems();
        if (!addedItems.isEmpty()) {
%>
<table>
    <tr>
        <th>Name</th>
        <th>Description</th>
        <th>Price</th>
        <th>Count</th>
    </tr>
    <%
        for (Map.Entry<Item, Integer> entry : addedItems.entrySet()) {
    %>
    <tr>
        <td><% out.print(entry.getKey().getItemName());%></td>
        <td><% out.print(entry.getKey().getItemDescription());%></td>
        <td><% out.print(entry.getKey().getItemPrice());%></td>
        <td><% out.print(entry.getValue());%></td>
    </tr>
    <%
            }
        } else {
            out.println("Your shopping cart is empty");
        }
    %>

</table>

<form action="workspace.jsp" method="post">
    <input type="submit" value="Go Shopping">
</form>

<%
    }
%>


</body>
</html>
