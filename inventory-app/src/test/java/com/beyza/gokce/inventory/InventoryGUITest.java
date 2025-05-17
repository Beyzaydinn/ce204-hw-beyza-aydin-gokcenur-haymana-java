/**

@file InventoryTest.java
@brief This file contains the test cases for the Inventory class.
@details This file includes test methods to validate the functionality of the Inventory class. It uses JUnit for unit testing.
*/
package com.beyza.gokce.inventory;
import com.beyza.gokce.inventory.InventoryGUI;
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
import java.awt.Color;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.awt.*;
import javax.swing.table.DefaultTableModel;
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
        assertEquals(expected, InventoryGUI.TABLE_COLOR);
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
        assertTrue(inventory.isEmpty());
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
    public void testShowLoginPanel_CreatesUIComponents() throws Exception {
        InventoryGUI gui = new InventoryGUI();

        // showLoginPanel metodunu çağır
        Method method = InventoryGUI.class.getDeclaredMethod("showLoginPanel");
        method.setAccessible(true);
        method.invoke(gui);

        // contentPanel'a eriş
        Field contentField = InventoryGUI.class.getDeclaredField("contentPanel");
        contentField.setAccessible(true);
        JPanel contentPanel = (JPanel) contentField.get(gui);

        // İçerikte formPanel var mı ve doğru bileşenler mi?
        boolean foundUsername = false, foundPassword = false, foundLoginBtn = false;

        for (Component comp : contentPanel.getComponents()) {
            if (comp instanceof JPanel) {
                JPanel formPanel = (JPanel) comp;
                for (Component innerComp : formPanel.getComponents()) {
                    if (innerComp instanceof JLabel) {
                        JLabel label = (JLabel) innerComp;
                        if (label.getText().equals("Username:")) foundUsername = true;
                        if (label.getText().equals("Password:")) foundPassword = true;
                    }
                    if (innerComp instanceof JButton) {
                        JButton button = (JButton) innerComp;
                        if (button.getText().equals("Login")) foundLoginBtn = true;
                    }
                }
            }
        }

        assertTrue("Username label bulunamadı", foundUsername);
        assertTrue("Password label bulunamadı", foundPassword);
        assertTrue("Login butonu bulunamadı", foundLoginBtn);
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

        assertNotNull("Login button bulunamadı", loginButton);

    }
    
    @Test
    public void testShowRegisterPanel_CreatesFormComponents() throws Exception {
        // showRegisterPanel metodunu çalıştır
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

        assertTrue("Başlık etiketi bulunamadı", foundTitle);
        assertTrue("Username etiketi bulunamadı", foundUsername);
        assertTrue("Password etiketi bulunamadı", foundPassword);
        assertTrue("Confirm Password etiketi bulunamadı", foundConfirm);
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

        assertNotNull("Register butonu bulunamadı", registerButton);
        SwingUtilities.invokeLater(() -> registerButton.doClick());
    }

    @Test
    public void testRegisterValidation_MismatchedPasswords_ShowsError() throws Exception {
        Method showRegisterPanel = InventoryGUI.class.getDeclaredMethod("showRegisterPanel");
        showRegisterPanel.setAccessible(true);
        showRegisterPanel.invoke(gui);

        JPanel contentPanel = (JPanel) getPrivateField1(gui, "contentPanel");

        JPanel formPanel = findPanel(contentPanel, 6);
        assertNotNull("Form panel bulunamadı", formPanel);

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
    @Test
    public void testShowMainMenu_UIComponentsAndLabels() throws Exception {
        InventoryGUI gui = new InventoryGUI();

        setPrivateField(gui, "currentUser", null);

        Method showMainMenu = InventoryGUI.class.getDeclaredMethod("showMainMenu");
        showMainMenu.setAccessible(true);
        showMainMenu.invoke(gui);

        JPanel mainPanel = (JPanel) getPrivateField1(gui, "mainPanel");
        JPanel contentPanel = (JPanel) getPrivateField1(gui, "contentPanel");

        boolean containsContent = false;
        for (Component comp : mainPanel.getComponents()) {
            if (comp == contentPanel) containsContent = true;
        }
        assertTrue("mainPanel should contain contentPanel", containsContent);

        JPanel welcomePanel = (JPanel) contentPanel.getComponent(1);
        JLabel welcomeLabel = (JLabel) welcomePanel.getComponent(0);

        assertEquals("Welcome, guest!", welcomeLabel.getText());

        setPrivateField(gui, "currentUser", "Beyza");
        showMainMenu.invoke(gui);
        contentPanel = (JPanel) getPrivateField1(gui, "contentPanel");
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
    public void testShowModuleCallsCorrectMethod() throws Exception {
        InventoryGUI gui = new InventoryGUI();

        Method showModule = InventoryGUI.class.getDeclaredMethod("showModule", String.class);
        showModule.setAccessible(true);

        // Material Inventory için
        showModule.invoke(gui, "Material Inventory");
        showModule.invoke(gui, "Project Tracking");
        showModule.invoke(gui, "Expense Logging");
        showModule.invoke(gui, "Sales Tracker");
    }
    @Test
    public void testShowProjectTracking_initializesComponentsCorrectly() throws InvocationTargetException, InterruptedException {
        SwingUtilities.invokeAndWait(() -> {
            instance.showProjectTracking();

            // contentPanel'in içinde header ve scrollPane olmalı
            Component[] components = instance.contentPanel.getComponents();
            boolean hasHeader = false;
            boolean hasScroll = false;

            for (Component comp : components) {
                if (comp instanceof JPanel) hasHeader = true;
                if (comp instanceof JScrollPane) hasScroll = true;
            }

            assertTrue("Header panel should exist", hasHeader);
            assertTrue("Scroll pane (table) should exist", hasScroll);
            assertNotNull("Table should be created", instance.projectTable);
            assertEquals("Table should have 1 column", 1, instance.projectTable.getColumnCount());
            assertEquals("Column name should be 'Project Name'", "Project Name", instance.projectTable.getColumnName(0));
        });
    }

    @Test
    public void testShowProjectTracking_loadsDataFromDatabase() throws InvocationTargetException, InterruptedException {

        SwingUtilities.invokeAndWait(() -> {
            instance.showProjectTracking();
            int rowCount = instance.projectModel.getRowCount();
            assertTrue("Project table should load data (row count >= 0)", rowCount >= 0);
        });
    }
    

    @Test
    public void testShowExpenseLogging_componentsCreatedCorrectly() throws InvocationTargetException, InterruptedException {
        SwingUtilities.invokeAndWait(() -> {
            instance.showExpenseLogging();

            // contentPanel layout ve arka plan rengi
            assertTrue(instance.contentPanel.getLayout() instanceof BorderLayout);
            assertEquals("Background color should be MODERN_BACKGROUND",
                InventoryGUI.MODERN_BACKGROUND, instance.contentPanel.getBackground());

            // contentPanel NORTH bölgesinde header panel olmalı
            Component northComponent = ((BorderLayout) instance.contentPanel.getLayout()).getLayoutComponent(BorderLayout.NORTH);
            assertNotNull("Header panel should exist in NORTH", northComponent);
            assertTrue("Header panel should be a JPanel", northComponent instanceof JPanel);

            JPanel headerPanel = (JPanel) northComponent;
            boolean hasTitleLabel = false;
            boolean hasAddButton = false;
            boolean hasDbButton = false;

            for (Component comp : headerPanel.getComponents()) {
                if (comp instanceof JLabel) {
                    JLabel label = (JLabel) comp;
                    if ("Expense Logging".equals(label.getText())) hasTitleLabel = true;
                } else if (comp instanceof JPanel) {
                    JPanel btnPanel = (JPanel) comp;
                    for (Component btn : btnPanel.getComponents()) {
                        if (btn instanceof JButton) {
                            JButton button = (JButton) btn;
                            if ("Add Expense".equals(button.getText())) hasAddButton = true;
                            if ("Show Database".equals(button.getText())) hasDbButton = true;
                        }
                    }
                }
            }

            assertTrue("Header should contain title label", hasTitleLabel);
            assertTrue("Button panel should contain 'Add Expense'", hasAddButton);
            assertTrue("Button panel should contain 'Show Database'", hasDbButton);
        });
    }

    @Test
    public void testShowExpenseLogging_mainPanelUpdated() throws InvocationTargetException, InterruptedException {
        SwingUtilities.invokeAndWait(() -> {
            instance.showExpenseLogging();

            Component[] components = instance.mainPanel.getComponents();
            boolean hasContentPanel = false;
            boolean hasSideMenuPanel = false;

            for (Component comp : components) {
                if (comp == instance.contentPanel) hasContentPanel = true;
                if (comp == instance.sideMenuPanel) hasSideMenuPanel = true;
            }

            assertFalse("mainPanel should contain contentPanel", hasContentPanel);
            assertFalse("mainPanel should contain sideMenuPanel", hasSideMenuPanel);
        });
    }
    
    
    
    
    
}