package ru.netology.jdbc;

import ru.netology.jdbc.model.Car;

import java.sql.*;

/**
 * Description
 *
 * @author bse71
 * Created on 23.07.2021
 * @since
 */
public class Main {

    public static void main(String[] args) throws SQLException {

        printAll();
        printStupidById(4);
        printById(4);
    }

    private static void printAll() throws SQLException {
        Connection connection =
                DriverManager.getConnection(
                        DB_URL, DB_USERNAME, DB_USERPASS);

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM cars;");
            while(resultSet.next()) {
                int id = resultSet.getInt(("id"));
                String name = resultSet.getString(("name"));
                String model = resultSet.getString(("model"));

                System.out.printf("%-5d%-16s%-16s\n", id, name, model);
            }
        }
    }

    private static void printStupidById(int targetId) throws SQLException {
        Connection connection =
                DriverManager.getConnection(
                        DB_URL, DB_USERNAME, DB_USERPASS);

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(
                    "SELECT * FROM cars WHERE id = " + targetId + ";");
            while(resultSet.next()) {
                int id = resultSet.getInt(("id"));
                String name = resultSet.getString(("name"));
                String model = resultSet.getString(("model"));

//                System.out.printf("%-5d%-16s%-16s\n", id, name, model);
                System.out.println(
                        new Car(id, name, model));
            }
        }
    }

    private static void printById(int targetId) throws SQLException {
        Connection connection = DriverManager.getConnection(
                DB_URL, DB_USERNAME, DB_USERPASS);

        try (PreparedStatement statement =
                     connection.prepareStatement("SELECT * FROM cars WHERE id = ?;")) {
            statement.setInt(1, targetId);

            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                int id = resultSet.getInt(("id"));
                String name = resultSet.getString(("name"));
                String model = resultSet.getString(("model"));

                System.out.printf("%-5d%-16s%-16s\n", id, name, model);
            }
        }
    }




    private static final String DB_URL = "jdbc:postgresql://localhost:5432/cars";
    private static final String DB_USERNAME = "user";
    private static final String DB_USERPASS = "pass";
}
