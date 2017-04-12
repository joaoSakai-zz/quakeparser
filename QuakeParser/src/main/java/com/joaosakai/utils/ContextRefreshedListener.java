package com.joaosakai.utils;

import com.joaosakai.parser.GameParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by jgenari on 4/8/17.
 */
@Component
public class ContextRefreshedListener implements ApplicationListener<ContextRefreshedEvent> {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private GameParser gameParser;

    @Autowired
    public void setGameParser(GameParser gameParser) {
        this.gameParser = gameParser;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        try {
            gameParser.parse();
            logger.info("#### The log has been parsed ####");
        } catch (IOException e) {
            logger.error("Error on parse log file: " + e.getMessage());
        }
    }
}
