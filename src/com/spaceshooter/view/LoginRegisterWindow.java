package com.spaceshooter.view;

import com.spaceshooter.controller.Controller;
import com.spaceshooter.view.listenerInterfaces.CancelButtonClickListener;
import com.spaceshooter.view.listenerInterfaces.LoginPanelLoginButtonClickListener;
import com.spaceshooter.view.listenerInterfaces.RegisterPanelRegisterButtonClickListener;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class LoginRegisterWindow extends JFrame {
    private Controller controller;
    private final LoginPanel loginPanel;
    private final RegisterPanel registerPanel;

    public LoginRegisterWindow() {
        setTitle("Login");
        setFrameIcon();
        setPreferredSize(new Dimension(320, 200));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        setLocationRelativeTo(null);

//        registerPanel = new RegisterPanel();
        loginPanel = new LoginPanel();
        registerPanel = new RegisterPanel();

//        add(loginPanel);
        add(loginPanel);
        pack();
        setLocationRelativeTo(null);
        addPanelListeners();
    }

    private void setFrameIcon() {
        URL iconURL = getClass().getResource("/com/spaceshooter/view/images/playerSpaceship.png");
        ImageIcon icon = new ImageIcon(iconURL);
        setIconImage(icon.getImage());
    }

    private void addPanelListeners() {
        registerPanel.setRegisterButtonListener(new RegisterPanelRegisterButtonClickListener() {
            @Override
            public void registerButtonClicked(String username, String password) throws Exception {
                if (controller != null) {
                    controller.register(username, password);
                    dispose();
                    controller.startGameWindow();
                }
            }
        });

        registerPanel.setCancelButtonListener(new CancelButtonClickListener() {
            @Override
            public void mouseCancelButtonClick() {
                if (controller != null) {
                    dispose();
                    System.exit(0);
                }
            }
        });

        loginPanel.setLoginButtonClickListener(new LoginPanelLoginButtonClickListener() {
            @Override
            public void loginButtonClick(String username, String password) throws Exception {
                if (controller.login(username, password)) {
                    if (controller != null) {
                        dispose();
                        controller.startGameWindow();
                    }
                } else {
                    setContentPane(registerPanel);
                    setTitle("Register");
                    pack();
                }
            }
        });

        loginPanel.setCancelButtonClickListener(new CancelButtonClickListener() {
            @Override
            public void mouseCancelButtonClick() {
                dispose();
                System.exit(0);
            }
        });
    }

    public void setController(Controller gameController) {
        this.controller = gameController;
    }
}
