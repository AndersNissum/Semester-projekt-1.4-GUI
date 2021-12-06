package com.example.test;

import javafx.animation.FadeTransition;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.List;

public class Gui extends GameZuulApplication {
    Scene                       scene;
    Group                       layout = new Group();

    String[]                    backgroundPaths = {
                                    "/img/room1test.png",
                                    "/img/room02.jpg",
                                    "/img/room03.jpg",
                                    "/img/room04.jpg",
                                    "/img/room05.jpg"
                                };
    BorderPane                  background = new BorderPane();
    List<BackgroundImage>       backgrounds = new ArrayList<>();

    ImageView                   itemObject = new ImageView();
    ArrayList<Item>             items = new ArrayList<>();

    Player                      player = new Player("Anders");
    ImageView                   playerObject = new ImageView();
    Npc                         npc = new Npc("Hawk", "/img/player_idle_down.png", "/img/player-headshot.png", "room01", new Integer[] {300, 300},  new String[]{"message 1", "message 2"}, new String[]{"response 1", "response 2"});
    ImageView                   npcObject = new ImageView();

    Group                       chatBar = new Group();
    ImageView                   playerPortrait = new ImageView();
    ImageView                   npcPortrait = new ImageView();
    Label                       playerChatMessage = new Label();
    Label                       npcChatMessage = new Label();

    HBox                        inventory = new HBox();
    ArrayList<ImageView>        inventoryItems = new ArrayList<>();

    VBox                        overlay = new VBox();

