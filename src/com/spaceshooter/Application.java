package com.spaceshooter;

import com.spaceshooter.controller.Controller;

public class Application {
    public static void main(String[] args) {
        Controller startGame = new Controller();
        startGame.startGameWindow();
    }
}
