package com.spaceshooter.controller;

import com.spaceshooter.model.BossSpaceship;
import com.spaceshooter.model.EnemySpaceship;
import com.spaceshooter.model.Game;
import com.spaceshooter.model.interfaces.ObjectObserver;
import com.spaceshooter.model.interfaces.ObservableObject;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class EnemySpaceshipManager implements ObservableObject, ObjectObserver {
    private final List<EnemySpaceship> enemySpaceships;
    private final LaserBeamManager enemyLaserBeamManager;
    private ObjectObserver objectObserver;

    public EnemySpaceshipManager() {
        this.enemySpaceships = new ArrayList<EnemySpaceship>();
        this.enemyLaserBeamManager = new LaserBeamManager();
    }

    public void onTick() {
        EnemySpaceship[] enemySpaceships = this.enemySpaceships.toArray(
                new EnemySpaceship[this.enemySpaceships.size()]);

        for (EnemySpaceship enemy : enemySpaceships) {
            enemy.onTick();
        }

        this.enemyLaserBeamManager.onTick();
    }

    public void draw(Graphics graphics) {
        EnemySpaceship[] enemySpaceships = this.enemySpaceships.toArray(
                new EnemySpaceship[this.enemySpaceships.size()]);

        for (EnemySpaceship enemy : enemySpaceships) {
            enemy.draw(graphics);
        }

        this.enemyLaserBeamManager.draw(graphics);
    }

    public void addEnemySpaceship(EnemySpaceship spaceship) {
        this.enemySpaceships.add(spaceship);
    }

    public void removeEnemySpaceship(EnemySpaceship spaceship) {
        this.enemySpaceships.remove(spaceship);
    }

    public LaserBeamManager getEnemyLaserBeamManager() {
        return this.enemyLaserBeamManager;
    }

    @Override
    public void objectStateChanged(ObservableObject observable) {
        if (!(observable instanceof BossSpaceship)) {
            EnemySpaceship enemySpaceship = (EnemySpaceship) observable;
            removeEnemySpaceship(enemySpaceship);
        }

        notifyObserver();
    }

    public void createEnemies(int numberOfEnemies) {
        int enemyWidth = Game.creator.getEnemySpaceshipWidth();
        int enemyDistance = (Game.WIDTH - enemyWidth) / (numberOfEnemies * 2);

        if (numberOfEnemies % 2 == 1) {
            Game.creator.createSpaceObject("enemyspaceship", Game.WIDTH / 2, Game.HEIGHT / 4);
            numberOfEnemies -= 1;
        }

        for (int i = 1; i <= numberOfEnemies / 2; i++) {
            Game.creator.createSpaceObject("enemyspaceship", Game.WIDTH / 2 + i * (enemyWidth + enemyDistance),
                    Game.HEIGHT / 4);
            Game.creator.createSpaceObject("enemyspaceship", Game.WIDTH / 2 - i * (enemyWidth + enemyDistance),
                    Game.HEIGHT / 4);
        }
    }

    public List<EnemySpaceship> getEnemySpaceships() {
        return this.enemySpaceships;
    }

    public boolean isFleetAnnihilated() {
        return this.enemySpaceships.isEmpty();
    }

    public void clear() {
        this.enemySpaceships.clear();
        this.enemyLaserBeamManager.clear();
    }

    @Override
    public void notifyObserver() {
        objectObserver.objectStateChanged(this);
    }

    public void setObservableObject(ObjectObserver objectObserver) {

        this.objectObserver = objectObserver;
    }
}