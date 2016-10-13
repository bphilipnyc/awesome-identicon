package me.bphilip.identicon.generator.impl;

import me.bphilip.identicon.generator.Generator;
import org.apache.commons.lang3.StringUtils;

import java.awt.Color;

public class DefaultGenerator implements Generator {

    @Override
    public boolean[][] getBooleanValueArray(String hash) {

        if (StringUtils.isBlank(hash) && hash.length() >= 16) {
            System.err.println("illegal argument hash: not null and size >= 16");
        }

        this.hash = hash;

        boolean[][] array = new boolean[6][5];

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                array[i][j] = false;
            }
        }

        for (int i = 0; i < hash.length(); i += 2) {
            int s = i / 2;

            Integer numerator = Integer.parseInt(hash.charAt(i) + "", 16);
            boolean v = (int) Math.round(numerator / 10.0) > 0;
            if (s % 3 == 0) {
                array[s / 3][0] = v;
                array[s / 3][4] = v;
            }
            else if (s % 3 == 1) {
                array[s / 3][1] = v;
                array[s / 3][3] = v;
            }
            else {
                array[s / 3][2] = v;
            }
        }

        return array;
    }

    @Override
    public Color getBackground() {

        int r = Integer.parseInt(String.valueOf(hash.charAt(0)), 16) * 16;
        int g = Integer.parseInt(String.valueOf(hash.charAt(1)), 16) * 16;
        int b = Integer.parseInt(String.valueOf(hash.charAt(2)), 16) * 16;

        return new Color(r, g, b);
    }

    @Override
    public Color getForeground() {

        int r = Integer.parseInt(String.valueOf(hash.charAt(hash.length() - 1)), 16) * 16;
        int g = Integer.parseInt(String.valueOf(hash.charAt(hash.length() - 2)), 16) * 16;
        int b = Integer.parseInt(String.valueOf(hash.charAt(hash.length() - 3)), 16) * 16;

        return new Color(r, g, b);
    }

    private String hash;
}
