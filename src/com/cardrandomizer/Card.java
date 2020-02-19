package com.cardrandomizer;

public class Card {

    private int face;
    private String suite;
    private String imagePath;

    public Card(int face, String suite) {
        this.face = face;
        this.suite = suite;
        generateImagePath();
    }

    private void generateImagePath() {
        imagePath = "images/" + Integer.toString(face) + suite + ".png";
    }

    public void regenerateImagePath() {
        generateImagePath();
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setFace(int face) {
        this.face = face;
    }

    public int getFace() {
        return face;
    }

    public void setSuite(String suite) {
        this.suite = suite;
    }

    public String getSuite() {
        return suite;
    }

}
