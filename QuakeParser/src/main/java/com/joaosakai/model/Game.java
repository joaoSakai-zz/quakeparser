package com.joaosakai.model;

import java.util.List;
import java.util.Map;

/**
 * Created by joao.sakai
 */
public class Game {

    private int totalKills;

    private List<String> players;

    private Map kills;

    public Game(int totalKills, List<String> players, Map<String, Integer> kills) {
        this.totalKills = totalKills;
        this.players = players;
        this.kills = kills;
    }

    public int getTotalKills() {
        return totalKills;
    }

    public void setTotalKills(int totalKills) {
        this.totalKills = totalKills;
    }

    public List<String> getPlayers() {
        return players;
    }

    public void setPlayers(List<String> players) {
        this.players = players;
    }

    public Map getKills() {
        return kills;
    }

    public void setKills(Map kills) {
        this.kills = kills;
    }
}
