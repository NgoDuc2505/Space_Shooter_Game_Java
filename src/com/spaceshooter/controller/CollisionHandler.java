package com.spaceshooter.controller;

import com.spaceshooter.model.EnemySpaceship;
import com.spaceshooter.model.LaserBeam;
import com.spaceshooter.model.Player;
import com.spaceshooter.model.PlayerSpaceship;

public class CollisionHandler {
    private final EnemySpaceshipManager enemyManager;
    private final Player player;

    public CollisionHandler(EnemySpaceshipManager enemyManager, Player player) {
        this.enemyManager = enemyManager;
        this.player = player;
    }

    public void onTick() {
        LaserBeamManager playerLaserBeamManager = player.getLaserBeamManager();
        LaserBeamManager enemyLaserBeamsManager = enemyManager.getEnemyLaserBeamManager();

        LaserBeam[] playerLaserBeams = playerLaserBeamManager.getLasers().toArray(
                new LaserBeam[playerLaserBeamManager.getLasers().size()]);
        LaserBeam[] enemyLaserBeams = enemyLaserBeamsManager.getLasers().toArray(
                new LaserBeam[enemyLaserBeamsManager.getLasers().size()]);

        // player laserbeams intersection with enemy spaceships
        for (LaserBeam laser : playerLaserBeams) {

            for (EnemySpaceship enemy : enemyManager.getEnemySpaceships()) {
                if (laser.intersects(enemy)) {
                    enemy.hit(20);
                    laser.notifyObserver();
                }
            }
        }

        PlayerSpaceship playerSpaceship = player.getPlayerSpaceship();

        // enemy laserbeams intersection with player spaceship
        for (LaserBeam laser : enemyLaserBeams) {
            if (laser.intersects(playerSpaceship)) {
                player.hit(2);
                laser.notifyObserver();
            }
        }

        // enemy spaceships intersection with player spaceship
        for (EnemySpaceship enemySpaceship : enemyManager.getEnemySpaceships()) {
            if (enemySpaceship.intersects(playerSpaceship)) {
                player.hit(25);
                enemySpaceship.hit(100);
            }
        }
    }
}
