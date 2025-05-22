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

public class InventoryGUI extends JFrame {
    public static final Color PRIMARY_COLOR = new Color(41, 128, 185);
    public static final Color SECONDARY_COLOR = new Color(52, 152, 219);
    public static final Color BACKGROUND_COLOR = new Color(44, 62, 80);
    public static final Color TEXT_COLOR = new Color(236, 240, 241);
    public static final Color ACCENT_COLOR = new Color(231, 76, 60);
    public static final Color PANEL_COLOR = new Color(52, 73, 94);
    public static Color TABLE_COLOR = new Color(44, 62, 80);
    public static Color TABLE_HEADER_COLOR = new Color(52, 73, 94);
    public static Color TABLE_TEXT_COLOR = new Color(236, 240, 241);
    
    // Yeni modern renkler
    public static Color MODERN_PRIMARY = new Color(26, 188, 156);
    public static final Color MODERN_SECONDARY = new Color(46, 204, 113);
    public static Color MODERN_ACCENT = new Color(155, 89, 182);
    public static Color MODERN_BACKGROUND = new Color(52, 73, 94);
    public static Color MODERN_PANEL = new Color(44, 62, 80);
    public static Color MODERN_TEXT = new Color(236, 240, 241);
    
    public JPanel mainPanel;
    public JPanel sideMenuPanel;
    public JPanel contentPanel;
    public JTextField usernameField;
    public JPasswordField passwordField;
    public String currentUser;
    public ImageIcon logoIcon;
    
    // Tables
    public JTable inventoryTable;
    public JTable projectTable;
    public JTable expenseTable;
    public JTable salesTable;
    
    // Data models
    public static DefaultTableModel inventoryModel;
    public DefaultTableModel projectModel;
    public DefaultTableModel expenseModel;
    public DefaultTableModel salesModel;
    
    // Lists to store data
    public static List<InventoryItem> inventory = new ArrayList<>();
    public List<Project> projects = new ArrayList<>();
    public List<Expense> expenses = new ArrayList<>();
    public List<Sale> sales = new ArrayList<>();
 
    public InventoryGUI() {
        super("Inventory Management System");
        initializeFrame();
        loadLogo();
        setupMainPanel();
        createWelcomeScreen();
        setVisible(true);
    }
    
    private void initializeFrame() {
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setBackground(MODERN_BACKGROUND);
    }
    
