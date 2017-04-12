package com.joaosakai.manager;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.joaosakai.model.Game;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import java.util.Map;
import java.util.Set;

import static com.joaosakai.constants.GameConstants.GAME_NUMBER;

/**
 * Created by joao.sakai on 4/8/17.
 */

@Component
public class LogManager {

    private ObjectMapper mapper;

    private Jedis jedis;

    public LogManager() {
        jedis = new Jedis();
        mapper = new ObjectMapper();
    }

    public void addLogInfo(final Map<String, Game> games) throws JsonProcessingException {
        jedis.flushAll();
        for (Map.Entry<String, Game> game : games.entrySet()) {
            jedis.set(game.getKey(), mapper.writeValueAsString(game.getValue()));
        }
    }

    public String get(final Integer gameId) {
        String key = GAME_NUMBER + gameId;
        return jedis.get(key);
    }

    public Set<String> getKeys() {
        return jedis.keys("*");
    }

    public boolean exists(final String key) {
        return jedis.exists(key);
    }
}
