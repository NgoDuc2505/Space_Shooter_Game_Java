package com.spaceshooter.model;

import com.spaceshooter.view.ImageHandler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class PlayerSpaceship extends SpaceObject {
    public static int count = 0;
    private static PlayerSpaceship instance;
    private final BufferedImage playerSpaceshipImage;

    private PlayerSpaceship(int x, int y, int width, int height) {
        super(x, y, width, height);
        count = 1;
        playerSpaceshipImage = ImageHandler.getPlayerSpaceshipImage();
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.drawImage(playerSpaceshipImage, (int) this.leftBorder(), (int) this.topBorder(), null);
    }

    @Override
    public void onTick() {
        assureObjectWithinBorders();
    }

    public static synchronized PlayerSpaceship createInstance() {
        if (count == 0) {
            int halfWidthOfScreen = Game.WIDTH / 2;
            int bottomOfScreen = Game.HEIGHT - 100;
            int widthOfSpaceship = 75, heightOfSpaceship = 50;
            instance = new PlayerSpaceship(halfWidthOfScreen, bottomOfScreen, widthOfSpaceship, heightOfSpaceship);
        }

        return instance;
    }

    public void reset() {
        this.x = Game.WIDTH / 2f;
        this.y = Game.HEIGHT - 100;
    }

    @Override
    public void notifyObserver() {

    }

}