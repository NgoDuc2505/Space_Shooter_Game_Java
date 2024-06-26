package com.spaceshooter.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SpaceObjectTest {
    private SpaceObject player;
    private SpaceObject firstObject;
    private SpaceObject secondObject;

    @BeforeEach
    void setUp() {
        player = PlayerSpaceship.createInstance();
        firstObject = new SimpleSpaceObject(0, 0, 100, 100);
        secondObject = new SimpleSpaceObject(0, 0, 20, 50);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void assureWithingBordersTest() {
        int x = -1, y = 1;

        player.setX(x);
        player.setY(y);

        player.assureObjectWithinBorders();
        Assertions.assertEquals(player.width / 2f, player.getX());

        x = 1281;
        player.setX(x);
        player.assureObjectWithinBorders();
        Assertions.assertEquals(Game.WIDTH - player.width / 2f, player.getX());

        x = 500;
        y = -2;
        player.setX(x);
        player.setY(y);
        player.assureObjectWithinBorders();
        Assertions.assertEquals(player.height / 2f, player.getY());

        y = 800;
        player.setY(y);
        player.assureObjectWithinBorders();
        Assertions.assertEquals(Game.HEIGHT - player.height / 2f, player.getY());
    }

    @Test
    void intersectionTest() {
        // 100 x 100 object
        // x:500, y:100, w:600, h:200
        firstObject.setX(500);
        firstObject.setY(100);

        // intersection top left
        secondObject.setX(445);
        secondObject.setY(40);
        Assertions.assertTrue(secondObject.intersects(firstObject));

        // intersection bottom left
        secondObject.setY(130);
        Assertions.assertTrue(secondObject.intersects(firstObject));

        // intersection top right
        secondObject.setX(540);
        secondObject.setY(40);
        Assertions.assertTrue(secondObject.intersects(firstObject));

        // intersection bottom right
        secondObject.setY(130);
        Assertions.assertTrue(secondObject.intersects(firstObject));
    }
}