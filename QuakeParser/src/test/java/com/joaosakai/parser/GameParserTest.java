package com.joaosakai.parser;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.test.context.ContextConfiguration;

import java.io.IOException;

/**
 * Created by joao.sakai on 4/6/17.
 */
@ContextConfiguration(classes = GameParser.class, initializers = ConfigFileApplicationContextInitializer.class)
public class GameParserTest {

    private static GameParser logParser;

    @BeforeClass
    public static void setUp() {
        logParser = new GameParser();
    }

    @Test
    public void testGameLogParser() throws IOException {
        logParser.parse();
    }
}
