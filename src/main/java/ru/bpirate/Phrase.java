package ru.bpirate;

public class Phrase implements Cloneable {
    private String foreign;
    private String transcription;
    private String english;

    /**
     * If true then in rotation will ask to translate from english to foreign, instead of foreign to english.
     */
    private boolean toForeign = false;

    public Phrase(String foreign, String transcription, String english) {
        this.foreign = foreign;
        this.transcription = transcription;
        this.english = english;
    }

    @Override
    public Phrase clone() {
        try {
            return (Phrase) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;

        }
    }

    public String getForeign() {
        return foreign;
    }

    public void setForeign(String foreign) {
        this.foreign = foreign;
    }

    public String getTranscription() {
        return transcription;
    }

    public void setTranscription(String transcription) {
        this.transcription = transcription;
    }

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public boolean isToForeign() {
        return toForeign;
    }

    public void setToForeign(boolean directionSwitch) {
        this.toForeign = directionSwitch;
    }
}
