package com.mycompany.proyecto_final.Models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
    private static Connection connection = null;
    private static ConnectDB connectDB;

    // Librería de MySQL
    private static String driver = "com.mysql.jdbc.Driver";
    // Nombre de la base de datos
    private static String database = "PROYECTO_FINAL";
    // Host
    private static String hostname = "localhost";
    // Puerto
    private static String port = "3306";
    // URL
    private static String url = "jdbc:mysql://" + hostname + ":" + port + "/" + database+"?useSSL=false&serverTimezone=UTC";
    // Nombre de usuario
    private static String username = "root";
    // Clave de usuario
    private static String password = "201931012";

    private ConnectDB() {
        try {
            Class.forName(driver).newInstance();
            connection = DriverManager.getConnection(url, username, password);
        } catch (IllegalAccessException | InstantiationException | ClassNotFoundException ex) {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        } catch (SQLException ex) {
            System.out.println("Error DB: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public static Connection getInstance() {
        if (connectDB == null) {
            connectDB = new ConnectDB();
        }
        return connection;
    }
}
