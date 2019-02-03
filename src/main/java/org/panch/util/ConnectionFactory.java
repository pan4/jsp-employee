package org.panch.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    //static reference to itself
    private static ConnectionFactory instance =
            new ConnectionFactory();
//    String url = "jdbc:mysql://localhost:3306/ashok";
//    String user = "root";
//    String password = "";
    String driverClass = "com.mysql.jdbc.Driver";

    private final static String user = "postgres";
    private final static String password = "sa1604wa";
    private final static String url = "jdbc:postgresql://localhost:5432/employees";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}
