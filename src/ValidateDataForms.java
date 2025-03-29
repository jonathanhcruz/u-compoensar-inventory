import javax.swing.*;
import java.util.Date;

public class ValidateDataForms {
  public static boolean ValidateEmployeeData(String name, String id, int age, String workingDay, Date startDate, JPanel view) {
    boolean isValid = true;
    if (name == null || name.isEmpty() || name.equals("Enter the employee name")) {
      JOptionPane.showMessageDialog(view, "Name is required", "Error", JOptionPane.ERROR_MESSAGE);
      isValid = false;
    }
    if (id == null || id.isEmpty() || id.equals("Enter the employee ID") || id.length() < 8 ) {
        JOptionPane.showMessageDialog(view, "Id is required", "Error", JOptionPane.ERROR_MESSAGE);
      isValid = false;
    }
    if (age < 18) {
        JOptionPane.showMessageDialog(view, "Age must be greater than 18", "Error", JOptionPane.ERROR_MESSAGE);
      isValid = false;
    }
    if (workingDay == null || workingDay.isEmpty() || workingDay.equals("Select Working Day")) {
      JOptionPane.showMessageDialog(view, "Working day is required", "Error", JOptionPane.ERROR_MESSAGE);
      isValid = false;
    }
    if (startDate == null || startDate.toString().isEmpty() || startDate.toString().equals("dd/mm/yyyy")) {
        JOptionPane.showMessageDialog(view, "Start date is required", "Error", JOptionPane.ERROR_MESSAGE);
      isValid = false;
    }
    return isValid;
  }

  public static boolean ValidateProductData(String name, int prices,int stock, int productSold, String typeOfProduct, JPanel view) {
    boolean isValid = true;
    if (name == null || name.isEmpty() || name.equals("Enter the product name")) {
      JOptionPane.showMessageDialog(view, "Name is required", "Error", JOptionPane.ERROR_MESSAGE);
      isValid = false;
    }
    if (prices <= 0) {
        JOptionPane.showMessageDialog(view, "Price must be greater than 0", "Error", JOptionPane.ERROR_MESSAGE);
      isValid = false;
    }
    if (stock < 0) {
        JOptionPane.showMessageDialog(view, "Stock must be greater than 0", "Error", JOptionPane.ERROR_MESSAGE);
      isValid = false;
    }
    if (productSold < 0) {
        JOptionPane.showMessageDialog(view, "Product sold must be greater than 0", "Error", JOptionPane.ERROR_MESSAGE);
      isValid = false;
    }
    if (typeOfProduct == null || typeOfProduct.isEmpty() || typeOfProduct.equals("Select Type Product")) {
      JOptionPane.showMessageDialog(view, "Type of product is required", "Error", JOptionPane.ERROR_MESSAGE);
      isValid = false;
    }
    return isValid;
  }

  public static int ConvertToTypeOfProduct(String typeOfProduct) {
    return switch (typeOfProduct) {
      case "Aseo" -> 1;
      case "Papeleria" -> 2;
      case "Producto para mascotas" -> 3;
      case "Vivieres" -> 4;
      default -> 0;
    };
  }
}
