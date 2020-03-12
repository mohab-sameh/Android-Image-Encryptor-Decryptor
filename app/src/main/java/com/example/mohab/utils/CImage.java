package com.example.mohab.utils;

public class CImage {
    private String imageUrl;
    private String name;

    public CImage(String imageUrl, String name) {
        if(name.trim().equals("")){
            this.name = "No Name";
        } else{
            this.name = name;
        }

        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setName(String name) {
        if(name.trim().equals("")){
            this.name = "No Name";
        } else{
            this.name = name;
        }
    }
}
