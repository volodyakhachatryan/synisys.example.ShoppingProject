<%--
  Created by IntelliJ IDEA.
  User: volodya.khachatryan
  Date: 3/15/2018
  Time: 4:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="java.util.Objects" %>
<%@ page import="models.User" %>
<%@ page import="dao.DatabaseConnection" %>
<%@ page import="java.util.List" %>
<%@ page import="models.Item" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>


<html>
<head>
    <title>Home</title>
</head>
<body>


<div style="display: block; float: right">


    <%
        User user;
        user = (User) request.getSession().getAttribute("userBean");
         if (user != null) {
            out.println(user.getFirstName() + " " + user.getLastName());
        }
    %>

    <%if (user != null && "user".equals(user.getRole())) {%>
    <form action="shoppingCart.jsp" method="post">
        <input type="submit" value="Shopping Cart">
    </form>
    <% } %>

    <form action="signOut" method="post">
        <input type="submit" value="Sign out">
    </form>
</div>


<%--<c:set var="admin" scope="session" value="admin"/>--%>
<%--<c:if test="${admin.equals(user.getRole())}"/>--%>

<%if (user != null && "user".equals(user.getRole())) {%>
<table>
    <tr>
        <th>Name</th>
        <th>Description</th>
        <th>Price</th>
        <th>Count</th>
        <th></th>
    </tr>

    <%
        List<Item> itemList = DatabaseConnection.getItems();
        for (Item item : itemList) {
            String name = item.getItemName();
            String desc = item.getItemDescription();
            int price = item.getItemPrice();
    %>

    <script>
        function addItem(/*name, desc, price, count*/) {

            var ajaxRequest;
            ajaxRequest = new XMLHttpRequest();

//            console.log(name);
//            console.log(desc);
//            console.log(price);
//            console.log(count);

            ajaxRequest.onreadystatechange = function () {
                if(ajaxRequest.readyState == 4){
                    ajaxRequest.setParameter("name", name);
                    ajaxRequest.setParameter("desc", desc);
                    ajaxRequest.setParameter("price", price);
                    ajaxRequest.setParameter("count", count);
                    ajaxRequest.open("post", "addItemToCart");
                    ajaxRequest.send();
                }
            }
        }
    </script>
    <tr>
        <td><%out.print(name); %></td>
        <td><%out.print(desc);%></td>
        <td><%out.print(price);%></td>
        <td><input name="itemCount" type="number" min="1" style="width: 50px" value="1" required></td>
        <td>
            <button onclick="addItem()">Add to Cart</button>
        </td>
    </tr>
    <%
            }
        }
    %>
</table>


<%if (user != null && "admin".equals(user.getRole())) {%>
<%%>
<form action="addNewItem" method="post">
    <label>Name</label><br>
    <input type="text" name="name" required><br>
    <label>Description</label><br>
    <input type="text" name="description" required><br>
    <label>Price</label><br>
    <input type="number" min="1" name="price" required><br>
    <input type="submit">
</form>

<%}%>

</body>
</html>
