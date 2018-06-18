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

        while (text.length() > 0) {

            if (text.substring(0, 1).equals(" ")) {
                text = text.substring(1);
            }
            if (text.length() > 40) {
                if (text.substring(40, 41).equals(" ") || text.substring(40, 41).equals("\n")) {
                    textFormated += text.substring(0, 40) + "\n";
                    text = text.substring(40, text.length());
                } else {
                    String stringAux = text.substring(0, 40);
                    int lastIndexOfEmpty;
                    if (stringAux.lastIndexOf("\n") != -1 && stringAux.lastIndexOf("\n") < 40) {
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

    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result = new ArrayList<String>();
        if (words == null || words.length == 0) {
            return result;
        }
        int count = 0;
        int last = 0;
        ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < words.length; i++) {
            count = count + words[i].length();
            if (count + i - last > maxWidth) {
                int wordsLen = count - words[i].length();
                int spaceLen = maxWidth - wordsLen;
                int eachLen = 1;
                int extraLen = 0;
                if (i - last - 1 > 0) {
                    eachLen = spaceLen / (i - last - 1);
                    extraLen = spaceLen % (i - last - 1);
                }
                StringBuilder sb = new StringBuilder();
                for (int k = last; k < i - 1; k++) {
                    sb.append(words[k]);
                    int ce = 0;
                    while (ce < eachLen) {
                        sb.append(" ");
                        ce++;
                    }
                    if (extraLen > 0) {
                        sb.append(" ");
                        extraLen--;
                    }
                }
                sb.append(words[i - 1]);//last words in the line
//if only one word in this line, need to fill left with space
                while (sb.length() < maxWidth) {
                    sb.append(" ");
                }
                result.add(sb.toString());
                last = i;
                count = words[i].length();
            }
        }
        int lastLen = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = last; i < words.length - 1; i++) {
            count = count + words[i].length();
            sb.append(words[i] + " ");
        }
        sb.append(words[words.length - 1]);
        int d = 0;
        while (sb.length() < maxWidth) {
            sb.append(" ");
        }
        result.add(sb.toString());
        return result;
    }
}
