package org.library.gui;

import org.library.model.User;
import org.library.wrappers.ErrorMessages;

import javax.swing.*;
import java.awt.event.*;

public class LoginDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField loginValue;
    private JPasswordField passwordValue;
    private JLabel errorLabel;

    public LoginDialog() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onSubmit();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

// call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

// call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onSubmit() {
        errorLabel.setText("");
        boolean isValid = true;
        boolean isLoginEmpty = loginValue.getText().isEmpty();
        boolean isPwdEmpty = passwordValue.getPassword().length == 0;
        if(isLoginEmpty && isPwdEmpty){
            errorLabel.setText(ErrorMessages.EMPTY_LOGIN_AND_PWD);
        } else if(isLoginEmpty){
            errorLabel.setText(ErrorMessages.EMPTY_LOGIN);
            isValid = false;
        } else if(isPwdEmpty){
            errorLabel.setText(ErrorMessages.EMPTY_PWD);
            isValid = false;
        }
        System.out.println(isValid);
        if(isValid && User.login(loginValue.getText(), passwordValue.getPassword())){
            TotalBookView a = new TotalBookView();
            dispose();
        } else if(errorLabel.getText().isEmpty()) {
            errorLabel.setText(ErrorMessages.WRONG_CREDENTIALS);
        }
    }

    private void onCancel() {
// add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        LoginDialog dialog = new LoginDialog();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