    private void loadLogo() {
        try {
            URL logoUrl = new URL("https://cdn-icons-png.flaticon.com/512/2103/2103633.png");
            Image logo = ImageIO.read(logoUrl);
            Image scaledLogo = logo.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
            logoIcon = new ImageIcon(scaledLogo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void setupMainPanel() {
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(MODERN_BACKGROUND);
        add(mainPanel);
        
        contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(MODERN_BACKGROUND);
        
        Inventory.createTables();
    }
 
    private void createWelcomeScreen() {
        mainPanel.removeAll();
 
        JPanel welcomePanel = new JPanel();
        welcomePanel.setLayout(new BoxLayout(welcomePanel, BoxLayout.Y_AXIS));
        welcomePanel.setBackground(MODERN_BACKGROUND);
        welcomePanel.setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100));

        // Logo Panel
        JPanel logoPanel = createLogoPanel();
        
        // Title and Subtitle
        JLabel welcomeLabel = createWelcomeLabel();
        JLabel subTitleLabel = createSubTitleLabel();
        
        // Buttons
        JPanel buttonPanel = createWelcomeButtons();
        
        welcomePanel.add(Box.createVerticalGlue());
        welcomePanel.add(logoPanel);
        welcomePanel.add(Box.createRigidArea(new Dimension(0, 20)));
        welcomePanel.add(welcomeLabel);
        welcomePanel.add(Box.createRigidArea(new Dimension(0, 10)));
        welcomePanel.add(subTitleLabel);
        welcomePanel.add(Box.createRigidArea(new Dimension(0, 30)));
        welcomePanel.add(buttonPanel);
        welcomePanel.add(Box.createVerticalGlue());
        
        mainPanel.add(welcomePanel, BorderLayout.CENTER);
        mainPanel.revalidate();
        mainPanel.repaint();
    }
    
    private JPanel createLogoPanel() {
        JPanel logoPanel = new JPanel();
        logoPanel.setBackground(MODERN_BACKGROUND);
        logoPanel.setPreferredSize(new Dimension(200, 200));
        logoPanel.setMaximumSize(new Dimension(200, 200));
        logoPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        if (logoIcon != null) {
            JLabel logoLabel = new JLabel(logoIcon);
            logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            logoPanel.add(logoLabel);
        }
        return logoPanel;
    }
    
    private JLabel createWelcomeLabel() {
        JLabel welcomeLabel = new JLabel("INVENTORY MANAGEMENT SYSTEM");
        welcomeLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        welcomeLabel.setForeground(MODERN_PRIMARY);
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        return welcomeLabel;
    }
    
    private JLabel createSubTitleLabel() {
        JLabel subTitleLabel = new JLabel("Manage your system efficiently");
        subTitleLabel.setFont(new Font("Segoe UI", Font.ITALIC, 18));
        subTitleLabel.setForeground(MODERN_TEXT);
        subTitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        return subTitleLabel;
    }
    
    private JPanel createWelcomeButtons() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        buttonPanel.setBackground(MODERN_BACKGROUND);
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JButton loginButton = createStyledButton("Login", MODERN_PRIMARY);
        loginButton.addActionListener(e -> showLoginPanel());
        
        JButton registerButton = createStyledButton("Register", MODERN_SECONDARY);
        registerButton.addActionListener(e -> showRegisterPanel());
        
        JButton guestButton = createStyledButton("Guest Mode", MODERN_ACCENT);
        guestButton.addActionListener(e -> {
            currentUser = null;
            showMainMenu();
        });
        
        buttonPanel.add(loginButton);
        buttonPanel.add(registerButton);
        buttonPanel.add(guestButton);
        
        return buttonPanel;
    }

