package com.example.googleform_with_sql.HomeAdapter;

public class categoryHelperClass {

    int image;
    String title;

    public categoryHelperClass(int image, String title) {
        this.image = image;
        this.title = title;
    }

    public int getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }
}
