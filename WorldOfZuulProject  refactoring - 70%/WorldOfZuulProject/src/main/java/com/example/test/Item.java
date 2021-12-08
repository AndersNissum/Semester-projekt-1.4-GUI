package com.example.test;

import javafx.scene.image.Image;

public class Item extends GameZuulApplication  {
    String      name;
    Image       image;
    String      location;
    Integer[]   position;
    Boolean     acquired = false;


    public Item (String name, String imagePath, String location, Integer[] position){
        this.name = name;
        this.image = new Image(getClass().getResource(imagePath).toExternalForm());
        this.location = location;
        this.position = position;
    }
    public void setAcquired(){
        this.acquired = true;
    }
    public Boolean getAcquired(){
        return this.acquired;
    }
    public String getLocation(){
        return this.location;
    }
    public Integer getPosition(String axis){
        if (axis == "X"){
           return this.position[0];
        } else {
            return this.position[1];
        }
    }
    public Image getImage(){
        return image;
    }
    public String getName(){ return this.name; }
}
