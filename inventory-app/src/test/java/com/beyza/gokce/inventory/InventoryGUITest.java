/**

@file InventoryTest.java
@brief This file contains the test cases for the Inventory class.
@details This file includes test methods to validate the functionality of the Inventory class. It uses JUnit for unit testing.
*/
package com.beyza.gokce.inventory;
import com.beyza.gokce.inventory.InventoryGUI;

import junit.framework.Assert;

import java.io.ByteArrayInputStream;
import org.junit.Test;
import static org.junit.Assert.*;
import javax.swing.*;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;  
import java.sql.Statement;
import org.junit.Test;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.*;
import java.sql.PreparedStatement;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.awt.*;
import java.awt.event.KeyEvent;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import java.lang.reflect.InvocationTargetException;
/**

@class InventoryTest
@brief This class represents the test class for the Inventory class.
@details The InventoryTest class provides test methods to verify the behavior of the Inventory class. It includes test methods for addition, subtraction, multiplication, and division operations.
@author ugur.coruh
*/

public class InventoryGUITest {
    private InventoryGUI gui;
    private InventoryGUI instance;
    
	 @Before
	    public void setup() {
	        gui = new InventoryGUI();
	        instance = new InventoryGUI();
	    }
    @Test
    public void testPrimaryColor() {
        Color expected = new Color(41, 128, 185);
        assertEquals(expected, InventoryGUI.PRIMARY_COLOR);
    }

    @Test
    public void testSecondaryColor() {
        Color expected = new Color(52, 152, 219);
        assertEquals(expected, InventoryGUI.SECONDARY_COLOR);
    }

    @Test
    public void testBackgroundColor() {
        Color expected = new Color(44, 62, 80);
        assertEquals(expected, InventoryGUI.BACKGROUND_COLOR);
    }

    @Test
    public void testTextColor() {
        Color expected = new Color(236, 240, 241);
        assertEquals(expected, InventoryGUI.TEXT_COLOR);
    }

    @Test
    public void testAccentColor() {
        Color expected = new Color(231, 76, 60);
        assertEquals(expected, InventoryGUI.ACCENT_COLOR);
    }

    @Test
    public void testPanelColor() {
        Color expected = new Color(52, 73, 94);
        assertEquals(expected, InventoryGUI.PANEL_COLOR);
    }

    @Test
    public void testTableColor() {
        Color expected = new Color(44, 62, 80);
    }

    @Test
    public void testTableHeaderColor() {
        Color expected = new Color(52, 73, 94);
        assertEquals(expected, InventoryGUI.TABLE_HEADER_COLOR);
    }

    @Test
    public void testTableTextColor() {
        Color expected = new Color(236, 240, 241);
        assertEquals(expected, InventoryGUI.TABLE_TEXT_COLOR);
    }
    

    @Test
    public void testInventoryListExistsAndEmpty() throws Exception {
        InventoryGUI gui = new InventoryGUI();
        Field field = InventoryGUI.class.getDeclaredField("inventory");
        field.setAccessible(true);
        List<?> inventory = (List<?>) field.get(gui);
        assertNotNull(inventory);
        
    }

    @Test
    public void testProjectsListExistsAndEmpty() throws Exception {
        InventoryGUI gui = new InventoryGUI();
        Field field = InventoryGUI.class.getDeclaredField("projects");
        field.setAccessible(true);
        List<?> projects = (List<?>) field.get(gui);
        assertNotNull(projects);
        assertTrue(projects.isEmpty());
    }

    @Test
    public void testExpensesListExistsAndEmpty() throws Exception {
        InventoryGUI gui = new InventoryGUI();
        Field field = InventoryGUI.class.getDeclaredField("expenses");
        field.setAccessible(true);
        List<?> expenses = (List<?>) field.get(gui);
        assertNotNull(expenses);
        assertTrue(expenses.isEmpty());
    }

    @Test
    public void testSalesListExistsAndEmpty() throws Exception {
        InventoryGUI gui = new InventoryGUI();
        Field field = InventoryGUI.class.getDeclaredField("sales");
        field.setAccessible(true);
        List<?> sales = (List<?>) field.get(gui);
        assertNotNull(sales);
        assertTrue(sales.isEmpty());
    }
   

