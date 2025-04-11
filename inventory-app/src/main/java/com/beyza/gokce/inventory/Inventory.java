/**

@file Inventory.java
@brief This file serves as a demonstration file for the Inventory class.
@details This file contains the implementation of the Inventory class, which provides various mathematical operations.
*/

/**

@package com.beyza.gokce.inventory
@brief The com.beyza.gokce.inventory package contains all the classes and files related to the Inventory App.
*/
package com.beyza.gokce.inventory;

import org.slf4j.LoggerFactory;
import java.util.*;
import java.io.FileOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.*;
import ch.qos.logback.classic.Logger;

/**
 * Base class for all records.
 * Implements Serializable to allow object serialization.
 */
abstract class Record implements Serializable {
String name;
/**
 * Constructor to initialize a record with a name.
 *
 * @param name The name of the record.
 */

public Record(String name) {
    this.name = name;
}
/**
 * Retrieves the name of the record.
 *
 * @return The name of the record.
 */
public String getName() {
    return name;
}
/**
 * Abstract method to display record information.
 * Must be implemented by subclasses.
 */
public abstract void displayInfo();
}
/**
 * Base class for users.
 * Implements Serializable to allow object serialization.
 */
class Person implements Serializable {
private String username;
String password;
/**
 * Constructor to initialize a user with a username and password.
 *
 * @param username The username of the person.
 * @param password The password of the person.
 */
public Person(String username, String password) {
    this.username = username;
    this.password = password;
}
/**
 * Retrieves the username of the person.
 *
 * @return The username of the person.
 */
public String getUsername() {
    return username;
}

/**
 * Retrieves the password of the person.
 *
 * @return The password of the person.
 */
public String getPassword() {
    return password;
}
/**
 * Authenticates the user by checking if the provided password matches the stored password.
 *
 * @param password The password to be checked.
 * @return True if the password matches, false otherwise.
 */
public boolean authenticate(String password) {
    return this.password.equals(password);
 
}
}
/**
 * Represents a user in the system.
 * Extends the Person class to inherit user-related attributes.
 */
class User extends Person {
public User(String username, String password) {
   super(username, password);
}
}
/**
 * Represents an inventory item.
 * Extends the Record class to store item details.
 */
class InventoryItem extends Record {
  int quantity;
  double cost;
  /**
   * Constructor to initialize an inventory item with a name, quantity, and cost.
   *
   * @param name     The name of the inventory item.
   * @param quantity The quantity of the item in stock.
   * @param cost     The cost of the item.
   */
  public InventoryItem(String name, int quantity, double cost) {
      super(name);
      this.quantity = quantity;
      this.cost = cost;
  }
  /**
   * Retrieves the quantity of the inventory item.
   *
   * @return The quantity of the item.
   */
  public int getQuantity() {
      return quantity;
  }

  /**
   * Retrieves the cost of the inventory item.
   *
   * @return The cost of the item.
   */
  public double getCost() {
      return cost;
  }

  /**
   * Sets the quantity of the inventory item.
   *
   * @param quantity The new quantity of the item.
   */
  public void setQuantity(int quantity) {
      this.quantity = quantity;
  }
  /**
   * Sets the cost of the inventory item.
   *
   * @param cost The new cost of the item.
   */
  public void setCost(double cost) {
      this.cost = cost;
  }

  /**
   * Displays information about the inventory item.
   * Prints the name, quantity, and cost of the item.
   */
  @Override
  public void displayInfo() {
      System.out.println(name + " - " + quantity + " piece - " + cost + " TL");
  }
}
/**
 * Interface that defines management operations.
 * Provides methods for adding and listing inventory materials.
 */
interface Manageable {
    /**
     * Adds a material to the inventory.
     *
     * @param item The inventory item to be added.
     */
  void addMaterial(InventoryItem item);
  /**
   * Lists all materials available in the inventory.
   */
  void listMaterials();
} 

/**
 * Represents a project that requires inventory materials.
 * Extends the Record class and implements the Manageable interface.
 */
class Project extends Record implements Manageable {
  List<InventoryItem> materials = new ArrayList<>();

  /**
   * Constructor to initialize a project with a name.
   *
   * @param name The name of the project.
   */
  public Project(String name) {
      super(name);
  }

