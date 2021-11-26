package com.example.test;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;


public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML
    private Button helloButton;

    @FXML
    private ImageView myAvatar;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
   /* scene.setOnKeyPressed(new EventHandler<KeyEvent>()) {
        @Override
        public void handle(KeyEvent event) {
            if (event.getCode() == KeyCode.UP || event.getCode() == KeyCode.DOWN || event.getCode() == KeyCode.LEFT || event.getCode() == KeyCode.RIGHT){
                moveImage(1,1);
            }
        }
    } */
    /*public void handle(KeyEvent event) {
        double x = 0;
        double y = 0;
        final int velocity = 50;
        switch (event.getCode()) {
            case UP -> y = -velocity;
            case DOWN -> y = velocity;
            case RIGHT -> x = velocity;
            case LEFT -> x = -velocity;
        }

        moveImage(x, y);
        event.consume();
    }*/

    private void moveImage(double x, double y) {
        myAvatar.relocate(myAvatar.getLayoutX() + x,myAvatar.getLayoutY() + y);
    }
}