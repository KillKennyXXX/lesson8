package lesson8;

import lesson8.components.NumberJButton;
import lesson8.components.OperatorJButton;
import lesson8.listeners.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;


public class ApplicationForm extends JFrame {

    private JTextField inputField;


    public ApplicationForm(String title) throws HeadlessException {
        super(title);
        Dimension objDimension = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(objDimension.width / 2 - 125, objDimension.height / 2 - 185, 250, 370);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);
//        testListener();

        setJMenuBar(createMenu());

        setLayout(new BorderLayout());

        add(createTopPanel(), BorderLayout.NORTH);
        add(createCenterPanel(), BorderLayout.CENTER);


        setVisible(true);
    }

    private JMenuBar createMenu() {
        JMenuBar menuBar = new JMenuBar();

        JMenu menuFile = new JMenu("File");

        menuBar.add(menuFile);
        menuBar.add(new JMenuItem("Help"));
        menuBar.add(new JMenuItem("About"));

        menuFile.add(new JMenuItem("Clear"));
        JMenuItem exitItem = menuFile.add(new JMenuItem("Exit"));
        menuBar.add(exitItem);

/*        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });  */
        exitItem.addActionListener(new ExitButtonListener());


        return menuBar;
    }


    private JPanel createTopPanel() {
        JPanel top = new JPanel();
        top.setLayout(new BorderLayout());

        inputField = new JTextField();
        inputField.setEditable(false);
        top.add(inputField);
        inputField.setFont(new Font("Arial", Font.PLAIN, 25));
        inputField.setMargin(new Insets(8, 0, 8, 0));
        inputField.setBackground(new Color(255, 255, 255));

        inputField.setText("");




        return top;
    }

    private JPanel createCenterPanel() {
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());

        ActionListener buttonListener = new ButtonListener(inputField);

        centerPanel.add(createDigitsPanel(buttonListener), BorderLayout.CENTER);
        centerPanel.add(createOperatorsPanel(buttonListener), BorderLayout.WEST);

        return centerPanel;
    }

    private JPanel createDigitsPanel(ActionListener buttonListener) {
        JPanel digitsPanel = new JPanel();

        digitsPanel.setLayout(new GridLayout(4, 3));

        for (int i = 0; i < 10; i++) {
            String buttonTitle = (i == 9) ? "0" : String.valueOf(i + 1);
            JButton btn = new NumberJButton(buttonTitle);
            btn.addActionListener(buttonListener);
            digitsPanel.add(btn);
        }

        JButton calc = new OperatorJButton("=");
        digitsPanel.add(calc);
        calc.addActionListener(buttonListener);

        JButton clear = new OperatorJButton("C");
        digitsPanel.add(clear);
        clear.addActionListener(buttonListener);

        return digitsPanel;
    }

    private JPanel createOperatorsPanel(ActionListener buttonListener) {

        JPanel panel = new JPanel();


        panel.setLayout(new GridLayout(6, 1));

        JButton minus = new OperatorJButton("-");
        minus.addActionListener(buttonListener);
        panel.add(minus);

        JButton plus = new OperatorJButton("+");
        plus.addActionListener(buttonListener);
        panel.add(plus);

        JButton multiply = new OperatorJButton("*");
        multiply.addActionListener(buttonListener);
        panel.add(multiply);

        JButton divide = new OperatorJButton("/");
        divide.addActionListener(buttonListener);
        panel.add(divide);

        JButton scobka1 = new OperatorJButton("(");
        scobka1.addActionListener(buttonListener);
        panel.add(scobka1);

        JButton scobka2 = new OperatorJButton(")");
        scobka2.addActionListener(buttonListener);
        panel.add(scobka2);
        return panel;
    }

    private void testListener() {
        Button button = new Button("Кнопка");
        button.addActionListener(new TestButtonListener());
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("СОБЫТИЕ!");
            }
        });
//                button.addActionListener(e -> System.out.println("СОБЫТИЕ ЧЕРЕЗ ЛЯМБДУ!"));
        super.add(button);
    }
}
