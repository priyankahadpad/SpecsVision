package com.example.googleform_with_sql;

public class BestSeller {

    private String name;
    private int image;
    private String price;
    private String type;
    private String rating;

    public BestSeller(String name, int image, String price, String type, String rating) {
        this.name = name;
        this.image = image;
        this.price = price;
        this.type = type;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
