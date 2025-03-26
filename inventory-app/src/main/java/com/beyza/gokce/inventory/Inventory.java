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


//Base class for all records
abstract class Record implements Serializable {
String name;

public Record(String name) {
    this.name = name;
}

public String getName() {
    return name;
}

public abstract void displayInfo();
}

//Base class for users
class Person implements Serializable {
private String username;
String password;

public Person(String username, String password) {
    this.username = username;
    this.password = password;
}

public String getUsername() {
    return username;
}
public String getPassword() {
    return password;
}

public boolean authenticate(String password) {
    return this.password.equals(password);
 
}
}

class User extends Person {
public User(String username, String password) {
   super(username, password);
}
}

class InventoryItem extends Record {
  int quantity;
  double cost;

  public InventoryItem(String name, int quantity, double cost) {
      super(name);
      this.quantity = quantity;
      this.cost = cost;
  }

  public int getQuantity() {
      return quantity;
  }

  public double getCost() {
      return cost;
  }

  public void setQuantity(int quantity) {
      this.quantity = quantity;
  }

  public void setCost(double cost) {
      this.cost = cost;
  }

  @Override
  public void displayInfo() {
      System.out.println(name + " - " + quantity + " piece - " + cost + " TL");
  }
}

interface Manageable {
  void addMaterial(InventoryItem item);
  void listMaterials();
} 

class Project extends Record implements Manageable {
  List<InventoryItem> materials = new ArrayList<>();

  public Project(String name) {
      super(name);
  }

  public void addMaterial(InventoryItem item) {
      materials.add(item);
  }

  public void listMaterials() {
      for (InventoryItem item : materials) {
          item.displayInfo();
      }
  }

  @Override
  public void displayInfo() {
      System.out.println("Project: " + name);
      listMaterials();
  }
}

class Expense extends Record {
  double amount;
  String description; 

  public Expense(String description, double amount) {
      super(description);
      this.amount = amount;
      this.description = description; 
  }

  public double getAmount() {
      return amount;
  }

  @Override
  public void displayInfo() {
      System.out.println(description + " - " + amount + " TL"); 
  }

public String getDescription() {
	return getDescription();
}
}


class Sale extends Record {
  int quantity; 
  double price; 
  String item;   

  public Sale(String item, int quantity, double price) {
      super(item);
      this.quantity = quantity;
      this.price = price;
      this.item = item; 
  }

  public int getQuantity() {
      return quantity;
  }

  public double getPrice() {
      return price;
  }

  @Override
  public void displayInfo() {
      System.out.println(name + " - " + quantity + " piece - " + price + " TL"); 
  }

	public String item() {
		// TODO Auto-generated method stub
		return item;
	}
}


public class Inventory {
	
	private static final String DATABASE_URL = "jdbc:sqlite:inventory_manager.db";

	private static Connection connect() {
	    Connection connection = null;
	    try {
	        connection = DriverManager.getConnection(DATABASE_URL);
	    } catch (SQLException e) {
	    }
	    return connection;
	}

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
	       
	    }
	}

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

	static void addProjectToDatabase(Project project) {
	    String sql = "INSERT INTO projects (name) VALUES (?)";
	    try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setString(1, project.getName());
	        pstmt.executeUpdate();
	        System.out.println("Project added to database.");
	    } catch (SQLException e) {
	        
	    }
	}

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


	    static Scanner scanner = new Scanner(System.in);
	    static List<User> users = new ArrayList<>();
	    static List<InventoryItem> inventory = new ArrayList<>();
	    static List<Project> projects = new ArrayList<>();
	    static List<Expense> expenses = new ArrayList<>();
	    static List<Sale> sales = new ArrayList<>();
	    static User currentUser = null;

	

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
    
    static boolean viewInventory() {
         if (inventory.isEmpty()) {
         } else {
            for (InventoryItem item : inventory) {
                System.out.println(item.name + " - " + item.quantity + " piece - " + item.cost + " TL");
            }
         }
		return false;
   }


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

   static boolean removeMaterial() {
   	  System.out.print("Enter material name to remove: ");
         String name = scanner.nextLine();
         if (inventory.removeIf(item -> item.name.equalsIgnoreCase(name))) {
             System.out.println("Material removed successfully.");
         } else {
             
         }
         return false;
   }

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

   static void addProject() {
   	  System.out.print("Project name: ");
         String name = scanner.nextLine();
         Project project = new Project(name);
         projects.add(project); 
         addProjectToDatabase(project); 
   }

   static void viewProject() {
       for (Project project : projects) {
           System.out.println(project.name);
       } 
   } 
   
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

   static void expensesList() {
       for (Expense expense : expenses) {
       	System.out.println((expense.description));
           System.out.println((expense.amount));
       }
   }

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

   static void listSales() {
       for (Sale sale : sales) {
           System.out.println(sale.item + " - " + sale.quantity + " piece - " + sale.price + " TL"); 
       } 
   }

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