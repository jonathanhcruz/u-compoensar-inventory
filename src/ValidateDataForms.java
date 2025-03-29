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


}
