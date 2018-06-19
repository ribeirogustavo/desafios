package idwall.desafio.string;

import idwall.desafio.Main;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rodrigo Catão Araujo on 06/02/2018.
 */
public class IdwallFormatter extends StringFormatter {

    /**
     * Should format as described in the challenge
     *
     * @param text
     * @return
     */
    @Override
    public String format(String text) {

        String textFormated = "";
        Integer limit = super.getLimit();

        while (text.length() > 0) {
            if (text.substring(0, 1).equals(" ")) {
                text = text.substring(1);
            }
            if (text.length() > limit) {
                if (text.substring(limit, limit+1).equals(" ") || text.substring(limit, limit+1).equals("\n")) {
                    textFormated += text.substring(0, limit) + "\n";
                    text = text.substring(limit, text.length());
                } else {
                    String stringAux = text.substring(0, limit);
                    int lastIndexOfEmpty;
                    if (stringAux.lastIndexOf("\n") != -1 && stringAux.lastIndexOf("\n") < limit) {
                        lastIndexOfEmpty = stringAux.lastIndexOf("\n");
                    } else {
                        lastIndexOfEmpty = stringAux.lastIndexOf(" ");
                    }
                    textFormated += text.substring(0, lastIndexOfEmpty) + "\n";
                    text = text.substring(lastIndexOfEmpty, text.length());
                }
            } else {
                textFormated += text;
                text = "";
            }
        }
        return textFormated;
    }


    public String fullJustify(String text) {
        Integer limit = super.getLimit();
        StringAlignUtils stringAlignUtils = new StringAlignUtils(limit, StringAlignUtils.Alignment.CENTER);
        return stringAlignUtils.format(text);
    }
}