    private void showLoginPanel() {
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
        loginButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        loginButton.setBackground(MODERN_PRIMARY);
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        loginButton.setBorderPainted(false);
        
        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        backButton.setBackground(Color.GRAY);
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.setBorderPainted(false);
        
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
   
    private void showRegisterPanel() {
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

        JButton registerButton = createStyledButton("Register", MODERN_PRIMARY);
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
        
        JButton backButton = createStyledButton("Back", MODERN_ACCENT);
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
    
    private void showMainMenu() {
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
        
        JButton addButton = createStyledButton("Add Material", MODERN_SECONDARY);
        JButton editButton = createStyledButton("Edit", MODERN_SECONDARY);
        JButton deleteButton = createStyledButton("Delete", MODERN_ACCENT);
        JButton showDbButton = createStyledButton("Show Database", new Color(155, 89, 182));
        
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
        
        JButton addButton = createStyledButton("Add Project", MODERN_PRIMARY);
        JButton viewButton = createStyledButton("View Details", MODERN_PRIMARY);
        JButton showDbButton = createStyledButton("Show Database", new Color(155, 89, 182));
        
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
        
        JButton addButton = createStyledButton("Add Expense", MODERN_PRIMARY);
        JButton showDbButton = createStyledButton("Show Database", new Color(155, 89, 182));
        
        addButton.addActionListener(e -> showAddExpenseDialog());
        showDbButton.addActionListener(e -> showModuleDatabase("Expense Logging", "expenses"));
        
        buttonPanel.add(addButton);
        buttonPanel.add(showDbButton);
        
        headerPanel.add(buttonPanel);
        
        contentPanel.add(headerPanel, BorderLayout.NORTH);
        // Table and scrollPane REMOVED from main screen
        
        mainPanel.removeAll();
        mainPanel.add(contentPanel, BorderLayout.CENTER);
        mainPanel.add(sideMenuPanel, BorderLayout.WEST);
        
        mainPanel.revalidate();
        mainPanel.repaint();
    }
    
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
        
        JButton addButton = createStyledButton("Add Sale", MODERN_PRIMARY);
        JButton calculateButton = createStyledButton("Calculate Profit", MODERN_SECONDARY);
        JButton showDbButton = createStyledButton("Show Database", new Color(155, 89, 182));
        
        addButton.addActionListener(e -> showAddSaleDialog());
        calculateButton.addActionListener(e -> calculateProfit());
        showDbButton.addActionListener(e -> showModuleDatabase("Sales Tracker", "sales"));
        
        buttonPanel.add(addButton);
        buttonPanel.add(calculateButton);
        buttonPanel.add(showDbButton);
        
        headerPanel.add(buttonPanel);
        
        contentPanel.add(headerPanel, BorderLayout.NORTH);
        // Table and scrollPane REMOVED from main screen
        
        mainPanel.removeAll();
        mainPanel.add(contentPanel, BorderLayout.CENTER);
        mainPanel.add(sideMenuPanel, BorderLayout.WEST);
        
        mainPanel.revalidate();
        mainPanel.repaint();
    }
    
    public void showAddMaterialDialog() {
        JDialog dialog = new JDialog(this, "Add Material", true);
        dialog.setLayout(new BorderLayout());
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
        
        JButton addButton = createStyledButton("Add", MODERN_PRIMARY);
        JButton cancelButton = createStyledButton("Cancel", MODERN_ACCENT);
        
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
        
        dialog.add(formPanel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);
        
        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }
    
    private void showEditMaterialDialog() {
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
        dialog.setLayout(new BorderLayout());
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
        
        JButton saveButton = createStyledButton("Save", MODERN_PRIMARY);
        JButton cancelButton = createStyledButton("Cancel", MODERN_ACCENT);
        
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
        
        dialog.add(formPanel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);
        
        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }
    
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
    
    public void showAddProjectDialog() {
        JDialog dialog = new JDialog(this, "Add Project", true);
        dialog.setLayout(new BorderLayout());
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
        
        JButton addButton = createStyledButton("Add", MODERN_PRIMARY);
        JButton cancelButton = createStyledButton("Cancel", MODERN_ACCENT);
        
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
        
        dialog.add(formPanel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);
        
        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }
    
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
    
    public void showAddExpenseDialog() {
        JDialog dialog = new JDialog(this, "Add Expense", true);
        dialog.setLayout(new BorderLayout());
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
        
        JButton addButton = createStyledButton("Add", MODERN_PRIMARY);
        JButton cancelButton = createStyledButton("Cancel", MODERN_ACCENT);
        
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
        
        dialog.add(formPanel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);
        
        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }
    
    public void showAddSaleDialog() {
        JDialog dialog = new JDialog(this, "Add Sale", true);
        dialog.setLayout(new BorderLayout());
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
        
        JButton addButton = createStyledButton("Add", MODERN_PRIMARY);
        JButton cancelButton = createStyledButton("Cancel", MODERN_ACCENT);
        
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
        
        dialog.add(formPanel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);
        
        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }
    
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
    
    private void refreshSalesTable() {
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
    
    private JButton createMenuButton(String text) {
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
    
    private JButton createStyledButton(String text, Color color) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                if (getModel().isPressed()) {
                    g2d.setColor(color.darker());
                } else if (getModel().isRollover()) {
                    g2d.setColor(color.brighter());
                } else {
                    g2d.setColor(color);
                }
                
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
                
                g2d.setColor(Color.WHITE);
                FontMetrics metrics = g2d.getFontMetrics();
                int x = (getWidth() - metrics.stringWidth(getText())) / 2;
                int y = ((getHeight() - metrics.getHeight()) / 2) + metrics.getAscent();
                g2d.drawString(getText(), x, y);
            }
        };
        
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setPreferredSize(new Dimension(120, 40));
        
        return button;
    }
    
