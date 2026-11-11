package com.example.recyclerview;

public class Item {
    private int image;
    private String name;
    private String description;
    private String keyID;

    public Item(){
    }
    public Item(int image, String name, String description) {
        this.image = image;
        this.name = name;
        this.description = description;
    }

    public int getImage() {
        return image;
    }

    public String getName() {return name;}
    public String getDescription() {
        return description;
    }
    public String getKeyID(){return keyID;}

    public void setImage(int image) {
        this.image = image;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setKeyID(String keyID) {
        this.keyID = keyID;
    }
}
