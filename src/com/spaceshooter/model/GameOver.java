package com.spaceshooter.model;

import java.awt.*;

public class GameOver {
    private final String gameOverText;
    private final String returnToMainMenuText;
    private final Font gameOverFont;
    private final Font returnToMainMenuFont;

    public GameOver() {
        gameOverText = "Game over!";
        returnToMainMenuText = "Press enter to get back to the menu...";
        gameOverFont = new Font("Arial", Font.BOLD, 100);
        returnToMainMenuFont = new Font("Arial", Font.BOLD, 20);
    }

    public void draw(Graphics graphics) {
        drawGameOverText(graphics);
        drawBackToMenuText(graphics);
    }

    private void drawBackToMenuText(Graphics graphics) {
        graphics.setFont(returnToMainMenuFont);
        graphics.setColor(Color.GREEN);

        FontMetrics fontMetrics = graphics.getFontMetrics();
        int textX = (Game.WIDTH - fontMetrics.stringWidth(returnToMainMenuText)) / 2;
        int textY = Game.HEIGHT - Game.HEIGHT / 4;

        graphics.drawString(returnToMainMenuText, textX, textY);
    }

    private void drawGameOverText(Graphics graphics) {
        graphics.setFont(gameOverFont);

        FontMetrics fontMetrics = graphics.getFontMetrics();
        int textX = (Game.WIDTH - fontMetrics.stringWidth(gameOverText)) / 2;
        int textY = ((Game.HEIGHT - fontMetrics.getHeight()) / 2) + fontMetrics.getAscent();

        graphics.drawString("Game over", textX, textY);
    }
}
