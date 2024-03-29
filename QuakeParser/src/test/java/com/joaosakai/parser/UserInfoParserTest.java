package com.joaosakai.parser;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jaao.sakai on 4/6/17.
 */
public class UserInfoParserTest {

    private static UserInfoParser userInfoParser;

    @BeforeClass
    public static void setUp() {
        userInfoParser = new UserInfoParser();
    }

    @Test
    public void testParseLineWithoutPatternUserInfo() {
        List<String> lines = new ArrayList<>();
        lines.add("Line 1");
        lines.add("Line 2");
        List<String> players = userInfoParser.parse(lines);
        Assert.assertTrue(players.isEmpty());
    }

    @Test
    public void testParseLineWithUserInfo() {
        List<String> lines = new ArrayList<>();
        lines.add("Line 1");
        lines.add("20:34 ClientUserinfoChanged: 2 n\\Isgalamido\\t\\0\\model\\xian/default\\hmodel\\xian/default\\g_redteam\\\\g_blueteam\\\\c1\\4\\c2\\5\\hc\\100\\w\\0\\l\\0\\tt\\0\\tl\\0");
        List<String> players = userInfoParser.parse(lines);
        Assert.assertFalse(players.isEmpty());
        Assert.assertEquals(1, players.size());
    }


}
