package com.spaceshooter.model;

import com.spaceshooter.controller.EnemySpaceshipManager;
import com.spaceshooter.controller.ExplosionManager;
import com.spaceshooter.model.interfaces.SpaceObjectFactory;
import com.spaceshooter.view.ImageHandler;

import java.awt.image.BufferedImage;

public class SpaceObjectCreator implements SpaceObjectFactory {

    private final int playerLaserBeamWidth = 3;
    private final int playerLaserBeamHeight = 32;
    private final int enemyEnergyBallWidth = 8;
    private final int enemyEnergyBallHeight = 8;
    private final int starWidth = 2;
    private final int starHeight = 2;
    private final int enemySpaceshipWidth = 60;
    private final int enemySpaceshipHeight = 60;
    private final int bossSpaceshipWidth = 403;
//    private final int bossSpaceshipWidth = 203;
    private final int bossSpaceshipHeight = 286;
    private final int smallExplosionWidth = 100;
    private final int smallExplosionHeight = 100;
    private final int bigExplosionWidth = 256;
    private final int bigExplosionHeight = 256;
    private EnemySpaceshipManager enemySpaceshipManager;
    private ExplosionManager explosionManager;
    private Player player;

    @Override
    public SpaceObject createSpaceObject(String nameOfObject, float objectX, float objectY) {
        SpaceObject spaceObjectToCreate;

        if (nameOfObject == "playerspaceship") {
            spaceObjectToCreate = createPlayerSpaceship();
        } else if (nameOfObject == "enemyspaceship") {
            spaceObjectToCreate = createEnemySpaceship(objectX, objectY);
        } else if (nameOfObject == "enemylaserbeam") {
            spaceObjectToCreate = createEnemyLaserBeam(objectX, objectY);
        } else if (nameOfObject == "playerlaserbeam") {
            spaceObjectToCreate = createPlayerLaserBeam(objectX, objectY);
        } else if (nameOfObject == "star") {
            spaceObjectToCreate = createStar(objectX, objectY);
        } else if (nameOfObject == "smallexplosion") {
            spaceObjectToCreate = createSmallExplosion(objectX, objectY);
        } else if (nameOfObject == "bigexplosion") {
            spaceObjectToCreate = createBigExplosion(objectX, objectY);
        } else if (nameOfObject == "boss") {
            spaceObjectToCreate = createBoss(objectX, objectY);
        } else {
            throw new IllegalArgumentException("Object name does not exist");
        }

        return spaceObjectToCreate;
    }

    private BossSpaceship createBoss(float bossX, float bossY) {
        BossSpaceship bossSpaceship = new BossSpaceship(bossX, bossY, bossSpaceshipWidth, bossSpaceshipHeight,
                enemySpaceshipManager);
        enemySpaceshipManager.addEnemySpaceship(bossSpaceship);
        return bossSpaceship;
    }

    private Star createStar(float starX, float starY) {
        return new Star(starX, starY, starWidth, starHeight);
    }

    private PlayerSpaceship createPlayerSpaceship() {
        return PlayerSpaceship.createInstance();
    }

    private LaserBeam createPlayerLaserBeam(float laserX, float laserY) {
        BufferedImage playerLaserBeamImage = ImageHandler.getPlayerLaserBeamImage();
        LaserBeam playerLaserBeam = new LaserBeam(laserX, laserY, playerLaserBeamWidth, playerLaserBeamHeight,
                player.getLaserBeamManager());
        playerLaserBeam.setImage(playerLaserBeamImage);
        player.getLaserBeamManager().addLaserBeam(playerLaserBeam);

        return playerLaserBeam;
    }

    private LaserBeam createEnemyLaserBeam(float laserX, float laserY) {
        BufferedImage enemyLaserBeamImage = ImageHandler.getEnemyLaserBeamImage();
        LaserBeam enemyLaserBeam = new LaserBeam(laserX, laserY, enemyEnergyBallWidth, enemyEnergyBallHeight,
                enemySpaceshipManager.getEnemyLaserBeamManager());
        enemyLaserBeam.setImage(enemyLaserBeamImage);
        enemyLaserBeam.calculateDirection(player.spaceshipX(), player.spaceshipY());
        enemySpaceshipManager.getEnemyLaserBeamManager().addLaserBeam(enemyLaserBeam);

        return enemyLaserBeam;
    }

    private EnemySpaceship createEnemySpaceship(float enemyX, float enemyY) {
        EnemySpaceship enemySpaceship = new EnemySpaceship(enemyX, enemyY, enemySpaceshipWidth, enemySpaceshipHeight,
                enemySpaceshipManager);
        enemySpaceshipManager.addEnemySpaceship(enemySpaceship);
        return enemySpaceship;
    }

    private Explosion createSmallExplosion(float explosionX, float explosionY) {
        Explosion explosion = new Explosion(explosionX, explosionY, smallExplosionWidth, smallExplosionHeight,
                explosionManager);
        explosionManager.addExplosion(explosion);
        explosion.setAnimation(ImageHandler.getSmallExplosionAnimation());
        return explosion;
    }


    private Explosion createBigExplosion(float explosionX, float explosionY) {
        Explosion explosion = new Explosion(explosionX, explosionY, bigExplosionWidth, bigExplosionHeight,
                explosionManager);
        explosionManager.addExplosion(explosion);
        explosion.setAnimation(ImageHandler.getBigExplosionAnimation());

        return explosion;
    }

    public void setEnemyManager(EnemySpaceshipManager enemySpaceshipManager) {
        this.enemySpaceshipManager = enemySpaceshipManager;
    }

    public void setExplosionManager(ExplosionManager explosionManager) {
        this.explosionManager = explosionManager;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getPlayerLaserBeamWidth() {
        return playerLaserBeamWidth;
    }

    public int getPlayerLaserBeamHeight() {
        return playerLaserBeamHeight;
    }

    public int getEnemyEnergyBallWidth() {
        return enemyEnergyBallWidth;
    }

    public int getEnemyEnergyBallHeight() {
        return enemyEnergyBallHeight;
    }

    public int getStarWidth() {
        return starWidth;
    }

    public int getStarHeight() {
        return starHeight;
    }

    public int getEnemySpaceshipWidth() {
        return enemySpaceshipWidth;
    }

    public int getEnemySpaceshipHeight() {
        return enemySpaceshipHeight;
    }

    public int getBossSpaceshipHeight() {
        return bossSpaceshipHeight;
    }

    public int getSmallExplosionWidth() {
        return smallExplosionWidth;
    }

    public int getSmallExplosionHeight() {
        return smallExplosionHeight;
    }

    public int getBigExplosionWidth() {
        return bigExplosionWidth;
    }

    public int getBigExplosionHeight() {
        return bigExplosionHeight;
    }
}
