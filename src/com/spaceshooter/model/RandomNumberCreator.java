package com.spaceshooter.model;

import java.util.Random;

public class RandomNumberCreator {
    private static final Random random = new Random();

    public static int getRandomNumberFromZeroTo(int number) {
        return random.nextInt(number);
    }
}
