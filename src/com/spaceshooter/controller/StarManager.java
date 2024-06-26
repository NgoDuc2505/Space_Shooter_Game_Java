package com.spaceshooter.controller;

import com.spaceshooter.model.Game;
import com.spaceshooter.model.Star;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StarManager {
    private final int NUMBER_OF_STARS = 50;

    private final List<Star> stars;

    public StarManager() {
        this.stars = new ArrayList<>(NUMBER_OF_STARS);

        createStars();
    }

    public void createStars() {
        Random random = new Random();

        for (int i = 0; i < NUMBER_OF_STARS; i++) {
            int starX = random.nextInt(Game.WIDTH);
            int starY = random.nextInt(Game.HEIGHT);

            Star starToAdd = (Star) Game.creator.createSpaceObject("star", starX, starY);
            this.stars.add(starToAdd);
        }
    }

    public void onTick() {
        for (Star star : this.stars) {
            star.onTick();
        }
    }

    public void draw(Graphics graphics) {
        for (Star star : this.stars) {
            star.draw(graphics);
        }
    }
}
