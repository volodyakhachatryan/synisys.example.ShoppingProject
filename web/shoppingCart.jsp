<%@ page import="models.User" %>
<%@ page import="models.ShoppingCart" %>
<%@ page import="models.Item" %>
<%@ page import="java.util.Map" %><%--
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

<%
    ShoppingCart shoppingCart;
    Map<Item, Integer> addItems;

    User user = (User) session.getAttribute("userBean");
    if (user != null) {
        shoppingCart = user.getShoppingCart();
        addItems = shoppingCart.getAddedItems();
        if (!addItems.isEmpty()) {
%>
<table>
    <tr>
        <th>Name</th>
        <th>Description</th>
        <th>Price</th>
        <th>Count</th>
    </tr>
    <%
        for (Map.Entry<Item, Integer> entry : addItems.entrySet()) {
    %>
    <tr>
        <th><% out.print(entry.getKey().getItemName());%></th>
        <th><% out.print(entry.getKey().getItemDescription());%></th>
        <th><% out.print(entry.getKey().getItemPrice());%></th>
        <th><% out.print(entry.getValue());%></th>
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
