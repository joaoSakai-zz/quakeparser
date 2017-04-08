package com.joaosakai.parser;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by joao.sakai on 4/6/17.
 */
public class GameParserTest {

    private static GameParser logParser;

    @BeforeClass
    public static void setUp() {
        logParser = new GameParser();
    }

    @AfterClass
    public static void down() {

    }

    private String getGameLogFile() {
        return "games.log";
    }

    @Test(expected = IOException.class)
    public void testFileNotExists() throws IOException {
        final String wrongFile = "wrongFile";
        logParser.parse(wrongFile);
    }

    @Test
    public void testCorrectFile() throws IOException {
        logParser.parse(getGameLogFile());
    }
}
