package com.example.test;

import javafx.scene.image.Image;

public class Location {
    String      name;
    Image       background;
    Integer[]   playerStart;
    Integer[]   bounds;

    public Location(String name, Image background, Integer[] playerStart, Integer[] bounds){
        this.name = name;
        this.background = background;
        this.playerStart = playerStart;
        this.bounds = bounds;
    }
    public Image getBackground(){
        return this.background;
    }
}
