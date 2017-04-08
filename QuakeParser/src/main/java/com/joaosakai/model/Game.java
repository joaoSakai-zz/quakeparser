package com.joaosakai.model;

import java.util.List;
import java.util.Set;

/**
 * Created by joao.sakai
 */
public class Game {

    private int totalKills;

    private Set<Player> players;

    private List<Kill> kills;

    public Game(int totalKills, Set<Player> players, List<Kill> kills) {
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

    public Set<Player> getPlayers() {
        return players;
    }

    public void setPlayers(Set<Player> players) {
        this.players = players;
    }

    public List<Kill> getKills() {
        return kills;
    }

    public void setKills(List<Kill> kills) {
        this.kills = kills;
    }
}
