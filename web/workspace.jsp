<%--
  Created by IntelliJ IDEA.
  User: volodya.khachatryan
  Date: 3/15/2018
  Time: 4:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.Objects" %>
<%@ page import="models.User" %>
<%@ page import="dao.Dao" %>
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

    <jsp:useBean id="connection" scope="application" class="dao.Dao"/>

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



<%if (user != null && "user".equals(user.getRole())) {%>

<div style="float: left">

    <table>
        <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Description</th>
            <th>Price</th>
            <th>Count</th>
            <th></th>
        </tr>

        <%
            List<Item> itemList = connection.getItems();
            Item item;
            int i;

            for (i = 0; i < itemList.size(); i++) {
                item = itemList.get(i);
                int id = item.getItemId();
                String name = item.getItemName();
                String desc = item.getItemDescription();
                int price = item.getItemPrice();
        %>
        <tr>
            <td><%out.print(id); %></td>
            <td><%out.print(name); %></td>
            <td><%out.print(desc);%></td>
            <td><%out.print(price);%></td>
            <td><input name="itemCount" type="number" min="1" style="width: 50px" value="1" required></td>
            <td>
                <button onclick="addItem(this)">Add to Cart</button>
            </td>
        </tr>
        <%
            }
        %>
    </table>
</div>


<script>
    var rowIndex = 0;

    /**
     * adds the item to the table to be saved with the given count
     * @param node row of the item list that needs to be added
     */
    function addItem(node) {
        var tr = document.createElement("tr");
        var table = document.getElementById("addedItems");
        var id = node.parentNode.parentNode.childNodes[1].innerText;
        var name = node.parentNode.parentNode.childNodes[3].innerText;
        var desc = node.parentNode.parentNode.childNodes[5].innerText;
        var price = node.parentNode.parentNode.childNodes[7].innerText;
        var count = node.parentNode.parentNode.childNodes[9].firstChild.value;

        var td1 = document.createElement("td");
        var td2 = document.createElement("td");
        var td3 = document.createElement("td");
        var td4 = document.createElement("td");
        var td5 = document.createElement("td");

        td1.appendChild(document.createTextNode(id));
        td2.appendChild(document.createTextNode(name));
        td3.appendChild(document.createTextNode(desc));
        td4.appendChild(document.createTextNode(price));
        td5.appendChild(document.createTextNode(count));

        var hidden = document.createElement("input");
        hidden.setAttribute("type", "hidden");
        hidden.setAttribute("value", id);
        hidden.setAttribute("name", "item" + rowIndex + "Id");

        var hidden2 = document.createElement("input");
        hidden2.setAttribute("type", "hidden");
        hidden2.setAttribute("value", count);
        hidden2.setAttribute("name", "item" + rowIndex + "Count");

        tr.appendChild(hidden);
        tr.appendChild(hidden2);

        tr.appendChild(td1);
        tr.appendChild(td2);
        tr.appendChild(td3);
        tr.appendChild(td4);
        tr.appendChild(td5);

        table.firstElementChild.appendChild(tr);
        rowIndex++;
    }
</script>

<div style="float: left; margin-left:15%">
    <form action="saveShoppingCart" method="post">
        <table id="addedItems">
            <tr>
                <th>Id</th>
                <th>Name</th>
                <th>Description</th>
                <th>Price</th>
                <th>Count</th>
                <th></th>
            </tr>
        </table>
        <input type="submit" value="Save Cart">
    </form>

</div>

<%}%>


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
