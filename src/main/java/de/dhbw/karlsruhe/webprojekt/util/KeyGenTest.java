package de.dhbw.karlsruhe.webprojekt.util;

import de.dhbw.karlsruhe.webprojekt.model.Games;
import java.util.ArrayList;

public class KeyGenTest {
    public static void main(String[] args){
        KeyGenerator generator = new KeyGenerator();
        ArrayList<Games> gameListe = new ArrayList<>();
        gameListe.add(new Games("Spiel1", "http://cdn.akamai.steamstatic.com/steam/apps/327680/header.jpg?t=1469216185"));
        gameListe.add(new Games("Spiel2", "http://cdn.akamai.steamstatic.com/steam/apps/327680/header.jpg?t=1469216185"));
        generator.generateKey(gameListe);
    }
}
