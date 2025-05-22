/**

@file InventoryAppTest.java
@brief This file contains the test cases for the InventoryApp class.
@details This file includes test methods to validate the functionality of the InventoryApp class. It uses JUnit for unit testing.
*/
package com.beyza.gokce.inventory;

import static org.junit.Assert.*;
import com.beyza.gokce.inventory.InventoryGUIWindowBuilder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.Window;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.Statement;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

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
public class InventoryGUIWindowBuilderTest {

  private static final Object MODERN_BACKGROUND = null;
/**
   * @brief This method is executed once before all test methods.
   * @throws Exception
   */

    public String gui;

    @Test
    public void testPrimaryColor() {
        Color expected = new Color(41, 128, 185);
        assertEquals(expected, InventoryGUIWindowBuilder.PRIMARY_COLOR);
    }

    @Test
    public void testSecondaryColor() {
        Color expected = new Color(52, 152, 219);
        assertEquals(expected, InventoryGUIWindowBuilder.SECONDARY_COLOR);
    }

    @Test
    public void testBackgroundColor() {
        Color expected = new Color(44, 62, 80);
        assertEquals(expected, InventoryGUIWindowBuilder.BACKGROUND_COLOR);
    }

    @Test
    public void testTextColor() {
        Color expected = new Color(236, 240, 241);
        assertEquals(expected, InventoryGUIWindowBuilder.TEXT_COLOR);
    }

    @Test
    public void testAccentColor() {
        Color expected = new Color(231, 76, 60);
        assertEquals(expected, InventoryGUIWindowBuilder.ACCENT_COLOR);
    }

    @Test
    public void testPanelColor() {
        Color expected = new Color(52, 73, 94);
        assertEquals(expected, InventoryGUIWindowBuilder.PANEL_COLOR);
    }

    @Test
    public void testTableColor() {
        Color expected = new Color(44, 62, 80);
    }

    @Test
    public void testTableHeaderColor() {
        Color expected = new Color(52, 73, 94);
        assertEquals(expected, InventoryGUIWindowBuilder.TABLE_HEADER_COLOR);
    }

    @Test
    public void testTableTextColor() {
        Color expected = new Color(236, 240, 241);
        assertEquals(expected, InventoryGUIWindowBuilder.TABLE_TEXT_COLOR);
    }

    @Test
    public void testModernPrimaryColor() {
        Color expected = new Color(26, 188, 156);
    }

    @Test
    public void testModernSecondaryColor() {
        Color expected = new Color(46, 204, 113);
        assertEquals(expected, InventoryGUIWindowBuilder.MODERN_SECONDARY);
    }

    @Test
    public void testModernAccentColor() {
        Color expected = new Color(155, 89, 182);
    }

    @Test
    public void testModernBackgroundColor() {
        Color expected = new Color(52, 73, 94);
    }

    @Test
    public void testModernPanelColor() {
        Color expected = new Color(44, 62, 80);
    }

    @Test
    public void testModernTextColor() {
        Color expected = new Color(236, 240, 241);
    }	
    
    @Test
    public void testInventoryStaticListInitialization() {
        assertNotNull(InventoryGUIWindowBuilder.inventory);
        assertFalse(InventoryGUIWindowBuilder.inventory.isEmpty());
        assertTrue(InventoryGUIWindowBuilder.inventory instanceof ArrayList);
    }


    @Test
    public void testCreateDashboardButton() {
        InventoryGUIWindowBuilder gui = new InventoryGUIWindowBuilder(); // Sınıf adını değiştir, gerekirse inner class ise uyarlayalım
        String title = "Test Button";
        Color color = Color.BLUE;

        JButton button = gui.createDashboardButton(title, color);

        // Text
        assertEquals("Test Button", button.getText());

        // Font
        assertEquals(new Font("Segoe UI", Font.BOLD, 28), button.getFont());

        // Foreground color
        assertEquals(Color.WHITE, button.getForeground());

        // Cursor
        assertEquals(Cursor.HAND_CURSOR, button.getCursor().getType());

        // Preferred size
        assertEquals(new Dimension(400, 90), button.getPreferredSize());

        // Horizontal alignment
        assertEquals(SwingConstants.CENTER, button.getHorizontalAlignment());

        // Visual flags
        assertFalse(button.isContentAreaFilled());
        assertFalse(button.isFocusPainted());
        assertFalse(button.isBorderPainted());
        assertFalse(button.isOpaque());
    }
    
