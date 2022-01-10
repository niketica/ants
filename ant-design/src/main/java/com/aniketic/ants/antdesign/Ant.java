package com.aniketic.ants.antdesign;

import java.awt.Image;
import java.util.Random;

public class Ant {

    private final Random random;
    private final Image[] images;
    private final int animationCount;
    private int currentAnimationCount;
    private int currentImage;

    private double x;
    private double y;
    private double speed;
    private double direction;

    public Ant(double x, double y, double speed, double direction, Image[] images) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.direction = direction;
        this.random = new Random();
        this.images = images;
        this.currentImage = 0;
        this.currentAnimationCount = 0;
        this.animationCount = 10;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getSpeed() {
        return speed;
    }

    public double getDirection() {
        return direction;
    }

    public Image getImage() {
        return images[currentImage];
    }

    public void tick() {
        double radians = Math.toRadians(direction);
        double newX = x + Math.cos(radians) * speed;
        double newY = y + Math.sin(radians) * speed;

        x = newX;
        y = newY;

        currentAnimationCount++;
        if (currentAnimationCount >= animationCount) {
            currentAnimationCount = 0;
            currentImage++;
            if (currentImage > images.length - 1) {
                currentImage = 0;
            }
        }

//        randomMovementPattern();
    }

    private void randomMovementPattern() {
        int rnd = random.nextInt(4);
        if(rnd == 0) {
            direction += 20;
        } else if (rnd == 1) {
            direction -= 20;
        }
    }
}
