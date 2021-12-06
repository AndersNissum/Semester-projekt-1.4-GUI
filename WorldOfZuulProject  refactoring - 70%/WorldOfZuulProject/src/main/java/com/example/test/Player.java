package com.example.test;

import javafx.scene.image.Image;
import java.util.ArrayList;
import java.util.List;

public class Player extends GameZuulApplication {
    String name;
    String[] imagePaths = {
            "/img/player_idle_up.png",
            "/img/player_idle_right.png",
            "/img/player_idle_down.png",
            "/img/player_idle_left.png",
            "/img/player_walk_up.gif",
            "/img/player_walk_right.gif",
            "/img/player_walk_down.gif",
            "/img/player_walk_left.gif"
    };
    List<Image> images = new ArrayList<>();
    String portraitPath = "/img/player-headshot.png";
    Image portraitImage = new Image(getClass().getResource(portraitPath).toExternalForm());
    Integer[] position;

    public Player(String name) {
        this.name = name;
        for (int i = 0; i < this.imagePaths.length; i++) {
            this.images.add(new Image(getClass().getResource(this.imagePaths[i]).toExternalForm()));
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public Image getImage(String type, String direction) {
        if (type == "IDLE") {
            switch (direction) {
                case "UP":
                    return images.get(0);
                case "RIGHT":
                    return images.get(1);
                case "DOWN":
                    return images.get(2);
                case "LEFT":
                    return images.get(3);
            }
        } else {
            switch (direction) {
                case "UP":
                    return images.get(4);
                case "RIGHT":
                    return images.get(5);
                case "DOWN":
                    return images.get(6);
                case "LEFT":
                    return images.get(7);
            }
        }
        return images.get(0);
    }

    public Image getPortraitImage() {
        return this.portraitImage;
    }
}
