package com.spaceshooter.view;

import com.spaceshooter.view.listenerInterfaces.GamePanelKeyInputListener;
import com.spaceshooter.view.listenerInterfaces.GamePanelMouseClickListener;
import com.spaceshooter.view.listenerInterfaces.GamePanelMouseMovementListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;

public class GamePanel extends JPanel {

    private final Canvas gameCanvas;
    private GamePanelMouseMovementListener gamePanelMouseMovementListener;
    private GamePanelMouseClickListener gamePanelMouseClickListener;
    private GamePanelKeyInputListener gamePanelKeyInputListener;

    public GamePanel(int width, int height) {
        gameCanvas = new Canvas();
        Dimension dimension = new Dimension(width, height);

        gameCanvas.setPreferredSize(dimension);
        gameCanvas.setMinimumSize(dimension);
        gameCanvas.setMaximumSize(dimension);

        setRequestFocusEnabled(true);
        setLayout(new BorderLayout());
        add(gameCanvas, BorderLayout.CENTER);
        hideCursor();
        addPanelListeners();
    }

    private void hideCursor() {
        Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(
                ImageHandler.getCursorImg(), new Point(0, 0), "blank cursor");

        this.setCursor(blankCursor);

    }

    private void addPanelListeners() {
        gameCanvas.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                if (gamePanelMouseMovementListener != null) {
                    gamePanelMouseMovementListener.mouseMovedInPanel(e.getX(), e.getY());
                }
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                if (gamePanelMouseMovementListener != null) {
                    gamePanelMouseMovementListener.mouseMovedInPanel(e.getX(), e.getY());
                }
            }
        });

        gameCanvas.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (gamePanelMouseClickListener != null) {
                    gamePanelMouseClickListener.mouseClickedOnPanel();
                }
            }
        });

        gameCanvas.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == MouseEvent.BUTTON1) {
                    if (gamePanelKeyInputListener != null) {
                        gamePanelKeyInputListener.enterKeyPressed();
                    }
                }
            }
        });
    }

    public void setGamePanelMouseMovementListener(GamePanelMouseMovementListener gamePanelMouseMovementListener) {
        this.gamePanelMouseMovementListener = gamePanelMouseMovementListener;
    }

    public void setGamePanelMouseClickListener(GamePanelMouseClickListener gamePanelMouseClickListener) {
        this.gamePanelMouseClickListener = gamePanelMouseClickListener;
    }

    public BufferStrategy getCanvasBufferStrategy() {
        BufferStrategy bs = gameCanvas.getBufferStrategy();
        if(bs == null){
            CreateGamePanelBufferStrategy(3);
        }
        return gameCanvas.getBufferStrategy();
    }

    public void CreateGamePanelBufferStrategy(int buffers) {
        gameCanvas.createBufferStrategy(buffers);
    }

    public void setGamePanelKeyInputListener(GamePanelKeyInputListener gamePanelKeyInputListener) {
        this.gamePanelKeyInputListener = gamePanelKeyInputListener;
    }
}
