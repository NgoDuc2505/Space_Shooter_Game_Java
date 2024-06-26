package com.spaceshooter.view;

import com.spaceshooter.view.listenerInterfaces.MenuExitButtonClickListener;
import com.spaceshooter.view.listenerInterfaces.MenuLeaderboardsClickListener;
import com.spaceshooter.view.listenerInterfaces.MenuPlayButtonClickListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class MenuPanel extends JPanel {
    private final ImageHandler imageHandler = new ImageHandler();

    private final BufferedImage menuBackgroundImage = ImageHandler.GetMenuImage(
            "/com/spaceshooter/view/images/menuBackground.jpg");

    private final JLabel menuLogo = new JLabel(imageHandler.CreateIcon("/com/spaceshooter/view/images/logo.png"));

    private final JButton playButton = new JButton();
    private final JButton leaderboardsButton = new JButton();
    private final JButton exitButton = new JButton();

    private final ImageIcon playButtonIcon = imageHandler.CreateIcon("/com/spaceshooter/view/images/menuPlayButton.png");
    private final ImageIcon playButtonOpaqueIcon = imageHandler.CreateIcon(
            "/com/spaceshooter/view/images/menuPlayButton_Opaque.png");
    private final ImageIcon leaderboardsButtonIcon = imageHandler.CreateIcon(
            "/com/spaceshooter/view/images/menuLeaderboardsButton.png");
    private final ImageIcon leaderboardsButtonOpaqueIcon = imageHandler.CreateIcon(
            "/com/spaceshooter/view/images/menuLeaderboardsButton_Opaque.png");
    private final ImageIcon exitButtonIcon = imageHandler.CreateIcon("/com/spaceshooter/view/images/menuExitButton.png");
    private final ImageIcon exitButtonOpaqueIcon = imageHandler.CreateIcon(
            "/com/spaceshooter/view/images/menuExitButton_Opaque.png");

    private MenuPlayButtonClickListener menuPlayButtonClickListener;
    private MenuLeaderboardsClickListener menuLeaderboardsClickListener;
    private MenuExitButtonClickListener menuExitButtonClickListener;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(menuBackgroundImage, 0, 0, null);
    }

    public MenuPanel(int width, int height) {
        Dimension windowDimension = new Dimension(width, height);

        setPreferredSize(windowDimension);
        setMinimumSize(windowDimension);
        setMaximumSize(windowDimension);

        setLayout(new GridBagLayout());

        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weighty = 0.55;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;

        menuLogo.setSize(new Dimension(500, 100));
        add(menuLogo, gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weighty = 0.15;
        add(playButton, gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.weighty = 0.15;
        add(leaderboardsButton, gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.weighty = 0.15;
        add(exitButton, gridBagConstraints);

        Dimension menuButtonDimension = new Dimension(300, 74);

        playButton.setOpaque(false);
        playButton.setPreferredSize(menuButtonDimension);
        playButton.setIcon(playButtonIcon);

        leaderboardsButton.setOpaque(false);
        leaderboardsButton.setPreferredSize(menuButtonDimension);
        leaderboardsButton.setIcon(leaderboardsButtonIcon);

        exitButton.setOpaque(false);
        exitButton.setPreferredSize(menuButtonDimension);
        exitButton.setIcon(exitButtonIcon);

        playButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                playButton.setIcon(playButtonOpaqueIcon);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                playButton.setIcon(playButtonIcon);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                playButton.setIcon(playButtonIcon);
            }
        });

        leaderboardsButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                leaderboardsButton.setIcon(leaderboardsButtonOpaqueIcon);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                leaderboardsButton.setIcon(leaderboardsButtonIcon);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                leaderboardsButton.setIcon(leaderboardsButtonIcon);
            }
        });

        exitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                exitButton.setIcon(exitButtonOpaqueIcon);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                exitButton.setIcon(exitButtonIcon);
            }
        });

        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (menuPlayButtonClickListener != null) {
                    menuPlayButtonClickListener.mouseButtonClick();
                }
            }
        });

        leaderboardsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (menuLeaderboardsClickListener != null) {
                    menuLeaderboardsClickListener.mouseButtonClick();
                }
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (menuExitButtonClickListener != null) {
                    menuExitButtonClickListener.mouseButtonClick();
                }
            }
        });
    }

    public void setMenuPlayButtonClickListener(
            MenuPlayButtonClickListener menuPlayButtonClickListener) {
        this.menuPlayButtonClickListener = menuPlayButtonClickListener;
    }

    public void setMenuLeaderboardsClickListener(MenuLeaderboardsClickListener menuLeaderboardsClickListener) {
        this.menuLeaderboardsClickListener = menuLeaderboardsClickListener;
    }

    public void setMenuExitClickListener(MenuExitButtonClickListener menuExitButtonClickListener) {
        this.menuExitButtonClickListener = menuExitButtonClickListener;
    }
}