package com.tamim.funbook;

public class FavItem {
    private int id;
    private String name;
    private String time;
    private String imageUrl;

    public FavItem() {
        // Default constructor
    }

    public FavItem(String name, String time, String imageUrl) {
        this.name = name;
        this.time = time;
        this.imageUrl = imageUrl;
    }

    // Setter methods
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    // Getter methods (if needed)
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTime() {
        return time;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
