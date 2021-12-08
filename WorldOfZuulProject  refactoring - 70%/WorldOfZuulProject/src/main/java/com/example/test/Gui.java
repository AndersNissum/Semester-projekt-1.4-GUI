package com.example.test;

import javafx.animation.FadeTransition;
import javafx.event.EventHandler;
import javafx.event.EventTarget;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
//create on the npc a integer showAnswer also make a arrey[] for answers. inside the gui we need to make vbox add 4 label.
// when npcState = showanswer setvisble to answer vbox
// if answerCorret border gree and border red if not corret

public class Gui extends GameZuulApplication {
    Scene                       scene;
    Group                       layout = new Group();

    String[]                    backgroundPaths = {
                                    "/img/room_Centerrum.png",
                                    "/img/room_West.png",
                                    "/img/room_Nord.png",
                                    "/img/room_East.png",
                                    "/img/room_Syd.png"
                                };
    BorderPane                  background = new BorderPane();
    List<BackgroundImage>       backgrounds = new ArrayList<>();

    ImageView                   itemObject = new ImageView();
    ArrayList<Item>             items = new ArrayList<>();

    Player                      player = new Player("Anders");
    ImageView                   playerObject = new ImageView();
    ImageView                   npcObject = new ImageView();
    ArrayList<Npc>              npcs = new ArrayList<>();

    Group                       chatBar = new Group();
    ImageView                   playerPortrait = new ImageView();
    ImageView                   npcPortrait = new ImageView();
    Label                       playerChatMessage = new Label();
    Label                       npcChatMessage = new Label();
    VBox                        answerBox = new VBox();
    ArrayList<Label>            answers = new ArrayList<>();

    HBox                        inventory = new HBox();
    ArrayList<ImageView>        inventoryItems = new ArrayList<>();

