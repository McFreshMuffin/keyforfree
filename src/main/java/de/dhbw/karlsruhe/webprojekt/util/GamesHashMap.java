package de.dhbw.karlsruhe.webprojekt.util;

import java.util.ArrayList;
import java.util.HashMap;
import de.dhbw.karlsruhe.webprojekt.model.Games;

public class GamesHashMap extends HashMap<Integer, ArrayList<Games>> {

    public ArrayList<Games> addGame(Games game) {
        ArrayList<Games> list;
        boolean isVorhanden = false;
        if (!this.keySet().isEmpty()) {
            for (int i : this.keySet()) {
                if (i == game.getGameId()) {
                    isVorhanden = true;
                }
            }
            if (isVorhanden) {
                list = this.get((int) game.getGameId());
                list.add(game);
                return this.putIfAbsent((int) game.getGameId(), list);
            } else {
                list = new ArrayList<>();
                list.add(game);
                int id = (int) game.getGameId();
                return this.putIfAbsent(id, list);
            }
        } else {
            list = new ArrayList<>();
            list.add(game);
            int id = (int) game.getGameId();
            return this.putIfAbsent(id, list);
        }
    }

    public void removeGame(Games game) {
        int test = 0;
        for (int i : this.keySet()) {
            if (i == game.getGameId()) {
                ArrayList<Games> list = this.get(i);
                int size = list.size();
                if(size > 1){
                    list.remove(size - 1);
                    this.put(i, list);
                }else{
                    test = i;
                }
            }  
        }
        if(test != 0){
            this.remove(test);
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

    public ArrayList<Games> convertToArrayList() {
        ArrayList<Games> gameListe = new ArrayList<>();
        for (int i : this.keySet()) {
            ArrayList<Games> curList = this.get(i);
            for (Games game : curList) {
                gameListe.add(game);
            }
        }
        return gameListe;
    }
}
