package models;

import java.io.Serializable;

/**
 * Created by volodya.khachatryan on 3/16/2018.
 */
public class User implements Serializable{
    private String firstName, lastName, username, password, role;
    private int age;
    private ShoppingCart shoppingCart;

    public ShoppingCart getShoppingCart() {
        return this.shoppingCart == null? new ShoppingCart(): this.shoppingCart;
    }


    public User(String firstName, String lastName, String username, String password, int age, String role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.age = age;
        this.role = role;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getRole() {
        return role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (age != user.age) return false;
        if (!firstName.equals(user.firstName)) return false;
        if (!lastName.equals(user.lastName)) return false;
        if (!username.equals(user.username)) return false;
        return password.equals(user.password);
    }

    @Override
    public int hashCode() {
        int result = firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + username.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + age;
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                '}';
    }
}
