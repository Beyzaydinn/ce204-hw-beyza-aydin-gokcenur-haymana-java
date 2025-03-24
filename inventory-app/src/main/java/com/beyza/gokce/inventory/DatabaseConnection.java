/**

@file InventoryApp.java
@brief This file serves as the main application file for the Inventory App.
@details This file contains the entry point of the application, which is the main method. It initializes the necessary components and executes the Inventory App.
*/
/**

@package com.beyza.gokce.inventory
@brief The com.beyza.gokce.inventory package contains all the classes and files related to the Inventory App.
*/
package com.beyza.gokce.inventory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public static Connection connect() {
        Connection conn = null;
        try {
            String url = "jdbc:sqlite:veritabani.db"; 
            conn = DriverManager.getConnection(url);
            System.out.println("SQLite bağlantısı başarılı!");
        } catch (SQLException e) {
            System.out.println("Bağlantı hatası: " + e.getMessage());
        }
        return conn;
    }

    public static void disconnect(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
                System.out.println("Bağlantı kapatıldı.");
            }
        } catch (SQLException e) {
            System.out.println("Bağlantı kapatma hatası: " + e.getMessage());
        }
    }
}