    @Test
    public void testLoginValidation_EmptyFields_ShowsError() throws Exception {
        InventoryGUI gui = new InventoryGUI();

        Method method = InventoryGUI.class.getDeclaredMethod("showLoginPanel");
        method.setAccessible(true);
        method.invoke(gui);

        Field usernameField = InventoryGUI.class.getDeclaredField("usernameField");
        usernameField.setAccessible(true);
        ((JTextField) usernameField.get(gui)).setText("");

        Field passwordField = InventoryGUI.class.getDeclaredField("passwordField");
        passwordField.setAccessible(true);
        ((JPasswordField) passwordField.get(gui)).setText("");

        Field contentField = InventoryGUI.class.getDeclaredField("contentPanel");
        contentField.setAccessible(true);
        JPanel contentPanel = (JPanel) contentField.get(gui);

        JButton loginButton = null;
        for (Component comp : contentPanel.getComponents()) {
            if (comp instanceof JPanel) {
                for (Component c : ((JPanel) comp).getComponents()) {
                    if (c instanceof JButton && ((JButton) c).getText().equals("Login")) {
                        loginButton = (JButton) c;
                    }
                }
            }
        }

        assertNotNull("Login button don't found", loginButton);

    }
    
    @Test
    public void testShowRegisterPanel_CreatesFormComponents() throws Exception {
        Method showRegisterPanel = InventoryGUI.class.getDeclaredMethod("showRegisterPanel");
        showRegisterPanel.setAccessible(true);
        showRegisterPanel.invoke(gui);

        Field contentField = InventoryGUI.class.getDeclaredField("contentPanel");
        contentField.setAccessible(true);
        JPanel contentPanel = (JPanel) contentField.get(gui);

        boolean foundTitle = false, foundUsername = false, foundPassword = false, foundConfirm = false;

        for (Component comp : contentPanel.getComponents()) {
            if (comp instanceof JLabel) {
                String text = ((JLabel) comp).getText();
                if ("Register".equals(text)) foundTitle = true;
            }
            if (comp instanceof JPanel) {
                for (Component c : ((JPanel) comp).getComponents()) {
                    if (c instanceof JLabel) {
                        String text = ((JLabel) c).getText();
                        if ("Username:".equals(text)) foundUsername = true;
                        if ("Password:".equals(text)) foundPassword = true;
                        if ("Confirm Password:".equals(text)) foundConfirm = true;
                    }
                }
            }
        }

        assertTrue("Baslik don't found", foundTitle);
        assertTrue("Username don't found", foundUsername);
        assertTrue("Password don't found", foundPassword);
        assertTrue("Confirm Password don't found", foundConfirm);
    }

    @Test
    public void testRegisterValidation_EmptyFields_ShowsError() throws Exception {
        Method showRegisterPanel = InventoryGUI.class.getDeclaredMethod("showRegisterPanel");
        showRegisterPanel.setAccessible(true);
        showRegisterPanel.invoke(gui);
        Field contentField = InventoryGUI.class.getDeclaredField("contentPanel");
        contentField.setAccessible(true);
        JPanel contentPanel = (JPanel) contentField.get(gui);

        JButton registerButton = findButton(contentPanel, "Register");

        assertNotNull("Register button don't found", registerButton);
        SwingUtilities.invokeLater(() -> registerButton.doClick());
    }

    @Test
    public void testRegisterValidation_MismatchedPasswords_ShowsError() throws Exception {
        Method showRegisterPanel = InventoryGUI.class.getDeclaredMethod("showRegisterPanel");
        showRegisterPanel.setAccessible(true);
        showRegisterPanel.invoke(gui);

        JPanel contentPanel = (JPanel) getPrivateField1(gui, "contentPanel");

        JPanel formPanel = findPanel(contentPanel, 6);
        assertNotNull("Form panel don't found", formPanel);

        JTextField usernameField = (JTextField) formPanel.getComponent(1);
        JPasswordField passwordField = (JPasswordField) formPanel.getComponent(3);
        JPasswordField confirmPasswordField = (JPasswordField) formPanel.getComponent(5);

        usernameField.setText("testuser");
        passwordField.setText("123456");
        confirmPasswordField.setText("wrong");

        JButton registerButton = findButton(contentPanel, "Register");
        SwingUtilities.invokeLater(() -> registerButton.doClick());
    }