    @Test
    public void testShowEditMaterialDialog_DoesNotThrow() {
        InventoryGUIWindowBuilder gui = new InventoryGUIWindowBuilder();

        // Örnek envanter verisi ve tablo setup
        gui.inventory = new ArrayList<>();
        gui.inventory.add(new InventoryItem("TestMaterial", 10, 20.0));

        // JTable ve model oluştur
        gui.inventoryTable = new JTable();
        DefaultTableModel model = new DefaultTableModel(new String[]{"Name", "Quantity", "Cost"}, 0);
        gui.inventoryTable.setModel(model);
        model.addRow(new Object[]{"TestMaterial", 10, 20.0});

        // Seçimi ayarla
        gui.inventoryTable.setRowSelectionInterval(0, 0);

        gui.showEditMaterialDialog();
    }
    
    @Test
    public void testShowMaterialInventory_DoesNotThrow() {
        InventoryGUIWindowBuilder gui = new InventoryGUIWindowBuilder();

        // Gerekli panelleri elle başlat
        gui.mainPanel = new JPanel(new BorderLayout());
        gui.contentPanel = new JPanel();
        gui.sideMenuPanel = new JPanel();

        // Gerekli sabitleri tanımla (varsayılan renkler ile)
        gui.MODERN_BACKGROUND = Color.WHITE;
        gui.MODERN_PRIMARY = Color.LIGHT_GRAY;
        gui.MODERN_TEXT = Color.BLACK;
        gui.TABLE_COLOR = Color.WHITE;

        // En azından boş inventory listesi
        gui.inventory = new ArrayList<>();

        // Test: sadece hata fırlatmıyor mu kontrol ediyoruz
        gui.showMaterialInventory();
    }

    @Test
    public void testShowAddMaterialDialog_DoesNotThrow() {
        InventoryGUIWindowBuilder gui = new InventoryGUIWindowBuilder();

        // Gerekli sabit renkleri varsayılan değerlerle tanımlıyoruz
        gui.MODERN_BACKGROUND = Color.WHITE;
        gui.MODERN_PANEL = Color.LIGHT_GRAY;
        gui.MODERN_TEXT = Color.BLACK;

        // Test sırasında dialog açılacak, bunu engellemek istemiyorsak visible olacak
        // Burada sadece hata fırlatmadığını kontrol ediyoruz
        gui.showAddMaterialDialog();
    }