    Group                       intro = new Group();

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
    public void startGame(VBox introContainer, TextField playerName){
        if (playerName.getText() != "Enter name" || playerName.getText() != ""){
            player.setName(playerName.getText());
            introContainer.setOpacity(0.0);
            layout.getChildren().remove(intro);
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setContentText("Please enter a name");
        }
    }
    public void setLanguage(String language, Label oldLanguage, Label newLanguage){
        if (player.getPlayerLanguage() != language){
            player.setPlayerLanguage(language);
            oldLanguage.setBorder(null);
            newLanguage.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        }
    }
    public void createScene(Integer width, Integer height) {
        createBackgrounds();
        VBox introContainer = new VBox();
        introContainer.setMinWidth(1365);
        introContainer.setMinHeight(720);
        introContainer.setSpacing(20);
        introContainer.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        introContainer.setAlignment(Pos.CENTER);
        Label gameTitle = new Label();
        gameTitle.setText("Save the world");
        gameTitle.setFont(new Font("Arial",32));
        Label credits = new Label();
        credits.setText("A game by Mikkel, Jonas, Christopher, Zack, Anders");
        credits.setPadding(new Insets(20));
        final TextField playerName = new TextField();
        playerName.setMaxWidth(150);
        playerName.setPromptText("Enter your name");
        playerName.setFocusTraversable(false);
        Button start = new Button();
        start.setText("Start");
        start.setOnMouseClicked(e -> { startGame(introContainer,playerName); });
        HBox language = new HBox();
        language.setAlignment(Pos.CENTER);
        Label DK = new Label();
        DK.setPadding(new Insets(10));
        DK.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        DK.setText("DK");
        Label EN = new Label();
        EN.setPadding(new Insets(10));
        EN.setText("EN");
        DK.setOnMouseClicked(e -> { setLanguage("DK", EN, DK); });
        DK.setOnMouseEntered(e -> { DK.setCursor(Cursor.HAND); });
        DK.setOnMouseExited(e -> { DK.setCursor(Cursor.DEFAULT); });
        EN.setOnMouseClicked(e -> { setLanguage("EN", DK, EN); });
        EN.setOnMouseEntered(e -> { EN.setCursor(Cursor.HAND); });
        EN.setOnMouseExited(e -> { EN.setCursor(Cursor.DEFAULT); });
        language.getChildren().addAll(DK,EN);
        introContainer.getChildren().addAll(gameTitle, credits, playerName, start, language);
        intro.getChildren().addAll(introContainer);

        answerBox.setPrefWidth(300);
        answerBox.setLayoutX(533);
        answerBox.setLayoutY(380);
        answerBox.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        answerBox.setVisible(false);

        npcs.add(new Npc("Hawk", "/img/player_idle_down.png", "/img/player-headshot.png", "room01", new Integer[] {300, 300}, new String[]{"message 1", "message 2"}, new String[]{"hvordan har du det i dag?", "fint hvad med dig?","aaaaaaaaaaaa"},2, new String[][]{{"aaaaaaaa","true"},{"bbbbbbbb","false"},{"ccccccc","false"},{"dddddddd","false"}}, new String[][]{{"Returnere og opsige hans konto på nemlig.com, og få ham til at handle ind 2 gange om ugen.","green|true"},{"Lave hele hans kælder om til en fryser, så han kan opbevare hans mad i fryseren.","red|false"},{"Alfred skal finde sig 20 roomies, så han kan få spist hans mad","orange|maybe"},{"Alfred spiser alt maden inden det bliver for gammelt, og behøver derfor ikke spise noget i et år","red|false"}}));
        npcs.add(new Npc("Hawk", "/img/player_idle_down.png", "/img/player-headshot.png", "room02", new Integer[] {300, 300}, new String[]{"message 1", "message 2"}, new String[]{"hvordan har du det i dag?", "fint hvad med dig?","aaaaaaaaaaaa"},2, new String[][]{{"a","true"},{"b","false"},{"c","false"},{"d","false"}}, new String[][]{{"a","true"},{"b","false"},{"c","false"},{"d","false"}}));
        npcs.add(new Npc("Hawk", "/img/player_idle_down.png", "/img/player-headshot.png", "room03", new Integer[] {300, 300}, new String[]{"message 1", "message 2"}, new String[]{"hvordan har du det i dag?", "fint hvad med dig?","aaaaaaaaaaaa"},2, new String[][]{{"a","true"},{"b","false"},{"c","false"},{"d","false"}}, new String[][]{{"a","true"},{"b","false"},{"c","false"},{"d","false"}}));
        npcs.add(new Npc("Hawk", "/img/player_idle_down.png", "/img/player-headshot.png", "room04", new Integer[] {300, 300}, new String[]{"message 1", "message 2"}, new String[]{"hvordan har du det i dag?", "fint hvad med dig?","aaaaaaaaaaaa"},2, new String[][]{{"a","true"},{"b","false"},{"c","false"},{"d","false"}}, new String[][]{{"a","true"},{"b","false"},{"c","false"},{"d","false"}}));

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

        npcObject.setImage(npcs.get(0).getImage());
        npcObject.setX(npcs.get(0).getPosition("X"));
        npcObject.setY(npcs.get(0).getPosition("Y"));
        npcObject.setFitWidth(50);
        npcObject.setPreserveRatio(true);
        npcObject.setSmooth(true);
        npcObject.setCache(true);

        npcPortrait.setImage(npcs.get(0).getPortrait());
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

        chatBar.getChildren().addAll(playerPortrait, npcPortrait, playerChatMessage, npcChatMessage, answerBox);
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

        layout.getChildren().addAll(background, itemObject, playerObject, npcObject, inventory, overlay, chatBar, intro);

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
                //du kan sætte npc ind her
        }
        fadeTransition("IN");
    }
    public void closeChat(){
        if (chatBar.isVisible()){
            for (int i = 0; i < npcs.size(); i++) {
                if (npcs.get(i).getLocation() == currentLocation){
                    if(npcs.get(i).getState() < npcs.get(i).conversationsLength(player.getPlayerLanguage())){
                        if(npcs.get(i).getState() % 2 != 0){
                            playerChatMessage.setVisible(false);
                            npcChatMessage.setText(npcs.get(i).getConversations(player.getPlayerLanguage())[npcs.get(i).getState()]);
                            npcChatMessage.setVisible(true);
                            npcs.get(i).incrementState();
                            if (npcs.get(i).getState() == npcs.get(i).getShowAnswer() && !npcs.get(i).getInteraction()){
                                answerBox.getChildren().clear();
                                answerBox.setBorder(null);
                                for (int j = 0; j < npcs.get(i).getAnswers(player.getPlayerLanguage()).length; j++) {
                                    Npc currentNpc = npcs.get(i);
                                    String answer = currentNpc.getAnswers(player.getPlayerLanguage())[j][0];
                                    String[] response = currentNpc.getAnswers(player.getPlayerLanguage())[j][1].split("\\|");
                                    answers.add(new Label(answer));
                                    answers.get(j).setPadding(new Insets(10));
                                    answers.get(j).setPrefWidth(280);
                                    answers.get(j).setWrapText(true);
                                    answers.get(j).setOnMouseClicked(e -> {
                                        answerBox.getChildren().clear();
                                        Label label = new Label();
                                        label.setText(response[1]);
                                        label.setPadding(new Insets(10));
                                        label.setPrefWidth(280);
                                        label.setWrapText(true);
                                        System.out.println(response[0]);
                                        if (response[0].equals("green")){
                                            answerBox.setBorder(new Border(new BorderStroke(Color.GREEN, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(10))));
                                        } else if (response[0].equals("orange")){
                                            answerBox.setBorder(new Border(new BorderStroke(Color.ORANGE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(10))));
                                        } else {
                                            answerBox.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(10))));
                                        }
                                        answerBox.getChildren().add(label);
                                        currentNpc.toggleInteraction();
                                        System.out.println(answer);
                                    });
                                    answerBox.getChildren().add(answers.get(j));
                                }
                                answerBox.setVisible(true);
                                /*answer1.setPadding(new Insets(10));
                                answer1.setPrefWidth(280);
                                answer2.setPadding(new Insets(10));
                                answer2.setPrefWidth(280);
                                answer3.setPadding(new Insets(10));
                                answer3.setPrefWidth(280);
                                answer4.setPadding(new Insets(10));
                                answer4.setPrefWidth(280);
                                answerBox.getChildren().addAll(answer1,answer2,answer3,answer4);
                                answer1.setText(npcs.get(i).getAnswers(player.getPlayerLanguage())[0][0]);
                                answer2.setText(npcs.get(i).getAnswers(player.getPlayerLanguage())[1][0]);
                                answer3.setText(npcs.get(i).getAnswers(player.getPlayerLanguage())[2][0]);
                                answer4.setText(npcs.get(i).getAnswers(player.getPlayerLanguage())[3][0]);
                                answer1.setOnMouseEntered(e -> {
                                    answer1.setCursor(Cursor.HAND);
                                });
                                answer1.setOnMouseClicked(e -> {
                                    System.out.println("click nummer 1");
                                });*/
                            }
                        } else {
                            npcChatMessage.setVisible(false);
                            playerChatMessage.setText(npcs.get(i).getConversations(player.getPlayerLanguage())[npcs.get(i).getState()]);
                            playerChatMessage.setVisible(true);
                            npcs.get(i).incrementState();
                        }
                    } else {
                        chatBar.setVisible(false);
                        overlay.setOpacity(0);
                    }
                }
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