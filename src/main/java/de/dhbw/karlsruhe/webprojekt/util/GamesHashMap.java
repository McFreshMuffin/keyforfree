package de.dhbw.karlsruhe.webprojekt.util;

import java.util.ArrayList;
import java.util.HashMap;
import de.dhbw.karlsruhe.webprojekt.model.Games;

public class GamesHashMap extends HashMap<Integer, ArrayList<Games>> {

    public ArrayList<Games> addGame(Games game) {
        ArrayList<Games> list;
        if (!this.keySet().isEmpty()) {
            for (int i : this.keySet()) {
                if (i == game.getGameId()) {
                    list = this.get(i);
                    list.add(game);
                    return this.putIfAbsent(i, list);
                } else {
                    list = new ArrayList<>();
                    list.add(game);
                    int id = (int) game.getGameId();
                    return this.putIfAbsent(id, list);
                }
            }
        } else {
            list = new ArrayList<>();
            list.add(game);
            int id = (int) game.getGameId();
            return this.putIfAbsent(id, list);
        }
        return null;
    }

    public void removeGame(Games game) {
        for (int i : this.keySet()) {
            if (i == game.getGameId()) {
                ArrayList<Games> list = this.get(i);
                int size = list.size();
                list.remove(size - 1);
                this.put(i, list);
            }
        }
    }

    public Games getGameById(int gameId) {
        Games game = null;
        for (int i : this.keySet()) {
            if (i == gameId) {
                ArrayList<Games> list = this.get(gameId);
                int size = list.size();
                game = list.get(size - 1);
            }
        }
        return game;
    }
    
    public ArrayList<Games> convertToArrayList(){
        ArrayList<Games> gameListe = new ArrayList<>();
        for(int i : this.keySet()){
            ArrayList<Games> curList = this.get(i);
            for(Games game : curList){
                gameListe.add(game);
            }
        }
        return gameListe;
    }
}
