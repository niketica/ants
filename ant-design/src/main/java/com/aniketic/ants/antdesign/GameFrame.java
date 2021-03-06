package com.aniketic.ants.antdesign;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

public class GameFrame extends JPanel implements Runnable {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int GRASS_WIDTH = 40;
    private static final int GRASS_HEIGHT = 40;
    private static final int DELAY = 25;

    private final ImageLoader imageLoader;

    private List<Tile> grassTiles;
    private Ant ant;

    public GameFrame() {
        imageLoader = new ImageLoader();
        init();
    }

    private void init() {
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));

        grassTiles = new ArrayList<>();
        for (int x=0; x<WIDTH; x+=GRASS_WIDTH) {
            for (int y=0; y<HEIGHT; y+=GRASS_HEIGHT) {
                Tile grassTile = new Tile(imageLoader.getImage(ImageType.GRASS), x, y, GRASS_WIDTH, GRASS_HEIGHT);
                grassTiles.add(grassTile);
            }
        }

        List<Image> antImages = imageLoader.getImages(ImageType.ANT);
        ant = new Ant(WIDTH / 2, HEIGHT / 2, 0, 0, antImages.toArray(new Image[antImages.size()]));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        grassTiles.forEach(gt -> g.drawImage(gt.getImage(), gt.getX(), gt.getY(), this));

        drawAnt((Graphics2D) g);

        Toolkit.getDefaultToolkit().sync();
    }

    private void drawAnt(Graphics2D g) {
        Image antImage = ant.getImage();
        int x = (int) (ant.getX() + (antImage.getWidth(this) / 2));
        int y = (int) (ant.getY() + (antImage.getHeight(this) / 2));
        g.rotate(Math.toRadians(ant.getDirection()), x, y);
        g.drawImage(antImage, (int)ant.getX(), (int)ant.getY(), this);
    }

    @Override
    public void addNotify() {
        super.addNotify();
        Thread animator = new Thread(this);
        animator.start();
    }

    @Override
    public void run() {
        long beforeTime, timeDiff, sleep;
        beforeTime = System.currentTimeMillis();

        while (true) {
            tick();
            repaint();

            timeDiff = System.currentTimeMillis() - beforeTime;
            sleep = DELAY - timeDiff;

            if (sleep < 0) {
                sleep = 2;
            }

            try {
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
                String msg = String.format("Thread interrupted: %s", e.getMessage());
                JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
            }

            beforeTime = System.currentTimeMillis();
        }
    }

    private void tick() {
        ant.tick();
    }
}
