package com.joaosakai.parser;

import com.joaosakai.manager.LogManager;
import com.joaosakai.model.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static com.joaosakai.constants.GameConstants.*;
/**
 * Created by joao.sakai
 */
@Component
public class GameParser {

    private static final String GAME_LOG = "games.log";

    private UserInfoParser userInfoParser;
    private KillInfoParser killInfoParser;

    @Autowired
    private LogManager logManager;

    public GameParser() {
        userInfoParser = new UserInfoParser();
        killInfoParser = new KillInfoParser();
        logManager = new LogManager();
    }

    /**
     * Method that parse the file in the root directory
     */
    public void parse() throws IOException {
        try(Scanner logScanner = new Scanner(new File(GAME_LOG))) {
            Map<String, Game> games = new HashMap();

            Map<String, List> gameMatches = groupRawGameData(logScanner);

            for(Map.Entry<String, List> match : gameMatches.entrySet()){
                games.put(match.getKey(), buildGameData(match.getValue()));
            }

            logManager.addLogInfo(games);

        } catch (IOException e) {
            throw new IOException(e);
        }
    }

    private Game buildGameData(final List<String> rawMatch) {
        List<String> players = userInfoParser.parse(rawMatch);

        Map<String, Integer> kills = killInfoParser.parse(players, rawMatch);

        return new Game(
                killInfoParser.getTotalOfKills(),
                players,
                kills
        );
    }

    private Map groupRawGameData(final Scanner logScanner) {
        Map games = new HashMap<String, List>();
        List gameData = new ArrayList<String>();

        boolean beginRecord = false;
        int gameMatch = 0;

        while (logScanner.hasNext()) {
            String line = logScanner.nextLine();

            if(!beginRecord && line.contains(INIT_GAME_PATTERN)) {
                beginRecord = true;
            }

            if(!beginRecord) {
                continue;
            }

            if(beginRecord && line.contains(SHUTDOWN_GAME_PATTERN)) {
                beginRecord = false;
                games.put(GAME_NUMBER + gameMatch, new ArrayList<>(gameData));
                gameData.clear();
                gameMatch++;
            }

            gameData.add(line);
        }
        return games;
    }

}
