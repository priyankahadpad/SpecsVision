package com.example.googleform_with_sql.HomeAdapter;

public class Most_ViewedHelperClass {

    int image;
    String title,description;

    public Most_ViewedHelperClass(int image, String title, String description) {
        this.image = image;
        this.title = title;
        this.description = description;
    }

    public int getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
