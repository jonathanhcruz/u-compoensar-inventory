import UiComponents.MenuButton;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class UiInterfaces extends JDialog {
  private JPanel contentPane;
  private JPanel menuBar;
  private JPanel views;
  private CardLayout cardLayout;

  public UiInterfaces() {
    setContentPane(contentPane);
    setModal(true);


    // Configurar el Layout principal
    setLayout(new BorderLayout());

    // Crear las vistas
    createUIComponents();

    // Configurar el CardLayout
    add(menuBar, BorderLayout.WEST);
    add(views, BorderLayout.CENTER);

    setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        onCancel();
      }
    });
  }

  private void onCancel() {
    dispose();
  }


  private void viewAboutCompensar() {
    // Crear un panel con la información de Compensar
  }

  private void viewAboutUs() {
    // Crear un panel con la información de los desarrolladores
  }

  private void viewInventory() {
    // Crear un panel con la información del inventario
  }

  private void viewInforms() {
    // Crear un panel con la información de los informes
  }

  private void viewEmployment() {
    // Crear un panel con la información de los empleados
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
      viewEmployment();
    });
    btnInventory.addActionListener(e -> {
      cardLayout.show(views, "inventory");
      viewInventory();
    });
    btnInforms.addActionListener(e -> {
      cardLayout.show(views, "Informs");
      viewInforms();
    });
    btnAbutCompensar.addActionListener(e -> {
      cardLayout.show(views, "Compensar");
      viewAboutCompensar();
    });
    btnAboutUs.addActionListener(e -> {
      cardLayout.show(views, "AboutUs");
      viewAboutUs();
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

  private void createViews() {
    // Crear vistas y añadirlas al CardLayout
    JPanel viewEmployment = new JPanel();
    viewEmployment.add(new JLabel("Vista de Empleados"));
    viewEmployment.setMaximumSize(new Dimension(900, 800));

    JPanel viewInventory = new JPanel();
    viewInventory.add(new JLabel("Vista de Inventario"));

    JPanel viewInforms = new JPanel();
    viewInforms.add(new JLabel("Vista de Informes"));

    JPanel viewAboutCompensar = new JPanel();
    viewAboutCompensar.add(new JLabel("Información de Compensar"));

    JPanel viewAboutUs = new JPanel();
    viewAboutUs.add(new JLabel("Sobre Nosotros"));
    views.add(viewInventory, "inventory");

    // Añadir paneles al CardLayout
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
