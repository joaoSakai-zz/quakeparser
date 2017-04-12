package com.joaosakai.parser;

import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.joaosakai.constants.GameConstants.BY_DELIMITER;
import static com.joaosakai.constants.GameConstants.KILLED_PATTERN;
import static com.joaosakai.constants.GameConstants.KILL_PATTERN;

/**
 * Created by joao.sakai
 */
@Component
public class KillInfoParser {

    private static final String WORLD = "<world>";
    private Map<String, Integer> kills;
    private int worldKillsCount;
    private int userKillsCount;

    public Map parse(final List<String> players, final List<String> lines) {
        if(players.isEmpty()) {
            return Collections.emptyMap();
        }

        kills = new HashMap();
        worldKillsCount = 0;
        userKillsCount = 0;

        for (String player : players) {
            kills.put(player, 0);
        }

        for(String line : lines) {
            if(line.contains(KILL_PATTERN)) {

                String targetPlayer = extractTargetName(line);

               userLoop:
               for(String player : players) {
                   /**
                    * If the user kills himself any count is record;
                    */
                   if(player.equals(targetPlayer)) {
                       continue;
                   }

                   /**
                    * If world kill someone the target player loses one kill
                    */
                   if (line.contains(WORLD + KILLED_PATTERN)) {
                       subtractKill(targetPlayer);
                       break userLoop;
                   }

                   /**
                    * When the world is the target player the number of kills increase;
                    */
                   if(targetPlayer.equals(WORLD)) {
                       worldKillsCount++;
                       break userLoop;
                   }

                   if (line.contains( player + KILLED_PATTERN )) {
                       addKill(player);
                       subtractKill(targetPlayer);
                       userKillsCount++;
                       break userLoop;
                   }
               }
            }
        }
        return kills;
    }

    public int getTotalOfKills() {
        return userKillsCount + worldKillsCount;
    }

    private void subtractKill(final String targetName) {
        Integer targetCount = kills.get(targetName);
        targetCount--;
        kills.put(targetName, targetCount);
    }

    private void addKill(final String user) {
        Integer killCount = kills.get(user);
        killCount++;
        kills.put(user, killCount);
    }

    private String extractTargetName(final String line) {
        return line.substring(line.lastIndexOf(KILLED_PATTERN) + KILLED_PATTERN.length(), line.indexOf(BY_DELIMITER)).trim();
    }
}