    @Test
    public void testShowDeleteMaterialDialog_NoSelection() {
        InventoryGUIWindowBuilder gui = new InventoryGUIWindowBuilder();

        // Tabloyu başlat
        String[] columns = {"Name", "Quantity", "Cost"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        JTable table = new JTable(model);
        gui.inventoryTable = table;

        // Herhangi bir seçim yapılmamışken çağır
        gui.showDeleteMaterialDialog();
    }

    @Test
    public void testShowSalesTracker_DoesNotThrow() throws Exception {
    	InventoryGUIWindowBuilder gui = new InventoryGUIWindowBuilder();

        // Gerekli panelleri elle başlat
        gui.mainPanel = new JPanel(new BorderLayout());
        gui.contentPanel = new JPanel();
        gui.sideMenuPanel = new JPanel();

        // Gerekli sabitleri tanımla (örnek renklerle)
        gui.MODERN_BACKGROUND = Color.WHITE;
        gui.MODERN_TEXT = Color.BLACK;

        // Test: sadece hata fırlatmıyor mu kontrol ediyoruz (GUI thread içinde çalıştır)
        SwingUtilities.invokeAndWait(() -> gui.showSalesTracker());
    }
    
    @Test
    public void testShowExpenseLogging_DoesNotThrow() throws Exception {
    	InventoryGUIWindowBuilder gui = new InventoryGUIWindowBuilder();

        // Gerekli panelleri elle başlat
        gui.mainPanel = new JPanel(new BorderLayout());
        gui.contentPanel = new JPanel();
        gui.sideMenuPanel = new JPanel();

        // Gerekli sabitleri tanımla (örnek renklerle)
        gui.MODERN_BACKGROUND = Color.WHITE;
        gui.MODERN_ACCENT = Color.LIGHT_GRAY;
        gui.MODERN_TEXT = Color.BLACK;

        // Test: sadece hata fırlatmıyor mu kontrol ediyoruz (GUI thread içinde çalıştır)
        SwingUtilities.invokeAndWait(() -> gui.showExpenseLogging());
    }

    @Test
    public void testShowLoginPanel_DoesNotThrow() throws Exception {
    	InventoryGUIWindowBuilder gui = new InventoryGUIWindowBuilder();

        // Gerekli panelleri ve bileşenleri elle başlat
        gui.mainPanel = new JPanel(new BorderLayout());
        gui.contentPanel = new JPanel();
        gui.sideMenuPanel = new JPanel(); // Bu metodda kullanılmıyor ama diğer metotlarda var, sorun olmaz.

        // Gerekli sabitleri tanımla (örnek renklerle)
        gui.MODERN_BACKGROUND = Color.WHITE;

        // JTextField ve JPasswordField'ları başlat
        gui.usernameField = new JTextField();
        gui.passwordField = new JPasswordField();

        // GUI metodu EDT içinde çağır
        SwingUtilities.invokeAndWait(() -> gui.showLoginPanel());
    }

    @Test
    public void testShowRegisterPanel_DoesNotThrow() throws Exception {
    	InventoryGUIWindowBuilder gui = new InventoryGUIWindowBuilder();

        // Gerekli panelleri elle başlat
        gui.mainPanel = new JPanel(new BorderLayout());
        gui.contentPanel = new JPanel();

        // Gerekli sabitleri tanımla (örnek renklerle)
        gui.MODERN_BACKGROUND = Color.WHITE;
        gui.MODERN_PRIMARY = Color.BLACK;

        // GUI metodu EDT içinde çağır
        SwingUtilities.invokeAndWait(() -> gui.showRegisterPanel());
    }

    @Test
    public void testShowMainMenu_DoesNotThrow() throws Exception {
    	InventoryGUIWindowBuilder gui = new InventoryGUIWindowBuilder();

        // Gerekli panelleri elle başlat
        gui.mainPanel = new JPanel(new BorderLayout());
        gui.sideMenuPanel = new JPanel();

        // Gerekli sabitleri tanımla (örnek renklerle)
        gui.MODERN_BACKGROUND = Color.WHITE;
        gui.MODERN_PRIMARY = Color.BLACK;
        gui.MODERN_SECONDARY = Color.GRAY;
        gui.MODERN_ACCENT = Color.LIGHT_GRAY;

        // currentUser örnek ataması
        gui.currentUser = "TestUser";

        // GUI metodu EDT içinde çağır
        SwingUtilities.invokeAndWait(() -> gui.showMainMenu());
    }

    @Test
    public void testShowModule_DoesNotThrow() throws Exception {
    	InventoryGUIWindowBuilder gui = new InventoryGUIWindowBuilder();

        // Gerekli panelleri elle başlat
        gui.mainPanel = new JPanel(new BorderLayout());
        gui.contentPanel = new JPanel();
        gui.sideMenuPanel = new JPanel();

        // Gerekli sabitleri tanımla (örnek renklerle)
        gui.MODERN_BACKGROUND = Color.WHITE;
        gui.MODERN_PRIMARY = Color.BLACK;
        gui.MODERN_SECONDARY = Color.GRAY;
        gui.MODERN_ACCENT = Color.LIGHT_GRAY;

        // Modülleri test et
        SwingUtilities.invokeAndWait(() -> {
            gui.showModule("Material Inventory");
            gui.showModule("Project Tracking");
            gui.showModule("Expense Logging");
            gui.showModule("Sales Tracker");
        });
    }
    
    @Test
    public void testShowAddProjectDialog_DoesNotThrow() throws Exception {
    	InventoryGUIWindowBuilder gui = new InventoryGUIWindowBuilder();

        // Gerekli panelleri elle başlat ve sabitleri ayarla
        gui.mainPanel = new JPanel(new BorderLayout());
        gui.contentPanel = new JPanel();
        gui.sideMenuPanel = new JPanel();
        gui.MODERN_BACKGROUND = Color.WHITE;
        gui.MODERN_PANEL = Color.LIGHT_GRAY;
        gui.MODERN_TEXT = Color.BLACK;

        // Boş model hazırla (insert sonrası ekleme için)
        gui.projectModel = new DefaultTableModel(new String[]{"Project Name"}, 0);

        // EDT içinde dialog'u çağır
        SwingUtilities.invokeAndWait(() -> gui.showAddProjectDialog());
    }

    @Test
    public void testShowProjectDetails_DoesNotThrow() {
    	InventoryGUIWindowBuilder gui = new InventoryGUIWindowBuilder();

        // Gerekli panelleri ve model nesnelerini hazırla
        gui.projectModel = new DefaultTableModel(new String[]{"Project Name"}, 0);
        gui.projectTable = new JTable(gui.projectModel);

        // GUI panellerini elle oluştur
        gui.mainPanel = new JPanel(new BorderLayout());
        gui.contentPanel = new JPanel();
        gui.sideMenuPanel = new JPanel();
        gui.MODERN_BACKGROUND = Color.WHITE;

        // Test 1: Satır seçilmemiş durumda - uyarı mesajı çıkar ama hata fırlatmaz
        gui.projectTable.clearSelection();
        gui.showProjectDetails();

        // Test 2: Bir satır seçilmiş durumda
        gui.projectModel.addRow(new Object[]{"Test Project"});
        gui.projectTable.setRowSelectionInterval(0, 0);
        gui.showProjectDetails();
    }

    @Test
    public void testShowAddExpenseDialog_DoesNotThrow() {
        InventoryGUIWindowBuilder gui = new InventoryGUIWindowBuilder();

        // Gerekli panelleri elle başlat
        gui.mainPanel = new JPanel(new BorderLayout());
        gui.contentPanel = new JPanel();
        gui.sideMenuPanel = new JPanel();

        // Gerekli sabitleri tanımla (varsayılan renkler ile)
        gui.MODERN_BACKGROUND = Color.WHITE;
        gui.MODERN_PANEL = Color.LIGHT_GRAY;
        gui.MODERN_TEXT = Color.BLACK;

        // Test: sadece hata fırlatmadığını kontrol ediyoruz
        // (Dialog UI ve event'ler GUI etkileşimi gerektirir, burada sadece çağırıyoruz)
        gui.showAddExpenseDialog();
    }
    
    @Test
    public void testShowModuleDatabase_DoesNotThrow() {
        InventoryGUIWindowBuilder gui = new InventoryGUIWindowBuilder();

        // Gerekli panelleri elle başlat
        gui.mainPanel = new JPanel(new BorderLayout());
        gui.contentPanel = new JPanel();
        gui.sideMenuPanel = new JPanel();

        gui.MODERN_BACKGROUND = Color.WHITE;
        gui.TABLE_COLOR = Color.LIGHT_GRAY;
        gui.TABLE_TEXT_COLOR = Color.BLACK;
        gui.TABLE_HEADER_COLOR = Color.DARK_GRAY;
        

        // Test: sadece hata fırlatmadığını kontrol ediyoruz
        // (Dialog UI ve event'ler GUI etkileşimi gerektirir, burada sadece çağırıyoruz)
        gui.showModuleDatabase(null, null);
    }
    
    @Test
    public void testShowAddSaleDialog_DoesNotThrow() {
        InventoryGUIWindowBuilder gui = new InventoryGUIWindowBuilder();

        // Gerekli panelleri başlat
        gui.mainPanel = new JPanel(new BorderLayout());
        gui.contentPanel = new JPanel();
        gui.sideMenuPanel = new JPanel();

        // Renk sabitlerini tanımla
        gui.MODERN_BACKGROUND = Color.WHITE;
        gui.MODERN_PANEL = Color.LIGHT_GRAY;
        gui.MODERN_TEXT = Color.BLACK;

        gui.showAddSaleDialog();

    }

    @Test
    public void testRefreshSalesTable_DoesNotThrow() {
        InventoryGUIWindowBuilder gui = new InventoryGUIWindowBuilder();

        // Gerekli objeleri başlat
        gui.sales = new ArrayList<>();
        gui.salesModel = new DefaultTableModel(new String[]{"Item", "Quantity", "Price", "Total"}, 0);

        // Örnek veri ekle
        gui.sales.add(new Sale("Item1", 2, 50.0));
        gui.sales.add(new Sale("Item2", 1, 100.0));

        gui.refreshSalesTable();
    }

    @Test
    public void testCalculateProfit_DoesNotThrow() {
        InventoryGUIWindowBuilder gui = new InventoryGUIWindowBuilder();

        // Satış ve gider listelerini başlat
        gui.sales = new ArrayList<>();
        gui.expenses = new ArrayList<>();

        // Örnek satış ve gider verileri ekle
        gui.sales.add(new Sale("Item1", 2, 50.0));
        gui.sales.add(new Sale("Item2", 1, 100.0));
        gui.expenses.add(new Expense("Expense1", 30.0));
        gui.expenses.add(new Expense("Expense2", 20.0));

        gui.calculateProfit();
    }

    @Test
    public void testLoadTableData_DoesNotThrow() {
        InventoryGUIWindowBuilder gui = new InventoryGUIWindowBuilder();

        // Boş bir tablo modeli oluştur
        DefaultTableModel model = new DefaultTableModel();

        // Geçerli bir tablo adı ver (var olan bir tablo olmalı, yoksa hata çıkar)
        String tableName = "projects"; // ya da veritabanındaki başka bir tablo adı

        gui.loadTableData(model, tableName);
    }

    @Test
    public void testMain_DoesNotThrow() {
      InventoryGUIWindowBuilder.main(new String[0]);
    }

    @Test
    public void testCreateStyledTable_DoesNotThrow() {
        InventoryGUIWindowBuilder gui = new InventoryGUIWindowBuilder();

        // Örnek tablo modeli oluştur
        String[] columns = {"Column1", "Column2"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        {
            JTable table = gui.createStyledTable(model);
            assertNotNull(table); // Tablo null değilse başarılı
        };
    }
    
    @Test
    public void testRefreshInventoryTable_DoesNotThrow() {
        // Öncelikle inventory ve inventoryModel static alanların uygun şekilde hazırlanması gerekir
        InventoryGUIWindowBuilder.inventory = new ArrayList<>();
        InventoryGUIWindowBuilder.inventoryModel = new DefaultTableModel(new String[]{"Name", "Quantity", "Cost"}, 0);

        // Örnek veri ekleyelim (opsiyonel)
        InventoryGUIWindowBuilder.inventory.add(new InventoryItem("Test Item", 10, 15.5));

        InventoryGUIWindowBuilder.refreshInventoryTable();
    }
    
    @Test
    public void testShowAddSaleDialog_AddSaleFlow() throws InvocationTargetException, InterruptedException {
     
            InventoryGUI gui = new InventoryGUI();

            // GUI bileşenleri oluştur
            gui.MODERN_BACKGROUND = Color.WHITE;
            gui.MODERN_PANEL = Color.LIGHT_GRAY;
            gui.MODERN_TEXT = Color.BLACK;

            gui.sales = new ArrayList<>(); // << ÖNEMLİ: boş liste oluşturulmalı
            gui.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            gui.setVisible(true);

            // Dialogu aç
            gui.showAddSaleDialog();

            // Aktif dialogu bul
            JDialog dialog = null;
            for (Window w : Window.getWindows()) {
                if (w instanceof JDialog && w.isVisible()) {
                    dialog = (JDialog) w;
                    break;
                }
            }


            // TextField ve butonları bul
            JTextField itemField = null, quantityField = null, priceField = null;
            JButton addButton = null;

            

            // Bekleyelim
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        };
    

    private Component[] getAllComponents(Container container) {
        java.util.List<Component> list = new java.util.ArrayList<>();
        for (Component c : container.getComponents()) {
            list.add(c);
            if (c instanceof Container) {
                Component[] children = getAllComponents((Container) c);
                for (Component child : children) {
                    list.add(child);
                }
            }
        }
        return list.toArray(new Component[0]);
    }
    @Test
    public void testCreateMenuButton_HoverEffect() {
        // Arrange
        InventoryGUIWindowBuilder gui = new InventoryGUIWindowBuilder(); // Sınıf adını seninkine göre değiştir
        JButton button = gui.createMenuButton("Test");

        // Button görünür olmalı ki bazı UI olayları işlesin
        JFrame frame = new JFrame();
        frame.add(button);
        frame.pack();
        frame.setVisible(true); // Zorunlu olabilir

        // Orijinal renk
        Color originalColor = button.getBackground();

        // Act - MouseEntered tetikle
        MouseEvent enterEvent = new MouseEvent(
            button, MouseEvent.MOUSE_ENTERED,
            System.currentTimeMillis(), 0, 0, 0, 0, false
        );
        for (MouseListener ml : button.getMouseListeners()) {
            ml.mouseEntered(enterEvent);
        }

        // Assert - Hover rengi
        assertEquals("Background should change on hover",
            new Color(41, 128, 185), button.getBackground());

        // Act - MouseExited tetikle
        MouseEvent exitEvent = new MouseEvent(
            button, MouseEvent.MOUSE_EXITED,
            System.currentTimeMillis(), 0, 0, 0, 0, false
        );
        for (MouseListener ml : button.getMouseListeners()) {
            ml.mouseExited(exitEvent);
        }

        // Assert - Renk eski haline dönmeli
        assertEquals("Background should revert on exit",
            originalColor, button.getBackground());

        frame.dispose(); // test sonunda kapat
    }
    
  
}
