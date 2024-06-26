package com.spaceshooter.model;

import java.awt.*;
import java.util.Random;

public class Star extends SpaceObject {

    public Star(float x, float y, int width, int height) {
        super(x, y, width, height);

        this.moveY = 1;

    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(Color.WHITE);
        graphics.fillOval((int) this.leftBorder(), (int) this.topBorder(), this.width, this.height);
    }

    @Override
    public void onTick() {
        this.y += this.moveY;

        if (this.y > Game.HEIGHT) {
            restartPosition();
        }
    }

    private void restartPosition() {
        Random random = new Random();
        this.x = random.nextInt(Game.WIDTH);
        this.y = 0;
    }

    @Override
    public void notifyObserver() {

    }
}