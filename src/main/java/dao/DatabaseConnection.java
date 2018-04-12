package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import models.Item;
import models.User;

//import java.sql.Connection;
//import java.sql.SQLException;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.Statement;


/**
 * Created by volodya.khachatryan on 3/19/2018.
 */
public class DatabaseConnection {

    public static User getUser(String usernameForSearch, String passwordForSearch) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            Connection connection = DriverManager.getConnection("jdbc:sqlserver://SIS4W005:1433;databaseName = volodya", "root", "root");
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM dbo.[users] WHERE username = ? AND password = ?");
            statement.setString(1, usernameForSearch);
            statement.setString(2, passwordForSearch);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String firstName = resultSet.getString("firstname");
                String lastName = resultSet.getString("lastname");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                int age = resultSet.getInt("age");
                String role = resultSet.getString("role");

                resultSet.close();
                statement.close();
                connection.close();

                return new User(firstName, lastName, username, password, age, role);
            }
            resultSet.close();
            statement.close();
            connection.close();
            return null;

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean saveUser(User user) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            try (Connection connection = DriverManager.getConnection("jdbc:sqlserver://SIS4W005:1433;databaseName = volodya", "root", "root");
//                 Statement statement = connection.createStatement();
                 PreparedStatement statement = connection.prepareStatement("INSERT INTO dbo.[users] ( username , firstname , lastname , password , age, role) VALUES  (?,?,?,?,?,?)");
            ) {

                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

                statement.setString(1, user.getUsername());
                statement.setString(2, user.getFirstName());
                statement.setString(3, user.getLastName());
                statement.setString(4, user.getPassword());
                statement.setInt(5, user.getAge());
                statement.setString(6, user.getRole());

                statement.executeUpdate();

//                statement.executeUpdate("INSERT INTO dbo.[users] ( username , firstname , lastname , password , age) VALUES  ( '"
//                        + user.getUsername() + "' ,  '" + user.getFirstName() + "' , '" + user.getLastName() + "' , '" + user.getPassword() + "' ,  " + user.getAge() + ") ");

                return true;
            } catch (SQLException | ClassNotFoundException e) {
                System.out.println("Exception: " + e);
                return false;
            }
        } catch (ClassNotFoundException e) {
            return false;
        }

    }

    public static void deleteUser(String username) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            try (Connection connection = DriverManager.getConnection("jdbc:sqlserver://SIS4W005:1433;databaseName = volodya", "root", "root");
//                 Statement statement = connection.createStatement();
                 PreparedStatement statement = connection.prepareStatement("DELETE FROM dbo.[users] WHERE username=?");
            ) {

                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

                statement.setString(1, username);

                statement.executeUpdate();

            } catch (SQLException | ClassNotFoundException e) {
                System.out.println("Exception: " + e);
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Exception: " + e);
        }

    }

    public static boolean addItem(Item item){
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            try (Connection connection = DriverManager.getConnection("jdbc:sqlserver://SIS4W005:1433;databaseName = volodya", "root", "root");

                 PreparedStatement statement = connection.prepareStatement("INSERT INTO dbo.[items] ( itemname , itemdescription , itemprice ) VALUES  (?,?,?)");
            ) {

                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

                statement.setString(1, item.getItemName());
                statement.setString(2, item.getItemDescription());
                statement.setInt(3, item.getItemPrice());

                statement.executeUpdate();

                return true;
            } catch (SQLException | ClassNotFoundException e) {
                System.out.println("Exception: " + e);
                return false;
            }
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public static List<Item> getItems(){
        List<Item> itemList = new ArrayList<>();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            Connection connection = DriverManager.getConnection("jdbc:sqlserver://SIS4W005:1433;databaseName = volodya", "root", "root");
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM dbo.[items]");

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String itemName = resultSet.getString("itemName");
                String itemDescription = resultSet.getString("itemDescription");
                int itemPrice = resultSet.getInt("itemPrice");

                itemList.add(new Item(itemName, itemDescription, itemPrice));
            }
            resultSet.close();
            statement.close();
            connection.close();
            return itemList;

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

}
