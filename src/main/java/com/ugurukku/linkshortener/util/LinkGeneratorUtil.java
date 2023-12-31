package com.ugurukku.linkshortener.util;

public class LinkGeneratorUtil {

    private static final String[] CHARACTERS = new String[]{
            "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
            "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
            "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
            "U", "V", "W", "X", "Y", "Z", "a", "b", "c", "d",
            "e", "f", "g", "h", "i", "j", "k", "l", "m", "n",
            "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};

    public static String generate() {
        StringBuilder generatedText = new StringBuilder();

        for (int i = 0; i < 15; i++) {
            int randomIndex = (int) (Math.random() * CHARACTERS.length);
            String randomCharacter = CHARACTERS[randomIndex];
            generatedText.append(randomCharacter);
        }

        return generatedText.toString();
    }

}
