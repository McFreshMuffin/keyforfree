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
    
    private ArrayList<Games> test;
    private ArrayList<Games> test1;
    private ArrayList<Games> test2;
    private ArrayList<Games> test3;
    private ArrayList<Games> test4;
                
    @Before
    public void initHashMap() {
        ghm = new GamesHashMap();
        game1 = new Games(123);
        game2 = new Games(345);
        
        test = ghm.addGame(game1);
        test1 = ghm.addGame(game1);
        test2 = ghm.addGame(game1);
        test3 = ghm.addGame(game2);
        test4 = ghm.addGame(game2);
    }
    
    @Test
    public void testAddGame(){
        Assert.assertTrue("Game1 hinzufügen (1)", test == null);
        Assert.assertTrue("Game2 hinzufügen (1)", test3 == null);
        Assert.assertTrue("Game1 hinzufügen (2)", !test1.isEmpty());
        Assert.assertTrue("Game1 hinzufügen (3)", !test2.isEmpty());
        Assert.assertTrue("Game2 hinzufügen (2)", !test4.isEmpty());
       
        Assert.assertTrue("Game1 vorhanden", ghm.get(123).size() == 3);
        Assert.assertTrue("Game2 vorhanden", ghm.get(345).size() == 2);
    }
    
    @Test
    public void testGetGame(){
        Games game = ghm.getGameById(123);
        Assert.assertTrue("Enthält alle Einträge", game.getGameId() == 123);
    }
    
    @Test
    public void testToArrayListConvert(){
        ArrayList<Games> gameListe = ghm.convertToArrayList();
        Assert.assertTrue("Hat entsprechende Länge", gameListe.size() == 5);
        
        int game123 = 0;
        int game345 = 0;
        
        for(Games game : gameListe){
            if(game.getGameId() == 123){
                game123++;
            }
            if(game.getGameId() == 345){
                game345++;
            }
        }
        
        Assert.assertTrue("Game123 in entsprechender Zahl enthalten", game123 == 3);
        Assert.assertTrue("Game345 in entsprechender Zahl enthalten", game345 == 2);
    
    }
}
