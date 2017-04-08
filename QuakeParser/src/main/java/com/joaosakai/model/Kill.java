package com.joaosakai.model;

/**
 * Created by jgenari on 4/7/17.
 */
public class Kill {

    private Player player;

    private int kill;

    public Kill(Player player, int kill) {
        this.player = player;
        this.kill = kill;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getKill() {
        return kill;
    }

    public void setKill(int kill) {
        this.kill = kill;
    }
}
