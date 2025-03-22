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

import ch.qos.logback.classic.Logger;
/**

@class Inventory
@brief This class represents a Inventory that performs mathematical operations.
@details The Inventory class provides methods to perform mathematical operations such as addition, subtraction, multiplication, and division. It also supports logging functionality using the logger object.
@author ugur.coruh
*/
public class Inventory {

  /**
   * @brief Logger for the Inventory class.
   */
  private static final Logger logger = (Logger) LoggerFactory.getLogger(Inventory.class);

  /**
   * @brief Calculates the sum of two integers.
   *
   * @details This function takes two integer values, `a` and `b`, and returns their sum. It also logs a message using the logger object.
   *
   * @param a The first integer value.
   * @param b The second integer value.
   * @return The sum of `a` and `b`.
   */
  public int add(int a, int b) {
    // Logging an informational message
    logger.info("Logging message");
    // Logging an error message
    logger.error("Error message");
    // Returning the sum of `a` and `b`
    return a + b;
  }
}
