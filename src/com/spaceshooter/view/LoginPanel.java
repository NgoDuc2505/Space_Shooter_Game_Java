package com.spaceshooter.view;

import com.spaceshooter.view.listenerInterfaces.CancelButtonClickListener;
import com.spaceshooter.view.listenerInterfaces.LoginPanelLoginButtonClickListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LoginPanel extends JPanel {
    private final JTextField userText;
    private final JPasswordField passwordText;
    private final JTextArea errorArea;
    private final JButton loginButton;
    private final JButton cancelButton;
    private LoginPanelLoginButtonClickListener loginButtonClickListener;
    private CancelButtonClickListener cancelButtonClickListener;

    public LoginPanel() {
        setLayout(null);
        setVisible(true);

        JLabel userLabel = new JLabel("Username");
        userLabel.setBounds(10, 20, 80, 25);
        add(userLabel);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(10, 50, 80, 25);//padding
        add(passwordLabel);

        passwordText = new JPasswordField(20);
        passwordText.setBounds(100, 50, 165, 25);
        add(passwordText);

        userText = new JTextField(20);
        userText.setBounds(100, 20, 165, 25);
        add(userText);

        loginButton = new JButton("Login");
        loginButton.setBounds(10, 80, 90, 25);
        add(loginButton);

        cancelButton = new JButton("Cancel");
        cancelButton.setBounds((int) (loginButton.getBounds().getWidth() + 20), 80, 90, 25);
        add(cancelButton);

        errorArea = new JTextArea("");
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

        addComponentListeners();
    }

    private void addComponentListeners() {
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (loginButtonClickListener != null) {
                    String username = userText.getText();
                    String password = new String(passwordText.getPassword());
                    System.out.println(username + password);
                    try {
                        loginButtonClickListener.loginButtonClick(username, password);
                        System.out.println("Enter here...!");
                    } catch (Exception exception) {
                        System.out.println("Bug here...!");
                        errorArea.setText(exception.getMessage());
//                        errorArea.setText("Bug here...!");
                    }
                }
            }
        });

        loginButton.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    loginButton.doClick();
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cancelButtonClickListener != null) {
                    cancelButtonClickListener.mouseCancelButtonClick();
                }
            }
        });

        cancelButton.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    cancelButton.doClick();
                }
            }
        });
    }

    public void setLoginButtonClickListener(LoginPanelLoginButtonClickListener loginButtonClickListener) {
        this.loginButtonClickListener = loginButtonClickListener;
    }

    public void setCancelButtonClickListener(CancelButtonClickListener cancelButtonClickListener) {
        this.cancelButtonClickListener = cancelButtonClickListener;
    }
}
