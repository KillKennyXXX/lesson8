package lesson8.listeners;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.lang.*;
import java.io.*;

public class ButtonListener implements ActionListener {

    private JTextField inputField;
    private boolean error = false;


    public ButtonListener(JTextField inputField) {
        this.inputField = inputField;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton btn = (JButton) e.getSource();
        if (error){
            inputField.setText("");
            error = false;
        }


        if (btn.getText() == "=") {
            //пдсчет
            try {
                inputField.setText(calculate(inputField.getText()));
            } catch (Exception ev) {
                inputField.setText("we tried " + ev);
                error = true;
            }


        } else if (btn.getText() == "C") {

            inputField.setText("");

        } else {
            inputField.setText(inputField.getText()  + btn.getText());
        }
    }


    private static String subProc(String command) throws Exception {
        Process proc = Runtime.getRuntime().exec(command); // kick off sub process
        BufferedReader stdout = new BufferedReader(new
                InputStreamReader(proc.getInputStream())); // read from stdout
        StringBuilder sb = new StringBuilder(); // build output
        String ln = stdout.readLine(); // read lines, until we are at the end
        while (ln != null) {
            sb.append(ln);
            ln = stdout.readLine();
        }
        proc.waitFor(); // wait for process to exit
        int exitCode = proc.exitValue(); // get exit code
        if (exitCode != 0) // if it isn't 0, something went wrong. Throw error
            throw new Exception("invalid math! exited with code: " + exitCode);
        return sb.toString(); // return stdout
    }

    //ApplicationForm a = new ApplicationForm( "Calc v2");
    private static String calculate(String math) throws Exception {
        //** Compile a new Java class that will spit out the calculation
        File file = File.createTempFile("tmp", ".java"); // create new temp file
        String classpath = file.getParent(), // get class path, and name
                classname = file.getName().substring(0, file.getName().length() - 5);
        PrintWriter writer = new PrintWriter(file); // write Java to temp file
        writer.println(
                "public class " + classname + "{" +
                        "public static void main(String[] args) { " +
                        "System.out.println(" + math + "); }}");
        writer.close();
        subProc("javac " + file.getAbsolutePath()); // compile it
        file.delete(); // remove our source file
        return subProc("java -cp " + classpath + " " + classname); // run it

    }


}
