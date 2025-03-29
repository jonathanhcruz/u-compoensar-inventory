import UiComponents.MenuButton;
import UiComponents.Views;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class UiInterfaces extends JDialog {
  private JPanel contentPane;
  private JPanel menuBar;
  private JPanel views;
  private CardLayout cardLayout;


  // General methods
  private void onCancel() {
    dispose();
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
  private void viewAboutCompensar(JPanel view) {
    view.add(new JLabel("Información de Compensar"));
  }

  private void viewAboutUs(JPanel view) {
    view.add(new JLabel("Sobre Nosotros"));
  }

  private void viewEmployment(JPanel view) {
    view.add(new JLabel("Vista de Empleados"));
  }

  private void viewInventory(JPanel view) {
    view.add(new JLabel("Vista de Inventario"));
  }

  private void viewInforms(JPanel view) {
    view.add(new JLabel("Vista de Informes"));
  }

  private void createViews() {
    // create views
    JPanel viewEmployment = new Views();
    viewEmployment(viewEmployment);

    JPanel viewInventory = new JPanel();
    viewInventory(viewInventory);

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
    cardLayout = new CardLayout();
    views = new JPanel(cardLayout);

    views.setPreferredSize(new Dimension(900, 800));
    views.setBackground(Color.WHITE);

    menuApp();
    createViews();
  }

  public static void main(String[] args) {
    UiInterfaces inventory = new UiInterfaces();
    inventory.setSize(1200, 800);
    inventory.pack();
    inventory.setVisible(true);
    System.exit(0);
  }
}
