package idwall.desafio.string;

import idwall.desafio.Main;

import java.util.*;

import static java.lang.StrictMath.abs;

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
                if (text.substring(limit, limit + 1).equals(" ") || text.substring(limit, limit + 1).equals("\n")) {
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

    /**
     *
     * @param text
     * @return
     */
    public String formatJustify(String text) {

        String textFormatted = "";
        Integer limit = super.getLimit();

        while (text.length() > 0) {
            if (text.substring(0, 1).equals(" ")) {
                text = text.substring(1);
            }
            if (text.length() > limit) {
                if (text.substring(limit, limit + 1).equals(" ") ||
                    text.substring(limit, limit + 1).equals("\n") ||
                    text.substring(0, 1).equals("\n")) {
                    if (text.substring(0, 1).equals("\n")){
                        String stringBeginning = text.substring(0, limit);
                        stringBeginning.replace("\n","");
                        textFormatted += padding(stringBeginning.lastIndexOf(" "), stringBeginning, " ") + "\n";
                        text = text.substring(limit, text.length());
                    }else {
                        textFormatted += text.substring(0, limit) + "\n";
                        text = text.substring(limit, text.length());
                    }
                } else {
                    String stringAux = text.substring(0, limit);
                    int lastIndexOfEmpty;
                    if (stringAux.lastIndexOf("\n") != -1 && stringAux.lastIndexOf("\n") < limit) {
                        lastIndexOfEmpty = stringAux.lastIndexOf("\n");
                    } else {
                        lastIndexOfEmpty = stringAux.lastIndexOf(" ");
                    }

                    String stringPrepared = text.substring(0, lastIndexOfEmpty);
                    Integer lengthOfStringPrepared = stringPrepared.length();
                    Integer quantityToPadding = limit - lengthOfStringPrepared;
                    List<Integer> listOfSpaces = listOfSpace(stringPrepared);
                    Integer quantityOfSpace = listOfSpaces.size();

                    if (quantityToPadding > 0) {
                        long spaceToIncludeInString;
                        if(quantityToPadding != 1) {
                            spaceToIncludeInString = Math.round((double) quantityOfSpace / quantityToPadding);
                        }else{
                            spaceToIncludeInString = 1;
                        }
                        String paddingWithSpace = "";
                        Integer spaceInteraction = 0;
                        for (Integer i = 1; i <= spaceToIncludeInString; i++) {
                            paddingWithSpace += " ";
                            spaceInteraction++;
                        }

                        final String[] textFormattedAux = {stringPrepared};
                        String finalPaddingWithSpace = paddingWithSpace;
                        final Integer[] iterations = {1};
                        iterations[0] = 1;

                        Integer finalSpaceInteraction = spaceInteraction;
                        listOfSpaces.forEach((i) -> {
                            if (iterations[0] <= listOfSpaces.size() - 1) {

                                long spaces = iterations[0] * finalSpaceInteraction;
                                if (spaces < quantityToPadding) {
                                    iterations[0]++;
                                    textFormattedAux[0] = padding(i, textFormattedAux[0], finalPaddingWithSpace);
                                }
                            }
                        });
                        long spaceAdded = (iterations[0] -1) * spaceInteraction;
                        paddingWithSpace = "";
                        for (long i = spaceAdded; i < quantityToPadding; i++) {
                            paddingWithSpace += " ";
                        }
                        textFormatted += padding(listOfSpaces.get(listOfSpaces.size() -1), textFormattedAux[0], paddingWithSpace);
                        textFormatted += "\n";
                        text = text.substring(lastIndexOfEmpty, text.length());
                    }else {
                        textFormatted += text.substring(0, lastIndexOfEmpty) + "\n";
                        text = text.substring(lastIndexOfEmpty, text.length());
                    }
                }
            } else {
                textFormatted += text;
                text = "";
            }
        }
        return textFormatted;
    }

    /**
     *  This method will return a list that contain index of spaces inside of a String
     * @param text : The text that will be extracted the indexes
     * @return Wiil return a List that contains the spaces' index inside of a String
     */
    private List<Integer> listOfSpace(String text) {
        List<Integer> list = new ArrayList<>();
        while (text.lastIndexOf(" ") != -1) {
            if (text.substring(text.length()-1, text.length()).equals(" ")){
                text = text.substring(0, text.length() -1);
            }
            list.add(text.lastIndexOf(" "));
            text = text.substring(0, text.lastIndexOf(" "));
        }
        return list;
    }

    /**
     *  This method will insert the padding in a String according with index.
     * @param index : The index of String to be filled
     * @param text : The text that will receive the padding
     * @param padding : String padded with spaces
     * @return Will return the String with relative padding
     */
    private String padding(Integer index, String text, String padding) {
        text = text.substring(0, index) + padding + text.substring(index, text.length());
        return text;
    }
}
