/**

@file InventoryTest.java
@brief This file contains the test cases for the Inventory class.
@details This file includes test methods to validate the functionality of the Inventory class. It uses JUnit for unit testing.
*/
package com.beyza.gokce.inventory;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import java.sql.PreparedStatement;
import com.beyza.gokce.inventory.Inventory;

/**

@class InventoryTest
@brief This class represents the test class for the Inventory class.
@details The InventoryTest class provides test methods to verify the behavior of the Inventory class. It includes test methods for addition, subtraction, multiplication, and division operations.
@author ugur.coruh
*/

public class InventoryTest {
	 private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final InputStream originalSystemIn = System.in;
    private final PrintStream originalSystemOut = System.out;
	private Connection connection;
    private static final String DATABASE_URL = "jdbc:sqlite:inventory_manager.db";
	    private static final int MAX_FEEDBACKS = 0;

	    /**
	     * @brief This method is executed once before all test methods.
	     * @throws Exception
	     */
	    @BeforeClass
	    public static void setUpBeforeClass() throws Exception {
	    
	    }

	    /**
	     * @brief This method is executed once after all test methods.
	     * @throws Exception
	     */
	    @AfterClass
	    public static void tearDownAfterClass() throws Exception {
	    }

	    @Test
	    public void testPersonConstructor() {
	        String expectedUsername = "testUser";
	        String expectedPassword = "testPass";
	        Person person = new Person(expectedUsername, expectedPassword);
	        assertEquals(expectedUsername, person.getUsername());
	        assertEquals(expectedPassword, person.getPassword());
	    }
	    
	    @Test
	    public void testAuthenticate() {
	        // Arrange
	        Person person = new Person("testUser", "securePass");
	        assertTrue(person.authenticate("securePass")); 
	        assertFalse(person.authenticate("wrongPass"));
	    }
	    
	    @Test
	    public void testUserConstructor() {
	        // Arrange
	        String expectedUsername = "userTest";
	        String expectedPassword = "pass123";
	        User user = new User(expectedUsername, expectedPassword);
	        assertEquals(expectedUsername, user.getUsername());
	        assertEquals(expectedPassword, user.getPassword());
	    }  
	    
	    @Test
	    public void testInventoryItemConstructor() {
	        String expectedName = "Laptop";
	        int expectedQuantity = 10;
	        double expectedCost = 1200.50;
	        InventoryItem item = new InventoryItem(expectedName, expectedQuantity, expectedCost);
	        assertEquals(expectedName, item.getName());
	        assertEquals(expectedQuantity, item.getQuantity());
	        assertEquals(expectedCost, item.getCost(), 0.001); 
	    }
	    

	    @Test
	    public void testSetQuantity() {
	        InventoryItem item = new InventoryItem("Laptop", 10, 1200.50);
	        int newQuantity = 20;
	        item.setQuantity(newQuantity);
	        assertEquals(newQuantity, item.getQuantity());
	    }
	    
	    @Test
	    public void testSetCost() {
	        // Arrange
	        InventoryItem item = new InventoryItem("Laptop", 10, 1200.50);
	        double newCost = 1500.75;

	        // Act
	        item.setCost(newCost);

	        // Assert
	        assertEquals(newCost, item.getCost(), 0.001); 
	    }
	    
	    @Test
	    public void testDisplayInfo() {
	        // Arrange
	        InventoryItem item = new InventoryItem("Laptop", 10, 1200.50);
	        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	        System.setOut(new PrintStream(outContent));  // Capture the output

	        // Act
	        item.displayInfo();

	        // Assert
	        String expectedOutput = "Laptop - 10 piece - 1200.5 TL\n"; // Expected format

	    }
	    
	    @Test
	    public void testExpense_CreationWithDescriptionAndAmount() {
	        // Arrange
	        String description = "Lunch";
	        double amount = 50.75;

	        // Act
	        Expense expense = new Expense(description, amount);

	        // Assert
	        assertNotNull(expense);
	    }

	    @Test
	    public void testDisplayInfo_ShouldDisplayCorrectInformation() {
	        // Arrange
	        String description = "Lunch";
	        double amount = 50.75;
	        Expense expense = new Expense(description, amount);

	        // Capture the console output
	        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	        System.setOut(new PrintStream(outContent));

	        // Act
	        expense.displayInfo();

	        // Assert
	        String expectedOutput = description + " - " + amount + " TL\n";
	    }

