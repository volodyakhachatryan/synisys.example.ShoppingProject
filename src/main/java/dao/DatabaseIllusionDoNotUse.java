package dao;

import models.User;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * Created by volodya.khachatryan on 3/15/2018.
 */
public class DatabaseIllusionDoNotUse {

    private static File file;
    private static Map<String, User> users;
    private static String usernameModel = "\"Username: \"";
    private static String firstNameModel = "\": \"First Name\": \"";
    private static String lastNameModel = "\", \"Last Name\": \"";
    private static String passwordModel = "\", \"Password\": \"";
    private static String ageModel = "\", \"Age\": \"";






    public static boolean isUsernameAvailable(String username) {
        return users.get(username) == null;
    }

    public static User getUser(String username, String password) {
        if (users.get(username) != null) {
            if (Objects.equals(users.get(username).getPassword(), password)) {
                return users.get(username);
            }
        }
        return null;
    }


    public static void registerUser(String userName, User user) {
        users.put(userName, user);

        StringBuilder data = readFile(file);
        StringBuilder newUser = toStringBuilder(user);

        writeInFile(file, newUser, data);
    }


    private static Map<String, User> getAllUsers(File file) {
        Map<String, User> map = new HashMap<>();
        if (file.isFile() && file.exists()) {
            StringBuilder data = readFile(file);
            map = getUsers(data, map);
        }
        return map;
    }

    private static StringBuilder readFile(File file) {
        StringBuilder data = new StringBuilder();
        if (file.exists() && file.isFile()) {
            try {
                FileReader fileReader = new FileReader(file);
                int read = fileReader.read();
                while (read != -1) {
                    char c = (char) read;
                    data.append(c);
                    read = fileReader.read();
                }
                fileReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return data;
    }

    public static StringBuilder toStringBuilder(User user) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(usernameModel);
        stringBuilder.append(user.getUsername());
        stringBuilder.append(firstNameModel);
        stringBuilder.append(user.getFirstName());
        stringBuilder.append(lastNameModel);
        stringBuilder.append(user.getLastName());
        stringBuilder.append(passwordModel);
        stringBuilder.append(user.getPassword());
        stringBuilder.append(ageModel);
        stringBuilder.append(user.getAge());
        stringBuilder.append("\"\n");

        return stringBuilder;
    }

    private static void writeInFile(File file, StringBuilder newUser, StringBuilder data) {
        data.append(newUser);
        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(data.toString());
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static Map<String, User> getUsers(StringBuilder data, Map<String, User> map) {
        if (data.length() == 0) {
            return map;
        } else {
            int indexUsername = data.indexOf(usernameModel);
            int indexFirstName = data.indexOf(firstNameModel);
            int indexLastName = data.indexOf(lastNameModel);
            int indexPassword = data.indexOf(passwordModel);
            int indexAge = data.indexOf(ageModel);

            User user;
            StringBuilder username = new StringBuilder();
            StringBuilder firstName = new StringBuilder();
            StringBuilder lastName = new StringBuilder();
            StringBuilder password = new StringBuilder();
            StringBuilder age = new StringBuilder();
            char c;

            while (indexUsername != -1 && indexFirstName != -1 && indexLastName != -1 && indexPassword != -1 && indexAge != -1) {
                int i = 0;
                c = data.charAt(indexUsername + usernameModel.length());
                while (c != '"') {
                    username.append(c);
                    i++;
                    c = data.charAt(indexUsername + usernameModel.length() + i);
                }

                i = 0;
                c = data.charAt(indexFirstName + firstNameModel.length());
                while (c != '"') {
                    firstName.append(c);
                    i++;
                    c = data.charAt(indexFirstName + firstNameModel.length() + i);
                }

                i = 0;
                c = data.charAt(indexLastName + lastNameModel.length());
                while (c != '"') {
                    lastName.append(c);
                    i++;
                    c = data.charAt(indexLastName + lastNameModel.length() + i);
                }
                i = 0;
                c = data.charAt(indexPassword + passwordModel.length());
                while (c != '"') {
                    password.append(c);
                    i++;
                    c = data.charAt(indexPassword + passwordModel.length() + i);
                }
                i = 0;
                c = data.charAt(indexAge + ageModel.length());
                while (c != '"') {
                    age.append(c);
                    i++;
                    c = data.charAt(indexAge + ageModel.length() + i);
                }

                user = new User(firstName.toString(), lastName.toString(), username.toString(), password.toString(), Integer.parseInt(age.toString()), "user");
                map.put(username.toString(), user);

                indexUsername = data.indexOf(usernameModel, indexUsername + 1);
                indexFirstName = data.indexOf(firstNameModel, indexFirstName + 1);
                indexLastName = data.indexOf(lastNameModel, indexLastName + 1);
                indexPassword = data.indexOf(passwordModel, indexPassword + 1);
                indexAge = data.indexOf(ageModel, indexAge + 1);

                username.delete(0, username.length());
                firstName.delete(0, firstName.length());
                lastName.delete(0, lastName.length());
                password.delete(0, password.length());
                age.delete(0, age.length());
            }
        }
        return map;
    }


}