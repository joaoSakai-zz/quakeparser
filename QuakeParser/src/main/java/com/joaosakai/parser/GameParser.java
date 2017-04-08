package com.joaosakai.parser;

import com.joaosakai.model.Game;
import com.joaosakai.model.Kill;
import com.joaosakai.model.Player;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import static com.joaosakai.patterns.GamePatterns.*;
/**
 * Created by joao.sakai
 */
public class GameParser {

    public static final String GAME_NUMBER = "game_";
    private UserInfoParser userInfoParser;
    private KillInfoParser killInfoParser;

    public GameParser() {
        userInfoParser = new UserInfoParser();
        killInfoParser = new KillInfoParser();
    }


    /**
     * Method that parse the file in the root directory
     * @param fileName
     */
    public void parse(final String fileName) throws IOException {
        try(Scanner logScanner = new Scanner(new File(fileName))) {
            Map games = new HashMap<String, Game>();

            Map<String, List> gameMatches = groupRawGameData(logScanner);

            for(Map.Entry<String, List> match : gameMatches.entrySet()){
                    buildGameData(match.getValue());
            }


        } catch (Exception e) {
            throw new IOException(e);
        }
    }

    private Game buildGameData(final List<String> rawMatch) {
        Set players = userInfoParser.parse(rawMatch);


        List kills = new ArrayList<Kill>();
        kills.add(new Kill(new Player("ASd"), 0));

        return new Game(0, players , kills);

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
