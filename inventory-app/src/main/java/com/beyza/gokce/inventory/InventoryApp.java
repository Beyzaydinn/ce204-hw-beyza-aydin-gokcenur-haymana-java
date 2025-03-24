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
	  public static void main(String[] args) {
		  
		   Connection conn = DatabaseConnection.connect(); 
	        DatabaseConnection.disconnect(conn);
	        
	        Inventory.createTables(); 
	        Inventory.loadUsersFromDatabase(); 
		  
		  Scanner scanner = new Scanner(System.in);
	      while (true) {
	          System.out.println("1. Login\n2. Register\n3. Guest Mode\n4. Exit");
	          int choice = scanner.nextInt();
	          scanner.nextLine();
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
