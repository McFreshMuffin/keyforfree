package de.dhbw.karlsruhe.webprojekt.util;

import de.dhbw.karlsruhe.webprojekt.model.Games;
import java.util.ArrayList;
import java.util.Random;

public class KeyGenerator {

    private final char[] CHARS = "abcdefghijklmnopqrstuvwxyz1234567890".toCharArray();

    public void generateKey(ArrayList<Games> gameList) {
        Random random = new Random();
        String key = "";

        for (Games game : gameList) {
            for (int i = 1; i < 16; i++) {
                key += CHARS[random.nextInt(CHARS.length)];
                if (i == 5 || i == 10) {
                    key += "-";
                }
            }
            System.out.println(game.getName() +": "+key.toUpperCase());
            key = "";
        }
    }
}
