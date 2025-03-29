import DB.DataBase;
import InterfacesData.Employees;
import InterfacesData.Product;
import InterfacesData.TextFile;
import UiComponents.IconCompensar;
import UiComponents.MenuButton;
import UiComponents.Views;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.desktop.AboutEvent;
import java.awt.event.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;


public class UiInterfaces extends JDialog {
  // Variables
  private DataBase db;
  private JPanel contentPane;
  private JPanel menuBar;
  private JPanel views;
  private CardLayout cardLayout;


  // General methods
  private void onCancel() {
    dispose();
  }

  // Event Employee
  private void handlerEventEmployee (JTextField txtId, JTextField txtName, JTextField txtAge, JComboBox<String> workingDaySelect, JTextField txtStartDate, DefaultTableModel model, JPanel view, DataBase db) {
    try {
      // Validation and conversion
      String id = txtId.getText() ;
      String name = txtName.getText();
      int age = Integer.parseInt(txtAge.getText());
      String workingDay = Objects.requireNonNull(workingDaySelect.getSelectedItem()).toString();
      Date startDate = new Date();

      // Validate data
      if (!ValidateDataForms.ValidateEmployeeData(name, id, age, workingDay, startDate, view)) {
          return;
      }

      // Create new employee
      Employees newEmployee = new Employees(name, id, age, workingDay, startDate);
      db.addEmployee(newEmployee);

      // Actualizar la tabla después de agregar
      refreshTableEmployee(model, db);

      // Clear form
      resetFormEmployee(txtId, txtName, txtAge, workingDaySelect, txtStartDate);

      JOptionPane.showMessageDialog(view, "Employee added successfully");
    } catch (Exception ex) {
      JOptionPane.showMessageDialog(view, "Error adding employee: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
  }
  private void refreshTableEmployee(DefaultTableModel model, DataBase db) {
    // Clear table
    model.setRowCount(0);

    // Add data to the table
    for (Employees employee : db.getEmployees()) {

      model.addRow(new Object[]{
              employee.getId(),
              employee.getName(),
              employee.getAge(),
              employee.getWorkingDay(),
              employee.getTimeWorked(),
              "Edit",
              "Delete"
      });
    }
  }
  private void resetFormEmployee(JTextField txtId, JTextField txtName, JTextField txtAge, JComboBox<String> workingDaySelect, JTextField txtStartDate) {
    txtId.setText("Enter the employee ID");
    txtName.setText("Enter the employee name");
    txtAge.setText("Enter the employee age");
    workingDaySelect.setSelectedIndex(0);
    txtStartDate.setText("dd/mm/yyyy");
  }

  // Event Inventory
  private void handlerEventInventory (JTextField txtName, JTextField txtPrices, JComboBox<String> typeProductSelect, JTextField txtStock, JTextField txtProductsSold, DefaultTableModel model, JPanel view, DataBase db) {
    try {
      // Validation and conversion
      String name = txtName.getText();
      int prices = Integer.parseInt(txtPrices.getText());
      int stock = Integer.parseInt(txtStock.getText());
      int productSold = Integer.parseInt(txtProductsSold.getText());
      String typeOfProduct = Objects.requireNonNull(typeProductSelect.getSelectedItem()).toString();

      // Validate data
      if (!ValidateDataForms.ValidateProductData(name, prices, stock, productSold, typeOfProduct, view)) {
        return;
      }

      // Convert type of product on integer
      int typeOfProductNumber = ValidateDataForms.ConvertToTypeOfProduct(typeOfProduct);

      // Create new product
      Product product = new Product(name, typeOfProductNumber, stock, prices, productSold);
      db.addProduct(product);

      // Actualizar la tabla después de agregar
      refreshTableInventory(model, db);

      // Clear form
      resetFormInventory(txtName, txtPrices, typeProductSelect, txtStock, txtProductsSold);

      JOptionPane.showMessageDialog(view, "Employee added successfully");
    } catch (Exception ex) {
      JOptionPane.showMessageDialog(view, "Error adding employee: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
  }
  private void refreshTableInventory(DefaultTableModel model, DataBase db) {
    // Clear table
    model.setRowCount(0);

    // Add data to the table
    for (Product product : db.getProducts()) {
      model.addRow(new Object[]{
              product.getId(),
              product.getName(),
              product.getTypeOfProduct(),
              product.getPrice(),
              product.getIva(),
              product.getTotal(),
              product.getStock(),
              product.getProductsSold(),
              "Edit",
              "Delete"
      });
    }
  }
  private void resetFormInventory(JTextField txtName, JTextField txtPrices, JComboBox<String> typeProductSelect, JTextField txtStock, JTextField txtProductsSold) {
    txtName.setText("Enter the product name");
    txtPrices.setText("Enter the product price");
    txtStock.setText("Enter the stock");
    txtProductsSold.setText("Enter number of products sold");
    typeProductSelect.setSelectedIndex(0);
  }

  // Interface methods
  public UiInterfaces() {
    setContentPane(contentPane);
    setModal(true);

    // config window properties
    setLayout(new BorderLayout());

    // mount of ui components
    createUIComponents();

    // add components to the window
    add(menuBar, BorderLayout.WEST);
    add(views, BorderLayout.CENTER);

    setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        onCancel();
      }
    });
  }

  // Menu
  private void menuApp (DataBase db) {
    // Crear el panel lateral
    menuBar = new JPanel();
    menuBar.setLayout(new BoxLayout(menuBar, BoxLayout.Y_AXIS));
    menuBar.setPreferredSize(new Dimension(300, 800));
    menuBar.setBackground(Color.LIGHT_GRAY);

    // mount icon in JTable
    JLabel iconoCompensar = new IconCompensar("compensar.png", 200, 200);

    // Agregar botones de ejemplo
    JButton btnEmploy = new MenuButton("Empleados");
    JButton btnInventory = new MenuButton("Inventario");
    JButton btnInforms = new MenuButton("Informes");
    JButton btnAbutCompensar = new MenuButton("Compensar Info");
    JButton btnAboutUs = new MenuButton("Sobre Nosotros");
    JButton btnExit = new MenuButton("Salir");


    // Acciones de botones
    btnEmploy.addActionListener(e -> {
      cardLayout.show(views, "Employ");
    });
    btnInventory.addActionListener(e -> {
      cardLayout.show(views, "inventory");
    });
    btnInforms.addActionListener(e -> {
      cardLayout.show(views, "Informs");
    });
    btnAbutCompensar.addActionListener(e -> {
      cardLayout.show(views, "Compensar");
    });
    btnAboutUs.addActionListener(e -> {
      cardLayout.show(views, "AboutUs");
    });
    btnExit.addActionListener(e -> onCancel());


    // Añadir botones al panel lateral
    menuBar.add(Box.createVerticalStrut(50)); // Espacio superior
    menuBar.add(iconoCompensar);
    menuBar.add(Box.createVerticalStrut(50)); // Espacio superior
    menuBar.add(btnEmploy);
    menuBar.add(Box.createVerticalStrut(10)); // Separación
    menuBar.add(btnInventory);
    menuBar.add(Box.createVerticalStrut(10)); // Separación
    menuBar.add(btnInforms);
    menuBar.add(Box.createVerticalStrut(10)); // Separación
    menuBar.add(btnAbutCompensar);
    menuBar.add(Box.createVerticalStrut(10)); // Separación
    menuBar.add(btnAboutUs);
    menuBar.add(Box.createVerticalStrut(10)); // Separación
    menuBar.add(btnExit);
  }

  // Views
  private void viewEmployment(JPanel view, DataBase db) {
    // Add title of view
    JLabel title = new JLabel("Empleados");
    title.setFont(new Font("Arial", Font.BOLD, 20));
    title.setBorder(BorderFactory.createEmptyBorder(40, 0, 0, 0));

    view.add(title);

    // Table configuration
    String[] columnNames = {"ID", "Nombre", "Edad", "Jornada", "Tiempo Trabajado", "Editar", "Eliminar"};
    DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
      @Override
      public boolean isCellEditable(int row, int column) {
        return false;
      }
    };

    JTable table = new JTable(model);
    table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
    table.setPreferredScrollableViewportSize(new Dimension(800, 400));
    table.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));

    // Update table initially
    refreshTableEmployee(model, db);

    // Form configuration
    JPanel formPanel = new JPanel();
    formPanel.setLayout(new GridLayout(2, 5, 10, 10));
    formPanel.setBorder(BorderFactory.createTitledBorder("Add Employee"));
    formPanel.setBorder(BorderFactory.createEmptyBorder(30, 10, 10, 10));
    formPanel.setPreferredSize(new Dimension(800, 150));

    // input fields
    JTextField txtId = new TextFile("Enter the employee ID");
    JTextField txtName = new TextFile("Enter the employee name");
    JTextField txtAge = new TextFile("Enter the employee age");
    JTextField txtStartDate = new JTextField("dd/mm/yyyy"); // Format: dd/mm/yyyy

    // Selects
    JComboBox<String> workingDaySelect = new JComboBox<>(new String[]{"Select Working Day", "Diurno", "Nocturno"});
    workingDaySelect.setPreferredSize(new Dimension(200, 40));

    // Button
    JButton btnAdd = new JButton("Add");
    btnAdd.setPreferredSize(new Dimension(200, 40));

    // Add data to DB
    btnAdd.addActionListener(e -> {
      handlerEventEmployee(txtId, txtName, txtAge, workingDaySelect, txtStartDate, model, view, db);
    });

    formPanel.add(txtId);
    formPanel.add(txtName);
    formPanel.add(txtAge);
    formPanel.add(workingDaySelect);
    formPanel.add(txtStartDate);

    // Add components to the view
    view.add(formPanel, BorderLayout.SOUTH);
    view.add(btnAdd, BorderLayout.CENTER);

    JScrollPane scrollPane = new JScrollPane(table);
    view.add(scrollPane, BorderLayout.CENTER);
  }

  private void viewInventory(JPanel view, DataBase db) {
    // Add title of view
    JLabel title = new JLabel("Inventario");
    title.setFont(new Font("Arial", Font.BOLD, 20));
    title.setBorder(BorderFactory.createEmptyBorder(40, 0, 0, 0));

    view.add(title);

    // Table configuration
    String[] columnNames = {"ID", "Nombre", "Tipode producto", "Precio", "Valor Iva", "Total", "Stock", "# Vendidos", "Editar", "Eliminar"};
    DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
      @Override
      public boolean isCellEditable(int row, int column) {
        return false;
      }
    };

    JTable table = new JTable(model);
    table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
    table.setPreferredScrollableViewportSize(new Dimension(800, 400));
    table.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));

    // Update table initially
    refreshTableInventory(model, db);

    // Form configuration
    JPanel formPanel = new JPanel();
    formPanel.setLayout(new GridLayout(2, 5, 10, 10));
    formPanel.setBorder(BorderFactory.createTitledBorder("Add Employee"));
    formPanel.setBorder(BorderFactory.createEmptyBorder(30, 10, 10, 10));
    formPanel.setPreferredSize(new Dimension(800, 150));

    // input fields
    JTextField txtName = new TextFile("Enter the product name");
    JTextField txtPrices = new TextFile("Enter the product price");
    JTextField txtStock = new JTextField("Enter the stock");
    JTextField txtProductsSold = new JTextField("Enter number of products sold");

    // Selects
    JComboBox<String> typeProductSelect = new JComboBox<>(new String[]{"Select Type Product", "Aseo", "Papeleria", "Producto para mascotas", "Vivieres", "Otros"});
    typeProductSelect.setPreferredSize(new Dimension(200, 40));

    // Button
    JButton btnAdd = new JButton("Add");
    btnAdd.setPreferredSize(new Dimension(200, 40));

    // Add data to DB
    btnAdd.addActionListener(e -> {
      handlerEventInventory(txtName, txtPrices, typeProductSelect, txtStock, txtProductsSold, model, view, db);
    });

    formPanel.add(txtName);
    formPanel.add(typeProductSelect);
    formPanel.add(txtPrices);
    formPanel.add(txtStock);
    formPanel.add(txtProductsSold);

    // Add components to the view
    view.add(formPanel, BorderLayout.SOUTH);
    view.add(btnAdd, BorderLayout.CENTER);

    JScrollPane scrollPane = new JScrollPane(table);
    view.add(scrollPane, BorderLayout.CENTER);
  }

  private void viewInforms(JPanel view, DataBase db) {
    view.removeAll();

    // Get data products
    ArrayList<Product> allProducts = db.getProducts();
    ArrayList<Employees> allEmployees = db.getEmployees();

    try {
      // Structure data for the graphic products sold
      DefaultPieDataset dataProductsSold = new DefaultPieDataset();
      for (Product product : allProducts) {
        dataProductsSold.setValue(product.getName(), product.getProductsSold() > 0 ? product.getProductsSold() : 1);
      }

      // Create graphic
      JFreeChart graphicProductsSellers = ChartFactory.createPieChart("Productos vendidos", dataProductsSold, true, true, false);

      // set panel for graphic
      ChartPanel productPanel = new ChartPanel(graphicProductsSellers);
      productPanel.setMouseWheelEnabled(true);
      productPanel.setPreferredSize(new Dimension(900, 400));

      // Structure data for the graphic employees worked time
      DefaultPieDataset dataEmployeesWorkedTime = new DefaultPieDataset();
      int dayWork = 0;
      int nightWork = 0;

      for (Employees employee : allEmployees) {
        if (employee.getWorkingDay().equals("Diurno")) {
          dayWork++;
        } else {
          nightWork++;
        }
      }

      dataEmployeesWorkedTime.setValue("Diurno", dayWork);
      dataEmployeesWorkedTime.setValue("Nocturno", nightWork);

      // Create graphic
      JFreeChart graphicEmployeesWorkedTime = ChartFactory.createPieChart("Jornada de trabajado por empleado", dataEmployeesWorkedTime, true, true, false);

      // set panel for graphic
      ChartPanel employeePanel = new ChartPanel(graphicEmployeesWorkedTime);
      employeePanel.setMouseWheelEnabled(true);
      employeePanel.setPreferredSize(new Dimension(900, 400));

      // Add components to the view
      view.setLayout(new BorderLayout());
      view.add(productPanel, BorderLayout.CENTER);
      view.add(employeePanel, BorderLayout.SOUTH);

      // Refrescar el panel
      view.revalidate();
      view.repaint();
    } catch (Exception ex) {
      JOptionPane.showMessageDialog(view, "Error creating the chart: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
      JLabel error = new JLabel("Error creating the chart");
      error.setFont(new Font("Arial", Font.BOLD, 20));
      view.add(error);
    }
  }

  private void viewAboutCompensar(JPanel view) {
    view.setLayout(new BorderLayout());

    // Create Image
    JLabel iconoCompensar = new IconCompensar("compensar.png", 200, 200);
    iconoCompensar.setBorder(BorderFactory.createEmptyBorder(30, 0, 50, 0));

    // Create text
    final String textAboutCompensar = "Compensar como entidad de Seguridad Social facilita a sus afiliados, a través de los Planes de Bienestar, un amplio portafolio\u200B de servicios que le permite, mediante su utilización empresarial e individual, generar condiciones que favorecen el desarrollo personal y laboral de sus trabajadores y grupo familiar.\n" +
            "\n" +
            "Se busca el cumplimiento de propósitos de la comunidad laboral, asociados con calidad de vida, desarrollo personal y profesional, clima laboral y productividad. La labor conjunta generada por la interacción con nuestros afiliados permite recibir sus opiniones, sueños y expectativas, así como afianzar nuestra incondicional voluntad de servicio en procura de brindar el mejor esfuerzo para la construcción de una sociedad mas justa y equilibrada.";

    JTextArea aboutArea = new JTextArea(textAboutCompensar);
    aboutArea.setLineWrap(true);
    aboutArea.setWrapStyleWord(true);
    aboutArea.setEditable(false);
    aboutArea.setFont(new Font("Arial", Font.PLAIN, 14));
    aboutArea.setBorder(BorderFactory.createEmptyBorder(30, 50, 10, 50));

    // Create panel for content
    JPanel panelContenido = new JPanel();
    panelContenido.setLayout(new BoxLayout(panelContenido, BoxLayout.Y_AXIS));
    panelContenido.add(iconoCompensar);
    panelContenido.add(aboutArea);

    // Add components to the view
    view.add(panelContenido, BorderLayout.CENTER);

    view.revalidate();
    view.repaint();
  }

  private void viewAboutUs(JPanel view) {
    view.setLayout(new BorderLayout());

    // Create Image
    JLabel iconoCompensar = new IconCompensar("me.jpg", 200, 200);
    iconoCompensar.setBorder(BorderFactory.createEmptyBorder(30, 0, 50, 0));

    // Create text
    final String textAboutCompensar = "Soy Jonathan Cruz, un desarrollador frontend con 7 años de experiencia en la industria. A lo largo de mi carrera, he trabajado con tecnologías como JavaScript, React, Vue, Next.js, Dart, Flutter y SQL, lo que me ha permitido desarrollar aplicaciones robustas y eficientes. Actualmente, soy Software Engineer en Mercado Libre, donde continúo aprendiendo y creciendo en el mundo del desarrollo.\n" +
            "\n" +
            "Fuera del trabajo, me apasiona viajar y explorar nuevos lugares, lo que me inspira a mantenerme curioso y abierto a nuevas experiencias. También disfruto del deporte, ya que me ayuda a mantener el equilibrio entre el trabajo y mi bienestar personal. Mi enfoque siempre ha sido el de aprender y mejorar constantemente, lo que me motiva a afrontar nuevos desafíos y continuar creciendo profesionalmente.";

    JTextArea aboutArea = new JTextArea(textAboutCompensar);
    aboutArea.setLineWrap(true);
    aboutArea.setWrapStyleWord(true);
    aboutArea.setEditable(false);
    aboutArea.setFont(new Font("Arial", Font.PLAIN, 14));
    aboutArea.setBorder(BorderFactory.createEmptyBorder(30, 50, 10, 50));

    // Create panel for content
    JPanel panelContenido = new JPanel();
    panelContenido.setLayout(new BoxLayout(panelContenido, BoxLayout.Y_AXIS));
    panelContenido.add(iconoCompensar);
    panelContenido.add(aboutArea);

    // Add components to the view
    view.add(panelContenido, BorderLayout.CENTER);

    view.revalidate();
    view.repaint();
  }

  private void createViews(DataBase db) {
    // create views
    JPanel viewEmployment = new Views();
    viewEmployment(viewEmployment, db);

    JPanel viewInventory = new JPanel();
    viewInventory(viewInventory, db);

    JPanel viewInforms = new JPanel();
    viewInforms(viewInforms, db);

    JPanel viewAboutCompensar = new JPanel();
    viewAboutCompensar(viewAboutCompensar);

    JPanel viewAboutUs = new JPanel();
    viewAboutUs(viewAboutUs);

    // Add views to the CardLayout
    views.add(viewInventory, "inventory");
    views.add(viewEmployment, "Employ");
    views.add(viewInforms, "Informs");
    views.add(viewAboutCompensar, "Compensar");
    views.add(viewAboutUs, "AboutUs");
  }

  private void createUIComponents() {
    DataBase db = new DataBase();
    cardLayout = new CardLayout();
    views = new JPanel(cardLayout);


    views.setPreferredSize(new Dimension(900, 800));
    views.setBackground(Color.WHITE);

    menuApp(db);
    createViews(db);
  }

  public static void main(String[] args) {
    UiInterfaces inventory = new UiInterfaces();
    inventory.setSize(1200, 800);
    inventory.pack();
    inventory.setVisible(true);
    System.exit(0);
  }
}
