import DB.DataBase;
import InterfacesData.Employees;
import InterfacesData.Product;
import InterfacesData.TextFile;
import UiComponents.MenuButton;
import UiComponents.Views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.awt.*;
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

  private void resetForm(JTextField txtId, JTextField txtName, JTextField txtAge, JComboBox<String> workingDaySelect, JTextField txtStartDate) {
    txtId.setText("Enter the employee ID");
    txtName.setText("Enter the employee name");
    txtAge.setText("Enter the employee age");
    workingDaySelect.setSelectedIndex(0);
    txtStartDate.setText("dd/mm/yyyy");
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
      resetForm(txtId, txtName, txtAge, workingDaySelect, txtStartDate);

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

  // Event Inventory
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

  private void menuApp () {
    // Crear el panel lateral
    menuBar = new JPanel();
    menuBar.setLayout(new BoxLayout(menuBar, BoxLayout.Y_AXIS));
    menuBar.setPreferredSize(new Dimension(300, 800));
    menuBar.setBackground(Color.LIGHT_GRAY);

    // mount icon in JTable
    JLabel iconoCompensar = new JLabel(new ImageIcon("src/assets/compensar.png"));
    iconoCompensar.setMaximumSize(new Dimension(200, 200));
    iconoCompensar.setAlignmentX(Component.CENTER_ALIGNMENT);
    iconoCompensar.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
    iconoCompensar.setHorizontalAlignment(SwingConstants.CENTER);

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

    // Actualizar tabla inicialmente
    refreshTableEmployee(model, db);

    // Evento para detectar clic en "Eliminar"
    table.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        int row = table.rowAtPoint(e.getPoint());
        int column = table.columnAtPoint(e.getPoint());

        // Si la columna "Eliminar" (índice 6) fue clickeada
        if (column == 6) {
          String employeeId = model.getValueAt(row, 0).toString();
          System.out.println("Eliminar empleado con ID: " + employeeId);
        }
      }
    });

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

    // Actualizar tabla inicialmente
    refreshTableInventory(model, db);

    // Evento para detectar clic en "Eliminar"
    table.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        int row = table.rowAtPoint(e.getPoint());
        int column = table.columnAtPoint(e.getPoint());

        // Si la columna "Eliminar" (índice 6) fue clickeada
        if (column == 6) {
          String employeeId = model.getValueAt(row, 0).toString();
          System.out.println("Eliminar empleado con ID: " + employeeId);
        }
      }
    });

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

  private void viewInforms(JPanel view) {
    view.add(new JLabel("Vista de Informes"));
  }

  private void viewAboutCompensar(JPanel view) {
    view.add(new JLabel("Información de Compensar"));
  }

  private void viewAboutUs(JPanel view) {
    view.add(new JLabel("Sobre Nosotros"));
  }

  private void createViews(DataBase db) {
    // create views
    JPanel viewEmployment = new Views();
    viewEmployment(viewEmployment, db);

    JPanel viewInventory = new JPanel();
    viewInventory(viewInventory, db);

    JPanel viewInforms = new JPanel();
    viewInforms(viewInforms);

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

    menuApp();
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
