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
		Inventory.scanner = new Scanner(System.in);
	    Connection conn = DatabaseConnection.connect(); 
	    DatabaseConnection.disconnect(conn);
	    
	    Inventory.createTables(); 
	    Inventory.loadUsersFromDatabase(); 
	    
	    Scanner scanner = new Scanner(System.in);
	    Inventory.runMainMenu();
	}



}