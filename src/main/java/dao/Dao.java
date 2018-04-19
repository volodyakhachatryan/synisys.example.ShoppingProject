package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.Item;
import models.ShoppingCart;
import models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

//import java.sql.Connection;
//import java.sql.SQLException;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.Statement;


/**
 * Created by volodya.khachatryan on 3/19/2018.
 */
@Component
public class Dao {
    private Connection connection;

    /**
     * searches for a user with the given username and password
     *
     * @param usernameForSearch username of the user
     * @param passwordForSearch password of the user
     * @return null if username or password incorrect
     */
    public User getUser(String usernameForSearch, String passwordForSearch) {
        connection = getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM dbo.[users] WHERE username = ? AND password = ?");
            statement.setString(1, usernameForSearch);
            statement.setString(2, passwordForSearch);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int userId = resultSet.getInt("id");
                String firstName = resultSet.getString("firstname");
                String lastName = resultSet.getString("lastname");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                int age = resultSet.getInt("age");
                String role = resultSet.getString("role");

                resultSet.close();
                statement.close();

                return new User(userId, firstName, lastName, username, password, age, role);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * adds the user object to the db
     *
     * @param user the object to be added
     * @return true if the user was added or false if there was a problem
     */
    public boolean saveUser(User user) {
        connection = getConnection();

        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO dbo.[users] ( username , firstname , lastname , password , age, role) VALUES  (?,?,?,?,?,?)")) {

            statement.setString(1, user.getUsername());
            statement.setString(2, user.getFirstName());
            statement.setString(3, user.getLastName());
            statement.setString(4, user.getPassword());
            statement.setInt(5, user.getAge());
            statement.setString(6, user.getRole());

            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Exception: " + e);
            return false;
        }
    }


    /**
     * deletes the user with the given username from the db
     *
     * @param username of the user to be deleted
     */
    public void deleteUser(String username) {
        connection = getConnection();
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM dbo.[users] WHERE username=?");) {
            statement.setString(1, username);
            statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Exception: " + e);
        }
    }

    /**
     * adds an item to the db
     *
     * @param item the item object to be added
     * @return true if the item was added or false if there was a problem
     */
    public boolean addItem(Item item) {
        connection = getConnection();
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO dbo.[items] ( itemname , itemdescription , itemprice ) VALUES  (?,?,?)")) {
            statement.setString(1, item.getItemName());
            statement.setString(2, item.getItemDescription());
            statement.setInt(3, item.getItemPrice());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Exception: " + e);
            return false;
        }
    }

    /**
     * gets all the items from the db
     *
     * @return list of items
     */
    public List<Item> getItems() {
        connection = getConnection();
        List<Item> itemList = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM dbo.[items]");
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int itemId = resultSet.getInt("id");
                String itemName = resultSet.getString("itemName");
                String itemDescription = resultSet.getString("itemDescription");
                int itemPrice = resultSet.getInt("itemPrice");
                itemList.add(new Item(itemId, itemName, itemDescription, itemPrice));
            }
            return itemList;
        } catch (SQLException e) {
            return null;
        }
    }

    /**
     * gets the item with the given id from the db
     *
     * @param itemId id of the item
     * @return item if there is an item with the given id or null
     */

    public Item getItem(Integer itemId) {
        connection = getConnection();
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement("SELECT * FROM dbo.[items] WHERE id = ?");
            statement.setInt(1, itemId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String itemName = resultSet.getString("itemName");
                String itemDescription = resultSet.getString("itemDescription");
                int itemPrice = resultSet.getInt("itemPrice");
                return new Item(itemId, itemName, itemDescription, itemPrice);
            } else {
                return null;
            }
        } catch (SQLException e) {
            return null;
        }
    }

    /**
     * saves the item to cart
     *
     * @param userId id of the user who added this item
     * @param itemId id of the item the user added
     * @param count count of the items to be added
     */
    public void saveItemToCart(Integer userId, Integer itemId, Integer count) {
        connection = getConnection();

        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO dbo.[addedItems] ( userId , itemId , count ) VALUES  (?,?,?)")) {

            statement.setInt(1, userId);
            statement.setInt(2, itemId);
            statement.setInt(3, count);
            statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Exception: " + e);
        }
    }

    /**
     * gets all the items that the user have been saved
     *
     * @param userId id of the user whose shopping cart we want to get
     * @return shopping cart object that contains the items the specific user has added
     */
    public ShoppingCart getShoppingCart(Integer userId) {
        connection = getConnection();
        ShoppingCart shoppingCart = new ShoppingCart();
        Item item;
        PreparedStatement statement;

        try {
            statement = connection.prepareStatement("SELECT * FROM dbo.[addedItems] WHERE userId = ? ");
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int itemId = resultSet.getInt("itemId");
                int count = resultSet.getInt("count");
                item = getItem(itemId);
                shoppingCart.addItemToCart(item, count);
            }
            return shoppingCart;
        } catch (SQLException e) {
            return null;
        }
    }

    public Connection getConnection() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            this.connection = DriverManager.getConnection("jdbc:sqlserver://SIS4W005:1433;databaseName = volodya", "root", "root");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

}
