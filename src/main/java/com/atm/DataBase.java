package com.atm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBase {
    //информация о СУБД
    private static final String username_DB = "postgres";
    private static final String password_DB = "7la8a-pl";
    private static final String url_DB = "jdbc:postgresql://localhost:5432/postgres";

    // подключение к СУБД
    public static Connection connection(){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url_DB, username_DB, password_DB);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
