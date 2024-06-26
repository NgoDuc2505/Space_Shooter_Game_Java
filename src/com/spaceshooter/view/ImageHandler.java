package com.spaceshooter.view;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.net.URL;

public class ImageHandler {
    private static final BufferedImageLoader bufferedImageLoader;

    private static final BufferedImage enemySpaceshipImage;
    private static final BufferedImage playerSpaceshipImage;
    private static final BufferedImage playerLaserBeamImage;
    private static final BufferedImage enemyLaserBeamImage;
    private static final BufferedImage bossImage;
    private static BufferedImage[] smallExplosionAnimation;
    private static final BufferedImage[] bigExplosionAnimation;
    private static final BufferedImage cursorImg;
    private final static int smallExplosionFrames = 80;
    private final static int smallExplosionSpriteSheetColumns = 10;
    private final static int smallExplosionSubImageWidth = 100;
    private final static int smallExplosionSubImageHeight = 100;

    private final static int bigExplosionFrames = 64;
    private final static int bigExplosionSpriteSheetColumns = 8;
    private final static int bigExplosionSubImageWidth = 256;
    private final static int bigExplosionSubImageHeight = 256;

    static {
        bufferedImageLoader = new BufferedImageLoader();

        playerSpaceshipImage = bufferedImageLoader.loadImage("/com/spaceshooter/view/images/playerSpaceship.png");
        enemySpaceshipImage = bufferedImageLoader.loadImage("/com/spaceshooter/view/images/enermyShip2.png");
        playerLaserBeamImage = bufferedImageLoader.loadImage("/com/spaceshooter/view/images/PlayerLaser.png");
        enemyLaserBeamImage = bufferedImageLoader.loadImage("/com/spaceshooter/view/images/enemyEnergyBall.png");
        bossImage = bufferedImageLoader.loadImage("/com/spaceshooter/view/images/boss.png");
        cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);

        BufferedImage explosionSpriteSheet = bufferedImageLoader.loadImage(
                "/com/spaceshooter/view/images/verticalexplosion.png");
        smallExplosionAnimation = initializeExplosionAnimation(explosionSpriteSheet, smallExplosionSpriteSheetColumns,
                smallExplosionSubImageWidth, smallExplosionSubImageHeight, smallExplosionFrames);

        explosionSpriteSheet = bufferedImageLoader.loadImage("/com/spaceshooter/view/images/explosion256.png");
        bigExplosionAnimation = initializeExplosionAnimation(explosionSpriteSheet, bigExplosionSpriteSheetColumns,
                bigExplosionSubImageWidth, bigExplosionSubImageHeight, bigExplosionFrames);
    }

    private static BufferedImage[] initializeExplosionAnimation(BufferedImage explosionSpriteSheet,
                                                                int explosionSpriteSheetColumns, int explosionSubImageWidth, int explosionSubImageHeight,
                                                                int explosionFrames) {
        BufferedImage[] explosionAnimation = new BufferedImage[explosionFrames];
        for (int i = 0; i < explosionAnimation.length; i++) {
            int row = i / explosionSpriteSheetColumns;
            int column = i % explosionSpriteSheetColumns;

            explosionAnimation[i] = explosionSpriteSheet.getSubimage((column * explosionSubImageWidth),
                    (row * explosionSubImageWidth), explosionSubImageWidth, explosionSubImageHeight);
        }

        return explosionAnimation;
    }

    public static BufferedImage getEnemySpaceshipImage() {
        return enemySpaceshipImage;
    }

    public static BufferedImage getPlayerSpaceshipImage() {
        return playerSpaceshipImage;
    }

    public static BufferedImage getEnemyLaserBeamImage() {
        return enemyLaserBeamImage;
    }

    public static BufferedImage[] getSmallExplosionAnimation() {
        return smallExplosionAnimation;
    }

    public static BufferedImage GetMenuImage(String imagePath) {
        return bufferedImageLoader.loadImage(imagePath);
    }

    public ImageIcon CreateIcon(String path) {
        URL url = getClass().getResource(path);

        if (url == null) {
            System.err.println("Unable to load image " + path);
            return null;
        }

        return new ImageIcon(url);
    }

    public static BufferedImage getPlayerLaserBeamImage() {
        return playerLaserBeamImage;
    }

    public static BufferedImage getBossSpaceshipImage() {
        return bossImage;
    }

    public static BufferedImage[] getBigExplosionAnimation() {
        return bigExplosionAnimation;
    }

    public static BufferedImage getCursorImg() {
        return cursorImg;
    }
}
