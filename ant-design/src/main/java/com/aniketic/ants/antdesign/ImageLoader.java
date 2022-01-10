package com.aniketic.ants.antdesign;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

public class ImageLoader {

    private static final String GRASS_TILE_01 = "images/tiles/tile_grass_01.png";
    private static final String GRASS_TILE_02 = "images/tiles/tile_grass_02.png";
    private static final String GRASS_TILE_03 = "images/tiles/tile_grass_03.png";
    private static final String GRASS_TILE_04 = "images/tiles/tile_grass_04.png";

    private static final String ANT_01 = "images/ant/ant01.png";
    private static final String ANT_02 = "images/ant/ant02.png";

    private final Map<ImageType, List<Image>> imageMap;

    public ImageLoader() {
        imageMap = new HashMap<>();
        imageMap.put(ImageType.GRASS, List.of(
                loadImage(GRASS_TILE_01),
                loadImage(GRASS_TILE_02),
                loadImage(GRASS_TILE_03),
                loadImage(GRASS_TILE_04)));
        imageMap.put(ImageType.ANT, List.of(
                loadImage(ANT_01),
                loadImage(ANT_02)));
    }

    public Image getImage(ImageType imageType) {
        List<Image> images = imageMap.get(imageType);

        if (images == null || images.isEmpty()) {
            throw new IllegalArgumentException("Provided imageType not implemented: " + imageType);
        }

        Random rnd = new Random();
        return images.get(rnd.nextInt(images.size()));
    }

    public List<Image> getImages(ImageType imageType) {
        return imageMap.get(imageType);
    }

    private Image loadImage(String path) {
        try {
            BufferedImage bufferedImage = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(path)));
            ImageIcon ii = new ImageIcon(bufferedImage);
            return ii.getImage();
        } catch (IOException e) {
            throw new IllegalStateException("No image found.");
        }
    }
}
