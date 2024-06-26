package com.spaceshooter.view;

import com.spaceshooter.controller.Controller;
import com.spaceshooter.model.LeaderboardData;
import com.spaceshooter.view.listenerInterfaces.*;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.net.URL;
import java.util.ArrayList;

public class GameWindow extends JFrame {

    private Controller gameController;
    private final MenuPanel menuPanel;
    private final GamePanel gamePanel;
    private final LeaderboardsPanel leaderboardsPanel;
    private final JPanel cardPanel;

    public GameWindow(int width, int height) {
        initializeGameWindowSettings();

        cardPanel = new JPanel();
        menuPanel = new MenuPanel(width, height);
        gamePanel = new GamePanel(width, height);
        leaderboardsPanel = new LeaderboardsPanel(width, height);

        cardPanel.setLayout(new CardLayout());
        cardPanel.add(gamePanel, "gamepanel");
        cardPanel.add(menuPanel, "menupanel");
        cardPanel.add(leaderboardsPanel, "leaderboardpanel");

        add(cardPanel, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(null);

        addListeners();

        CardLayout cardLayout = (CardLayout) (cardPanel.getLayout());
        cardLayout.show(cardPanel, "menupanel");
    }

    private void initializeGameWindowSettings() {
        setTitle("Space Shooter");
        setFrameIcon();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(false);
        setLayout(new BorderLayout());
    }

    public void setController(Controller gameController) {
        this.gameController = gameController;
    }

    private void addListeners() {
        gamePanel.setGamePanelMouseMovementListener(new GamePanelMouseMovementListener() {
            @Override
            public void mouseMovedInPanel(int playerX, int playerY) {
                if (gameController != null) {
                    gameController.updatePlayerPosition(playerX, playerY);
                }
            }
        });

        gamePanel.setGamePanelMouseClickListener(new GamePanelMouseClickListener() {
            @Override
            public void mouseClickedOnPanel() {
                if (gameController != null) {
                    gameController.playerMouseClicked();
                }
            }
        });

        gamePanel.setGamePanelKeyInputListener(new GamePanelKeyInputListener() {
            @Override
            public void enterKeyPressed() {
                if (gameController.attemptStopGame()) {
                    CardLayout cardLayout = (CardLayout) (cardPanel.getLayout());
                    cardLayout.show(cardPanel, "menupanel");
                    pack();
                }
            }
        });

        menuPanel.setMenuPlayButtonClickListener(new MenuPlayButtonClickListener() {
            @Override
            public void mouseButtonClick() {
                CardLayout cardLayout = (CardLayout) (cardPanel.getLayout());
                cardLayout.show(cardPanel, "gamepanel");
                gamePanel.CreateGamePanelBufferStrategy(2);
                pack();
                gameController.startGame();
            }
        });

        menuPanel.setMenuLeaderboardsClickListener(new MenuLeaderboardsClickListener() {
            @Override
            public void mouseButtonClick() {
                CardLayout cardLayout = (CardLayout) (cardPanel.getLayout());
                cardLayout.show(cardPanel, "leaderboardpanel");
                leaderboardsPanel.drawLeaderboardsTable();
                pack();
            }
        });

        menuPanel.setMenuExitClickListener(new MenuExitButtonClickListener() {
            @Override
            public void mouseButtonClick() {
                dispose();
            }
        });

        leaderboardsPanel.setLeaderboardsBackClickListener(new BackToMenuButtonClickListener() {
            @Override
            public void mouseButtonClick() {
                CardLayout cardLayout = (CardLayout) (cardPanel.getLayout());
                cardLayout.show(cardPanel, "menupanel");
                pack();
            }
        });

        leaderboardsPanel.setLeaderboardDataListener(new LeaderboardDataListener() {
            @Override
            public ArrayList<LeaderboardData> getLeaderboardData() {
                if (gameController != null) {
                    return gameController.getLeaderboardData();
                }

                return null;
            }
        });

    }

    public BufferStrategy getGamePanelCanvasBufferStrategy() {
        System.out.println(gamePanel.getCanvasBufferStrategy());
        return gamePanel.getCanvasBufferStrategy();
    }

    private void setFrameIcon() {
        URL iconURL = getClass().getResource("/com/spaceshooter/view/images/playerSpaceship.png");
        ImageIcon icon = new ImageIcon(iconURL);
        setIconImage(icon.getImage());
    }
}