    private JButton createDashboardButton(String title, Color color) {
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
    
    public void showDatabaseManager() {
        contentPanel.removeAll();
        contentPanel.setLayout(new BorderLayout());
        contentPanel.setBackground(MODERN_BACKGROUND);
        
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(155, 89, 182)); // Purple color for DB Manager
        headerPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        
        JLabel titleLabel = new JLabel("Database Manager");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(MODERN_TEXT);
        headerPanel.add(titleLabel);
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(new Color(155, 89, 182));
        
        JButton backupButton = createStyledButton("Backup DB", MODERN_PRIMARY);
        JButton restoreButton = createStyledButton("Restore DB", MODERN_SECONDARY);
        JButton clearButton = createStyledButton("Clear DB", MODERN_ACCENT);
        
        
        buttonPanel.add(backupButton);
        buttonPanel.add(restoreButton);
        buttonPanel.add(clearButton);
        
        headerPanel.add(buttonPanel);
        
        // Create table to show database tables
        String[] columns = {"Table Name", "Record Count"};
        DefaultTableModel dbModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
            return false;
        }
        };
        JTable dbTable = new JTable(dbModel);
        dbTable.setBackground(TABLE_COLOR);
        dbTable.setForeground(TABLE_TEXT_COLOR);
        dbTable.setGridColor(TABLE_HEADER_COLOR);
        dbTable.getTableHeader().setBackground(TABLE_HEADER_COLOR);
        dbTable.getTableHeader().setForeground(TABLE_TEXT_COLOR);
        dbTable.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        
        JScrollPane scrollPane = new JScrollPane(dbTable);
        scrollPane.getViewport().setBackground(TABLE_COLOR);
        
        contentPanel.add(headerPanel, BorderLayout.NORTH);
        contentPanel.add(scrollPane, BorderLayout.CENTER);
        
        mainPanel.removeAll();
        mainPanel.add(contentPanel, BorderLayout.CENTER);
        mainPanel.add(sideMenuPanel, BorderLayout.WEST);
        
        mainPanel.revalidate();
        mainPanel.repaint();
        
        loadDatabaseInfo(dbModel);
    }
    
    private void loadDatabaseInfo(DefaultTableModel model) {
        model.setRowCount(0);
        try {
            String[] tables = {"users", "inventory", "projects", "project_materials", "expenses", "sales"};
            try (Connection conn = DatabaseConnection.connect();
                 Statement stmt = conn.createStatement()) {
                
                for (String table : tables) {
                    ResultSet rs = stmt.executeQuery("SELECT COUNT(*) as count FROM " + table);
                    if (rs.next()) {
                        int count = rs.getInt("count");
                        model.addRow(new Object[]{table, count});
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                "Error loading database info: " + e.getMessage(),
                "Database Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void showModuleDatabase(String moduleName, String tableName) {
        JDialog dialog = new JDialog(this, moduleName + " - Database View", true);
        dialog.setLayout(new BorderLayout());
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
        
        JButton refreshButton = createStyledButton("Refresh", MODERN_PRIMARY);
        JButton closeButton = createStyledButton("Close", MODERN_ACCENT);
        
        refreshButton.addActionListener(e -> loadTableData(tableModel, tableName));
        closeButton.addActionListener(e -> dialog.dispose());
        
        buttonPanel.add(refreshButton);
        buttonPanel.add(closeButton);
        
        headerPanel.add(buttonPanel);
        
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.getViewport().setBackground(TABLE_COLOR);
        
        dialog.add(headerPanel, BorderLayout.NORTH);
        dialog.add(scrollPane, BorderLayout.CENTER);
        
        // Load table data
        loadTableData(tableModel, tableName);
        
        dialog.setSize(800, 600);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }
    
    private void loadTableData(DefaultTableModel model, String tableName) {
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
    
    public JTextField createStyledTextField() {
        JTextField field = new JTextField() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                g2d.setColor(MODERN_PANEL);
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
                
                super.paintComponent(g);
            }
        };
        
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setForeground(MODERN_TEXT);
        field.setCaretColor(MODERN_TEXT);
        field.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        field.setBackground(MODERN_PANEL);
        
        return field;
    }
    
    public JPasswordField createStyledPasswordField() {
        JPasswordField field = new JPasswordField() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                g2d.setColor(MODERN_PANEL);
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
                
                super.paintComponent(g);
            }
        };
        
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setForeground(MODERN_TEXT);
        field.setCaretColor(MODERN_TEXT);
        field.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        field.setBackground(MODERN_PANEL);
        
        return field;
    }
    
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        SwingUtilities.invokeLater(() -> new InventoryGUI());
    }
}