package com.spaceshooter.view;

import com.spaceshooter.model.LeaderboardData;
import com.spaceshooter.view.listenerInterfaces.BackToMenuButtonClickListener;
import com.spaceshooter.view.listenerInterfaces.LeaderboardDataListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class LeaderboardsPanel extends JPanel {

    private final JButton backToMenuButton = new JButton();
    private final ImageHandler imageHandler = new ImageHandler();
    private final BufferedImage menuBackgroundImage = ImageHandler.GetMenuImage(
            "/com/spaceshooter/view/images/leaderboardsBackground.png");
    private final JLabel leaderboardsTitle = new JLabel(
            imageHandler.CreateIcon("/com/spaceshooter/view/images/leaderboardsTitle.png"));
    private final JLabel usernameTitle = new JLabel(
            imageHandler.CreateIcon("/com/spaceshooter/view/images/usernameTitle.png"));
    private final JLabel scoreTitle = new JLabel(
            imageHandler.CreateIcon("/com/spaceshooter/view/images/scoreTitle.png"));
    private final ImageIcon backToMenuButtonIcon = imageHandler.CreateIcon(
            "/com/spaceshooter/view/images/backToMenuButton.png");
    private final ImageIcon backToMenuButtonOpaqueIcon = imageHandler.CreateIcon(
            "/com/spaceshooter/view/images/backToMenuButton_Opaque.png");

    private JLabel[] scoreTable;
    private JLabel[] usernameTable;

    private final JPanel headerPanel = new JPanel();
    private final JPanel tablePanel = new JPanel();
    private final JPanel tableTitlePanel = new JPanel();
    private final JPanel footerPanel = new JPanel();

    private Font tableFont;
    private Color tableColor;
    private GridBagConstraints gridBagConstraints;

    private BackToMenuButtonClickListener backToMenuButtonClickListener;
    private LeaderboardDataListener leaderboardDataListener;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(menuBackgroundImage, 0, 0, null);
    }

    public LeaderboardsPanel(int width, int height) {
        createMainPanel(width, height);

        backToMenuButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                backToMenuButton.setIcon(backToMenuButtonOpaqueIcon);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                backToMenuButton.setIcon(backToMenuButtonIcon);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                backToMenuButton.setIcon(backToMenuButtonIcon);
            }
        });

        backToMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (backToMenuButtonClickListener != null) {
                    backToMenuButtonClickListener.mouseButtonClick();
                    tablePanel.removeAll();
                }
            }
        });
    }

    private void createMainPanel(int width, int height) {
        Dimension windowDimension = new Dimension(width, height);

        setPreferredSize(windowDimension);

        setLayout(new BorderLayout());

        createHeaderPanel();
        createTableTitlePanel();
        createTablePanel();
        createFooterPanel();

        gridBagConstraints = new GridBagConstraints();

        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;

        headerPanel.add(leaderboardsTitle, gridBagConstraints);
        gridBagConstraints.gridy = 1;
        headerPanel.add(tableTitlePanel, gridBagConstraints);
        add(headerPanel, BorderLayout.PAGE_START);

        createScrollPane();

        tableFont = new Font("Helvetica", Font.BOLD, 28);
        tableColor = new Color(0, 150, 245);

        Dimension menuButtonDimension = new Dimension(300, 74);
        backToMenuButton.setPreferredSize(menuButtonDimension);
        backToMenuButton.setIcon(backToMenuButtonIcon);

        footerPanel.add(backToMenuButton);
        add(footerPanel, BorderLayout.PAGE_END);

        setBorder(BorderFactory.createEtchedBorder());
    }

    private void createHeaderPanel() {
        headerPanel.setOpaque(false);
        headerPanel.setLayout(new GridBagLayout());
    }

    private void createTableTitlePanel() {
        tableTitlePanel.setOpaque(false);
        tableTitlePanel.setLayout(new GridBagLayout());
    }

    private void createTablePanel() {
        tablePanel.setOpaque(false);
        tablePanel.setLayout(new GridBagLayout());
    }

    private void createScrollPane() {
        JScrollPane scrollPane = new JScrollPane(tablePanel);
        scrollPane.setBackground(new Color(0, 0, 0, 0));
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        add(scrollPane, BorderLayout.CENTER);
    }

    private void createFooterPanel() {
        footerPanel.setOpaque(false);
        footerPanel.setLayout(new GridBagLayout());
    }

    private ArrayList<LeaderboardData> getLeaderboardFromMainWindow() {
        if (leaderboardDataListener != null) {
            return leaderboardDataListener.getLeaderboardData();
        }

        return null;
    }

    public void setLeaderboardsBackClickListener(BackToMenuButtonClickListener backToMenuButtonClickListener) {
        this.backToMenuButtonClickListener = backToMenuButtonClickListener;
    }

    public void setLeaderboardDataListener(LeaderboardDataListener leaderboardDataListener) {
        this.leaderboardDataListener = leaderboardDataListener;
    }

    public void drawLeaderboardsTable() {
        ArrayList<LeaderboardData> leaderboardScoreTable = getLeaderboardFromMainWindow();

        int numOfLeaderboardsUsers;

        if (leaderboardScoreTable != null) {
            numOfLeaderboardsUsers = leaderboardScoreTable.size() + 1;
        } else {
            numOfLeaderboardsUsers = 1;
        }

        scoreTable = new JLabel[numOfLeaderboardsUsers];
        usernameTable = new JLabel[numOfLeaderboardsUsers];

        scoreTable[0] = usernameTitle;
        scoreTable[0].setPreferredSize(new Dimension(200, 50));
        gridBagConstraints.gridx = 0;
        tableTitlePanel.add(scoreTable[0], gridBagConstraints);
        usernameTable[0] = scoreTitle;
        usernameTable[0].setPreferredSize(new Dimension(200, 50));
        gridBagConstraints.gridx = 1;
        tableTitlePanel.add(usernameTable[0], gridBagConstraints);

        for (int i = 1; i < numOfLeaderboardsUsers; i++) {
            LeaderboardData currentRow = leaderboardScoreTable.get(i - 1);

            scoreTable[i] = new JLabel(String.valueOf(currentRow.getScore()));
            scoreTable[i].setFont(tableFont);
            scoreTable[i].setForeground(tableColor);
            scoreTable[i].setPreferredSize(new Dimension(200, 50));
            scoreTable[i].setHorizontalAlignment(SwingConstants.CENTER);
            gridBagConstraints.gridx = 1;
            gridBagConstraints.gridy = i + 1;
            tablePanel.add(scoreTable[i], gridBagConstraints);

            usernameTable[i] = new JLabel(String.valueOf(currentRow.getUsername()));
            usernameTable[i].setFont(tableFont);
            usernameTable[i].setForeground(tableColor);
            usernameTable[i].setPreferredSize(new Dimension(200, 50));
            usernameTable[i].setHorizontalAlignment(SwingConstants.CENTER);
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = i + 1;
            tablePanel.add(usernameTable[i], gridBagConstraints);
        }
    }
}