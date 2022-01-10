package com.aniketic.ants.antdesign;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Ant {

    private static final int BODY_SIZE = 8;
    private static final int HEAD_SIZE = 10;
    private static final int BACK_SIZE = 10;
    private static final int LEG_SIZE = 10;
    private static final int FEELER_SIZE = 10;

    private final Random random;

    private double x;
    private double y;
    private double speed;
    private double direction;


    public Ant(double x, double y, double speed, double direction) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.direction = direction;
        this.random = new Random();
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

    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        animateBody(g);
        animateHead(g);
        animateBack(g);
    }

    private void animateBody(Graphics g) {
        g.fillOval((int) x - BODY_SIZE / 2, (int) y - BODY_SIZE / 2, BODY_SIZE, BODY_SIZE);

        animateLeg(g, 80);
        animateLeg(g, 90);
        animateLeg(g, 100);
        animateLeg(g, 260);
        animateLeg(g, 270);
        animateLeg(g, 280);
    }

    private void animateLeg(Graphics g, double directionOffset) {
        double legRadians;
        double legOffsetX;
        double legOffsetY;
        int legX;
        int legY;
        legRadians = Math.toRadians(direction + directionOffset);
        legOffsetX = Math.cos(legRadians) * LEG_SIZE;
        legOffsetY = Math.sin(legRadians) * LEG_SIZE;
        legX = (int) (x + legOffsetX);
        legY = (int) (y + legOffsetY);
        g.drawLine((int) x, (int) y, legX, legY);
    }

    private void animateHead(Graphics g) {
        int radius = BODY_SIZE / 2;
        double radians = Math.toRadians(direction);
        double offsetX = Math.cos(radians) * radius;
        double offsetY = Math.sin(radians) * radius;
        int headX = (int) (x + offsetX);
        int headY = (int) (y + offsetY);

        g.fillOval(headX - HEAD_SIZE / 2, headY - HEAD_SIZE / 2, HEAD_SIZE, HEAD_SIZE);

        double feelerRadians;
        double feelerOffsetX;
        double feelerOffsetY;
        int feelerX;
        int feelerY;

        feelerRadians = Math.toRadians(direction + 20);
        feelerOffsetX = Math.cos(feelerRadians) * FEELER_SIZE;
        feelerOffsetY = Math.sin(feelerRadians) * FEELER_SIZE;
        feelerX = (int) (headX + feelerOffsetX);
        feelerY = (int) (headY + feelerOffsetY);
        g.drawLine((int) x, (int) y, feelerX, feelerY);

        feelerRadians = Math.toRadians(direction - 20);
        feelerOffsetX = Math.cos(feelerRadians) * FEELER_SIZE;
        feelerOffsetY = Math.sin(feelerRadians) * FEELER_SIZE;
        feelerX = (int) (headX + feelerOffsetX);
        feelerY = (int) (headY + feelerOffsetY);
        g.drawLine((int) x, (int) y, feelerX, feelerY);
    }

    private void animateBack(Graphics g) {
        int radius = BODY_SIZE / 2;
        double radians = Math.toRadians(direction);
        double offsetX = Math.cos(radians) * radius;
        double offsetY = Math.sin(radians) * radius;
        int backX = (int) (x + (offsetX * -1));
        int backY = (int) (y + (offsetY * -1));
        g.fillOval(backX - BACK_SIZE / 2, backY - BACK_SIZE / 2, BACK_SIZE, BACK_SIZE);
    }

    public void tick() {
        double radians = Math.toRadians(direction);
        double newX = x + Math.cos(radians) * speed;
        double newY = y + Math.sin(radians) * speed;

        x = newX;
        y = newY;

        randomMovementPattern();
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
