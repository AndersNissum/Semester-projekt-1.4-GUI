package com.example.test;

import javafx.application.Application;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import java.io.IOException;

/*
TODO
- need to npcs for all room at test if all the quisten work
- get the final asstes
- help box = reed me or text in the start of the game.
- end for the game - do in the same way we do intro. make a big loop for all interactrons or do pointsystem.
- set in the collision detection from mikkel

Note to reprot
Needed to change chat bubbles to portraits would text instead because I couldn't dynamically get the chat bubbles width and height after changing the text within them to work properly it would constantly The height and width aren't rendered before the content on the screen
*/

public class GameZuulApplication extends Application {

    String currentLocation = "room01";
    Gui gui;

    @Override
    public void start(Stage stage) throws IOException {
        gui = new Gui();
        gui.createScene(1365,720);
        gui.scene.setOnKeyPressed(e -> { movementHandler(e); });
        gui.scene.setOnKeyReleased(e -> { keyRelease(e); });
        gui.scene.setOnMouseClicked(e -> { gui.closeChat(); });
        stage.setTitle("World of Zuul");
        stage.setResizable(false);
        stage.setScene(gui.scene);
        stage.show();
    }
    public void movementHandler (KeyEvent e) {
        Integer velocity = 10;
        if (!gui.chatBar.isVisible()){
            switch (e.getCode()) {
                case W:
                    gui.playerObject.setImage(gui.player.getImage("ANIMATED","UP"));
                    if(gui.playerObject.getY() - velocity >= 0) {
                        gui.playerObject.setY(gui.playerObject.getY() - velocity);
                        if (gui.playerObject.getBoundsInParent().intersects(gui.itemObject.getBoundsInParent()) && gui.itemObject.isVisible()){
                            gui.itemObject.setVisible(false);
                            for (int i = 0; i < gui.items.size(); i++) {
                                if (gui.items.get(i).getLocation() == currentLocation){
                                    gui.items.get(i).setAcquired();
                                    gui.inventory.getChildren().get(i).setOpacity(1);
                                    gui.overlay.setOpacity(0.75);
                                    gui.playerChatMessage.setText("I picked up " + gui.items.get(i).getName());
                                    gui.playerChatMessage.setVisible(true);
                                    gui.chatBar.setVisible(true);
                                }
                            }
                        }
                        if (gui.playerObject.getBoundsInParent().intersects(gui.npcObject.getBoundsInParent()) && gui.npcObject.isVisible()){
                            gui.answerBox.setVisible(false); // add for at se om vi kunne få true grøn værk
                            for (int i = 0; i < gui.npcs.size(); i++) {
                                if (gui.npcs.get(i).getLocation() == currentLocation && gui.npcs.get(i).getState() == 0){
                                    gui.overlay.setOpacity(0.75);
                                    gui.playerChatMessage.setText(gui.npcs.get(i).getConversations(gui.player.getPlayerLanguage())[0]);
                                    gui.npcs.get(i).incrementState();
                                    gui.playerChatMessage.setVisible(true);
                                    gui.chatBar.setVisible(true);
                                }
                            }
                        }
                    } else {
                        if (gui.playerObject.getX() >= 590 && gui.playerObject.getX() <= 690) {
                            if (currentLocation == "room01") {
                                currentLocation = "room03";
                                gui.updateScene(currentLocation, new Integer[]{660, 640});
                            } else if (currentLocation == "room05") {
                                currentLocation = "room01";
                                gui.updateScene(currentLocation, new Integer[]{660, 640});
                            }
                        }
                    }
                    break;
                case D:
                    gui.playerObject.setImage(gui.player.getImage("ANIMATED","RIGHT"));
                    if (gui.playerObject.getX() + gui.playerObject.getBoundsInLocal().getWidth() + velocity <= gui.scene.getWidth()) {
                        gui.playerObject.setX(gui.playerObject.getX() + velocity);
                        if (gui.playerObject.getBoundsInParent().intersects(gui.itemObject.getBoundsInParent()) && gui.itemObject.isVisible()){
                            gui.itemObject.setVisible(false);
                            for (int i = 0; i < gui.items.size(); i++) {
                                if (gui.items.get(i).getLocation() == currentLocation){
                                    gui.items.get(i).setAcquired();
                                    gui.inventory.getChildren().get(i).setOpacity(1);
                                    gui.overlay.setOpacity(0.75);
                                    gui.playerChatMessage.setText("I picked up " + gui.items.get(i).getName());
                                    gui.playerChatMessage.setVisible(true);
                                    gui.chatBar.setVisible(true);
                                }
                            }
                        }
                    } else {
                        if (gui.playerObject.getY() >= 280 && gui.playerObject.getY() <= 440) {
                            if (currentLocation == "room01") {
                                currentLocation = "room04";
                                gui.updateScene(currentLocation, new Integer[]{0, 310});
                            } else if (currentLocation == "room02") {
                                currentLocation = "room01";
                                gui.updateScene(currentLocation, new Integer[]{0, 310});
                            }
                        }
                    }
                    break;
                case S:
                    gui.playerObject.setImage(gui.player.getImage("ANIMATED","DOWN"));
                    if (gui.playerObject.getY() + gui.playerObject.getBoundsInLocal().getHeight() + velocity <= gui.scene.getHeight()) {
                        gui.playerObject.setY(gui.playerObject.getY() + velocity);
                        if (gui.playerObject.getBoundsInParent().intersects(gui.itemObject.getBoundsInParent()) && gui.itemObject.isVisible()){
                            gui.itemObject.setVisible(false);
                            for (int i = 0; i < gui.items.size(); i++) {
                                if (gui.items.get(i).getLocation() == currentLocation){
                                    gui.items.get(i).setAcquired();
                                    gui.inventory.getChildren().get(i).setOpacity(1);
                                    gui.overlay.setOpacity(0.75);
                                    gui.playerChatMessage.setText("I picked up " + gui.items.get(i).getName());
                                    gui.playerChatMessage.setVisible(true);
                                    gui.chatBar.setVisible(true);
                                }
                            }
                        }
                    } else {
                        if (gui.playerObject.getX() >= 590 && gui.playerObject.getX() <= 690) { // NOTE && means that we want both conditions to be met. Extra NOTE || Means OR so we just want one of the conditions
                            if (currentLocation == "room01") {
                                currentLocation = "room05";
                                gui.updateScene(currentLocation, new Integer[]{670, 0});
                            } else if (currentLocation == "room03") {
                                currentLocation = "room01";
                                gui.updateScene(currentLocation, new Integer[]{670, 0});
                            }
                        }
                    }
                    break;
                case A:
                    gui.playerObject.setImage(gui.player.getImage("ANIMATED","LEFT"));
                    if (gui.playerObject.getX() - velocity >= 0) {
                        gui.playerObject.setX(gui.playerObject.getX() - velocity);
                        if (gui.playerObject.getBoundsInParent().intersects(gui.itemObject.getBoundsInParent()) && gui.itemObject.isVisible()){
                            gui.itemObject.setVisible(false);
                            for (int i = 0; i < gui.items.size(); i++) {
                                if (gui.items.get(i).getLocation() == currentLocation){
                                    gui.items.get(i).setAcquired();
                                    gui.inventory.getChildren().get(i).setOpacity(1);
                                    gui.overlay.setOpacity(0.75);
                                    gui.playerChatMessage.setText("I picked up " + gui.items.get(i).getName());
                                    gui.playerChatMessage.setVisible(true);
                                    gui.chatBar.setVisible(true);
                                }
                            }
                        }
                    } else {
                        if (gui.playerObject.getY() >= 280 && gui.playerObject.getY() <= 440) { // høj /2 + 50% høj af spillen
                            if (currentLocation == "room01") {
                                currentLocation = "room02";
                                gui.updateScene(currentLocation, new Integer[]{1320, 310});
                            } else if (currentLocation == "room04") {
                                currentLocation = "room01";
                                gui.updateScene(currentLocation, new Integer[]{1320, 310});
                            }
                        }
                    }
                    break;
            }
        }
    }
    public void keyRelease (KeyEvent e) {
        switch (e.getCode()) {
            case W:
                gui.playerObject.setImage(gui.player.getImage("IDLE","OP"));
                break;
            case D:
                gui.playerObject.setImage(gui.player.getImage("IDLE","RIGHT"));
                break;
            case S:
                gui.playerObject.setImage(gui.player.getImage("IDLE","DOWN"));
                break;
            case A:
                gui.playerObject.setImage(gui.player.getImage("IDLE","LEFT"));
                break;
        }
    }
    public static void main(String[] args) {
        launch();
    }
}