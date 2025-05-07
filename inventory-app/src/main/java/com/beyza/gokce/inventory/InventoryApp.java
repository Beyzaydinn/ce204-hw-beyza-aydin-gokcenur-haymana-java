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
import java.util.Arrays;
import java.util.Scanner;


/**
 *
 * @class InventoryApp
 * @brief This class represents the main application class for the Inventory
 *        App.
 * @details The InventoryApp class provides the entry point for the Inventory
 *          App. It initializes the necessary components, performs calculations,
 *          and handles exceptions.
 * @author ugur.coruh
 */
public class InventoryApp {
  /**
   * @brief Logger for the InventoryApp class.
   */

  /**
   * @brief The main entry point of the Inventory App.
   *
   * @details The main method is the starting point of the Inventory App. It
   *          initializes the logger, performs logging, displays a greeting
   *          message, and handles user input.
   *
   * @param args The command-line arguments passed to the application.
   */
	/**
	 * The main entry point of the application.
	 * Establishes a database connection, initializes the inventory system,
	 * and provides a menu for user interaction.
	 *
	 * @param args Command-line arguments (not used in this application).
	 */
	public static void main(String[] args) {
	    
	    // Establish a connection to the database
	    Connection conn = DatabaseConnection.connect(); 
	    
	    // Disconnect from the database after use
	    DatabaseConnection.disconnect(conn);
	    
	    // Initialize inventory tables and load users
	    Inventory.createTables(); 
	    Inventory.loadUsersFromDatabase(); 
	    
	    // Create a scanner for user input
	    Scanner scanner = new Scanner(System.in);
	    
	    // Infinite loop for the main menu
	    while (true) {
	        System.out.println("1. Login\n2. Register\n3. Guest Mode\n4. Exit");
	        
	        // Read user choice
	        int choice = scanner.nextInt();
	        scanner.nextLine();
	        
	        // Handle user choice
	        switch (choice) {
	            case 1:
	                Inventory.login();
	                break;
	            case 2:
	                Inventory.register();
	                break;
	            case 3:
	                Inventory.mainMenu();
	                break;
	            case 4:
	                System.exit(0);
	                break;
	            default:
	                System.out.println("Invalid choice. Please try again.");
	        }
	    }
	}

}