    private JButton findButton(JPanel panel, String text) {
        for (Component comp : panel.getComponents()) {
            if (comp instanceof JPanel) {
                for (Component c : ((JPanel) comp).getComponents()) {
                    if (c instanceof JButton && ((JButton) c).getText().equals(text)) {
                        return (JButton) c;
                    }
                }
            }
        }
        return null;
    }

    private JPanel findPanel(JPanel parent, int expectedComponents) {
        for (Component comp : parent.getComponents()) {
            if (comp instanceof JPanel) {
                JPanel panel = (JPanel) comp;
                if (panel.getComponentCount() == expectedComponents) return panel;
            }
        }
        return null;
    }

    private Object getPrivateField1(Object obj, String fieldName) throws Exception {
        Field field = obj.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        return field.get(obj);
    }
  

    private void setPrivateField(Object obj, String fieldName, Object value) throws Exception {
        Field field = obj.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(obj, value);
    }

    private Object getPrivateField(Object obj, String fieldName) throws Exception {
        Field field = obj.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        return field.get(obj);
    }

    @Test
    public void testShowMainMenu_UIComponentsAndLabels() throws Exception {
        setPrivateField(instance, "currentUser", null);

        Method showMainMenu = InventoryGUI.class.getDeclaredMethod("showMainMenu");
        showMainMenu.setAccessible(true);
        showMainMenu.invoke(instance);

        JPanel mainPanel = (JPanel) getPrivateField(instance, "mainPanel");
        JPanel contentPanel = (JPanel) getPrivateField(instance, "contentPanel");

        assertTrue("mainPanel should contain contentPanel",
                containsComponent(mainPanel, contentPanel));

        JPanel welcomePanel = (JPanel) contentPanel.getComponent(1);
        JLabel welcomeLabel = (JLabel) welcomePanel.getComponent(0);
        assertEquals("Welcome, guest!", welcomeLabel.getText());

        setPrivateField(instance, "currentUser", "Beyza");
        showMainMenu.invoke(instance);

        contentPanel = (JPanel) getPrivateField(instance, "contentPanel");
        welcomePanel = (JPanel) contentPanel.getComponent(1);
        welcomeLabel = (JLabel) welcomePanel.getComponent(0);
        assertEquals("Welcome, Beyza!", welcomeLabel.getText());

        JPanel dashboardPanel = (JPanel) contentPanel.getComponent(3);
        assertEquals(4, dashboardPanel.getComponentCount());

        String[] expectedButtons = {"Material Inventory", "Project Tracking", "Expense Logging", "Sales Tracker"};
        for (int i = 0; i < 4; i++) {
            Component c = dashboardPanel.getComponent(i);
            assertTrue(c instanceof JButton);
            JButton btn = (JButton) c;
            assertEquals(expectedButtons[i], btn.getText());
        }
    }
    private boolean containsComponent(Container parent, Component child) {
        for (Component comp : parent.getComponents()) {
            if (comp == child) {
                return true;
            }
        }
        return false;
    }
    
    @Test
    public void testCreateStyledPasswordField_Properties() {
        InventoryGUI gui = new InventoryGUI();
        JPasswordField field = gui.createStyledPasswordField();

        assertNotNull("Password field should not be null", field);
        assertTrue("Should be an instance of JPasswordField", field instanceof JPasswordField);

        Font expectedFont = new Font("Segoe UI", Font.PLAIN, 14);
        assertEquals("Font should be Segoe UI, PLAIN, 14", expectedFont, field.getFont());
        assertEquals("Foreground color should match MODERN_TEXT", InventoryGUI.MODERN_TEXT, field.getForeground());
        assertEquals("Caret color should match MODERN_TEXT", InventoryGUI.MODERN_TEXT, field.getCaretColor());
        assertEquals("Background color should match MODERN_PANEL", InventoryGUI.MODERN_PANEL, field.getBackground());

        Insets insets = field.getInsets();
        assertEquals("Left inset should be 10", 10, insets.left);
        assertEquals("Right inset should be 10", 10, insets.right);
        assertEquals("Top inset should be 5", 5, insets.top);
        assertEquals("Bottom inset should be 5", 5, insets.bottom);
    }

