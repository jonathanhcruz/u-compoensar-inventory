package UiComponents;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class IconCompensar extends JLabel {
  public IconCompensar(String url, int width, int height) {
    try {
      File imageFile = new File("src/assets/" + url);
      if (imageFile.exists()) {
        ImageIcon icon = new ImageIcon(imageFile.getAbsolutePath());
        Image scaledImage = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        this.setIcon(new ImageIcon(scaledImage));
      } else {
        System.err.println("⚠ Imagen no encontrada en la ruta: " + url);
      }

      this.setMaximumSize(new Dimension(200, 200));
      this.setAlignmentX(Component.CENTER_ALIGNMENT);
      this.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
      this.setHorizontalAlignment(SwingConstants.CENTER);
    } catch (Exception e) {
      System.err.println("⚠ Error al cargar la imagen: " + e.getMessage());
    }
  }
}