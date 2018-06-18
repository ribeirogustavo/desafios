package idwall.desafio.string;

import static org.junit.Assert.*;

public class IdwallFormatterTest {

    @org.junit.Test
    public void format() {
        String DEFAULT_INPUT_TEXT = "In the beginning God created the heavens and the earth. Now the earth was formless and empty, darkness was over the surface of the deep, and the Spirit of God was hovering over the waters.\n" +
                "\n" +
                "And God said, \"Let there be light,\" and there was light. God saw that the light was good, and he separated the light from the darkness. God called the light \"day,\" and the darkness he called \"night.\" And there was evening, and there was morning - the first day.";
        String DEFAULT_OUTPUT_TEXT = "In the beginning God created the heavens\n" +
                "and the earth. Now the earth was\n" +
                "formless and empty, darkness was over\n" +
                "the surface of the deep, and the Spirit\n" +
                "of God was hovering over the waters.\n" +
                "\n" +
                "\n" +
                "And God said, \"Let there be light,\" and\n" +
                "there was light. God saw that the light\n" +
                "was good, and he separated the light\n" +
                "from the darkness. God called the light\n" +
                "\"day,\" and the darkness he called\n" +
                "\"night.\" And there was evening, and\n" +
                "there was morning - the first day.";

                IdwallFormatter idwallFormatter = new IdwallFormatter();
                String output = idwallFormatter.format(DEFAULT_INPUT_TEXT);

                assertEquals(DEFAULT_OUTPUT_TEXT, output);
    }
}