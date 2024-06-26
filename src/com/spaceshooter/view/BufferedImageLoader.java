package com.spaceshooter.view;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class BufferedImageLoader {
    private BufferedImage image;

    public BufferedImage loadImage(String path) {
        URL url = getClass().getResource(path);

        if (url == null) {
            System.out.println("Could not load image " + path);

            return null;
        }

        try {
            image = ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return image;
    }
}
