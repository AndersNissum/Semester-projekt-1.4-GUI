package com.example.test;

import javafx.scene.image.Image;

import java.util.ArrayList;

public class Npc {
    String      name;
    Image       image;
    Image       portrait;
    String      location;
    Integer[]   position;
    Boolean     interaction = false;
    Integer     state = 0;
    String[][]  conversitions; //
    String[]    statements;
    String[]    responses;

    public Npc(String name, String npcImagePath, String npcPortraitPath, String location, Integer[] position, String[] statements, String[] responses){
        this.name = name;
        this.image = new Image(getClass().getResource(npcImagePath).toExternalForm());
        this.portrait = new Image(getClass().getResource(npcPortraitPath).toExternalForm());
        this.location = location;
        this.position = position;
        this.statements = statements;
        this.responses = responses;
    }
    public Image getImage(){
        return this.image;
    }
    public Integer getPosition(String axis){
        if (axis == "X"){
            return this.position[0];
        } else {
            return this.position[1];
        }
    }
    public Image getPortrait(){
        return this.portrait;
    }
    public String[] getStatements(){
        return this.statements;
    }
}
