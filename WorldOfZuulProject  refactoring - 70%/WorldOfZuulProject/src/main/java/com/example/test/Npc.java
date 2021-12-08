package com.example.test;

import javafx.scene.image.Image;

public class Npc {
    String      name;
    Image       image;
    Image       portrait;
    String      location;
    Integer[]   position;
    Boolean     interaction = false;
    Integer     state = 0;
    String[]    conversations_EN;
    String[]    conversations_DK;
    Integer     showAnswer;
    String[][]  answers_EN;
    String[][]  answers_DK;

    public Npc(String name, String npcImagePath, String npcPortraitPath, String location, Integer[] position, String[] conversations_EN, String[] conversations_DK, Integer showAnswer, String[][] answers_EN, String[][] answers_DK){
        this.name = name;
        this.image = new Image(getClass().getResource(npcImagePath).toExternalForm());
        this.portrait = new Image(getClass().getResource(npcPortraitPath).toExternalForm());
        this.location = location;
        this.position = position;
        this.conversations_EN = conversations_EN;
        this.conversations_DK = conversations_DK;
        this.showAnswer = showAnswer;
        this.answers_EN = answers_EN;
        this.answers_DK = answers_DK;

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
    public void incrementState(){
        this.state++;
    }
    public Integer getState(){
        return this.state;
    }
    public Integer getShowAnswer(){
        return this.showAnswer;
    }
    public String[][] getAnswers(String language){
        if (language == "DK"){
            return this.answers_DK;
        } else {
            return this.answers_EN;
        }
    }
    public Boolean getInteraction(){
        return this.interaction;
    }
    public void toggleInteraction(){
        if(this.interaction){
            this.interaction = false;
        } else {
            this.interaction = true;
        }
    }

    public Integer conversationsLength(String language){
        if (language == "DK"){
            return this.conversations_DK.length;
        } else {
            return this.conversations_EN.length;
        }
    }
    public String getLocation(){
        return this.location;
    }
    public Image getPortrait(){
        return this.portrait;
    }
    public String[] getConversations(String language){
        if (language == "DK"){
            return this.conversations_DK;
        } else {
            return this.conversations_EN;
        }
    }
}
