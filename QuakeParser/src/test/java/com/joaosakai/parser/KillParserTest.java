package com.joaosakai.parser;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by jaao.sakai on 4/6/17.
 */
public class KillParserTest {

    public static final String LOG_LINE = "22:18 Kill: 2 2 7: Isgalamido killed Isgalamido by MOD_ROCKET_SPLASH";
    private static KillInfoParser killInfoParser;

    @BeforeClass
    public static void setUp() {
        killInfoParser = new KillInfoParser();
    }

    @Test
    public void testParseLinesWithoutPlayers() {
        List players = new ArrayList<String>();
        List lines = new ArrayList<String>();
        Map killInfo = killInfoParser.parse(players, lines);
        Assert.assertTrue(killInfo.isEmpty());
    }

    @Test
    public void testParseLinesWithoutKillInfoPattern() {
        List players = new ArrayList<String>();
        List lines = new ArrayList<String>();
        lines.add(new String("Line 1"));
        Map killInfo = killInfoParser.parse(players, lines);
        Assert.assertTrue(killInfo.isEmpty());
    }

    @Test
    public void testParseLineWithKillInfo() {
        List players = new ArrayList<String> ();
        players.add("Isgalamido");
        List lines = new ArrayList<String>();
        lines.add(LOG_LINE);
        Map killInfo = killInfoParser.parse(players, lines);
        Assert.assertFalse(killInfo.isEmpty());
    }

    @Test
    public void testParseLinePlayerWithoutInteraction() {
        List players = new ArrayList<String> ();
        String nobody = "Nobody";
        players.add(nobody);
        List lines = new ArrayList<String>();
        lines.add(LOG_LINE);
        Map killInfo = killInfoParser.parse(players, lines);
        Assert.assertEquals(0, killInfo.get(nobody));
    }


}
