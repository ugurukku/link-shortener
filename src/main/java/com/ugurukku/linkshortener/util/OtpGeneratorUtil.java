package com.ugurukku.linkshortener.util;

public final class OtpGeneratorUtil {

    private static final String[] CHARACTERS = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};

    public static String generate(){
        StringBuilder generatedText = new StringBuilder();

        for (int i = 0; i < 6; i++) {
            int randomIndex = (int) (Math.random() * CHARACTERS.length);
            String randomCharacter = CHARACTERS[randomIndex];
            generatedText.append(randomCharacter);
        }

        return generatedText.toString();
    }


}

