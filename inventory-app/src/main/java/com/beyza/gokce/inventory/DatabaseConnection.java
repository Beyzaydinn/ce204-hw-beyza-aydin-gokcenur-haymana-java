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
/**
 * @class DatabaseConnection
 * @brief Manages the connection to the SQLite database.
 *
 * This class provides methods to establish a connection to the database
 * and safely close the connection. It handles any SQL exceptions
 * that may occur during the connection process and ensures the connection 
 * is properly closed when no longer needed.
 */
public class DatabaseConnection {
	   /**
     * @brief Establishes a connection to the SQLite database.
     *
     * Connects to the SQLite database using the connection string obtained
     * from the environment variable "SQLITE_DB_PATH". If the connection is
     * successful, it prints a confirmation message; otherwise, it catches
     * and prints the error.
     *
     * @return A Connection object representing the connection to the database,
     *         or null if the connection fails.
     */
    public static Connection connect() {
        Connection conn = null;
        try {
        	String url = "jdbc:sqlite:inventory_manager.db";
            conn = DriverManager.getConnection(url);
            System.out.println("SQLite connection successful!");
        } catch (SQLException e) {
        }
        return conn;
    }
    
    /**
     * @brief Closes the given database connection.
     *
     * Closes the provided Connection object if it is not null. If the connection 
     * is successfully closed, it prints a confirmation message. In case of an error, 
     * it catches the exception and prints the error message.
     *
     * @param conn The Connection object to be closed.
     */
    public static void disconnect(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
                System.out.println("The connection has been closed.");
            }
        } catch (SQLException e) {
        }
    }
}