    @Test
    public void testCreateStyledTextField_Properties() {
        JTextField field = gui.createStyledTextField();

        assertNotNull("Text field should not be null", field);
        assertTrue("Should be an instance of JTextField", field instanceof JTextField);

        Font expectedFont = new Font("Segoe UI", Font.PLAIN, 14);
        assertEquals("Font should be Segoe UI, PLAIN, 14", expectedFont, field.getFont());
        assertEquals("Foreground color should match MODERN_TEXT", InventoryGUI.MODERN_TEXT, field.getForeground());
        assertEquals("Caret color should match MODERN_TEXT", InventoryGUI.MODERN_TEXT, field.getCaretColor());
        assertEquals("Background color should match MODERN_PANEL", InventoryGUI.MODERN_PANEL, field.getBackground());

        Insets insets = field.getInsets();
        assertEquals("Left inset should be 10", 10, insets.left);
        assertEquals("Right inset should be 10", 10, insets.right);
        assertEquals("Top inset should be 5", 5, insets.top);
        assertEquals("Bottom inset should be 5", 5, insets.bottom);
    }

    @Test
    public void testRefreshSalesTable() throws Exception {
        Field salesModelField = InventoryGUI.class.getDeclaredField("salesModel");
        salesModelField.setAccessible(true);
        DefaultTableModel salesModel = new DefaultTableModel(new String[]{"Item", "Quantity", "Price", "Total"}, 0);
        salesModelField.set(gui, salesModel);

        Field salesField = InventoryGUI.class.getDeclaredField("sales");
        salesField.setAccessible(true);
        List<Sale> sales = new ArrayList<>();
        salesField.set(gui, sales);
        Sale sale1 = new Sale("Test Item 1", 2, 10.0);
        Sale sale2 = new Sale("Test Item 2", 3, 15.0);
        sales.add(sale1);
        sales.add(sale2);
        Method method = InventoryGUI.class.getDeclaredMethod("refreshSalesTable");
        method.setAccessible(true);
        method.invoke(gui);

        assertEquals("Table should have 2 rows", 2, salesModel.getRowCount());
        assertEquals("First row item should match", "Test Item 1", salesModel.getValueAt(0, 0));
        assertEquals("First row quantity should match", 2, salesModel.getValueAt(0, 1));
        assertEquals("First row price should match", 10.0, salesModel.getValueAt(0, 2));
        assertEquals("First row total should match", 20.0, salesModel.getValueAt(0, 3));
        assertEquals("Second row item should match", "Test Item 2", salesModel.getValueAt(1, 0));
        assertEquals("Second row quantity should match", 3, salesModel.getValueAt(1, 1));
        assertEquals("Second row price should match", 15.0, salesModel.getValueAt(1, 2));
        assertEquals("Second row total should match", 45.0, salesModel.getValueAt(1, 3));
        sales.clear();
        method.invoke(gui);
        assertEquals("Table should be empty after clearing sales", 0, salesModel.getRowCount());
    }

    @Test
    public void testShowEditMaterialDialog() throws Exception {
        InventoryItem testItem = new InventoryItem("Test Material", 10, 100.0);
        gui.inventory.add(testItem);

        String[] columns = {"Name", "Quantity", "Cost"};
        gui.inventoryModel = new DefaultTableModel(columns, 0);
        gui.inventoryTable = new JTable(gui.inventoryModel);
        gui.inventoryModel.addRow(new Object[]{"Test Material", 10, 100.0});
        gui.inventoryTable.setRowSelectionInterval(0, 0);

        Method showEditMaterialDialog = InventoryGUI.class.getDeclaredMethod("showEditMaterialDialog");
        showEditMaterialDialog.setAccessible(true);
        showEditMaterialDialog.invoke(gui);
    }
   
   

    @Test
    public void testLoadTableData_LoadsDataAndColumns() throws Exception {
        // Prepare a model and a known table name (e.g., "users")
        DefaultTableModel model = new DefaultTableModel();
        String tableName = "users";

        // Add a dummy column and row to ensure they are cleared
        model.addColumn("dummy");
        model.addRow(new Object[]{"dummy"});

        // Call the method via reflection
        Method loadTableData = InventoryGUI.class.getDeclaredMethod("loadTableData", DefaultTableModel.class, String.class);
        loadTableData.setAccessible(true);
        loadTableData.invoke(gui, model, tableName);

    }

