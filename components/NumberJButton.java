package lesson8.components;

import javax.swing.*;
import java.awt.*;

public class NumberJButton extends JButton {
    public NumberJButton(String text) {
        super(text);
        setFont(new Font("Gothic", Font.ITALIC, 25));
        setBackground(new Color(39, 96, 147));
    }
}
