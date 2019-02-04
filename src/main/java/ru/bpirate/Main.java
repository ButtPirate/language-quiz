package ru.bpirate;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Main {
    static BufferedReader reader;

    static String path = System.getProperty("user.dir");

    private static List<Phrase> phrases = new ArrayList<>();

    public static void main(String[] args) {
        loadPhrases();

        launchRotation();

    }

    private static void launchRotation() {
        List<Phrase> copiedList = new ArrayList<>();

        for (Phrase phrase : phrases) {
            copiedList.add(phrase.clone());
        }

        for (Phrase x : copiedList) {
            x.setToForeign(true);
        }

        phrases.addAll(copiedList);
        Collections.shuffle(phrases);

        reader = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i <= phrases.size()-1; i++) {
            Phrase phrase = phrases.get(i);

            if (phrase.isToForeign()) {
                System.out.println("Translate to english: " + phrase.getForeign());
            } else {
                System.out.println("Translate to foreign: " + phrase.getEnglish());
            }

            readLine();

            System.out.println("Correct answer was: ");
            System.out.println(phrase.getEnglish());
            System.out.println(phrase.getTranscription());
            System.out.println(phrase.getForeign());

            System.out.println("\n");


        }

        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void loadPhrases() {

        JSONParser parser = new JSONParser();

        Object obj = null;
        try {
            obj = parser.parse(new FileReader(
                    path+"/data.json"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        JSONObject jsonObject = (JSONObject) obj;

        JSONArray array = (JSONArray) jsonObject.get("phrases");
        for (int i = 0; i <= array.size()-1; i++)
        {
            JSONObject object = (JSONObject) array.get(i);
            Phrase parsedPhrase = new Phrase(
                    (String) object.get("foreign"),
                    (String) object.get("transcription"),
                    (String) object.get("english"));
            phrases.add(parsedPhrase);
        }

    }

    private static void readLine() {
        try {
            reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