  /**
   * Adds an inventory item as a material to the project.
   *
   * @param item The inventory item to be added to the project.
   */
  public void addMaterial(InventoryItem item) {
      materials.add(item);
  }
  /**
   * Lists all materials used in the project.
   * Iterates through the materials list and displays item information.
   */
  public void listMaterials() {
      for (InventoryItem item : materials) {
          item.displayInfo();
      }
  }
  /**
   * Displays information about the project.
   * Prints the project name and lists associated materials.
   */
  @Override
  public void displayInfo() {
      System.out.println("Project: " + name);
      listMaterials();
  }
}

/**
 * Represents an expense related to a project or inventory.
 * Extends the Record class to include financial details.
 */
class Expense extends Record {
  double amount;
  String description; 

  /**
   * Constructor to initialize an expense with a description and amount.
   *
   * @param description A brief description of the expense.
   * @param amount      The monetary value of the expense.
   */
  public Expense(String description, double amount) {
      super(description);
      this.amount = amount;
      this.description = description; 
  }
  /**
   * Retrieves the amount of the expense.
   *
   * @return The amount spent on the expense.
   */
  public double getAmount() {
      return amount;
  }
  /**
   * Displays information about the expense.
   * Prints the description and amount spent.
   */
  @Override
  public void displayInfo() {
      System.out.println(description + " - " + amount + " TL"); 
  }
  /**
   * Retrieves the description of the expense.
   *
   * @return A short description of the expense.
   */
public String getDescription() {
	return getDescription();
}
}
/**
 * Represents a sale transaction of an item.
 * Extends the Record class to include details about sold items.
 */

class Sale extends Record {
  int quantity; 
  double price; 
  String item;   
  /**
   * Constructor to initialize a sale transaction with item details.
   *
   * @param item     The name of the item being sold.
   * @param quantity The quantity of the item sold.
   * @param price    The selling price per unit of the item.
   */
  public Sale(String item, int quantity, double price) {
      super(item);
      this.quantity = quantity;
      this.price = price;
      this.item = item; 
  }
  /**
   * Retrieves the quantity of the sold item.
   *
   * @return The quantity of the item sold.
   */
  public int getQuantity() {
      return quantity;
  }

  /**
   * Retrieves the price of the sold item.
   *
   * @return The price per unit of the item.
   */
  public double getPrice() {
      return price;
  }

  /**
   * Displays information about the sale transaction.
   * Prints the item name, quantity sold, and total price.
   */
  @Override
  public void displayInfo() {
      System.out.println(name + " - " + quantity + " piece - " + price + " TL"); 
  }

  /**
   * Retrieves the name of the sold item.
   *
   * @return The name of the item sold.
   */

	public String item() {
		// TODO Auto-generated method stub
		return item;
	}
}
/**
 * Manages the inventory system, including database operations.
 */

public class Inventory {
	   // Database URL for SQLite connection
	private static final String DATABASE_URL = "jdbc:sqlite:inventory_manager.db";
    /**
     * Establishes a connection to the SQLite database.
     *
     * @return A Connection object if successful, null otherwise.
     */
	private static Connection connect() {
	    Connection connection = null;
	    try {
	        connection = DriverManager.getConnection(DATABASE_URL);
	    } catch (SQLException e) {
	    	// Handle database connection error
	    }
	    return connection;
	}
    /**
     * Creates necessary tables for users, inventory, projects, expenses, and sales if they do not exist.
     * Ensures data is structured and stored properly within the database.
     */
	static void createTables() {
	    String createUsersTable = "CREATE TABLE IF NOT EXISTS users ("
	            + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
	            + "username TEXT NOT NULL UNIQUE,"
	            + "password TEXT NOT NULL"
	            + ");";

	    String createInventoryTable = "CREATE TABLE IF NOT EXISTS inventory ("
	            + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
	            + "name TEXT NOT NULL,"
	            + "quantity INTEGER NOT NULL,"
	            + "cost REAL NOT NULL"
	            + ");";

	    String createProjectsTable = "CREATE TABLE IF NOT EXISTS projects ("
	            + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
	            + "name TEXT NOT NULL"
	            + ");";

	    String createExpensesTable = "CREATE TABLE IF NOT EXISTS expenses ("
	            + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
	            + "description TEXT NOT NULL,"
	            + "amount REAL NOT NULL"
	            + ");";

	    String createSalesTable = "CREATE TABLE IF NOT EXISTS sales ("
	            + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
	            + "item TEXT NOT NULL,"
	            + "quantity INTEGER NOT NULL,"
	            + "price REAL NOT NULL"
	            + ");";

	    try (Connection conn = connect(); Statement stmt = conn.createStatement()) {
	        stmt.execute(createUsersTable);
	        stmt.execute(createInventoryTable);
	        stmt.execute(createProjectsTable);
	        stmt.execute(createExpensesTable);
	        stmt.execute(createSalesTable);
	        System.out.println("Tables created successfully.");
	    } catch (SQLException e) {
	        
	    }
	}
	