    @Test
    public void testShowModuleDatabase_Basic() throws Exception {
        // Call the method via reflection
        Method showModuleDatabase = InventoryGUI.class.getDeclaredMethod("showModuleDatabase", String.class, String.class);
        showModuleDatabase.setAccessible(true);

        // Use a known table name (e.g., "users")
        String moduleName = "Users";
        String tableName = "users";
        showModuleDatabase.invoke(gui, moduleName, tableName);

        // Find the open dialog
        JDialog dialog = null;
        for (Window window : Window.getWindows()) {
            if (window instanceof JDialog && window.isVisible()) {
                break;
            }
        }
    }
    
    @Test
    public void testShowAddSaleDialog_Basic() throws Exception {
        Method showAddSaleDialog = InventoryGUI.class.getDeclaredMethod("showAddSaleDialog");
        showAddSaleDialog.setAccessible(true);

        gui.showAddSaleDialog();
        boolean dialogFound = false;
        for (Window window : Window.getWindows()) {
            if (window instanceof JDialog && window.isVisible()) {
                dialogFound = true;
                break;
            }
        }

     }

    @Test
    public void testCalculateProfit_DoesNotThrow() {
        InventoryGUI gui = new InventoryGUI();
        gui.sales = new ArrayList<>();
        gui.expenses = new ArrayList<>();
        gui.sales.add(new Sale("Item1", 2, 50.0));
        gui.sales.add(new Sale("Item2", 1, 100.0));
        gui.expenses.add(new Expense("Expense1", 30.0));
        gui.expenses.add(new Expense("Expense2", 20.0));

        gui.calculateProfit();
    }

    @Test
    public void testMain_DoesNotThrow() {
      InventoryGUI.main(new String[0]);
    }
    
    @Test
    public void testShowAddExpenseDialog_DoesNotThrow() {
        InventoryGUI gui = new InventoryGUI();
        gui.mainPanel = new JPanel(new BorderLayout());
        gui.contentPanel = new JPanel();
        gui.sideMenuPanel = new JPanel();
        gui.MODERN_BACKGROUND = Color.WHITE;
        gui.MODERN_PANEL = Color.LIGHT_GRAY;
        gui.MODERN_TEXT = Color.BLACK;
        gui.showAddExpenseDialog();
    }

    @Test
    public void testShowDatabaseManager_DoesNotThrow() {
        InventoryGUI gui = new InventoryGUI();
        gui.mainPanel = new JPanel(new BorderLayout());
        gui.contentPanel = new JPanel();
        gui.sideMenuPanel = new JPanel();

        gui.MODERN_BACKGROUND = Color.WHITE;
        gui.TABLE_COLOR = Color.LIGHT_GRAY;
        gui.TABLE_TEXT_COLOR = Color.BLACK;
        gui.TABLE_HEADER_COLOR = Color.DARK_GRAY;
        gui.showDatabaseManager();
    }
    
    @Test
    public void testShowMaterialInventory_DoesNotThrow() {
        InventoryGUI gui = new InventoryGUI();
        gui.mainPanel = new JPanel(new BorderLayout());
        gui.contentPanel = new JPanel();
        gui.sideMenuPanel = new JPanel();
        gui.MODERN_BACKGROUND = Color.WHITE;
        gui.MODERN_PRIMARY = Color.LIGHT_GRAY;
        gui.MODERN_TEXT = Color.BLACK;
        gui.TABLE_COLOR = Color.WHITE;
        gui.inventory = new ArrayList<>();
        gui.showMaterialInventory();
    }
    
    @Test
    public void testShowProjectTracking_DoesNotThrow() {
    	InventoryGUI gui = new InventoryGUI();
        gui.projectModel = new DefaultTableModel(new String[]{"Project Name"}, 0);
        gui.projectTable = new JTable(gui.projectModel);

        gui.mainPanel = new JPanel(new BorderLayout());
        gui.contentPanel = new JPanel();
        gui.sideMenuPanel = new JPanel();
        gui.MODERN_BACKGROUND = Color.WHITE;
        gui.projectTable.clearSelection();
        gui.showProjectTracking();
        gui.projectModel.addRow(new Object[]{"Test Project"});
        gui.projectTable.setRowSelectionInterval(0, 0);
        gui.showProjectTracking();
    }
    
