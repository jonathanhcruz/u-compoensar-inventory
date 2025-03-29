package InterfacesData;

import javax.swing.*;
import java.awt.*;

public class TextFile extends JTextField {
    public TextFile(String placeholder) {
        super();
        this.setPreferredSize(new Dimension(200, 40));
        this.setText(placeholder);
    }
}
