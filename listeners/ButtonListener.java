package lesson8.listeners;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class ButtonListener implements ActionListener {

    private JTextField inputField;
    static private LinkedList list = new LinkedList();

    public ButtonListener(JTextField inputField) {
        this.inputField = inputField;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton btn = (JButton) e.getSource();
        if (btn.getText() == "=") {
            inputField.setText("=");
        } else if (btn.getText() == "C") {
            inputField.setText("");
            list.clear();

        } else {
            list.add(btn.getText());
            inputField.setText(inputField.getText() + " " + btn.getText());
        }
    }
}
