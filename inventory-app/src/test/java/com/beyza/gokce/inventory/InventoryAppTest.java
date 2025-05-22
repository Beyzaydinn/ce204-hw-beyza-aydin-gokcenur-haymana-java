/**

@file InventoryAppTest.java
@brief This file contains the test cases for the InventoryApp class.
@details This file includes test methods to validate the functionality of the InventoryApp class. It uses JUnit for unit testing.
*/
package com.beyza.gokce.inventory;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.beyza.gokce.inventory.InventoryApp;


/**

@class InventoryAppTest
@brief This class represents the test class for the InventoryApp class.
@details The InventoryAppTest class provides test methods to verify the behavior of the InventoryApp class. It includes test methods for successful execution, object creation, and error handling scenarios.
@author ugur.coruh
*/
public class InventoryAppTest {

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

  /**
   * @brief This method is executed before each test method.
   * @throws Exception
   */
  @Before
  public void setUp() throws Exception {
  }

  /**
   * @brief This method is executed after each test method.
   * @throws Exception
   */
  @After
  public void tearDown() throws Exception {
  }

  /**
   * @brief Test method to validate the successful execution of the main method.
   *
   * @details This method redirects the System.in and System.out streams to simulate user input and capture the output. It calls the main method of InventoryApp with a valid argument and asserts the expected behavior based on the output.
   */
  
  @Test
  public void testMainMethod_RunsWithoutException() {
      String input = "4\n"; // Menüde 4 = Exit direkt çıkış
      InputStream originalIn = System.in;
      PrintStream originalOut = System.out;
      ByteArrayOutputStream outContent = new ByteArrayOutputStream();

      try {
          System.setIn(new ByteArrayInputStream(input.getBytes()));
          System.setOut(new PrintStream(outContent));

          Inventory.scanner = new Scanner(System.in);


          // main çağrısı
          InventoryApp.main(new String[0]);

          String output = outContent.toString();
          assertTrue(output.contains("1. Login")); // Menü çıktısı kontrolü

      } finally {
          System.setIn(originalIn);
          System.setOut(originalOut);
      }
  }


}
