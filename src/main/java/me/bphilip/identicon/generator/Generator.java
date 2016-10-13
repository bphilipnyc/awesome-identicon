package me.bphilip.identicon.generator;

import java.awt.Color;

public interface Generator {

    boolean[][] getBooleanValueArray(String hash);

    Color getBackground();

    Color getForeground();
}
