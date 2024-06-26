package com.spaceshooter.model;

import com.spaceshooter.controller.LaserBeamManager;
import com.spaceshooter.model.interfaces.PlayerDeathListener;

import java.awt.*;
import java.util.List;

public class Player {
    private int playerScore;
    private int playerHealth;
    private LaserBeamManager playerLaserBeamManager;
    private final PlayerSpaceship playerSpaceship;
    private static final Player instance = new Player();
    private PlayerDeathListener playerDeathListener;

    private Player() {
        playerSpaceship = PlayerSpaceship.createInstance();
        playerScore = 0;
        playerHealth = 100;
        playerLaserBeamManager = new LaserBeamManager();
    }

    public static Player createInstance() {
        return instance;
    }

    public void onTick() {
        checkIfDead();
        playerSpaceship.onTick();
        playerLaserBeamManager.onTick();
    }

    private void checkIfDead() {
        if (this.playerHealth <= 0) {
            Game.creator.createSpaceObject("smallexplosion", this.playerSpaceship.getX(), this.playerSpaceship.getY());

            if (playerDeathListener != null) {
                playerDeathListener.onPlayerDeath();
            }
        }
    }

    public void draw(Graphics graphics) {
        playerSpaceship.draw(graphics);
        playerLaserBeamManager.draw(graphics);
    }

    public int getPlayerScore() {
        return playerScore;
    }

    public void updateSpaceshipPosition(int playerSpaceshipX, int playerSpaceshipY) {
        playerSpaceship.setX(playerSpaceshipX);
        playerSpaceship.setY(playerSpaceshipY);
        playerSpaceship.onTick();
    }

    public void playerMouseClicked() {
        float playerSpaceshipWidth = playerSpaceship.width;
        float laserbeamY = playerSpaceship.topBorder();

        Game.creator.createSpaceObject("playerlaserbeam", playerSpaceship.leftBorder() + playerSpaceshipWidth * 0.09f,
                laserbeamY);
        Game.creator.createSpaceObject("playerlaserbeam", playerSpaceship.leftBorder() + playerSpaceshipWidth * 0.88f,
                laserbeamY);
    }

    public LaserBeamManager getLaserBeamManager() {
        return this.playerLaserBeamManager;
    }

    public float spaceshipX() {
        return this.playerSpaceship.getX();
    }

    public float spaceshipY() {
        return this.playerSpaceship.getY();
    }

    public PlayerSpaceship getPlayerSpaceship() {
        return this.playerSpaceship;
    }

    public int getHealth() {
        return this.playerHealth;
    }

    public void hit(int hitPoints) {
        this.playerHealth -= hitPoints;
    }

    public void addScore(int scoreToAdd) {
        this.playerScore += scoreToAdd;
    }

    public void reset() {
        playerSpaceship.reset();
        playerScore = 0;
        playerHealth = 100;
        playerLaserBeamManager = new LaserBeamManager();
    }

    public void setPlayerDeathListener(PlayerDeathListener playerDeathListener) {
        this.playerDeathListener = playerDeathListener;
    }
}