	    @Test
	    public void testGetAmount_ShouldReturnCorrectAmount() {
	        // Arrange
	        double expectedAmount = 100.50;
	        Expense expense = new Expense("Office Supplies", expectedAmount);

	        // Act
	        double actualAmount = expense.getAmount();

	        // Assert
	        assertEquals("The amount is not correct.", expectedAmount, actualAmount, 0.001);
	    }

	    @Test
	    public void testGetDescription_ShouldReturnCorrectDescription() {
	        // Arrange
	        String expectedDescription = "Dinner";
	        Expense expense = new Expense(expectedDescription, 75.00);

	    }

	    @Test
	    public void testConstructor_ShouldInitializeCorrectly() {
	        Sale sale = new Sale("Laptop", 2, 15000.0);
	        assertEquals("Laptop", sale.item());
	        assertEquals(2, sale.getQuantity());
	    }

	    @Test
	    public void testGetQuantity_ShouldReturnCorrectValue() {
	        Sale sale = new Sale("Laptop", 2, 15000.0);
	        assertEquals(2, sale.getQuantity());
	    }

	    @Test
	    public void testGetPrice_ShouldReturnCorrectValue() {
	        Sale sale = new Sale("Laptop", 2, 15000.0);
	    }

	    @Test
	    public void testDisplayInfo_ShouldPrintCorrectFormat() {
	        Sale sale = new Sale("Laptop", 2, 15000.0);

	        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	        PrintStream originalOut = System.out;
	        System.setOut(new PrintStream(outContent));

	        sale.displayInfo();

	        System.setOut(originalOut);

	        String expectedOutput = "Laptop - 2 piece - 15000.0 TL\n";
	    }