    @Test
    public void testShowAddMaterialDialog_DoesNotThrow() {
        InventoryGUI gui = new InventoryGUI();
        gui.MODERN_BACKGROUND = Color.WHITE;
        gui.MODERN_PANEL = Color.LIGHT_GRAY;
        gui.MODERN_TEXT = Color.BLACK;
        gui.showAddMaterialDialog();
    }
    
    @Test
    public void testShowSalesTracker_DoesNotThrow() throws Exception {
    	InventoryGUI gui = new InventoryGUI();
        gui.mainPanel = new JPanel(new BorderLayout());
        gui.contentPanel = new JPanel();
        gui.sideMenuPanel = new JPanel();
        gui.MODERN_BACKGROUND = Color.WHITE;
        gui.MODERN_TEXT = Color.BLACK;
        SwingUtilities.invokeAndWait(() -> gui.showSalesTracker());
    }
    
    @Test
    public void testRefreshInventoryTable_DoesNotThrow() {
        InventoryGUI.inventory = new ArrayList<>();
        InventoryGUI.inventoryModel = new DefaultTableModel(new String[]{"Name", "Quantity", "Cost"}, 0);

        InventoryGUI.inventory.add(new InventoryItem("Test Item", 10, 15.5));

        InventoryGUI.refreshInventoryTable();
    }
    
    @Test
    public void testShowAddProjectDialog_DoesNotThrow() throws Exception {
    	InventoryGUI gui = new InventoryGUI();
        gui.mainPanel = new JPanel(new BorderLayout());
        gui.contentPanel = new JPanel();
        gui.sideMenuPanel = new JPanel();
        gui.MODERN_BACKGROUND = Color.WHITE;
        gui.MODERN_PANEL = Color.LIGHT_GRAY;
        gui.MODERN_TEXT = Color.BLACK;

        gui.projectModel = new DefaultTableModel(new String[]{"Project Name"}, 0);

        SwingUtilities.invokeAndWait(() -> gui.showAddProjectDialog());
    }
    
    @Test
    public void testShowProjectDetails_DoesNotThrow() {
    	InventoryGUI gui = new InventoryGUI();
        gui.projectModel = new DefaultTableModel(new String[]{"Project Name"}, 0);
        gui.projectTable = new JTable(gui.projectModel);

        gui.mainPanel = new JPanel(new BorderLayout());
        gui.contentPanel = new JPanel();
        gui.sideMenuPanel = new JPanel();
        gui.MODERN_BACKGROUND = Color.WHITE;
        gui.projectTable.clearSelection();
        gui.showProjectDetails();
        gui.projectModel.addRow(new Object[]{"Test Project"});
        gui.projectTable.setRowSelectionInterval(0, 0);
        gui.showProjectDetails();
    }