	static void addUserToDatabase(User user) {
	    String sql = "INSERT INTO users (username, password) VALUES (?, ?)";
	    try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setString(1, user.getUsername());
	        pstmt.setString(2, user.password);
	        pstmt.executeUpdate();
	        System.out.println("User added to database.");
	    } catch (SQLException e) {
	    	 // Handle table creation error
	    }
	}
	/**
	 * Loads all users from the database into the users list.
	 * Retrieves username and password fields from the users table.
	 */
	static void loadUsersFromDatabase() {
	    String sql = "SELECT username, password FROM users";
	    try (Connection conn = connect(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
	        users.clear();
	        while (rs.next()) {
	            String username = rs.getString("username");
	            String password = rs.getString("password");
	            users.add(new User(username, password));
	        }
	        System.out.println("Users loaded from database.");
	    } catch (SQLException e) {
	     
	    }
	}
	/**
	 * Adds a new material (inventory item) to the database.
	 * 
	 * @param item The inventory item to be added.
	 */
	static void addMaterialToDatabase(InventoryItem item) {
	    String sql = "INSERT INTO inventory (name, quantity, cost) VALUES (?, ?, ?)";
	    try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setString(1, item.getName());
	        pstmt.setInt(2, item.getQuantity());
	        pstmt.setDouble(3, item.getCost());
	        pstmt.executeUpdate();
	        System.out.println("Material added to database.");
	    } catch (SQLException e) {
	       
	    }
	}
	/**
	 * Loads all inventory items from the database into the inventory list.
	 * Retrieves name, quantity, and cost fields from the inventory table.
	 */
	static void loadInventoryFromDatabase() {
	    String sql = "SELECT name, quantity, cost FROM inventory";
	    try (Connection conn = connect(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
	        inventory.clear(); 
	        while (rs.next()) {
	            String name = rs.getString("name");
	            int quantity = rs.getInt("quantity");
	            double cost = rs.getDouble("cost");
	            inventory.add(new InventoryItem(name, quantity, cost));
	        }
	        System.out.println("Inventory loaded from database.");
	    } catch (SQLException e) {
	       
	    }
	}
	/**
	 * Adds a new project to the database.
	 * 
	 * @param project The project to be added.
	 */
	static void addProjectToDatabase(Project project) {
	    String sql = "INSERT INTO projects (name) VALUES (?)";
	    try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setString(1, project.getName());
	        pstmt.executeUpdate();
	        System.out.println("Project added to database.");
	    } catch (SQLException e) {
	        
	    }
	}
	/**
	 * Adds a new expense entry to the database.
	 * 
	 * @param expense The expense to be added.
	 */
	static void addExpenseToDatabase(Expense expense) {
	    String sql = "INSERT INTO expenses (description, amount) VALUES (?, ?)";
	    try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setString(1, expense.description);
	        pstmt.setDouble(2, expense.getAmount());
	        pstmt.executeUpdate();
	        System.out.println("Expense added to database.");
	    } catch (SQLException e) {
	    }
	}
	/**
	 * Adds a new sale record to the database.
	 * 
	 * @param sale The sale record to be added.
	 */
	static void addSaleToDatabase(Sale sale) {
	    String sql = "INSERT INTO sales (item, quantity, price) VALUES (?, ?, ?)";
	    try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setString(1, sale.item); 
	        pstmt.setInt(2, sale.getQuantity());
	        pstmt.setDouble(3, sale.getPrice());
	        pstmt.executeUpdate(); 
	        System.out.println("Sale added to database.");
	    } catch (SQLException e) {
	    }
	}

	// Global scanner and lists for managing users, inventory, projects, expenses, and sales.
	    static Scanner scanner = new Scanner(System.in);
	    static List<User> users = new ArrayList<>();
	    static List<InventoryItem> inventory = new ArrayList<>();
	    static List<Project> projects = new ArrayList<>();
	    static List<Expense> expenses = new ArrayList<>();
	    static List<Sale> sales = new ArrayList<>();
	    static User currentUser = null;

