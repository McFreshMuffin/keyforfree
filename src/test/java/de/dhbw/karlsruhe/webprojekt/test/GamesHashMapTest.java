package de.dhbw.karlsruhe.webprojekt.test;

import de.dhbw.karlsruhe.webprojekt.model.Games;
import de.dhbw.karlsruhe.webprojekt.util.GamesHashMap;
import java.util.ArrayList;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class GamesHashMapTest {
    
    private GamesHashMap ghm;
    private Games game1;
    private Games game2;
                
    @Before
    public void initHashMap() {
        ghm = new GamesHashMap();
        game1 = new Games(123);
        game2 = new Games(345);
    }
    
    @Test
    public void testAddGame(){
        ArrayList<Games> test = ghm.addGame(game1);
        Assert.assertTrue("Game1 hinzufügen (1)", test == null);
        ArrayList<Games> test1 = ghm.addGame(game1);
        Assert.assertTrue("Game1 hinzufügen (2)", !test1.isEmpty());
        ArrayList<Games> test2 = ghm.addGame(game1);
        Assert.assertTrue("Game1 hinzufügen (3)", !test2.isEmpty());
        ArrayList<Games> test3 = ghm.addGame(game2);
        Assert.assertTrue("Game2 hinzufügen (1)", test3 == null);
        ArrayList<Games> test4 = ghm.addGame(game2);
        Assert.assertTrue("Game2 hinzufügen (2)", !test4.isEmpty());
        Assert.assertTrue("Game1 vorhanden", ghm.get(123).size() == 3);
        Assert.assertTrue("Game2 vorhanden", ghm.get(345).size() == 2);
    }
}
