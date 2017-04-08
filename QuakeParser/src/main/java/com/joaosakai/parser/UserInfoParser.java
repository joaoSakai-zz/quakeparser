package com.joaosakai.parser;

import com.joaosakai.model.Player;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import static com.joaosakai.patterns.GamePatterns.N_DELIMITER;
import static com.joaosakai.patterns.GamePatterns.T_DELIMITER;
import static com.joaosakai.patterns.GamePatterns.USER_INFO_PATTERN;

/**
 * Created by joao.sakai
 */
public class UserInfoParser {

    public Set<Player> parse(final List<String> lines) {
        List<String> nicknames = new ArrayList();

        Set players = new HashSet<Player>();
        for(String line : lines) {
            if(line.contains(USER_INFO_PATTERN)) {
                String userName = line.substring(line.indexOf(N_DELIMITER) + 2, line.indexOf(T_DELIMITER));
                if(!nicknames.contains(userName)) {
                    nicknames.add(userName);
                }
            }
        }

        for (String nickname : nicknames) {
            players.add(new Player(nickname));
        }

        return players;
    }


}