/**
 * Handles the user registration process.
 * Prompts the user for a username and password, then stores the new user in the database.
 */
   static void register() {
       System.out.print("Username: ");
       String username = scanner.nextLine();
       System.out.print("Password: ");
       String password = scanner.nextLine();
       User newUser = new User(username, password);
       users.add(newUser);
       addUserToDatabase(newUser);
       System.out.println("Registration successful!");
   }
   /**
    * Handles user login by checking credentials against the stored users list.
    * 
    * @return True if login is successful, false otherwise.
    */
   static boolean login() {
       System.out.print("Username: ");
       String username = scanner.nextLine();
       System.out.print("Password: ");
       String password = scanner.nextLine();

       for (User user : users) {
           if (user.getUsername().equals(username) && user.authenticate(password)) {
               currentUser = user;
               System.out.println("Login successful!");
               return false;
           }
       }
       System.out.println("Login failed! Please try again.");
	return false;
   }
   /**
    * Displays the main menu for inventory management, project tracking, expense logging, and sales tracking.
    * 
    * @return False when the user chooses to exit.
    */
   static boolean mainMenu() {
       while (true) {
           System.out.println("1. Material Inventory\n2. Project Tacking\n3. Expense Logging\n4. Sales Tracker\n5. Exit");
           int choice = scanner.nextInt();
           scanner.nextLine();
           switch (choice) {
               case 1: materialInventory();
               break;
               case 2: projectTacking();
               break;
               case 3: expenseLogging();
               break;
               case 4: salesTracker();
               break;
               case 5: return false;
               default: System.out.println("Invalid choice. Please try again.");
           }
       }
   }
   /**
    * Manages the material inventory system, providing options to add, view, edit, 
    * remove materials, and return to the main menu.
    * 
    * @return false to indicate the menu continues.
    */
   static boolean materialInventory() {
       System.out.println("1. Add Material\n2. View Inventory\n3. Edit Material\n4. Remove Material\n5. Return to main menu");
       int choice = scanner.nextInt();
       scanner.nextLine();
       switch (choice) {
       case 1: addMaterial();
       break;
       case 2: viewInventory();
       break;
       case 3:editMaterial();
       break;
       case 4:removeMaterial();
       break;
       case 5: mainMenu();
       break;
       default: System.out.println("Invalid choice. Please try again."); 
       }
	return false;
   }
   /**
    * Adds a new material to the inventory and database.
    * 
    * @return false to indicate the operation is complete.
    */
   static boolean addMaterial() {
       System.out.print("Material name: ");
       String name = scanner.nextLine();
       System.out.print("Amount: ");
       int quantity = scanner.nextInt();
       System.out.print("Cost: ");
       double cost = scanner.nextDouble();
       InventoryItem item = new InventoryItem(name, quantity, cost);
       inventory.add(item);
       addMaterialToDatabase(item);
       System.out.println("Material added successfully.");
	return false;
   }
   /**
    * Displays the materials currently available in the inventory.
    * If the inventory is empty, no items will be listed.
    * 
    * @return false to indicate the operation is complete.
    */
    static boolean viewInventory() {
         if (inventory.isEmpty()) {
         } else {
            for (InventoryItem item : inventory) {
                System.out.println(item.name + " - " + item.quantity + " piece - " + item.cost + " TL");
            }
         }
		return false;
   }

    /**
     * Allows the user to edit the details of an existing material in the inventory.
     * 
     * @return false to indicate the operation is complete.
     */
   static boolean editMaterial() {
   	   System.out.print("Enter material name to edit: ");
          String name = scanner.nextLine();
          for (InventoryItem item : inventory) {
              if (item.name.equalsIgnoreCase(name)) {
                  System.out.print("New Amount: ");
                  item.quantity = scanner.nextInt();
                  System.out.print("New Cost: ");
                  item.cost = scanner.nextDouble();
                  scanner.nextLine();
                  System.out.println("Material updated successfully.");
                  return false;
              }
          }
		return false;
   }
   /**
    * Allows the user to remove a material from the inventory.
    * 
    * @return false to indicate the operation is complete.
    */
   static boolean removeMaterial() {
   	  System.out.print("Enter material name to remove: ");
         String name = scanner.nextLine();
         if (inventory.removeIf(item -> item.name.equalsIgnoreCase(name))) {
             System.out.println("Material removed successfully.");
         } else {
             
         }
         return false;
   }

