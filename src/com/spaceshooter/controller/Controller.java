package com.spaceshooter.controller;

import com.spaceshooter.model.Game;
import com.spaceshooter.model.GameState;
import com.spaceshooter.model.LeaderboardData;
import com.spaceshooter.view.GameWindow;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

public class Controller {
    private final GameWindow gameWindow;
    private Game game;
    private final LeaderboardsManager leaderboardsManager;
    private Thread thread;

    public Controller() {
        this.gameWindow = new GameWindow(Game.WIDTH, Game.HEIGHT);
        this.gameWindow.setController(this);
        leaderboardsManager = new LeaderboardsManager();
    }

    public synchronized void startGame() {
        game = new Game(leaderboardsManager);
        Game.gameState = GameState.GameRunning;

        // game loop in a different thread from UI components.
        thread = new Thread(() -> {
            BufferStrategy bufferStrategy = gameWindow.getGamePanelCanvasBufferStrategy();
            game.setStartTime(System.currentTimeMillis());
            long lastTime;
            double amountOfTicks = 60.0;
            double waitingTime = (1000000000) / amountOfTicks;
            long difference, now;

            while (Game.gameState != GameState.GameMenu) {
                lastTime = System.nanoTime();
                System.out.println("controller buffer: "+bufferStrategy);
                // prepare the ground
                Graphics graphics = bufferStrategy.getDrawGraphics();

                // update game
                game.update(graphics);

                // dispose of graphics and show
                graphics.dispose();
                bufferStrategy.show();

                now = System.nanoTime();
                difference = now - lastTime;
                if (difference < waitingTime) {
                    try {
                        Thread.sleep((long) ((waitingTime - difference) / 1000000));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start();
    }

    public void updatePlayerPosition(int playerX, int playerY) {
        game.updatePlayerPosition(playerX, playerY);
    }

    public void playerMouseClicked() {
        game.playerMouseClicked();
    }

    public void register(String username, String password) throws Exception {
        SecurityManager.register(username, password);
    }

    public void startGameWindow() {
        thread = new Thread(() -> {
            gameWindow.setVisible(true);
        });
        thread.start();
    }

    public boolean login(String username, String password) throws Exception {
        return SecurityManager.login(username, password);
    }

    public ArrayList<LeaderboardData> getLeaderboardData() {
        return leaderboardsManager.getLeaderboardsData();
    }

    public boolean attemptStopGame() {
        if (Game.gameState == GameState.GameOver) {
            Game.gameState = GameState.GameMenu;
            game.reset();
            return true;
        }

        return false;
    }

    public static void main(String[] args) {
        Controller startUp = new Controller();
//        startUp.startGame();
        startUp.startGameWindow();
    }
}