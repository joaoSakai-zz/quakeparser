package com.joaosakai.model;

/**
 * Created by jgenari on 4/6/17.
 */
public class Player {

    private String nickName;

    public Player(String nickName) {
        this.nickName = nickName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null ) {
            return false;
        }
        final Player other = (Player) obj;
        if(this.nickName.equals(other.nickName)) {
            return true;
        }
        return false;
    }
}