    public void createBackgrounds(){
        for (int i = 0; i < backgroundPaths.length; i++) {
            backgrounds.add(new BackgroundImage(new Image(getClass().getResource(backgroundPaths[i]).toExternalForm()),
                    BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT));
        }
    }
    public void addPlayerObject(Integer size, Integer[] position){
        playerObject.setImage(player.getImage("IDLE", "DOWN"));
        playerObject.setFitWidth(size);
        playerObject.setX(position[0]);
        playerObject.setY(position[1]);
        playerObject.setPreserveRatio(true);
        playerObject.setSmooth(true);
        playerObject.setCache(true);
    }
    public void createScene(Integer width, Integer height) {
        createBackgrounds();

        items.add(new Item("Item 1", "/img/star.png", "room02", new Integer[]{680, 360}));
        items.add(new Item("Item 2", "/img/star.png", "room03", new Integer[]{680, 360}));
        items.add(new Item("Item 3", "/img/star.png", "room04", new Integer[]{680, 360}));
        items.add(new Item("Item 4", "/img/star.png", "room05", new Integer[]{680, 360}));

        background.setMinWidth(width);
        background.setMinHeight(height);
        background.setBackground(new Background(backgrounds.get(0)));

        overlay.setMinWidth(width);
        overlay.setMinHeight(height);
        overlay.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));

        itemObject.setImage(items.get(0).getImage());
        itemObject.setFitWidth(25);
        itemObject.setX(0);
        itemObject.setY(0);
        itemObject.setPreserveRatio(true);
        itemObject.setSmooth(true);
        itemObject.setCache(true);
        itemObject.setVisible(false);

        playerPortrait.setImage(player.getPortraitImage());
        playerPortrait.setX(0);
        playerPortrait.setY(570);
        playerPortrait.setFitWidth(200);
        playerPortrait.setPreserveRatio(true);

        playerChatMessage.setPrefWidth(300);
        playerChatMessage.setPrefHeight(200);
        playerChatMessage.setWrapText(true);
        playerChatMessage.setLayoutX(150);
        playerChatMessage.setLayoutY(300);
        playerChatMessage.setPadding(new Insets(20));
        playerChatMessage.setFont(new Font("Arial", 15));
        playerChatMessage.setTextAlignment(TextAlignment.CENTER);
        playerChatMessage.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(20), Insets.EMPTY)));
        playerChatMessage.setVisible(false);

        npcObject.setImage(npc.getImage());
        npcObject.setX(npc.getPosition("X"));
        npcObject.setY(npc.getPosition("Y"));
        npcObject.setFitWidth(50);
        npcObject.setPreserveRatio(true);
        npcObject.setSmooth(true);
        npcObject.setCache(true);

        npcPortrait.setImage(npc.getPortrait());
        npcPortrait.setX(1165);
        npcPortrait.setY(570);
        npcPortrait.setFitWidth(200);
        npcPortrait.setPreserveRatio(true);

        npcChatMessage.setPrefWidth(300);
        npcChatMessage.setPrefHeight(200);
        npcChatMessage.setWrapText(true);
        npcChatMessage.setLayoutX(915);
        npcChatMessage.setLayoutY(300);
        npcChatMessage.setPadding(new Insets(20));
        npcChatMessage.setFont(new Font("Arial", 15));
        npcChatMessage.setTextAlignment(TextAlignment.CENTER);
        npcChatMessage.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(20), Insets.EMPTY)));
        npcChatMessage.setVisible(false);

        chatBar.getChildren().addAll(playerPortrait, npcPortrait, playerChatMessage, npcChatMessage);
        chatBar.setVisible(false);

        inventory.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(20), Insets.EMPTY)));
        inventory.setTranslateX(50);
        inventory.setTranslateY(580);
        inventory.setPadding(new Insets(20));

        for (int i = 0; i < items.size(); i++) {
            inventoryItems.add(new ImageView());
            inventoryItems.get(i).setImage(items.get(i).getImage());
            inventoryItems.get(i).setFitWidth(50);
            inventoryItems.get(i).setOpacity(0.3);
            inventoryItems.get(i).setPreserveRatio(true);
            inventoryItems.get(i).setSmooth(true);
            inventoryItems.get(i).setCache(true);
            inventory.getChildren().add(inventoryItems.get(i));
        }

        addPlayerObject(50, new Integer[] {640, 360});

        layout.getChildren().addAll(background, itemObject, playerObject, npcObject, inventory, overlay, chatBar);

        scene = new javafx.scene.Scene(layout, width, height);
        fadeTransition("IN");
    }
    public void updateScene(String currentLocation, Integer[] position){
        itemObject.setX(0);
        itemObject.setY(0);
        itemObject.setVisible(false);
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getLocation() == currentLocation && items.get(i).getAcquired() == false){
                itemObject.setX(items.get(i).getPosition("X"));
                itemObject.setY(items.get(i).getPosition("Y"));
                itemObject.setVisible(true);
            }
        }
        playerObject.setX(position[0]);
        playerObject.setY(position[1]);
        fadeTransition("OUT");
        switch (currentLocation){
            case "room01":
                background.setBackground(new Background(backgrounds.get(0)));
                break;
            case "room02":
                background.setBackground(new Background(backgrounds.get(1)));
                break;
            case "room03":
                background.setBackground(new Background(backgrounds.get(2)));
                break;
            case "room04":
                background.setBackground(new Background(backgrounds.get(3)));
                break;
            case "room05":
                background.setBackground(new Background(backgrounds.get(4)));
                break;
        }
        fadeTransition("IN");
    }
    public void closeChat(){
        if (chatBar.isVisible()){
            if (playerChatMessage.isVisible() && playerChatMessage.getText() != "Thank you!") {
                playerChatMessage.setVisible(false);
                npcChatMessage.setText("Arn't you amazing!");
                npcChatMessage.setVisible(true);
            } else if (npcChatMessage.isVisible()) {
                npcChatMessage.setVisible(false);
                playerChatMessage.setText("Thank you!");
                playerChatMessage.setVisible(true);
            } else if (playerChatMessage.getText() == "Thank you!"){
                chatBar.setVisible(false);
                overlay.setOpacity(0);
            }
        }
    }
    public void fadeTransition(String type){
        FadeTransition ft = new FadeTransition(Duration.millis(1000), overlay);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        if (type == "OUT"){
            ft.setFromValue(0.0);
            ft.setToValue(1.0);
        } else {
            ft.setFromValue(1.0);
            ft.setToValue(0.0);
        }
        ft.play();
    }

}

