/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package drawingtable;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

/**
 *
 * @author tanelvir
 */
public class SignUp extends JFrame implements ActionListener {

    JButton SUBMIT;
    JPanel panel;
    JLabel label1, label2;
    final JTextField text1, text2;

    SignUp() {

        label1 = new JLabel();
        label1.setText("Username:");
        text1 = new JTextField(15);

        label2 = new JLabel();
        label2.setText("Password:");
        text2 = new JTextField(15);

        JButton sign = new JButton();
        sign.setText("Cancel");


        SUBMIT = new JButton("Register");

        panel = new JPanel(new GridLayout(3, 1));
        panel.add(label1);
        panel.add(text1);
        panel.add(label2);
        panel.add(text2);
        panel.add(SUBMIT);
        panel.add(sign);
        add(panel, BorderLayout.CENTER);
        SUBMIT.addActionListener(this);
        setTitle("Give new username and password, please");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent ae) {
//        String value1 = text1.getText();
//        String value2 = text2.getText();
//        if (value1.equals("roseindia") && value2.equals("roseindia")) {
//            panel.removeAll();
//            GUI gui = new GUI();
//            this.dispose();
//            gui.run();
//        } else {
//            System.out.println("enter the valid username and password");
//            JOptionPane.showMessageDialog(this, "Incorrect login or password",
//                    "Error", JOptionPane.ERROR_MESSAGE);
//        }
    }
}
