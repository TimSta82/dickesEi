package com.example.tstaats.myvermeer.Common;

public class Kunstwerk {

    private static final String TAG = "Kunstwerk";

    private int imageId;
    private String artist;
    private String imageName;
    private int imageValue;
    private int imageSoldFor;

    public Kunstwerk(int imageId, String artist, String imageName, int imageValue, int imageSoldFor) {
        this.imageId = imageId;
        this.artist = artist;
        this.imageName = imageName;
        this.imageValue = imageValue;
        this.imageSoldFor = imageSoldFor;
    }

//    public Kunstwerk() {
//    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public int getImageValue() {
        return imageValue;
    }

    public void setImageValue(int imageValue) {
        this.imageValue = imageValue;
    }

    public int getImageSoldFor() {
        return imageSoldFor;
    }

    public void setImageSoldFor(int imageSoldFor) {
        this.imageSoldFor = imageSoldFor;
    }

    @Override
    public String toString() {
        return "Künstler: " + artist +
                "\nTitel: " + imageName + " Preis: " + imageValue;
    }
}
