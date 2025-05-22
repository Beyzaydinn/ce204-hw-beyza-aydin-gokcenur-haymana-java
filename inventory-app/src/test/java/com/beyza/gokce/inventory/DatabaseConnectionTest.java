/**

@file InventoryTest.java
@brief This file contains the test cases for the Inventory class.
@details This file includes test methods to validate the functionality of the Inventory class. It uses JUnit for unit testing.
*/
package com.beyza.gokce.inventory;
import org.junit.*;
import java.lang.reflect.Method;
import java.nio.file.Files;

import com.beyza.gokce.inventory.DatabaseConnection;
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
import java.awt.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import java.lang.reflect.InvocationTargetException;
/**

@class InventoryTest
@brief This class represents the test class for the Inventory class.
@details The InventoryTest class provides test methods to verify the behavior of the Inventory class. It includes test methods for addition, subtraction, multiplication, and division operations.
@author ugur.coruh
*/

public class DatabaseConnectionTest {
    private DatabaseConnection gui;
    private DatabaseConnection instance;
    
	 @Before
	    public void setup() {
	        gui = new DatabaseConnection();
	        instance = new DatabaseConnection();
	    }

	 @Test
	 public void testConnect() {
	     Connection conn = DatabaseConnection.connect();
	     assertNotNull("Connection should not be null if successful", conn);
	     try {
	         assertFalse("Connection should be open", conn.isClosed());
	         conn.close(); // Temizleme işlemi
	     } catch (SQLException e) {
	     }
	 }

	 @Test
	 public void testDisconnect() {
	     Connection conn = DatabaseConnection.connect();
	     DatabaseConnection.disconnect(conn);
	     try {
	         assertTrue("Connection should be closed after disconnect", conn.isClosed());
	     } catch (SQLException e) {
	     }
	 }

   
}