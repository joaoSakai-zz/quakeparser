package com.joaosakai.service;

import com.joaosakai.manager.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

import static com.joaosakai.constants.GameConstants.GAME_NUMBER;

/**
 * Created by jgenari on 4/8/17.
 */
@Service
public class GameService {

    @Autowired
    private LogManager logManager;

    public String getGameById(final int gameId) {
        String gameKey = GAME_NUMBER + gameId;
        if(logManager.exists(gameKey)){
            return logManager.get(gameId);
        }
        return null;
    }

    public Set<String> getAllAvailableGames() {
        return logManager.getKeys();
    }
}
