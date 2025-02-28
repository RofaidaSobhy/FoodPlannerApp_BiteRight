package com.example.biteright.model;

public class Details_Ingredient {
    String name;
    String measure;
    String image;

    public Details_Ingredient(String name, String measure, String image) {
        this.name = name;
        this.measure = measure;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
