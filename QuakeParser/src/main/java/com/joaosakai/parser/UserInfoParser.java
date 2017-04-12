package com.joaosakai.parser;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static com.joaosakai.constants.GameConstants.N_DELIMITER;
import static com.joaosakai.constants.GameConstants.T_DELIMITER;
import static com.joaosakai.constants.GameConstants.USER_INFO_PATTERN;

/**
 * Created by joao.sakai
 */
@Component
public class UserInfoParser {

    public List<String> parse(final List<String> lines) {
        List<String> nicknames = new ArrayList();
        for(String line : lines) {
            if(line.contains(USER_INFO_PATTERN)) {
                String userName = line.substring(line.indexOf(N_DELIMITER) + 2, line.indexOf(T_DELIMITER));
                if(!nicknames.contains(userName)) {
                    nicknames.add(userName);
                }
            }
        }
        return nicknames;
    }


}