/**
 * Manages project tracking, including adding and viewing projects, or returning to the main menu.
 * 
 * @return false to indicate the menu continues.
 */
   static boolean projectTacking() {
       System.out.println("1. Add Project\n2. View Project\n3. Return to main menu");
       int choice = scanner.nextInt();
       scanner.nextLine();
       switch (choice) {
       case 1: addProject();
       break;
       case 2: viewProject();
       break;
       case 3: mainMenu();
       break;
       default: System.out.println("Invalid choice. Please try again.");
       }
	return false;
   }

/**
 * Adds a new project to the project list and database.
 */
   static void addProject() {
   	  System.out.print("Project name: ");
         String name = scanner.nextLine();
         Project project = new Project(name);
         projects.add(project); 
         addProjectToDatabase(project); 
   }
   /**
    * Displays the list of all projects.
    */
   static void viewProject() {
       for (Project project : projects) {
           System.out.println(project.name);
       } 
   } 

/**
 * Manages expense logging, including adding expenses, viewing the expenses list, 
 * and returning to the main menu.
 * 
 * @return false to indicate the menu continues.
 */
   static boolean expenseLogging() {
       System.out.println("1. Add Expense\n2. Expenses List\n3. Return to main menu");
       int choice = scanner.nextInt();
       scanner.nextLine();
       switch (choice) {
       case 1: addExpense();
       break;
       case 2: expensesList();
       break;
       case 3: mainMenu();
       break;
       default: System.out.println("Invalid choice. Please try again.");
       }
	return false;
   }
   /**
    * Adds a new expense to the expense list and database.
    * 
    * @return false to indicate the operation is complete.
    */
   static boolean addExpense() {
   	   System.out.print("Expense Statement: ");
          String description = scanner.nextLine();
          System.out.print("Amount: ");
          double amount = scanner.nextDouble();
          Expense expense = new Expense(description, amount); 
          expenses.add(expense); 
          addExpenseToDatabase(expense); 
          return false;
   }
   /**
    * Displays the list of all logged expenses.
    */
   static void expensesList() {
       for (Expense expense : expenses) {
       	System.out.println((expense.description));
           System.out.println((expense.amount));
       }
   }
   /**
    * Manages sales tracking, including adding sales, listing sales, 
    * calculating profit, and returning to the main menu.
    * 
    * @return false to indicate the menu continues.
    */
   static boolean salesTracker() {
       System.out.println("1. Add Sales\n2. List Sales\n3. Calculate Profit\n4. Return to main menu");
       int choice = scanner.nextInt();
       scanner.nextLine();
       switch (choice) {
       case 1: addSales();
       break;
       case 2: listSales();
       break;
       case 3: calculateProfit();
       break;
       case 4: mainMenu();
       break; 
       default: System.out.println("Invalid choice. Please try again.");
       }
	return false;
   }
   /**
    * Adds a new sale to the sales list and database.
    * 
    * @return false to indicate the operation is complete.
    */
   static boolean addSales() {
       System.out.print("Product name: ");
       String item = scanner.nextLine();
       System.out.print("Amount: ");
       int quantity = scanner.nextInt();
       System.out.print("Price: ");
       double price = scanner.nextDouble();
       Sale sale = new Sale(item, quantity, price); 
       sales.add(sale); 
       addSaleToDatabase(sale); 
       return false;
   }
   /**
    * Lists all sales in the system.
    */
   static void listSales() {
       for (Sale sale : sales) {
           System.out.println(sale.item + " - " + sale.quantity + " piece - " + sale.price + " TL"); 
       } 
   }

/**
 * Calculates the total profit from sales by subtracting the cost price from the sale price.
 */
   static void calculateProfit() {
  	 double totalProfit = 0;
  	    
  	    for (Sale sale : sales) {
  	        System.out.print("Enter cost for " + sale.item() + " : ");
  	        double costPrice = scanner.nextDouble();
  	        
  	        double profit = (sale.getPrice() - costPrice) * sale.getQuantity();
  	        totalProfit += profit;
  	    }
  	    System.out.println("Total profit: " + totalProfit + " TL");
  }
}