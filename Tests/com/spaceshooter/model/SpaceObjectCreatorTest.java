package com.spaceshooter.model;

import com.spaceshooter.controller.EnemySpaceshipManager;
import com.spaceshooter.controller.ExplosionManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class SpaceObjectCreatorTest {
    static private SpaceObjectCreator creator;

    @BeforeAll
    static void setUp() {
        creator = new SpaceObjectCreator();
        Player player = Player.createInstance();
        EnemySpaceshipManager enemySpaceshipManager = new EnemySpaceshipManager();

        creator.setPlayer(player);
        creator.setEnemyManager(enemySpaceshipManager);
        creator.setExplosionManager(new ExplosionManager());
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void createSpaceObjectTest() {
        Assertions.assertEquals(EnemySpaceship.class, creator.createSpaceObject("enemyspaceship", 0, 0).getClass());
        Assertions.assertEquals(PlayerSpaceship.class, creator.createSpaceObject("playerspaceship", 0, 0).getClass());
        Assertions.assertEquals(LaserBeam.class, creator.createSpaceObject("enemylaserbeam", 0, 0).getClass());
        Assertions.assertEquals(LaserBeam.class, creator.createSpaceObject("playerlaserbeam", 0, 0).getClass());
        Assertions.assertEquals(Star.class, creator.createSpaceObject("star", 0, 0).getClass());
        Assertions.assertEquals(Explosion.class, creator.createSpaceObject("smallexplosion", 0, 0).getClass());
        Assertions.assertEquals(Explosion.class, creator.createSpaceObject("bigexplosion", 0, 0).getClass());
        Assertions.assertEquals(BossSpaceship.class, creator.createSpaceObject("boss", 0, 0).getClass());
    }

}