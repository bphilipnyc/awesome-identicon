package me.bphilip.identicon;

import me.bphilip.identicon.generator.Generator;
import me.bphilip.identicon.generator.impl.DefaultGenerator;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Identicon {

    public Identicon() {

        this.generator = new DefaultGenerator();
    }

    public BufferedImage create(String hash, int size) {

        if (size <= 0 || hash == null) {
            System.err.println("Cannot create image for hash " + hash + " with size " + size);
            return null;
        }

        boolean[][] array = generator.getBooleanValueArray(hash);

        int ratio = (int) Math.round(size / 5.0);

        BufferedImage identicon = new BufferedImage(ratio * 5, ratio * 5, BufferedImage.TYPE_INT_ARGB);
        Graphics graphics = identicon.getGraphics();

        graphics.setColor(generator.getBackground());
        graphics.fillRect(0, 0, identicon.getWidth(), identicon.getHeight());

        graphics.setColor(generator.getForeground());
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                if (array[i][j]) {
                    graphics.fillRect(j * ratio, i * ratio, ratio, ratio);
                }
            }
        }

        return identicon;
    }

    private final Generator generator;

}