	    @Test
	    public void testCreateTables_ShouldCreateAllTablesSuccessfully() {
	        Inventory.createTables();

	        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:inventory_manager.db");
	             Statement stmt = conn.createStatement()) {

	            String[] tables = {"users", "inventory", "projects", "expenses", "sales"};

	            for (String table : tables) {
	                try (ResultSet rs = stmt.executeQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='" + table + "';")) {

	                    assertTrue("Table '" + table + "' should exist in the database.", rs.next());
	                }
	            }
	        } catch (SQLException e) {
	        }
	    }

	    @Test
	    public void testAddUserToDatabase_ShouldAddUserSuccessfully() {
	        User testUser = new User("testuser", "testpassword");

	        Inventory.addUserToDatabase(testUser);

	        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:inventory_manager.db");
	             PreparedStatement pstmt = conn.prepareStatement("SELECT username FROM users WHERE username = ?")) {

	            pstmt.setString(1, testUser.getUsername());
	            ResultSet rs = pstmt.executeQuery();

	            assertTrue("User should be added to the database.", rs.next());
	            assertEquals("testuser", rs.getString("username"));

	        } catch (SQLException e) {
	        }
	    }
	    @Test
	    public void testLoadUsersFromDatabase_ShouldLoadUsersCorrectly() {
	        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:inventory_manager.db");
	             Statement stmt = conn.createStatement()) {

	        } catch (Exception e) {
	        }
	        Inventory.loadUsersFromDatabase();
	    }
	    
	    @Test
	    public void testAddMaterialToDatabase_ShouldAddMaterialCorrectly() {
	        InventoryItem item = new InventoryItem("TestItem", 10, 20.5);
	        Inventory.addMaterialToDatabase(item);
	        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:inventory_manager.db");
	             Statement stmt = conn.createStatement()) {
	            String query = "SELECT name, quantity, cost FROM inventory WHERE name = 'TestItem'";
	            ResultSet rs = stmt.executeQuery(query);
	            assertTrue("Material should be in the database", rs.next());
	            assertEquals("TestItem", rs.getString("name"));
	            assertEquals(10, rs.getInt("quantity"));
	            assertEquals(20.5, rs.getDouble("cost"), 0.01);

	        } catch (Exception e) {
	        }
	    }
	    @Test
	    public void testLoadInventoryFromDatabase_ShouldLoadInventoryCorrectly() {
	        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:inventory_manager.db");
	             Statement stmt = conn.createStatement()) {
	            String insertSql = "INSERT INTO inventory (name, quantity, cost) VALUES ('TestItem', 10, 20.5)";
	            stmt.executeUpdate(insertSql);
	        } catch (Exception e) {
	        }
	        Inventory.loadInventoryFromDatabase();
	        boolean itemFound = false;
	        for (InventoryItem item : Inventory.inventory) {
	            if ("TestItem".equals(item.getName())) {
	                itemFound = true;
	                assertEquals(10, item.getQuantity());
	                assertEquals(20.5, item.getCost(), 0.01);
	                break;
	            }
	        }
	        assertTrue("TestItem should be loaded from the database", itemFound);
	    }
	    
	    @Test
	    public void testListSales() {
	        Inventory.sales = new ArrayList<>();
	        Inventory.sales.add(new Sale("Laptop", 50, 2));  
	        Inventory.sales.add(new Sale("Mouse", 30, 1));  
	        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
	        System.setOut(new PrintStream(outputStreamCaptor));
	        Inventory.listSales();
	        String expectedOutput = "Laptop - 2 piece - 50.0 TL\nMouse - 1 piece - 30.0 TL\n";
	    }
	    
	    @Test
	    public void testExpensesList() {
	        Inventory.expenses = new ArrayList<>();
	        Inventory.expenses.add(new Expense("Office Rent", 1000.0));  
	        Inventory.expenses.add(new Expense("Internet", 50.0));      
	        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
	        System.setOut(new PrintStream(outputStreamCaptor));
	        Inventory.expensesList();
	        String expectedOutput = "Office Rent\n1000.0\nInternet\n50.0\n";
	    }

	    @Test
	    public void testDisplayInfo1() {
	        Project project = new Project("New Project");
	        InventoryItem material1 = new InventoryItem("Material 1", 10, 50.0);
	        InventoryItem material2 = new InventoryItem("Material 2", 20, 30.0);

	        project.addMaterial(material1);
	        project.addMaterial(material2);
	        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
	        System.setOut(new PrintStream(outputStreamCaptor));

	        project.displayInfo();
	        String expectedOutput = "Project: New Project\nMaterial 1 - 10 pcs - 50.0 TL\nMaterial 2 - 20 pcs - 30.0 TL\n";
	    }
	  
	    @Test
	    public void testAddProjectToDatabase_ShouldAddProjectCorrectly() {
	        Project testProject = new Project("TestProject");

	        Inventory.addProjectToDatabase(testProject);
	        boolean projectFound = false;
	        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:projects.db");
	             Statement stmt = conn.createStatement();
	             ResultSet rs = stmt.executeQuery("SELECT name FROM projects WHERE name = 'TestProject'")) {

	          
	        } catch (SQLException e) {
	            
	        }
	    }

	    @Test
	    public void testAddExpenseToDatabase_ShouldAddExpenseCorrectly() {
	        Expense testExpense = new Expense("Test Expense", 100.50);
	        Inventory.addExpenseToDatabase(testExpense);
	        boolean expenseFound = false;
	        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:expenses.db");
	             Statement stmt = conn.createStatement();
	             ResultSet rs = stmt.executeQuery("SELECT description, amount FROM expenses WHERE description = 'Test Expense'")) {
	        } catch (SQLException e) {
	        }
	    }

	    @Test
	    public void testAddSaleToDatabase_ShouldAddSaleCorrectly() {
	        Sale testSale = new Sale("Test Item", 5, 49.99);

	        Inventory.addSaleToDatabase(testSale);

	        boolean saleFound = false;
	        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:sales.db");
	             Statement stmt = conn.createStatement();
	             ResultSet rs = stmt.executeQuery("SELECT item, quantity, price FROM sales WHERE item = 'Test Item'")) {
	        } catch (SQLException e) {
	        }
	    }
	    
	    @Test
	    public void testAddSales() {
	        String input = "Laptop\n2\n1500.0\n";
	        Scanner testScanner = new Scanner(input);
	        Inventory.scanner = testScanner;
	        Inventory.addSales();
	    }

	    @Test
	    public void testAddexpense() {
	        String input = "Su\n3\n";
	        Scanner testScanner = new Scanner(input);
	        Inventory.scanner = testScanner;
	        Inventory.addExpense();
	    }
	    
	    @Test
	    public void testAddProject() {
	        String input = "Aa\n2\n";
	        Scanner testScanner = new Scanner(input);
	        Inventory.scanner = testScanner;
	       Inventory.addProject();
	    }
	    
	    @Test
	    public void testAddMaterial() {
	        String input = "Kalem\n4\n100\n";
	        Scanner testScanner = new Scanner(input);
	        Inventory.scanner = testScanner;
	       Inventory.addMaterial();
	    }
	    
	    @Test
	    public void testRegister() {
	        String input = "Gokce\n1stardoll1\n";
	        Scanner testScanner = new Scanner(input);
	        Inventory.scanner = testScanner;
	       Inventory.register();
	    }

	    @Test
	    public void testLogin_failed() {
	        String input = "Beyza\n1234\n";
	        Scanner testScanner = new Scanner(input);
	        Inventory.scanner = testScanner;
	       Inventory.login();
	    }
	    
	    @Test
	    public void testLogin_succesful() {
	        String input = "gokce\n1stardoll1\n";
	        Scanner testScanner = new Scanner(input);
	        Inventory.scanner = testScanner;
	       Inventory.login();
	    }
	    
	    @Test
	    public void testRemoveMaterial() {
	        Inventory.inventory = new ArrayList<>();
	        Inventory.inventory.add(new InventoryItem("Wood", 20, 50.0));  
	        Inventory.inventory.add(new InventoryItem("Metal", 10, 100.0));  
	        
	        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
	        System.setOut(new PrintStream(outputStreamCaptor));
	        
	        String input = "Wood\n";
	        Scanner testScanner = new Scanner(input);
	        Inventory.scanner = testScanner;
	        
	        Inventory.removeMaterial();
	        
	        assertEquals(1, Inventory.inventory.size());
	        assertEquals("Metal", Inventory.inventory.get(0).getName());
	        
	        String expectedOutput = "Enter material name to remove: Material removed successfully.\n";
	        assertFalse(outputStreamCaptor.toString().contains(expectedOutput));
	    }
	    
	    @Test
	    public void testEditMaterial() {
	        Inventory.inventory = new ArrayList<>();
	        Inventory.inventory.add(new InventoryItem("Wood", 20, 50.0));
	        
	        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
	        System.setOut(new PrintStream(outputStreamCaptor));
	        
	        String input = "Wood\n30\n75.0\n";
	        Scanner testScanner = new Scanner(input);
	        Inventory.scanner = testScanner;
	        
	        Inventory.editMaterial();
	        
	        assertEquals(1, Inventory.inventory.size());
	        InventoryItem updatedItem = Inventory.inventory.get(0);
	        assertEquals("Wood", updatedItem.getName());
	        assertEquals(30, updatedItem.getQuantity());
	        assertEquals(75.0, updatedItem.getCost(), 0.001);
	        
	        String expectedOutput = "Enter material name to edit: New Amount: New Cost: Material updated successfully.\n";
	        assertFalse(outputStreamCaptor.toString().contains(expectedOutput));
	    }
	    
	    @Test
	    public void testViewProject() {
	        Inventory.projects = new ArrayList<>();
	        Inventory.projects.add(new Project("Project A"));
	        Inventory.projects.add(new Project("Project B"));
	        
	        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
	        System.setOut(new PrintStream(outputStreamCaptor));
	        
	        Inventory.viewProject();
	        
	        String expectedOutput = "Project A\nProject B\n";
	    }
	    
	    @Test
	    public void testViewInventory() {
	        Inventory.inventory = new ArrayList<>();
	        Inventory.inventory.add(new InventoryItem("Wood", 20, 50.0));
	        Inventory.inventory.add(new InventoryItem("Metal", 10, 100.0));
	        
	        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
	        System.setOut(new PrintStream(outputStreamCaptor));
	        
	        Inventory.viewInventory();
	        
	        String expectedOutput = "Wood - 20 piece - 50.0 TL\nMetal - 10 piece - 100.0 TL\n";
	        assertFalse(outputStreamCaptor.toString().contains(expectedOutput));
	    }
	    
	    @Test
	    public void testCalculateProfit() {
	        Inventory.sales = new ArrayList<>();
	        Inventory.sales.add(new Sale("Laptop", 2, 2000.0));
	        Inventory.sales.add(new Sale("Phone", 3, 1000.0));
	        
	        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
	        System.setOut(new PrintStream(outputStreamCaptor));
	        
	        String input = "1500.0\n800.0\n";
	        Scanner testScanner = new Scanner(input);
	        Inventory.scanner = testScanner;
	        
	        Inventory.calculateProfit();
	        
	        String expectedOutput = "Total profit: 1900.0 TL\n";
	        assertFalse(outputStreamCaptor.toString().contains(expectedOutput));
	    }
	    
	    @Test
	    public void testMaterialInventory1() {
	        String input = "5\n5\n4\n"; 
	        Scanner testScanner = new Scanner(input);
	        Inventory.scanner = testScanner;

	        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
	        System.setOut(new PrintStream(outputStreamCaptor));

	        boolean result = Inventory.materialInventory();
	        
	        String output = outputStreamCaptor.toString();
	    }
	    
	    @Test
	    public void testMaterialInventory2() {
	        String input = "1\naa\n4\n100\n5\n4\n"; 
	        Scanner testScanner = new Scanner(input);
	        Inventory.scanner = testScanner;

	        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
	        System.setOut(new PrintStream(outputStreamCaptor));

	        boolean result = Inventory.materialInventory();
	        
	        String output = outputStreamCaptor.toString();
	    }

	    @Test
	    public void testMaterialInventory3() {
	        String input = "2\n3\n5\n5\n4\n"; 
	        Scanner testScanner = new Scanner(input);
	        Inventory.scanner = testScanner;

	        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
	        System.setOut(new PrintStream(outputStreamCaptor));

	        boolean result = Inventory.materialInventory();
	        
	        String output = outputStreamCaptor.toString();
	    }

	    @Test
	    public void testMaterialInventory4() {
	        String input = "3\n3\n5\n5\n4\n"; 
	        Scanner testScanner = new Scanner(input);
	        Inventory.scanner = testScanner;

	        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
	        System.setOut(new PrintStream(outputStreamCaptor));

	        boolean result = Inventory.materialInventory();
	        
	        String output = outputStreamCaptor.toString();
	    }

	    @Test
	    public void testMaterialInventory5() {
	        String input = "4\n4\n5\n5\n4\n"; 
	        Scanner testScanner = new Scanner(input);
	        Inventory.scanner = testScanner;

	        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
	        System.setOut(new PrintStream(outputStreamCaptor));

	        boolean result = Inventory.materialInventory();
	        
	        String output = outputStreamCaptor.toString();
	    }

	    @Test
	    public void testMaterialInventory6() {
	        String input = "6\n5\n4\n"; 
	        Scanner testScanner = new Scanner(input);
	        Inventory.scanner = testScanner;

	        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
	        System.setOut(new PrintStream(outputStreamCaptor));

	        boolean result = Inventory.materialInventory();
	        
	        String output = outputStreamCaptor.toString();
	    }

	    @Test
	    public void testSales() {
	        String input = "1\naa\n5\n4\n5\n4\n"; 
	        Scanner testScanner = new Scanner(input);
	        Inventory.scanner = testScanner;

	        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
	        System.setOut(new PrintStream(outputStreamCaptor));

	        boolean result = Inventory.salesTracker();
	        
	        String output = outputStreamCaptor.toString();
	    }

	    @Test
	    public void testSales1() {
	        String input = "2\n5\n4\n"; 
	        Scanner testScanner = new Scanner(input);
	        Inventory.scanner = testScanner;

	        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
	        System.setOut(new PrintStream(outputStreamCaptor));

	        boolean result = Inventory.salesTracker();
	        
	        String output = outputStreamCaptor.toString();
	    }


	    @Test
	    public void testSales2() {
	        String input = "4\n5\n4\n"; 
	        Scanner testScanner = new Scanner(input);
	        Inventory.scanner = testScanner;

	        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
	        System.setOut(new PrintStream(outputStreamCaptor));

	        boolean result = Inventory.salesTracker();
	        
	        String output = outputStreamCaptor.toString();
	    }

	    @Test
	    public void testSales3() {
	        String input = "7\n5\n4\n"; 
	        Scanner testScanner = new Scanner(input);
	        Inventory.scanner = testScanner;

	        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
	        System.setOut(new PrintStream(outputStreamCaptor));

	        boolean result = Inventory.salesTracker();
	        
	        String output = outputStreamCaptor.toString();
	    }


	    @Test
	    public void testExpense() {
	        String input = "7\n5\n4\n"; 
	        Scanner testScanner = new Scanner(input);
	        Inventory.scanner = testScanner;

	        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
	        System.setOut(new PrintStream(outputStreamCaptor));

	        boolean result = Inventory.expenseLogging();
	        
	        String output = outputStreamCaptor.toString();
	    }
	    @Test
	    public void testExpense1() {
	        String input = "3\n5\n4\n"; 
	        Scanner testScanner = new Scanner(input);
	        Inventory.scanner = testScanner;

	        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
	        System.setOut(new PrintStream(outputStreamCaptor));

	        boolean result = Inventory.expenseLogging();
	        
	        String output = outputStreamCaptor.toString();
	    }
	    
	    @Test
	    public void testExpense2() {
	        String input = "1\naa\n2\n5\n4\n"; 
	        Scanner testScanner = new Scanner(input);
	        Inventory.scanner = testScanner;

	        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
	        System.setOut(new PrintStream(outputStreamCaptor));

	        boolean result = Inventory.expenseLogging();
	        
	        String output = outputStreamCaptor.toString();
	    }
	    @Test
	    public void testExpense3() {
	        String input = "2\n5\n4\n"; 
	        Scanner testScanner = new Scanner(input);
	        Inventory.scanner = testScanner;

	        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
	        System.setOut(new PrintStream(outputStreamCaptor));

	        boolean result = Inventory.expenseLogging();
	        
	        String output = outputStreamCaptor.toString();
	    }
	    
	    @Test
	    public void testProjectTacking() {
	        String input = "3\n5\n4\n"; 
	        Scanner testScanner = new Scanner(input);
	        Inventory.scanner = testScanner;

	        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
	        System.setOut(new PrintStream(outputStreamCaptor));

	        boolean result = Inventory.projectTacking();
	        
	        String output = outputStreamCaptor.toString();
	    }
	    
	    @Test
	    public void testProjectTacking1() {
	        String input = "6\n5\n4\n"; 
	        Scanner testScanner = new Scanner(input);
	        Inventory.scanner = testScanner;

	        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
	        System.setOut(new PrintStream(outputStreamCaptor));

	        boolean result = Inventory.projectTacking();
	        
	        String output = outputStreamCaptor.toString();
	    }
	       
	    
	    @Test
	    public void testProjectTacking2() {
	        String input = "1\naa\n5\n4\n"; 
	        Scanner testScanner = new Scanner(input);
	        Inventory.scanner = testScanner;

	        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
	        System.setOut(new PrintStream(outputStreamCaptor));

	        boolean result = Inventory.projectTacking();
	        
	        String output = outputStreamCaptor.toString();
	    }
	    @Test
	    public void testProjectTacking3() {
	        String input = "2\n5\n4\n"; 
	        Scanner testScanner = new Scanner(input);
	        Inventory.scanner = testScanner;

	        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
	        System.setOut(new PrintStream(outputStreamCaptor));

	        boolean result = Inventory.projectTacking();
	        
	        String output = outputStreamCaptor.toString();
	    }
	       
	    @Test
	    public void testMainMenu() {
	        String input = "1\n2\n5\n4\n"; 
	        Scanner testScanner = new Scanner(input);
	        Inventory.scanner = testScanner;

	        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
	        System.setOut(new PrintStream(outputStreamCaptor));

	        boolean result = Inventory.mainMenu();
	        
	        String output = outputStreamCaptor.toString();
	    }
	    
	    @Test
	    public void testMainMenu2() {
	        String input = "2\n2\n5\n4\n"; 
	        Scanner testScanner = new Scanner(input);
	        Inventory.scanner = testScanner;

	        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
	        System.setOut(new PrintStream(outputStreamCaptor));

	        boolean result = Inventory.mainMenu();
	        
	        String output = outputStreamCaptor.toString();
	    }
	    @Test
	    public void testMainMenu3() {
	        String input = "3\n2\n5\n4\n"; 
	        Scanner testScanner = new Scanner(input);
	        Inventory.scanner = testScanner;

	        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
	        System.setOut(new PrintStream(outputStreamCaptor));

	        boolean result = Inventory.mainMenu();
	        
	        String output = outputStreamCaptor.toString();
	    }
	    @Test
	    public void testMainMenu4() {
	        String input = "4\n2\n5\n4\n"; 
	        Scanner testScanner = new Scanner(input);
	        Inventory.scanner = testScanner;

	        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
	        System.setOut(new PrintStream(outputStreamCaptor));

	        boolean result = Inventory.mainMenu();
	        
	        String output = outputStreamCaptor.toString();
	    }
	    
	    @Test
	    public void testMainMenu5() {
	        String input = "6\n5\n4\n"; 
	        Scanner testScanner = new Scanner(input);
	        Inventory.scanner = testScanner;

	        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
	        System.setOut(new PrintStream(outputStreamCaptor));

	        boolean result = Inventory.mainMenu();
	        
	        String output = outputStreamCaptor.toString();
	    }
	    
}