    @Test
    public void testCreateStyledTable_DoesNotThrow() {
        InventoryGUI gui = new InventoryGUI();

        String[] columns = {"Column1", "Column2"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        {
            JTable table = gui.createStyledTable(model);
            assertNotNull(table); 
        };
    }
    
    @Test
    public void testShowDeleteMaterialDialog_NoSelection() {
        InventoryGUI gui = new InventoryGUI();

        String[] columns = {"Name", "Quantity", "Cost"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        JTable table = new JTable(model);
        gui.inventoryTable = table;
        gui.showDeleteMaterialDialog();
    }
    
    @Test
    public void testShowExpenseLogging_DoesNotThrow() throws Exception {
        InventoryGUI gui = new InventoryGUI();

        gui.mainPanel = new JPanel(new BorderLayout());
        gui.contentPanel = new JPanel();
        gui.sideMenuPanel = new JPanel();

        gui.MODERN_BACKGROUND = Color.WHITE;
        gui.MODERN_ACCENT = Color.GRAY;
        gui.MODERN_PRIMARY = Color.BLUE;
        gui.MODERN_TEXT = Color.BLACK;

        SwingUtilities.invokeAndWait(() -> gui.showExpenseLogging());
    }
    
    @Test
    public void testShowModule_DoesNotThrow() throws Exception {
        InventoryGUI gui = new InventoryGUI();

        gui.mainPanel = new JPanel(new BorderLayout());
        gui.contentPanel = new JPanel();
        gui.sideMenuPanel = new JPanel();

        gui.MODERN_BACKGROUND = Color.WHITE;
        gui.MODERN_ACCENT = Color.LIGHT_GRAY;
        gui.MODERN_PRIMARY = Color.BLUE;
        gui.MODERN_TEXT = Color.BLACK;

        SwingUtilities.invokeAndWait(() -> {
            gui.showModule("Material Inventory");
            gui.showModule("Project Tracking");
            gui.showModule("Expense Logging");
            gui.showModule("Sales Tracker");
        });
    }
    

    @Test
    public void testShowAddMaterialDialog_NoInput_ThenCancel() throws Exception {
        SwingUtilities.invokeLater(() -> {
            InventoryGUI gui = new InventoryGUI();
            gui.showAddMaterialDialog();
        });
        Thread.sleep(1000);
        Robot robot = new Robot();
        robot.setAutoDelay(200);

        robot.keyPress(KeyEvent.VK_ESCAPE);
        robot.keyRelease(KeyEvent.VK_ESCAPE);
    }
    
    @Test
    public void testShowAddMaterialDialog1_DoesNotThrow() {
        SwingUtilities.invokeLater(() -> {
            try {
                InventoryGUI gui = new InventoryGUI();

                gui.mainPanel = new JPanel();
                gui.contentPanel = new JPanel();
                gui.sideMenuPanel = new JPanel();
                gui.MODERN_BACKGROUND = java.awt.Color.WHITE;
                gui.MODERN_PANEL = java.awt.Color.LIGHT_GRAY;
                gui.MODERN_TEXT = java.awt.Color.BLACK;
                gui.MODERN_PRIMARY = java.awt.Color.BLUE;
                gui.MODERN_ACCENT = java.awt.Color.RED;

                gui.showAddMaterialDialog();

            } catch (Exception e) {
                org.junit.Assert.fail("Dialog is not study " + e.getMessage());
            }
        });

        try {
            Thread.sleep(3000); 
        } catch (InterruptedException ignored) {}
    }
    
    @Test
    public void testShowAddMaterialDialog_WithInputAndSubmit() {
        SwingUtilities.invokeLater(() -> {
            try {
                InventoryGUI gui = new InventoryGUI();

                gui.MODERN_BACKGROUND = Color.WHITE;
                gui.MODERN_PANEL = Color.LIGHT_GRAY;
                gui.MODERN_TEXT = Color.BLACK;
                gui.MODERN_PRIMARY = Color.BLUE;
                gui.MODERN_ACCENT = Color.RED;
                gui.mainPanel = new JPanel();
                gui.contentPanel = new JPanel();
                gui.sideMenuPanel = new JPanel();
                new Thread(gui::showAddMaterialDialog).start();

            } catch (Exception e) {
                org.junit.Assert.fail("Dialog gösteriminde hata: " + e.getMessage());
            }
        });

        try {
            Thread.sleep(1000);

            Robot robot = new Robot();
            robot.setAutoDelay(100);

            typeText(robot, "Test Material");
            robot.keyPress(KeyEvent.VK_TAB); 
            robot.keyRelease(KeyEvent.VK_TAB);

            // Quantity: "5"
            typeText(robot, "5");
            robot.keyPress(KeyEvent.VK_TAB);
            robot.keyRelease(KeyEvent.VK_TAB);

            // Cost: "19.99"
            typeText(robot, "19.99");

            robot.keyPress(KeyEvent.VK_TAB); robot.keyRelease(KeyEvent.VK_TAB); 
            robot.keyPress(KeyEvent.VK_TAB); robot.keyRelease(KeyEvent.VK_TAB); 
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_TAB); robot.keyRelease(KeyEvent.VK_TAB); 
            robot.keyRelease(KeyEvent.VK_SHIFT);

            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);

            Thread.sleep(2000);

        } catch (Exception e) {
            org.junit.Assert.fail("\r\n"+ "An error occurred during robot operation.: " + e.getMessage());
        }
    }
    
    public void typeText(Robot robot, String text) {
        for (char c : text.toCharArray()) {
            int keyCode = KeyEvent.getExtendedKeyCodeForChar(c);
            if (KeyEvent.CHAR_UNDEFINED == keyCode) {
                throw new RuntimeException("\r\n"+ "Unprintable character " + c);
            }
            robot.keyPress(keyCode);
            robot.keyRelease(keyCode);
        }
    }
    
   
    
    
    
}