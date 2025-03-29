package UiComponents;

import javax.swing.*;
import java.awt.*;

public class MenuButton extends JButton {
    public MenuButton(String text) {
        super(text);

      this.setAlignmentX(Component.CENTER_ALIGNMENT);
      this.setMaximumSize(new Dimension(200, 50));
    }
}
