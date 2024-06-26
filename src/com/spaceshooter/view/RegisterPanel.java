package com.spaceshooter.view;

import com.spaceshooter.view.listenerInterfaces.CancelButtonClickListener;
import com.spaceshooter.view.listenerInterfaces.RegisterPanelRegisterButtonClickListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterPanel extends JPanel {
    private static JTextField userText;
    private static JPasswordField passwordText;
    private static JLabel userLabel, passwordLabel;
    private final JTextArea errorArea;
    private static JButton registerButton, cancelButton, loginButton;
    private RegisterPanelRegisterButtonClickListener registerButtonListener;
    private CancelButtonClickListener cancelButtonListener;

    public RegisterPanel() {
        setLayout(null);

        userLabel = new JLabel("Username");
        userLabel.setBounds(10, 20, 80, 25);
        add(userLabel);

        passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(10, 50, 80, 25);//padding
        add(passwordLabel);

        passwordText = new JPasswordField(20);
        passwordText.setBounds(100, 50, 165, 25);
        add(passwordText);

        userText = new JTextField(20);
        userText.setBounds(100, 20, 165, 25);
        add(userText);

        registerButton = new JButton("Register");
        registerButton.setBounds(10, 80, 90, 25);
//        add(registerButton);

        cancelButton = new JButton("Cancel");
        loginButton = new JButton("Login");
        cancelButton.setBounds((int) (registerButton.getBounds().getWidth() + 20), 80, 90, 25);
        loginButton.setBounds((int) (cancelButton.getBounds().getWidth() + 120), 80, 90, 25);
        add(cancelButton);
//        add(loginButton);
        add(registerButton);

        errorArea = new JTextArea("User not found. You must register first");
        errorArea.setOpaque(false);
        errorArea.setWrapStyleWord(true);
        errorArea.setLineWrap(true);
        Font errorFont = new Font("Helvetica", Font.PLAIN, 12);
        errorArea.setFont(errorFont);
        errorArea.setForeground(Color.RED);
        errorArea.setEnabled(false);
        errorArea.setDisabledTextColor(Color.RED);
        errorArea.setBounds(10, 120, 270, 50);
        add(errorArea);

        createComponentListeners();
    }

    private void createComponentListeners() {
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (registerButtonListener != null) {
                    String username = userText.getText();
                    String password = new String(passwordText.getPassword());
                    System.out.println(username +"=="+ password);
                    try {
                        registerButtonListener.registerButtonClicked(username, password);
                    } catch (Exception exception) {
                        errorArea.setText(exception.getMessage());
                    }
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cancelButtonListener != null) {
                    cancelButtonListener.mouseCancelButtonClick();
                }
            }
        });
    }

    public void setRegisterButtonListener(RegisterPanelRegisterButtonClickListener registerButtonListener) {
        this.registerButtonListener = registerButtonListener;
    }

    public void setCancelButtonListener(CancelButtonClickListener cancelButtonListener) {
        this.cancelButtonListener = cancelButtonListener;
    }
}
