package com.example.kelly.namethatsong;

/**
 * Created by Kelly on 10/15/2015.
 */
public class Genre {

    private String type;
    private String caption;
    private int icon;

    public Genre(String type,String caption,int icon){
        this.type = type;
        this.caption = caption;
        this.icon = icon;
    }

    public String getType() {
        return type;
    }

    public int getIcon() {
        return icon;
    }

    public String getCaption() {
        return caption;
    }



}
