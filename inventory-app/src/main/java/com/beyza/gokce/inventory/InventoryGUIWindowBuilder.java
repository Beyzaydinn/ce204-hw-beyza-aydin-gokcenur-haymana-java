/**
 * @file InventoryGUIWindowBuilder.java
 * @brief Window builder class for the Inventory Management System
 * @author Beyza Aydın, Gökcenur Haymana
 * @date 2024
 * @version 1.0
 * 
 * This file contains the implementation of the window builder class for the
 * Inventory Management System. It provides functionality for creating and
 * managing the application's windows and UI components.
 */

package com.beyza.gokce.inventory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class InventoryGUIWindowBuilder extends JFrame {
	private static final long serialVersionUID = 1L;
	
	/**
	 * @brief Primary color used in the application's color scheme
	 * RGB values: (41, 128, 185) - A shade of blue
	 */
	public static final Color PRIMARY_COLOR = new Color(41, 128, 185);
	
	/**
	 * @brief Secondary color used in the application's color scheme
	 * RGB values: (52, 152, 219) - A lighter shade of blue
	 */
	public static final Color SECONDARY_COLOR = new Color(52, 152, 219);
	
	/**
	 * @brief Background color for the main application window
	 * RGB values: (44, 62, 80) - A dark blue-gray
	 */
	public static final Color BACKGROUND_COLOR = new Color(44, 62, 80);
	
	/**
	 * @brief Color used for text throughout the application
	 * RGB values: (236, 240, 241) - A light gray, almost white
	 */
	public static final Color TEXT_COLOR = new Color(236, 240, 241);
	
	/**
	 * @brief Accent color used for highlighting and important elements
	 * RGB values: (231, 76, 60) - A shade of red
	 */
	public static final Color ACCENT_COLOR = new Color(231, 76, 60);
	
	/**
	 * @brief Color used for panel backgrounds
	 * RGB values: (52, 73, 94) - A dark blue-gray
	 */
	public static final Color PANEL_COLOR = new Color(52, 73, 94);
	
	/**
	 * @brief Background color for tables
	 * RGB values: (44, 62, 80) - A dark blue-gray
	 */
	public static Color TABLE_COLOR = new Color(44, 62, 80);
	
	/**
	 * @brief Color used for table headers
	 * RGB values: (52, 73, 94) - A dark blue-gray
	 */
	public static Color TABLE_HEADER_COLOR = new Color(52, 73, 94);
	
	/**
	 * @brief Color used for text in tables
	 * RGB values: (236, 240, 241) - A light gray, almost white
	 */
	public static Color TABLE_TEXT_COLOR = new Color(236, 240, 241);
	
	/**
	 * @brief Modern primary color for the updated UI theme
	 * RGB values: (26, 188, 156) - A turquoise color
	 */
	public static Color MODERN_PRIMARY = new Color(26, 188, 156);
	
	/**
	 * @brief Modern secondary color for the updated UI theme
	 * RGB values: (46, 204, 113) - A green color
	 */
	public static Color MODERN_SECONDARY = new Color(46, 204, 113);
	
	/**
	 * @brief Modern accent color for the updated UI theme
	 * RGB values: (155, 89, 182) - A purple color
	 */
	public static Color MODERN_ACCENT = new Color(155, 89, 182);
	
	/**
	 * @brief Modern background color for the updated UI theme
	 * RGB values: (52, 73, 94) - A dark blue-gray
	 */
	public static Color MODERN_BACKGROUND = new Color(52, 73, 94);
	
	/**
	 * @brief Modern panel color for the updated UI theme
	 * RGB values: (44, 62, 80) - A dark blue-gray
	 */
	public static Color MODERN_PANEL = new Color(44, 62, 80);
	
	/**
	 * @brief Modern text color for the updated UI theme
	 * RGB values: (236, 240, 241) - A light gray, almost white
	 */
	public static Color MODERN_TEXT = new Color(236, 240, 241);
	
	/**
	 * @brief Main panel that contains all other panels
	 * Used as the primary container for the application's content
	 */
	public JPanel mainPanel;
	
	/**
	 * @brief Panel containing the side menu navigation
	 * Displays navigation buttons and options
	 */
	public JPanel sideMenuPanel;
	
	/**
	 * @brief Panel that displays the main content
	 * Changes based on the selected module/function
	 */
	public JPanel contentPanel;
	
	/**
	 * @brief Text field for entering username during login
	 */
	public JTextField usernameField;
	
	/**
	 * @brief Password field for entering password during login
	 */
	public JPasswordField passwordField;
	
	/**
	 * @brief Stores the username of the currently logged-in user
	 * Null if in guest mode
	 */
	public String currentUser;
	
	/**
	 * @brief Icon used for the application logo
	 * Loaded from an external URL
	 */
	public ImageIcon logoIcon;
	
	/**
	 * @brief Label that displays the application logo
	 * Used in the welcome screen and main menu
	 */
	public JLabel logoLabel; 
	
	/**
	 * @brief Table displaying inventory items
	 * Shows name, quantity, and cost of materials
	 */
	public JTable inventoryTable;
	
	/**
	 * @brief Table displaying project information
	 * Shows project names and details
	 */
	public JTable projectTable;
	
	/**
	 * @brief Table displaying expense records
	 * Shows expense descriptions and amounts
	 */
	public JTable expenseTable;
	
	/**
	 * @brief Table displaying sales records
	 * Shows item, quantity, price, and total
	 */
	public JTable salesTable;
	
	/**
	 * @brief Data model for the inventory table
	 * Manages the data displayed in the inventory table
	 */
	public static DefaultTableModel inventoryModel;
	
	/**
	 * @brief Data model for the project table
	 * Manages the data displayed in the project table
	 */
	public DefaultTableModel projectModel;
	
	/**
	 * @brief Data model for the expense table
	 * Manages the data displayed in the expense table
	 */
	public DefaultTableModel expenseModel;
	
	/**
	 * @brief Data model for the sales table
	 * Manages the data displayed in the sales table
	 */
	public DefaultTableModel salesModel;
	
	/**
	 * @brief List storing inventory items
	 * Contains all materials in the inventory
	 */
	public static List<InventoryItem> inventory = new ArrayList<>();
	
	/**
	 * @brief List storing project information
	 * Contains all projects in the system
	 */
	public static List<Project> projects = new ArrayList<>();
	
	/**
	 * @brief List storing expense records
	 * Contains all expenses recorded in the system
	 */
	public static List<Expense> expenses = new ArrayList<>();
	
	/**
	 * @brief List storing sales records
	 * Contains all sales recorded in the system
	 */
	public static List<Sale> sales = new ArrayList<>();
	
	/**
	 * @brief Panel containing welcome screen buttons
	 * Holds login, register, and guest mode buttons
	 */
	public JPanel welcomeButtonPanel;
	
	/**
	 * @brief Button for accessing the login screen
	 * Styled with the primary color scheme
	 */
	public JButton loginButton;
	
	/**
	 * @brief Button for accessing the registration screen
	 * Styled with the secondary color scheme
	 */
	public JButton registerButton;
	
	/**
	 * @brief Button for accessing the application in guest mode
	 * Styled with the accent color scheme
	 */
	public JButton guestButton;

	/**
	 * @brief Constructor for the InventoryGUIWindowBuilder class that initializes the main application window
	 * @details This constructor sets up the primary window frame with modern styling and fixed dimensions.
	 * It initializes all necessary components, sets up the database connection, and creates the welcome screen.
	 * The window is configured with a non-resizable size of 1200x800 pixels and uses the system's look and feel.
	 * The constructor also sets up the main panel, content panel, and initializes the database tables through
	 * the Inventory.createTables() method. This is the entry point for the application's UI construction.
	 */
	public InventoryGUIWindowBuilder() {
		setTitle("Inventory Management System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 800);
		setResizable(false);
		
		initializeComponents();
		setupMainPanel();
		createWelcomeScreen();
	}
	
	/**
	 * @brief Initializes the core components of the application's user interface
	 * @details This method is responsible for setting up the fundamental UI components of the application.
	 * It creates and configures the main panel with a modern background color and border layout.
	 * The content panel is initialized with a box layout for vertical component arrangement.
	 * The method also triggers the creation of necessary database tables through Inventory.createTables().
	 * This initialization is crucial for the proper functioning of all subsequent UI operations and
	 * ensures that the application has a solid foundation for its interface components.
	 */
	public void initializeComponents() {
		mainPanel = new JPanel();
		mainPanel.setBackground(MODERN_BACKGROUND);
		setContentPane(mainPanel);
		mainPanel.setLayout(new BorderLayout(0, 0));
		
		contentPanel = new JPanel();
		contentPanel.setBackground(MODERN_BACKGROUND);
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
		
		Inventory.createTables();
	}
	
	/**
	 * @brief Loads and displays the application logo with proper scaling and error handling
	 * @details This method attempts to load the application logo from a specified URL and displays it
	 * in the logo label. It includes comprehensive error handling for network and IO operations.
	 * The logo is scaled to 150x150 pixels while maintaining aspect ratio using Image.SCALE_SMOOTH.
	 * If the logo loading fails, the error is logged but doesn't crash the application.
	 * The method ensures the logo is properly displayed in the welcome screen and maintains
	 * the application's professional appearance.
	 */
	private void loadLogo() {
		try {
			URL logoUrl = new URL("https://cdn-icons-png.flaticon.com/512/2103/2103633.png");
			Image logo = ImageIO.read(logoUrl);
			Image scaledLogo = logo.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
			logoIcon = new ImageIcon(scaledLogo);
			if (logoLabel != null) {
				logoLabel.setIcon(logoIcon);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @brief Configures and sets up the main panel of the application with proper layout management
	 * @details This method is responsible for the structural setup of the main application window.
	 * It removes any existing components from the main panel and adds the content panel to the center
	 * of the border layout. The method ensures proper revalidation and repainting of the UI components
	 * to maintain visual consistency. This setup is crucial for the proper display of all subsequent
	 * content and ensures that the application's layout remains stable during navigation.
	 */
	public void setupMainPanel() {
		mainPanel.removeAll();
		mainPanel.add(contentPanel, BorderLayout.CENTER);
		mainPanel.revalidate();
		mainPanel.repaint();
	}
	
	/**
	 * @brief Creates and displays the welcome screen of the application
	 * 
	 * This method sets up the initial welcome screen with the application logo,
	 * title, subtitle, and navigation buttons. The screen is designed with a modern
	 * look and feel using the application's color scheme.
	 * 
	 * @details The welcome screen includes:
	 * - Application logo in a centered panel
	 * - Main title "INVENTORY MANAGEMENT SYSTEM"
	 * - Subtitle "Manage your system efficiently"
	 * - Three navigation buttons:
	 *   * Login button (Primary color)
	 *   * Register button (Secondary color)
	 *   * Guest Mode button (Accent color)
	 * 
	 * The layout uses BoxLayout for vertical arrangement and includes proper spacing
	 * between elements. All components are styled according to the application's
	 * modern theme.
	 * 
	 * @see #showLoginPanel()
	 * @see #showRegisterPanel()
	 * @see #showMainMenu()
	 */
	private void createWelcomeScreen() {
		contentPanel.removeAll();

		JPanel welcomePanel = new JPanel();
		welcomePanel.setLayout(new BoxLayout(welcomePanel, BoxLayout.Y_AXIS));
		welcomePanel.setBackground(MODERN_BACKGROUND);
		welcomePanel.setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100));

		// Logo Panel
		JPanel logoPanel = new JPanel();
		logoPanel.setBackground(MODERN_BACKGROUND);
		logoPanel.setPreferredSize(new Dimension(200, 200));
		logoPanel.setMaximumSize(new Dimension(200, 200));
		logoPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

		logoLabel = new JLabel();
		logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		logoLabel.setIcon(new ImageIcon("\\Users\\Useres\\Desktop\\ce204-hw-beyza-aydin-gokcenur-haymana-java\\inventory-app\\src\\main\\resources\\images\\logo.png"));
		logoPanel.add(logoLabel);
		loadLogo();

		JLabel welcomeLabel = new JLabel("INVENTORY MANAGEMENT SYSTEM");
		welcomeLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
		welcomeLabel.setForeground(MODERN_PRIMARY);
		welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

		JLabel subTitleLabel = new JLabel("Manage your system efficiently");
		subTitleLabel.setFont(new Font("Segoe UI", Font.ITALIC, 18));
		subTitleLabel.setForeground(MODERN_TEXT);
		subTitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
		buttonPanel.setBackground(MODERN_BACKGROUND);
		buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

		loginButton = new JButton("Login");
		styleButton(loginButton, MODERN_PRIMARY);
		loginButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				showLoginPanel();
			}
		});

		registerButton = new JButton("Register");
		registerButton.setBorderPainted(false);
		registerButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		registerButton.setForeground(new Color(255, 255, 255));
		registerButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
		registerButton.setBackground(new Color(26, 188, 156));
		styleButton(registerButton, MODERN_SECONDARY);
		registerButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				showRegisterPanel();
			}
		});

		guestButton = new JButton("Guest Mode");
		guestButton.setForeground(new Color(255, 255, 255));
		guestButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
		guestButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		guestButton.setBorderPainted(false);
		guestButton.setBackground(new Color(26, 188, 156));
		styleButton(guestButton, MODERN_ACCENT);
		guestButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				showMainMenu();
			}
		});

		buttonPanel.add(loginButton);
		buttonPanel.add(registerButton);
		buttonPanel.add(guestButton);

		welcomePanel.add(Box.createVerticalGlue());
		welcomePanel.add(logoPanel);
		welcomePanel.add(Box.createRigidArea(new Dimension(0, 20)));
		welcomePanel.add(welcomeLabel);
		welcomePanel.add(Box.createRigidArea(new Dimension(0, 10)));
		welcomePanel.add(subTitleLabel);
		welcomePanel.add(Box.createRigidArea(new Dimension(0, 30)));
		welcomePanel.add(buttonPanel);
		welcomePanel.add(Box.createVerticalGlue());

		contentPanel.add(welcomePanel);
		contentPanel.revalidate();
		contentPanel.repaint();
	}

	/**
	 * @brief Applies comprehensive modern styling to a button with interactive effects
	 * @param button The JButton component to be styled
	 * @param color The base color to use for the button's background and hover effects
	 * @details This method applies a consistent and modern styling to buttons throughout the application.
	 * It sets up the font to Segoe UI Bold 14pt, configures the foreground color to white,
	 * and applies the specified background color. The method adds mouse listeners for hover effects,
	 * making the button brighter when the mouse enters and returning to the original color when it exits.
	 * The button is also configured with no border painting and a hand cursor for better user interaction.
	 * This styling ensures a consistent and professional appearance across all buttons in the application.
	 */
	private void styleButton(JButton button, Color color) {
		button.setFont(new Font("Segoe UI", Font.BOLD, 14));
		button.setForeground(Color.WHITE);
		button.setBackground(color);
		button.setFocusPainted(false);
		button.setBorderPainted(false);
		button.setCursor(new Cursor(Cursor.HAND_CURSOR));
		button.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				button.setBackground(color.brighter());
			}
			public void mouseExited(java.awt.event.MouseEvent evt) {
				button.setBackground(color);
			}
		});
	}
	
	/**
	 * @brief Displays the login panel with user authentication functionality
	 * @details This method creates and shows the login interface with username and password fields.
	 * It includes comprehensive input validation and error handling. The login form features
	 * modern styling with proper spacing and alignment. The method handles user authentication
	 * through the Inventory.authenticateUser() method and provides appropriate feedback messages
	 * for various scenarios (empty fields, invalid credentials, etc.). Upon successful login,
	 * it stores the current user and transitions to the main menu. The panel also includes
	 * a back button to return to the welcome screen.
	 */
	public void showLoginPanel() {
		contentPanel.removeAll();
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
		contentPanel.setBackground(MODERN_BACKGROUND);
		
		JPanel formPanel = new JPanel();
		formPanel.setLayout(new GridLayout(3, 2, 10, 15));
		formPanel.setBackground(MODERN_BACKGROUND);
		formPanel.setMaximumSize(new Dimension(400, 150));
		
		JLabel usernameLabel = new JLabel("Username:");
		usernameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		usernameField = new JTextField(); 
		usernameField.setPreferredSize(new Dimension(200, 30));
		
		JLabel passwordLabel = new JLabel("Password:");
		passwordLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		passwordField = new JPasswordField();
		passwordField.setPreferredSize(new Dimension(200, 30));
		
		JButton loginButton = new JButton("Login");
		JButton backButton = new JButton("Back");
		
		formPanel.add(usernameLabel);
		formPanel.add(usernameField);
		formPanel.add(passwordLabel);
		formPanel.add(passwordField);
		formPanel.add(loginButton);
		formPanel.add(backButton);
		
		loginButton.addActionListener(e -> {
			String username = usernameField.getText();
			String password = new String(passwordField.getPassword());
			
			if (username.isEmpty() || password.isEmpty()) {
				JOptionPane.showMessageDialog(this, 
					"Please enter both username and password.",
					"Login Error",
					JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			if (Inventory.authenticateUser(username, password)) {
				currentUser = username;
				showMainMenu();
			} else {
				JOptionPane.showMessageDialog(this, 
					"Invalid username or password.",
					"Login Failed",
					JOptionPane.ERROR_MESSAGE);
			}
		});
		
		backButton.addActionListener(e -> createWelcomeScreen());
		
		contentPanel.add(Box.createVerticalGlue());
		contentPanel.add(formPanel);
		contentPanel.add(Box.createVerticalGlue());
		
		mainPanel.removeAll();
		mainPanel.add(contentPanel, BorderLayout.CENTER);
		mainPanel.revalidate();
		mainPanel.repaint();
	}
	
	/**
	 * @brief Displays the registration panel with comprehensive user registration functionality
	 * @details This method creates and shows the registration interface with fields for username,
	 * password, and password confirmation. It includes thorough input validation, checking for
	 * empty fields and password matching. The registration form features modern styling with
	 * proper spacing and alignment. The method handles user registration through the Inventory
	 * class and provides appropriate feedback messages for various scenarios. Upon successful
	 * registration, it automatically transitions to the login panel. The panel includes a back
	 * button to return to the welcome screen and proper error handling for database operations.
	 */
	public void showRegisterPanel() {
		contentPanel.removeAll();
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
		contentPanel.setBackground(MODERN_BACKGROUND);
		contentPanel.setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100));
		
		JLabel titleLabel = new JLabel("Register");
		titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
		titleLabel.setForeground(MODERN_PRIMARY);
		titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JPanel formPanel = new JPanel();
		formPanel.setLayout(new GridLayout(3, 2, 10, 15));
		formPanel.setBackground(MODERN_BACKGROUND);
		formPanel.setMaximumSize(new Dimension(400, 150));
		formPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JLabel usernameLabel = new JLabel("Username:");
		usernameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		JTextField usernameField = new JTextField();
		usernameField.setPreferredSize(new Dimension(200, 30));
		
		JLabel passwordLabel = new JLabel("Password:");
		passwordLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		JPasswordField passwordField = new JPasswordField();
		passwordField.setPreferredSize(new Dimension(200, 30));
		
		JLabel confirmPasswordLabel = new JLabel("Confirm Password:");
		confirmPasswordLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		JPasswordField confirmPasswordField = new JPasswordField();
		confirmPasswordField.setPreferredSize(new Dimension(200, 30));

		formPanel.add(usernameLabel);
		formPanel.add(usernameField);
		formPanel.add(passwordLabel);
		formPanel.add(passwordField);
		formPanel.add(confirmPasswordLabel);
		formPanel.add(confirmPasswordField);

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
		buttonPanel.setBackground(MODERN_BACKGROUND);
		buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

		JButton registerButton = new JButton("Register");
		registerButton.addActionListener(e -> {
			String username = usernameField.getText();
			String password = new String(passwordField.getPassword());
			String confirmPassword = new String(confirmPasswordField.getPassword());
			
			if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
				JOptionPane.showMessageDialog(this, 
					"Please fill in all fields.", 
					"Registration Error", 
					JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			if (!password.equals(confirmPassword)) {
				JOptionPane.showMessageDialog(this, 
					"Passwords do not match.", 
					"Registration Error", 
					JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			try {
				User newUser = new User(username, password);
				Inventory.addUserToDatabase(newUser);
				
				JOptionPane.showMessageDialog(this, 
					"Registration successful! Please login.", 
					"Success",
					JOptionPane.INFORMATION_MESSAGE);
				showLoginPanel();
			} catch (RuntimeException ex) {
				JOptionPane.showMessageDialog(this, 
					ex.getMessage(),
					"Registration Error", 
					JOptionPane.ERROR_MESSAGE);
			}
		});
		
		JButton backButton = new JButton("Back");
		backButton.addActionListener(e -> createWelcomeScreen());
	
		buttonPanel.add(registerButton);
		buttonPanel.add(backButton);
		
		contentPanel.add(Box.createVerticalGlue());
		contentPanel.add(titleLabel);
		contentPanel.add(Box.createRigidArea(new Dimension(0, 30)));
		contentPanel.add(formPanel);
		contentPanel.add(Box.createRigidArea(new Dimension(0, 30)));
		contentPanel.add(buttonPanel);
		contentPanel.add(Box.createVerticalGlue());
		
		mainPanel.removeAll();
		mainPanel.add(contentPanel, BorderLayout.CENTER);
		mainPanel.revalidate();
		mainPanel.repaint();
	}
	
	/**
	 * @brief Displays the main menu with comprehensive navigation and user-specific content
	 * @details This method creates and shows the main dashboard interface with a personalized
	 * welcome message based on the current user. It features a modern layout with four main
	 * module buttons: Material Inventory, Project Tracking, Expense Logging, and Sales Tracker.
	 * Each button is styled with a unique color and gradient effect. The dashboard includes
	 * proper spacing and alignment using BoxLayout and rigid areas. The method also creates
	 * the side menu panel for additional navigation options. The main menu serves as the central
	 * hub for accessing all application features and provides a clear overview of available
	 * functionality.
	 */
	public void showMainMenu() {
		contentPanel = new JPanel();
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
		contentPanel.setBackground(MODERN_BACKGROUND);
		contentPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
		
		JPanel welcomePanel = new JPanel();
		welcomePanel.setLayout(new BoxLayout(welcomePanel, BoxLayout.Y_AXIS));
		welcomePanel.setBackground(MODERN_BACKGROUND);
		welcomePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JLabel welcomeLabel;
		if (currentUser != null) {
			welcomeLabel = new JLabel("Welcome, " + currentUser + "!");
		} else {
			welcomeLabel = new JLabel("Welcome, guest!");
		}
		welcomeLabel.setFont(new Font("Segoe UI", Font.BOLD, 32));
		welcomeLabel.setForeground(MODERN_PRIMARY);
		welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		welcomePanel.add(welcomeLabel);
		
		JPanel dashboardPanel = new JPanel(new GridLayout(2, 2, 30, 30));
		dashboardPanel.setBackground(MODERN_BACKGROUND);
		dashboardPanel.setMaximumSize(new Dimension(900, 350));
		dashboardPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JButton materialBtn = createDashboardButton("Material Inventory", MODERN_PRIMARY);
		materialBtn.addActionListener(e -> showMaterialInventory());
		JButton projectBtn = createDashboardButton("Project Tracking", MODERN_SECONDARY);
		projectBtn.addActionListener(e -> showProjectTracking());
		JButton expenseBtn = createDashboardButton("Expense Logging", MODERN_ACCENT);
		expenseBtn.addActionListener(e -> showExpenseLogging());
		JButton salesBtn = createDashboardButton("Sales Tracker", new Color(46, 204, 113));
		salesBtn.addActionListener(e -> showSalesTracker());
		
		dashboardPanel.add(materialBtn);
		dashboardPanel.add(projectBtn);
		dashboardPanel.add(expenseBtn);
		dashboardPanel.add(salesBtn);
		
		contentPanel.add(Box.createVerticalGlue());
		contentPanel.add(welcomePanel);
		contentPanel.add(Box.createRigidArea(new Dimension(0, 30)));
		contentPanel.add(dashboardPanel);
		contentPanel.add(Box.createVerticalGlue());
		
		createSideMenuPanel();
		
		mainPanel.removeAll();
		mainPanel.add(contentPanel, BorderLayout.CENTER);
		mainPanel.add(sideMenuPanel, BorderLayout.WEST);
		
		mainPanel.revalidate();
		mainPanel.repaint();
	}

	/**
	 * @brief Creates a sophisticated dashboard button with modern styling and gradient effects
	 * @param title The text to display on the button
	 * @param color The base color to use for the button's gradient
	 * @return JButton A fully styled dashboard button with gradient background and hover effects
	 * @details This method creates a custom JButton with advanced styling features. It implements
	 * a custom paintComponent method to create a gradient background effect. The button features
	 * rounded corners (30px radius), anti-aliasing for smooth edges, and a gradient that transitions
	 * from the specified color to a darker shade. The button is configured with a large font
	 * (Segoe UI Bold 28pt), white text, and proper padding. It includes hover effects and
	 * maintains a consistent size of 400x90 pixels. The button is designed to be visually
	 * appealing and clearly visible in the dashboard layout.
	 */
	public static JButton createDashboardButton(String title, Color color) {
		JButton button = new JButton(title) {
			@Override
			protected void paintComponent(Graphics g) {
				Graphics2D g2d = (Graphics2D) g;
				g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				GradientPaint gp = new GradientPaint(0, 0, color, 0, getHeight(), color.darker());
				g2d.setPaint(gp);
				g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
				super.paintComponent(g);
			}
		};
		button.setFont(new Font("Segoe UI", Font.BOLD, 28));
		button.setForeground(Color.WHITE);
		button.setContentAreaFilled(false);
		button.setFocusPainted(false);
		button.setBorderPainted(false);
		button.setOpaque(false);
		button.setPreferredSize(new Dimension(400, 90));
		button.setCursor(new Cursor(Cursor.HAND_CURSOR));
		button.setHorizontalAlignment(SwingConstants.CENTER);
		return button;
	}

	/**
	 * @brief Creates a comprehensive side menu panel with navigation options and system controls
	 * @details This method builds the side navigation menu with a modern and consistent design.
	 * It includes a menu title, navigation buttons for all main modules, and system control
	 * buttons (Home and Exit). The menu is styled with the application's color scheme and
	 * proper spacing between elements. Each button is created with consistent styling and
	 * includes appropriate action listeners for navigation. The menu is fixed to the left
	 * side of the application and provides quick access to all major features. The method
	 * ensures proper alignment and spacing of all elements for optimal user experience.
	 */
	private void createSideMenuPanel() {
		sideMenuPanel = new JPanel();
		sideMenuPanel.setLayout(new BoxLayout(sideMenuPanel, BoxLayout.Y_AXIS));
		sideMenuPanel.setBackground(MODERN_BACKGROUND);
		sideMenuPanel.setPreferredSize(new Dimension(200, getHeight()));
		sideMenuPanel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
		
		JLabel menuTitle = new JLabel("Menu");
		menuTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
		menuTitle.setForeground(Color.WHITE);
		menuTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
		sideMenuPanel.add(menuTitle);
		sideMenuPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		
		String[] menuItems = {"Material Inventory", "Project Tracking", "Expense Logging", "Sales Tracker"};
		for (String item : menuItems) {
			JButton menuButton = createMenuButton(item);
			menuButton.addActionListener(e -> showModule(item));
			sideMenuPanel.add(menuButton);
			sideMenuPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		}
		
		sideMenuPanel.add(Box.createVerticalGlue());
		JButton homeButton = createMenuButton("Home");
		homeButton.addActionListener(e -> showMainMenu());
		sideMenuPanel.add(homeButton);
		
		JButton exitButton = createMenuButton("Exit");
		exitButton.addActionListener(e -> System.exit(0));
		sideMenuPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		sideMenuPanel.add(exitButton);
	}

	/**
	 * @brief Creates a consistently styled menu button for the side navigation panel
	 * @param text The text to display on the button
	 * @return JButton A styled menu button with hover effects and proper alignment
	 * @details This method creates a menu button with a consistent style across the side menu.
	 * The button features a modern design with a fixed width of 180 pixels and height of 40 pixels.
	 * It uses the application's color scheme with hover effects that brighten the button when
	 * the mouse enters. The button is configured with Segoe UI font, white text, and no border.
	 * It includes a hand cursor for better user interaction and is properly aligned in the
	 * side menu panel. The method ensures that all menu buttons have a uniform appearance
	 * while maintaining good visibility and usability.
	 */
	public JButton createMenuButton(String text) {
		JButton button = new JButton(text);
		button.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		button.setForeground(Color.WHITE);
		button.setBackground(new Color(52, 73, 94));
		button.setBorderPainted(false);
		button.setFocusPainted(false);
		button.setAlignmentX(Component.CENTER_ALIGNMENT);
		button.setMaximumSize(new Dimension(180, 40));
		button.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		button.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				button.setBackground(new Color(41, 128, 185));
			}
			
			public void mouseExited(MouseEvent evt) {
				button.setBackground(new Color(52, 73, 94));
			}
		});
		
		return button;
	}

	/**
	 * @brief Routes to the appropriate module based on the module name with proper error handling
	 * @param moduleName The name of the module to display (Material Inventory, Project Tracking, etc.)
	 * @details This method serves as the central routing mechanism for the application's modules.
	 * It uses a switch statement to determine which module to display based on the provided
	 * module name. The method includes proper error handling and ensures that the correct
	 * module is displayed with all necessary components. It maintains the application's state
	 * and ensures smooth transitions between different sections. The method is called by
	 * various navigation elements throughout the application to provide consistent module
	 * switching functionality.
	 */
	public void showModule(String moduleName) {
		switch (moduleName) {
			case "Material Inventory":
				showMaterialInventory();
				break;
			case "Project Tracking":
				showProjectTracking();
				break;
			case "Expense Logging":
				showExpenseLogging();
				break;
			case "Sales Tracker":
				showSalesTracker();
				break;
		}
	}
	
	/**
	 * @brief Displays the comprehensive material inventory management module
	 * @details This method creates and shows the material inventory interface with a modern
	 * design and full functionality. It includes a header panel with the module title and
	 * action buttons (Add, Edit, Delete, Show Database). The main content area displays a
	 * styled table showing material details (name, quantity, cost). The table features
	 * alternating row colors, proper column sizing, and selection highlighting. The method
	 * loads data from the database and populates the table with current inventory items.
	 * It includes proper error handling for database operations and provides user feedback
	 * for all actions. The interface is designed to be intuitive and efficient for managing
	 * inventory items.
	 */
	public void showMaterialInventory() {
		contentPanel.removeAll();
		contentPanel.setLayout(new BorderLayout());
		contentPanel.setBackground(MODERN_BACKGROUND);
		
		JPanel headerPanel = new JPanel();
		headerPanel.setBackground(MODERN_PRIMARY);
		headerPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		JLabel titleLabel = new JLabel("Material Inventory");
		titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
		titleLabel.setForeground(MODERN_TEXT);
		headerPanel.add(titleLabel);
		
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		buttonPanel.setBackground(MODERN_PRIMARY);
		
		JButton addButton = new JButton("Add Material");
		JButton editButton = new JButton("Edit");
		JButton deleteButton = new JButton("Delete");
		JButton showDbButton = new JButton("Show Database");
		
		addButton.addActionListener(e -> showAddMaterialDialog());
		editButton.addActionListener(e -> showEditMaterialDialog());
		deleteButton.addActionListener(e -> showDeleteMaterialDialog());
		showDbButton.addActionListener(e -> showModuleDatabase("Material Inventory", "inventory"));
		
		buttonPanel.add(addButton);
		buttonPanel.add(editButton);
		buttonPanel.add(deleteButton);
		buttonPanel.add(showDbButton);
		
		headerPanel.add(buttonPanel);
		
		// Create table model
		String[] columns = {"Name", "Quantity", "Cost"};
		inventoryModel = new DefaultTableModel(columns, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		// Create table
		inventoryTable = createStyledTable(inventoryModel);
		JScrollPane scrollPane = new JScrollPane(inventoryTable);
		scrollPane.getViewport().setBackground(TABLE_COLOR);
		
		contentPanel.add(headerPanel, BorderLayout.NORTH);
		contentPanel.add(scrollPane, BorderLayout.CENTER);
		
		mainPanel.removeAll();
		mainPanel.add(contentPanel, BorderLayout.CENTER);
		mainPanel.add(sideMenuPanel, BorderLayout.WEST);
		
		mainPanel.revalidate();
		mainPanel.repaint();
		
		// Load data from database
		try {
			String sql = "SELECT name, quantity, cost FROM inventory";
			try (Connection conn = DatabaseConnection.connect();
				 Statement stmt = conn.createStatement();
				 ResultSet rs = stmt.executeQuery(sql)) {
				
				inventory.clear();
				inventoryModel.setRowCount(0);
				
				while (rs.next()) {
					String name = rs.getString("name");
					int quantity = rs.getInt("quantity");
					double cost = rs.getDouble("cost");
					
					InventoryItem item = new InventoryItem(name, quantity, cost);
					inventory.add(item);
					inventoryModel.addRow(new Object[]{name, quantity, cost});
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(this,
				"Error loading inventory: " + ex.getMessage(),
				"Database Error",
				JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * @brief Shows a comprehensive dialog for adding new materials to the inventory
	 * @details This method creates a modal dialog for entering new material details with
	 * thorough validation and error handling. The dialog includes fields for material name,
	 * quantity, and cost, each with proper labels and input validation. The form features
	 * modern styling with proper spacing and alignment. The method handles database insertion
	 * with proper error handling and provides user feedback for various scenarios (empty fields,
	 * invalid numbers, database errors). Upon successful addition, it updates the inventory
	 * table and provides confirmation to the user. The dialog includes both Add and Cancel
	 * buttons with proper styling and functionality.
	 */
	public void showAddMaterialDialog() {
		JDialog dialog = new JDialog(this, "Add Material", true);
		dialog.getContentPane().setLayout(new BorderLayout());
		dialog.getContentPane().setBackground(MODERN_BACKGROUND);
		
		JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 10));
		formPanel.setBackground(MODERN_PANEL);
		formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		
		JTextField nameField = new JTextField();
		JTextField quantityField = new JTextField();
		JTextField costField = new JTextField();
		
		JLabel nameLabel = new JLabel("Name:");
		nameLabel.setForeground(MODERN_TEXT);
		JLabel quantityLabel = new JLabel("Quantity:");
		quantityLabel.setForeground(MODERN_TEXT);
		JLabel costLabel = new JLabel("Cost:");
		costLabel.setForeground(MODERN_TEXT);
		
		formPanel.add(nameLabel);
		formPanel.add(nameField);
		formPanel.add(quantityLabel);
		formPanel.add(quantityField);
		formPanel.add(costLabel);
		formPanel.add(costField);
		
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		buttonPanel.setBackground(MODERN_PANEL);
		
		JButton addButton = new JButton("Add");
		JButton cancelButton = new JButton("Cancel");
		
		addButton.addActionListener(e -> {
			try {
				String name = nameField.getText();
				int quantity = Integer.parseInt(quantityField.getText());
				double cost = Double.parseDouble(costField.getText());
				
				if (!name.isEmpty()) {
					String sql = "INSERT INTO inventory (name, quantity, cost) VALUES (?, ?, ?)";
					try (Connection conn = DatabaseConnection.connect();
						 PreparedStatement pstmt = conn.prepareStatement(sql)) {
						
						pstmt.setString(1, name);
						pstmt.setInt(2, quantity);
						pstmt.setDouble(3, cost);
						pstmt.executeUpdate();

						dialog.dispose();
						
						JOptionPane.showMessageDialog(this,
							"Material added successfully!",
							"Success",
							JOptionPane.INFORMATION_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(this,
						"Please enter a material name.",
						"Input Error",
						JOptionPane.ERROR_MESSAGE);
				}
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(this,
					"Please enter valid numbers for quantity and cost.",
					"Input Error",
					JOptionPane.ERROR_MESSAGE);
			} catch (SQLException ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(this,
					"Error adding material: " + ex.getMessage(),
					"Database Error",
					JOptionPane.ERROR_MESSAGE);
			}
		});
		
		cancelButton.addActionListener(e -> dialog.dispose());
		
		buttonPanel.add(addButton);
		buttonPanel.add(cancelButton);
		
		dialog.getContentPane().add(formPanel, BorderLayout.CENTER);
		dialog.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
		
		dialog.pack();
		dialog.setLocationRelativeTo(this);
		dialog.setVisible(true);
	}

	/**
	 * @brief Shows a comprehensive dialog for editing existing materials in the inventory
	 * @details This method creates a modal dialog for modifying existing material details
	 * with thorough validation and error handling. It first checks if a material is selected
	 * and provides appropriate feedback if not. The dialog pre-fills the form with current
	 * material details and includes fields for name, quantity, and cost. The method handles
	 * database updates with proper error handling and provides user feedback for various
	 * scenarios. Upon successful update, it refreshes the inventory table and provides
	 * confirmation to the user. The dialog includes both Save and Cancel buttons with
	 * proper styling and functionality.
	 */
	public void showEditMaterialDialog() {
		int selectedRow = inventoryTable.getSelectedRow();
		if (selectedRow == -1) {
			JOptionPane.showMessageDialog(this,
				"Please select a material to edit.",
				"No Selection",
				JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		InventoryItem item = inventory.get(selectedRow);
		
		JDialog dialog = new JDialog(this, "Edit Material", true);
		dialog.getContentPane().setLayout(new BorderLayout());
		dialog.getContentPane().setBackground(MODERN_BACKGROUND);
		
		JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 10));
		formPanel.setBackground(MODERN_PANEL);
		formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		
		JTextField nameField = new JTextField(item.getName());
		JTextField quantityField = new JTextField(String.valueOf(item.getQuantity()));
		JTextField costField = new JTextField(String.valueOf(item.getCost()));
		
		JLabel nameLabel = new JLabel("Name:");
		nameLabel.setForeground(MODERN_TEXT);
		JLabel quantityLabel = new JLabel("Quantity:");
		quantityLabel.setForeground(MODERN_TEXT);
		JLabel costLabel = new JLabel("Cost:");
		costLabel.setForeground(MODERN_TEXT);
		
		formPanel.add(nameLabel);
		formPanel.add(nameField);
		formPanel.add(quantityLabel);
		formPanel.add(quantityField);
		formPanel.add(costLabel);
		formPanel.add(costField);
		
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		buttonPanel.setBackground(MODERN_PANEL);
		
		JButton saveButton = new JButton("Save");
		JButton cancelButton = new JButton("Cancel");
		
		saveButton.addActionListener(e -> {
			try {
				String name = nameField.getText();
				int quantity = Integer.parseInt(quantityField.getText());
				double cost = Double.parseDouble(costField.getText());
				
				if (!name.isEmpty()) {
					String sql = "UPDATE inventory SET name = ?, quantity = ?, cost = ? WHERE name = ?";
					try (Connection conn = DatabaseConnection.connect();
						 PreparedStatement pstmt = conn.prepareStatement(sql)) {
						
						pstmt.setString(1, name);
						pstmt.setInt(2, quantity);
						pstmt.setDouble(3, cost);
						pstmt.setString(4, item.getName());
						pstmt.executeUpdate();
						
						// Update local list
						item.setName(name);
						item.setQuantity(quantity);
						item.setCost(cost);
						
						refreshInventoryTable();
						dialog.dispose();
						
						JOptionPane.showMessageDialog(this,
							"Material updated successfully!",
							"Success",
							JOptionPane.INFORMATION_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(this,
						"Please enter a material name.",
						"Input Error",
						JOptionPane.ERROR_MESSAGE);
				}
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(this,
					"Please enter valid numbers for quantity and cost.",
					"Input Error",
					JOptionPane.ERROR_MESSAGE);
			} catch (SQLException ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(this,
					"Error updating material: " + ex.getMessage(),
					"Database Error",
					JOptionPane.ERROR_MESSAGE);
			}
		});
		
		cancelButton.addActionListener(e -> dialog.dispose());
		
		buttonPanel.add(saveButton);
		buttonPanel.add(cancelButton);
		
		dialog.getContentPane().add(formPanel, BorderLayout.CENTER);
		dialog.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
		
		dialog.pack();
		dialog.setLocationRelativeTo(this);
		dialog.setVisible(true);
	}

	/**
	 * @brief Shows a comprehensive dialog for deleting materials from the inventory
	 * @details This method handles the material deletion process with proper validation
	 * and user confirmation. It first checks if a material is selected and provides
	 * appropriate feedback if not. The method shows a confirmation dialog to prevent
	 * accidental deletions. Upon confirmation, it removes the material from the database
	 * and updates the inventory table. The method includes proper error handling for
	 * database operations and provides user feedback for the deletion process. It ensures
	 * data consistency by updating both the database and the local inventory list.
	 */
	public void showDeleteMaterialDialog() {
		int selectedRow = inventoryTable.getSelectedRow();
		if (selectedRow == -1) {
			JOptionPane.showMessageDialog(this,
				"Please select a material to delete.",
				"No Selection",
				JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		InventoryItem item = inventory.get(selectedRow);
		
		int confirm = JOptionPane.showConfirmDialog(this,
			"Are you sure you want to delete this material?",
			"Confirm Delete",
			JOptionPane.YES_NO_OPTION);
			
		if (confirm == JOptionPane.YES_OPTION) {
			try {
				String sql = "DELETE FROM inventory WHERE name = ?";
				try (Connection conn = DatabaseConnection.connect();
					 PreparedStatement pstmt = conn.prepareStatement(sql)) {
					
					pstmt.setString(1, item.getName());
					pstmt.executeUpdate();
					
					// Remove from local list
					inventory.remove(selectedRow);
					refreshInventoryTable();
					
					JOptionPane.showMessageDialog(this,
						"Material deleted successfully!",
						"Success",
						JOptionPane.INFORMATION_MESSAGE);
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(this,
					"Error deleting material: " + ex.getMessage(),
					"Database Error",
					JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	/**
	 * @brief Refreshes the inventory table display with current data from the database
	 * @details This method updates the inventory table model with the current state of
	 * the inventory items. It clears the existing table data and repopulates it with
	 * the current inventory list. The method ensures that the display is synchronized
	 * with the database and maintains data consistency. It handles the table model
	 * updates efficiently and ensures that all columns (name, quantity, cost) are
	 * properly displayed. This method is called after any inventory modifications to
	 * keep the display current and accurate.
	 */
	public static void refreshInventoryTable() {
		inventoryModel.setRowCount(0);
		for (InventoryItem item : inventory) {
			inventoryModel.addRow(new Object[]{
				item.getName(),
				item.getQuantity(),
				item.getCost()
			});
		}
	}

	/**
	 * @brief Creates a sophisticated styled table with modern appearance and functionality
	 * @param model The table model to use for data display
	 * @return JTable A fully styled table component with modern appearance and features
	 * @details This method creates a custom JTable with advanced styling features. It
	 * implements a custom prepareRenderer method to create alternating row colors and
	 * proper selection highlighting. The table features a modern font (Segoe UI 14pt),
	 * increased row height (30px), and no grid lines for a cleaner appearance. The
	 * table header is styled with a bold font and proper background color. The method
	 * ensures consistent styling across all tables in the application and provides
	 * good readability and visual appeal.
	 */
	public JTable createStyledTable(DefaultTableModel model) {
		JTable table = new JTable(model) {
			@Override
			public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
				Component comp = super.prepareRenderer(renderer, row, column);
				
				if (!isRowSelected(row)) {
					comp.setBackground(row % 2 == 0 ? MODERN_PANEL : MODERN_BACKGROUND);
					comp.setForeground(MODERN_TEXT);
				}
				
				return comp;
			}
		};
		
		table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		table.setRowHeight(30);
		table.setShowGrid(false);
		table.setIntercellSpacing(new Dimension(0, 0));
		table.setSelectionBackground(MODERN_PRIMARY);
		table.setSelectionForeground(Color.WHITE);
		
		// Style header
		JTableHeader header = table.getTableHeader();
		header.setFont(new Font("Segoe UI", Font.BOLD, 14));
		header.setBackground(MODERN_PRIMARY);
		header.setForeground(Color.WHITE);
		header.setPreferredSize(new Dimension(header.getWidth(), 40));
		
		return table;
	}

	/**
	 * @brief Displays the comprehensive project tracking module with management features
	 * @details This method creates and shows the project tracking interface with a modern
	 * design and full functionality. It includes a header panel with the module title and
	 * action buttons (Add Project, View Details, Show Database). The main content area
	 * displays a styled table showing project names. The table features alternating row
	 * colors, proper column sizing, and selection highlighting. The method loads data
	 * from the database and populates the table with current projects. It includes proper
	 * error handling for database operations and provides user feedback for all actions.
	 * The interface is designed to be intuitive and efficient for managing projects.
	 */
	public void showProjectTracking() {
		contentPanel.removeAll();
		contentPanel.setLayout(new BorderLayout());
		contentPanel.setBackground(MODERN_BACKGROUND);
		
		JPanel headerPanel = new JPanel();
		headerPanel.setBackground(MODERN_SECONDARY);
		headerPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		JLabel titleLabel = new JLabel("Project Tracking");
		titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
		titleLabel.setForeground(MODERN_TEXT);
		headerPanel.add(titleLabel);
		
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		buttonPanel.setBackground(MODERN_SECONDARY);
		
		JButton addButton = new JButton("Add Project");
		JButton viewButton = new JButton("View Details");
		JButton showDbButton = new JButton("Show Database");
		
		addButton.addActionListener(e -> showAddProjectDialog());
		viewButton.addActionListener(e -> showProjectDetails());
		showDbButton.addActionListener(e -> showModuleDatabase("Project Tracking", "projects"));
		
		buttonPanel.add(addButton);
		buttonPanel.add(viewButton);
		buttonPanel.add(showDbButton);
		
		headerPanel.add(buttonPanel);
		
		// Create table model
		String[] columns = {"Project Name"};
		projectModel = new DefaultTableModel(columns, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		// Create table
		projectTable = createStyledTable(projectModel);
		JScrollPane scrollPane = new JScrollPane(projectTable);
		scrollPane.getViewport().setBackground(TABLE_COLOR);
		
		contentPanel.add(headerPanel, BorderLayout.NORTH);
		contentPanel.add(scrollPane, BorderLayout.CENTER);
		
		mainPanel.removeAll();
		mainPanel.add(contentPanel, BorderLayout.CENTER);
		mainPanel.add(sideMenuPanel, BorderLayout.WEST);
		
		mainPanel.revalidate();
		mainPanel.repaint();
		
		// Load data from database
		try {
			String sql = "SELECT name FROM projects";
			try (Connection conn = DatabaseConnection.connect();
				 Statement stmt = conn.createStatement();
				 ResultSet rs = stmt.executeQuery(sql)) {
				
				projectModel.setRowCount(0);
				
				while (rs.next()) {
					String name = rs.getString("name");
					projectModel.addRow(new Object[]{name});
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(this,
				"Error loading projects: " + ex.getMessage(),
				"Database Error",
				JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * @brief Shows a comprehensive dialog for adding new projects to the tracking system
	 * @details This method creates a modal dialog for entering new project details with
	 * thorough validation and error handling. The dialog includes a field for project
	 * name with proper labeling and input validation. The form features modern styling
	 * with proper spacing and alignment. The method handles database insertion with
	 * proper error handling and provides user feedback for various scenarios. Upon
	 * successful addition, it updates the project table and provides confirmation to
	 * the user. The dialog includes both Add and Cancel buttons with proper styling
	 * and functionality.
	 */
	public void showAddProjectDialog() {
		JDialog dialog = new JDialog(this, "Add Project", true);
		dialog.getContentPane().setLayout(new BorderLayout());
		dialog.getContentPane().setBackground(MODERN_BACKGROUND);
		
		JPanel formPanel = new JPanel(new GridLayout(1, 2, 10, 10));
		formPanel.setBackground(MODERN_PANEL);
		formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		
		JTextField nameField = new JTextField();
		
		JLabel nameLabel = new JLabel("Project Name:");
		nameLabel.setForeground(MODERN_TEXT);
		
		formPanel.add(nameLabel);
		formPanel.add(nameField);
		
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		buttonPanel.setBackground(MODERN_PANEL);
		
		JButton addButton = new JButton("Add");
		JButton cancelButton = new JButton("Cancel");
		
		addButton.addActionListener(e -> {
			String name = nameField.getText();
			if (!name.isEmpty()) {
				try {
					String sql = "INSERT INTO projects (name) VALUES (?)";
					try (Connection conn = DatabaseConnection.connect();
						 PreparedStatement pstmt = conn.prepareStatement(sql)) {
						
						pstmt.setString(1, name);
						pstmt.executeUpdate();
						
						// Add to table
						projectModel.addRow(new Object[]{name});
						
						dialog.dispose();
						
						JOptionPane.showMessageDialog(this,
							"Project added successfully!",
							"Success",
							JOptionPane.INFORMATION_MESSAGE);
					}
				} catch (SQLException ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(this,
						"Error adding project: " + ex.getMessage(),
						"Database Error",
						JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(this,
					"Please enter a project name.",
					"Input Error",
					JOptionPane.ERROR_MESSAGE);
			}
		});
		
		cancelButton.addActionListener(e -> dialog.dispose());
		
		buttonPanel.add(addButton);
		buttonPanel.add(cancelButton);
		
		dialog.getContentPane().add(formPanel, BorderLayout.CENTER);
		dialog.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
		
		dialog.pack();
		dialog.setLocationRelativeTo(this);
		dialog.setVisible(true);
	}

	/**
	 * @brief Shows a comprehensive dialog displaying detailed information about a selected project
	 * @details This method creates a dialog showing detailed information about the selected
	 * project. It first checks if a project is selected and provides appropriate feedback
	 * if not. The dialog displays the project name and any additional project-specific
	 * information. The method includes proper error handling and provides user feedback
	 * for various scenarios. The dialog is styled consistently with the application's
	 * design and provides a clear view of the project's details.
	 */
	public void showProjectDetails() {
		int selectedRow = projectTable.getSelectedRow();
		if (selectedRow == -1) {
			JOptionPane.showMessageDialog(this,
				"Please select a project to view details.",
				"No Selection",
				JOptionPane.WARNING_MESSAGE);
			return;
		}

		String projectName = (String) projectModel.getValueAt(selectedRow, 0);

		JOptionPane.showMessageDialog(this,
			"Project Name: " + projectName,
			"Project Details",
			JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * @brief Displays the comprehensive expense logging module with management features
	 * @details This method creates and shows the expense logging interface with a modern
	 * design and full functionality. It includes a header panel with the module title and
	 * action buttons (Add Expense, Show Database). The interface is designed to be
	 * intuitive and efficient for managing expenses. The method includes proper error
	 * handling for database operations and provides user feedback for all actions.
	 * The interface maintains consistency with the application's overall design and
	 * provides clear visibility of expense-related information.
	 */
	public void showExpenseLogging() {
		contentPanel.removeAll();
		contentPanel.setLayout(new BorderLayout());
		contentPanel.setBackground(MODERN_BACKGROUND);
		
		JPanel headerPanel = new JPanel();
		headerPanel.setBackground(MODERN_ACCENT);
		headerPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		JLabel titleLabel = new JLabel("Expense Logging");
		titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
		titleLabel.setForeground(MODERN_TEXT);
		headerPanel.add(titleLabel);
		
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		buttonPanel.setBackground(MODERN_ACCENT);
		
		JButton addButton = new JButton("Add Expense");
		JButton showDbButton = new JButton("Show Database");
		
		addButton.addActionListener(e -> showAddExpenseDialog());
		showDbButton.addActionListener(e -> showModuleDatabase("Expense Logging", "expenses"));
		
		buttonPanel.add(addButton);
		buttonPanel.add(showDbButton);
		
		headerPanel.add(buttonPanel);
		
		contentPanel.add(headerPanel, BorderLayout.NORTH);
		
		mainPanel.removeAll();
		mainPanel.add(contentPanel, BorderLayout.CENTER);
		mainPanel.add(sideMenuPanel, BorderLayout.WEST);
		
		mainPanel.revalidate();
		mainPanel.repaint();
	}

	/**
	 * @brief Shows a comprehensive dialog for adding new expenses to the logging system
	 * @details This method creates a modal dialog for entering new expense details with
	 * thorough validation and error handling. The dialog includes fields for expense
	 * description and amount, each with proper labeling and input validation. The form
	 * features modern styling with proper spacing and alignment. The method handles
	 * database insertion with proper error handling and provides user feedback for
	 * various scenarios. Upon successful addition, it updates the expense records and
	 * provides confirmation to the user. The dialog includes both Add and Cancel buttons
	 * with proper styling and functionality.
	 */
	public void showAddExpenseDialog() {
		JDialog dialog = new JDialog(this, "Add Expense", true);
		dialog.getContentPane().setLayout(new BorderLayout());
		dialog.getContentPane().setBackground(MODERN_BACKGROUND);
		
		JPanel formPanel = new JPanel(new GridLayout(2, 2, 10, 10));
		formPanel.setBackground(MODERN_PANEL);
		formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		
		JTextField descriptionField = new JTextField();
		JTextField amountField = new JTextField();
		
		JLabel descLabel = new JLabel("Description:");
		descLabel.setForeground(MODERN_TEXT);
		JLabel amountLabel = new JLabel("Amount:");
		amountLabel.setForeground(MODERN_TEXT);
		
		formPanel.add(descLabel);
		formPanel.add(descriptionField);
		formPanel.add(amountLabel);
		formPanel.add(amountField);
		
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		buttonPanel.setBackground(MODERN_PANEL);
		
		JButton addButton = new JButton("Add");
		JButton cancelButton = new JButton("Cancel");
		
		addButton.addActionListener(e -> {
			try {
				String description = descriptionField.getText();
				double amount = Double.parseDouble(amountField.getText());
				
				if (!description.isEmpty()) {
					String sql = "INSERT INTO expenses (description, amount) VALUES (?, ?)";
					try (Connection conn = DatabaseConnection.connect();
						 PreparedStatement pstmt = conn.prepareStatement(sql)) {
						
						pstmt.setString(1, description);
						pstmt.setDouble(2, amount);
						pstmt.executeUpdate();

						dialog.dispose();
						
						JOptionPane.showMessageDialog(this,
							"Expense added successfully!",
							"Success",
							JOptionPane.INFORMATION_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(this,
						"Please enter a description.",
						"Input Error",
						JOptionPane.ERROR_MESSAGE);
				}
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(this,
					"Please enter a valid amount.",
					"Input Error",
					JOptionPane.ERROR_MESSAGE);
			} catch (SQLException ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(this,
					"Error adding expense: " + ex.getMessage(),
					"Database Error",
					JOptionPane.ERROR_MESSAGE);
			}
		});
		
		cancelButton.addActionListener(e -> dialog.dispose());
		
		buttonPanel.add(addButton);
		buttonPanel.add(cancelButton);
		
		dialog.getContentPane().add(formPanel, BorderLayout.CENTER);
		dialog.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
		
		dialog.pack();
		dialog.setLocationRelativeTo(this);
		dialog.setVisible(true);
	}

	/**
	 * @brief Displays the comprehensive sales tracker module with management features
	 * @details This method creates and shows the sales tracking interface with a modern
	 * design and full functionality. It includes a header panel with the module title and
	 * action buttons (Add Sale, Calculate Profit, Show Database). The interface is designed
	 * to be intuitive and efficient for managing sales. The method includes proper error
	 * handling for database operations and provides user feedback for all actions. The
	 * interface maintains consistency with the application's overall design and provides
	 * clear visibility of sales-related information.
	 */
	public void showSalesTracker() {
		contentPanel.removeAll();
		contentPanel.setLayout(new BorderLayout());
		contentPanel.setBackground(MODERN_BACKGROUND);
		
		JPanel headerPanel = new JPanel();
		headerPanel.setBackground(new Color(46, 204, 113));
		headerPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		JLabel titleLabel = new JLabel("Sales Tracker");
		titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
		titleLabel.setForeground(MODERN_TEXT);
		headerPanel.add(titleLabel);
		
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		buttonPanel.setBackground(new Color(46, 204, 113));
		
		JButton addButton = new JButton("Add Sale");
		JButton calculateButton = new JButton("Calculate Profit");
		JButton showDbButton = new JButton("Show Database");
		
		addButton.addActionListener(e -> showAddSaleDialog());
		calculateButton.addActionListener(e -> calculateProfit());
		showDbButton.addActionListener(e -> showModuleDatabase("Sales Tracker", "sales"));
		
		buttonPanel.add(addButton);
		buttonPanel.add(calculateButton);
		buttonPanel.add(showDbButton);
		
		headerPanel.add(buttonPanel);
		
		contentPanel.add(headerPanel, BorderLayout.NORTH);
		
		mainPanel.removeAll();
		mainPanel.add(contentPanel, BorderLayout.CENTER);
		mainPanel.add(sideMenuPanel, BorderLayout.WEST);
		
		mainPanel.revalidate();
		mainPanel.repaint();
	}

	/**
	 * @brief Shows a comprehensive dialog for adding new sales to the tracking system
	 * @details This method creates a modal dialog for entering new sale details with
	 * thorough validation and error handling. The dialog includes fields for item name,
	 * quantity, and price, each with proper labeling and input validation. The form
	 * features modern styling with proper spacing and alignment. The method handles
	 * database insertion with proper error handling and provides user feedback for
	 * various scenarios. Upon successful addition, it updates the sales records and
	 * provides confirmation to the user. The dialog includes both Add and Cancel buttons
	 * with proper styling and functionality.
	 */
	public void showAddSaleDialog() {
		JDialog dialog = new JDialog(this, "Add Sale", true);
		dialog.getContentPane().setLayout(new BorderLayout());
		dialog.getContentPane().setBackground(MODERN_BACKGROUND);
		
		JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 10));
		formPanel.setBackground(MODERN_PANEL);
		formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		
		JTextField itemField = new JTextField();
		JTextField quantityField = new JTextField();
		JTextField priceField = new JTextField();
		
		JLabel itemLabel = new JLabel("Item:");
		itemLabel.setForeground(MODERN_TEXT);
		JLabel quantityLabel = new JLabel("Quantity:");
		quantityLabel.setForeground(MODERN_TEXT);
		JLabel priceLabel = new JLabel("Price:");
		priceLabel.setForeground(MODERN_TEXT);
		
		formPanel.add(itemLabel);
		formPanel.add(itemField);
		formPanel.add(quantityLabel);
		formPanel.add(quantityField);
		formPanel.add(priceLabel);
		formPanel.add(priceField);
		
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		buttonPanel.setBackground(MODERN_PANEL);
		
		JButton addButton = new JButton("Add");
		JButton cancelButton = new JButton("Cancel");
		
		addButton.addActionListener(e -> {
			try {
				String item = itemField.getText();
				int quantity = Integer.parseInt(quantityField.getText());
				double price = Double.parseDouble(priceField.getText());
				
				if (!item.isEmpty()) {
					try {
						Sale sale = new Sale(item, quantity, price);
						Inventory.addSaleToDatabase(sale);
						sales.add(sale);
						
						// Create table model if not exists
						if (salesModel == null) {
							String[] columns = {"Item", "Quantity", "Price", "Total"};
							salesModel = new DefaultTableModel(columns, 0) {
								@Override
								public boolean isCellEditable(int row, int column) {
									return false;
								}
							};
						}
						
						// Add to table
						salesModel.addRow(new Object[]{item, quantity, price, price * quantity});
						
						dialog.dispose();
						
						JOptionPane.showMessageDialog(this,
							"Sale added successfully!",
							"Success",
							JOptionPane.INFORMATION_MESSAGE);
					} catch (Exception ex) {
						ex.printStackTrace();
						JOptionPane.showMessageDialog(this,
							"Error adding sale: " + ex.getMessage(),
							"Database Error",
							JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(this,
						"Please enter an item name.",
						"Input Error",
						JOptionPane.ERROR_MESSAGE);
				}
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(this,
					"Please enter valid numbers for quantity and price.",
					"Input Error",
					JOptionPane.ERROR_MESSAGE);
			}
		});
		
		cancelButton.addActionListener(e -> dialog.dispose());
		
		buttonPanel.add(addButton);
		buttonPanel.add(cancelButton);
		
		dialog.getContentPane().add(formPanel, BorderLayout.CENTER);
		dialog.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
		
		dialog.pack();
		dialog.setLocationRelativeTo(this);
		dialog.setVisible(true);
	}

	/**
	 * @brief Calculates and displays the current profit with comprehensive financial details
	 * @details This method computes the total profit by analyzing sales and expenses.
	 * It calculates the total sales revenue from all sales records and subtracts the
	 * total expenses to determine the net profit. The method displays the results in
	 * a message dialog showing total sales, total expenses, and the final profit.
	 * All monetary values are formatted properly and displayed in Turkish Lira (TL).
	 * The calculation provides a clear overview of the business's financial performance.
	 */
	public void calculateProfit() {
		double totalSales = 0;
		double totalExpenses = 0;
		
		for (Sale sale : sales) {
			totalSales += sale.getPrice() * sale.getQuantity();
		}
		
		for (Expense expense : expenses) {
			totalExpenses += expense.getAmount();
		}
		
		double profit = totalSales - totalExpenses;
		
		JOptionPane.showMessageDialog(this,
			String.format("Total Sales: %.2f TL\nTotal Expenses: %.2f TL\nProfit: %.2f TL",
				totalSales, totalExpenses, profit),
			"Profit Calculation",
			JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * @brief Refreshes the sales table display with current data from the database
	 * @details This method updates the sales table model with the current state of
	 * the sales records. It clears the existing table data and repopulates it with
	 * the current sales list. The method ensures that the display is synchronized
	 * with the database and maintains data consistency. It handles the table model
	 * updates efficiently and ensures that all columns (item, quantity, price, total)
	 * are properly displayed. This method is called after any sales modifications to
	 * keep the display current and accurate.
	 */
	public void refreshSalesTable() {
		salesModel.setRowCount(0);
		for (Sale sale : sales) {
			salesModel.addRow(new Object[]{
				sale.item(),
				sale.getQuantity(),
				sale.getPrice(),
				sale.getPrice() * sale.getQuantity()
			});
		}
	}

	/**
	 * @brief Shows a comprehensive database view for a specific module
	 * @param moduleName The name of the module whose database is to be displayed
	 * @param tableName The name of the database table to be viewed
	 * @details This method creates a modal dialog showing the raw database contents
	 * for the specified module. It includes a header panel with the module name and
	 * action buttons (Refresh, Close). The main content area displays a styled table
	 * showing all database records. The table features proper column sizing and
	 * selection highlighting. The method loads data from the database and populates
	 * the table with current records. It includes proper error handling for database
	 * operations and provides user feedback for all actions. The interface is designed
	 * to be intuitive and efficient for viewing database contents.
	 */
	public void showModuleDatabase(String moduleName, String tableName) {
		JDialog dialog = new JDialog(this, moduleName + " - Database View", true);
		dialog.getContentPane().setLayout(new BorderLayout());
		dialog.getContentPane().setBackground(MODERN_BACKGROUND);
		
		JPanel headerPanel = new JPanel();
		headerPanel.setBackground(new Color(155, 89, 182));
		headerPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		JLabel titleLabel = new JLabel(moduleName + " Database");
		titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
		titleLabel.setForeground(MODERN_TEXT);
		headerPanel.add(titleLabel);
		
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		buttonPanel.setBackground(new Color(155, 89, 182));
		
		// Create table model first
		DefaultTableModel tableModel = new DefaultTableModel();
		JTable table = new JTable(tableModel);
		table.setBackground(TABLE_COLOR);
		table.setForeground(TABLE_TEXT_COLOR);
		table.setGridColor(TABLE_HEADER_COLOR);
		table.getTableHeader().setBackground(TABLE_HEADER_COLOR);
		table.getTableHeader().setForeground(TABLE_TEXT_COLOR);
		table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		
		JButton refreshButton = new JButton("Refresh");
		JButton closeButton = new JButton("Close");
		
		refreshButton.addActionListener(e -> loadTableData(tableModel, tableName));
		closeButton.addActionListener(e -> dialog.dispose());
		
		buttonPanel.add(refreshButton);
		buttonPanel.add(closeButton);
		
		headerPanel.add(buttonPanel);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.getViewport().setBackground(TABLE_COLOR);
		
		dialog.getContentPane().add(headerPanel, BorderLayout.NORTH);
		dialog.getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		// Load table data
		loadTableData(tableModel, tableName);
		
		dialog.setSize(800, 600);
		dialog.setLocationRelativeTo(this);
		dialog.setVisible(true);
	}

	/**
	 * @brief Loads comprehensive data into a table model from the database
	 * @param model The table model to populate with database data
	 * @param tableName The name of the database table to load data from
	 * @details This method retrieves data from the specified database table and
	 * populates the provided table model. It first gets the column names from the
	 * database metadata and sets them as the table's column identifiers. Then it
	 * retrieves all records from the table and adds them to the model. The method
	 * includes proper error handling for database operations and provides user
	 * feedback for any errors that occur. It ensures that the table model is
	 * properly updated with all current data from the database.
	 */
	public void loadTableData(DefaultTableModel model, String tableName) {
		model.setRowCount(0);
		try {
			try (Connection conn = DatabaseConnection.connect();
				 Statement stmt = conn.createStatement()) {
				
				// Get column names
				ResultSet rs = stmt.executeQuery("SELECT * FROM " + tableName + " LIMIT 1");
				java.sql.ResultSetMetaData metaData = rs.getMetaData();
				int columnCount = metaData.getColumnCount();
				
				// Set column names
				String[] columns = new String[columnCount];
				for (int i = 1; i <= columnCount; i++) {
					columns[i-1] = metaData.getColumnName(i);
				}
				model.setColumnIdentifiers(columns);
				
				// Get all data
				rs = stmt.executeQuery("SELECT * FROM " + tableName);
				while (rs.next()) {
					Object[] row = new Object[columnCount];
					for (int i = 1; i <= columnCount; i++) {
						row[i-1] = rs.getObject(i);
					}
					model.addRow(row);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this,
				"Error loading table data: " + e.getMessage(),
				"Database Error",
				JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * @brief Main method to launch the application with proper initialization
	 * @param args Command line arguments (not used in this application)
	 * @details This method serves as the entry point for the application. It sets
	 * up the system look and feel for a native appearance. The method creates and
	 * displays the main application window using SwingUtilities.invokeLater to
	 * ensure proper thread handling. It includes proper error handling for the
	 * look and feel setup. The method initializes the application in a way that
	 * ensures smooth startup and proper display of the user interface.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		SwingUtilities.invokeLater(() -> {
			InventoryGUIWindowBuilder frame = new InventoryGUIWindowBuilder();
			frame.setVisible(true);
		});
	}
}