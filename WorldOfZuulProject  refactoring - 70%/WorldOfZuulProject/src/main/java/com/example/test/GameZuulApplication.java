package com.example.test;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.IOException;
import java.util.ArrayList;

/*
TODO
- do player spawn location
- refactoring - 70%
    - FadeTransition
    - movementHandler
    - playerimage
- pickup items - inventroy
- dynamic chat bubbles 50%
- collision detection
    - add item
    - NPC
    - map collision
- title screen with name input -
- help box -
*/

public class GameZuulApplication extends Application {

    Scene scene;
    ImageView player;
    Group layout = new Group();
    BackgroundImage room01Background, room02Background, room03Background, room04Background, room05Background;
    String currentLocation = "room01";
    ArrayList<BackgroundImage> background = new ArrayList<BackgroundImage>();

    //make in to a arraylist
    //here we declared the different player image
    String playerUp = "/img/player_idle_up.png";
    String playerRight = "/img/player_idle_right.png";
    String playerDown = "/img/player_idle_down.png";
    String playerLeft = "/img/player_idle_left.png";
    Image playerUpImage = new Image(getClass().getResource(playerUp).toExternalForm());
    Image playerRightImage = new Image(getClass().getResource(playerRight).toExternalForm());
    Image playerDownImage = new Image(getClass().getResource(playerDown).toExternalForm());
    Image playerLeftImage = new Image(getClass().getResource(playerLeft).toExternalForm());

    //here we declared the different player animations
    String playerWalkUp = "/img/player_walk_up.gif";
    String playerWalkRight = "/img/player_walk_right.gif";
    String playerWalkDown = "/img/player_walk_down.gif";
    String playerWalkLeft = "/img/player_walk_left.gif";
    Image playerWalkUpImage = new Image(getClass().getResource(playerWalkUp).toExternalForm());
    Image playerWalkRightImage = new Image(getClass().getResource(playerWalkRight).toExternalForm());
    Image playerWalkDownImage = new Image(getClass().getResource(playerWalkDown).toExternalForm());
    Image playerWalkLeftImage = new Image(getClass().getResource(playerWalkLeft).toExternalForm());

    @Override
    public void start(Stage stage) throws IOException {

        // Backgrounds
        createBackground("/img/room1test.png");
        createBackground("/img/room02.jpg");
        createBackground("/img/room03.jpg");
        createBackground("/img/room04.jpg");
        createBackground("/img/room05.jpg");

        //answerBox
        //String message = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean et nisi at elit hendrerit luctus vel vel sapien. Nunc quis nisl egestas, bibendum neque vel, tristique tellus. Aenean non felis in mauris elementum hendrerit. Mauris et lacinia velit, nec blandit nulla. Aliquam erat volutpat. Proin sit amet justo fermentum orci egestas laoreet quis sed quam. Donec quis quam non nisi viverra hendrerit. Fusce in eros id libero pellentesque mattis. Curabitur quis efficitur lectus, vitae molestie sem. Quisque nec pharetra nulla. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae; Praesent vel leo sed nunc vehicula tempor. Aenean sed magna lectus. Curabitur tempus mauris congue, fermentum sem vel, molestie dui. Sed est mauris, euismod non semper id, blandit id nisl.";
        String message = "HELLO WORLD!";
        GridPane room01Container = chatHandler("message", message);
       /* GridPane room01Answer = new GridPane();
        room01Answer.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        room01Answer.setPrefSize(200, 200);
        room01Answer.setVgap(10);
        room01Answer.setHgap(10);
        room01Answer.relocate(680,1000);
        room01Answer.setPadding(new Insets(25,25,25,25));
        room01Answer.setAlignment(Pos.CENTER);
        room01Answer.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        room01Answer.setVisible(true);
        Label question = new Label("How many ghosts show W in A Christmas Carol?");
        Button answer01 = new Button("1");
        Button answer02 = new Button("2");
        Button answer03 = new Button("3");
        Button answer04 = new Button("4");
        room01Answer.add(question, 0,0);
        room01Answer.add(answer01,0,1);
        room01Answer.add(answer02,0,2);
        room01Answer.add(answer03,0,3);
        room01Answer.add(answer04,0,4);
        room01Answer.getChildren().addAll(question,answer01,answer02,answer03,answer04);
        VBox room01root = new VBox();
        room01root.getChildren().addAll(room01Container,room01Answer);*/

        //Circle: this is just a Circle for testing
        Circle circle = new Circle();
        circle.setCenterX(500);
        circle.setCenterY(300);
        circle.setRadius(10.0f);
        circle.setFill(Color.PINK);
        circle.setStrokeWidth(1);
        circle.setStroke(Color.BLACK);

        // Building scene
        scene = createScene(1365, 720);

        // KeyPress
        scene.setOnKeyPressed(e -> {
            movementHandler(e, player, scene, circle, room01Container);
        });
        scene.setOnKeyReleased(e -> {
            keyRelease(e, player);
        });
        stage.setTitle("World of Zuul");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
    public GridPane chatHandler (String type, String message)  {
        GridPane container = new GridPane();
        container.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(30), Insets.EMPTY)));
        /*container.setPrefSize(200, 200);*/
        container.relocate(0,0);
        container.setPadding(new Insets(25,25,25,25));
        container.setAlignment(Pos.CENTER);
        container.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, new CornerRadii(30), BorderWidths.DEFAULT)));
        container.setVisible(false);
        if (type == "message"){
            Label label = new Label(message);
            label.setWrapText(true);
            label.setTextAlignment(TextAlignment.JUSTIFY);
            label.setMaxWidth(350);
            container.getChildren().add(label);
        } else if (type == "question") {
            Label question = new Label("How many ghosts show W in A Christmas Carol?");
            Button answer01 = new Button("1");
            Button answer02 = new Button("2");
            Button answer03 = new Button("3");
            Button answer04 = new Button("4");
            container.add(question, 0,0);
            container.add(answer01,0,1);
            container.add(answer02,0,2);
            container.add(answer03,0,3);
            container.add(answer04,0,4);
        }
        return container;
    }
    public void createBackground(String imagePath){
        Image image = new Image(getClass().getResource(imagePath).toExternalForm());
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        background.add(backgroundImage);
    }
    public ImageView createPlayer(Integer size, Integer positionX, Integer positionY){
        ImageView player = new ImageView();
        player.setImage(playerDownImage);
        player.setFitWidth(size);
        player.setX(positionX);
        player.setY(positionY);
        player.setPreserveRatio(true);
        player.setSmooth(true);
        player.setCache(true);
        return player;
    }
    public void updateScene(BackgroundImage background, Integer playerStartX, Integer playerStartY){
        BorderPane room = (BorderPane) layout.getChildren().get(0);
        ImageView player = (ImageView) layout.getChildren().get(1);
        VBox overlay = (VBox) layout.getChildren().get(2);
        player.setX(playerStartX);
        player.setY(playerStartY);
        FadeTransition fadeOut = new FadeTransition(Duration.millis(1000), overlay);
        fadeOut.setFromValue(0.0);
        fadeOut.setToValue(1.0);
        fadeOut.setCycleCount(1);
        fadeOut.setAutoReverse(false);
        fadeOut.play();
        room.setBackground(new Background(background));
        FadeTransition fadeIn = new FadeTransition(Duration.millis(1000), overlay);
        fadeIn.setFromValue(1.0);
        fadeIn.setToValue(0.0);
        fadeIn.setCycleCount(1);
        fadeIn.setAutoReverse(false);
        fadeIn.play();
    }
    public Scene createScene(Integer width, Integer height) {
        BorderPane room = new BorderPane();
        room.setMinWidth(width);
        room.setMinHeight(height);
        room.setBackground(new Background(background.get(0)));
        VBox overlay = new VBox();
        overlay.setMinWidth(width);
        overlay.setMinHeight(height);
        overlay.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        //Group layout = new Group();
        player = createPlayer(50,300,300);
        layout.getChildren().addAll(room, player, overlay);
        Scene scene = new Scene(layout, width, height);
        FadeTransition ft = new FadeTransition(Duration.millis(1000), overlay);
        ft.setFromValue(1.0);
        ft.setToValue(0.0);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();
        return scene;
    }
    public void movementHandler (KeyEvent e, ImageView player, Scene scene, Circle circle, GridPane container) {
        Scene currentScene = ((Scene) e.getSource());
        Stage currentStage = (Stage) (currentScene).getWindow();
        Integer velocity = 10;
        switch (e.getCode()) {
            case S:
                player.setImage(playerWalkDownImage);
                if (player.getY() + player.getBoundsInLocal().getHeight() + velocity <= scene.getHeight()) {
                    player.setY(player.getY() + velocity);
                    if (player.getBoundsInParent().intersects(circle.getBoundsInParent())){
                        container.setVisible(true);
                        container.setTranslateX(player.getX()-(container.getWidth()/2)+25);
                        container.setTranslateY(player.getY()-container.getHeight());
                    } else {
                        container.setVisible(false);
                    }
                } else {
                    if (player.getX() >= 590 && player.getX() <= 690) { // NOTE && means that we want both conditions to be met. Extra NOTE || Means OR so we just want one of the conditions
                        if (currentLocation == "room01") {
                            updateScene(background.get(4), 300,300);
                            currentLocation = "room05";
                        } else if (currentLocation == "room03") {
                            updateScene(background.get(0), 660,0);
                            currentLocation = "room01";
                        }
                    }
                }
                break;
            case W:
                player.setImage(playerWalkUpImage);
                if (player.getY() - velocity >= 0) {
                    player.setY(player.getY() - velocity);
                    if (player.getBoundsInParent().intersects(circle.getBoundsInParent())){
                        container.setVisible(true);
                        container.setTranslateX(player.getX()-(container.getWidth()/2)+25);
                        container.setTranslateY(player.getY()-container.getHeight());
                    } else {
                        container.setVisible(false);
                    }
                } else {
                    if (currentLocation == "room01") {
                        updateScene(background.get(2), 300,300);
                        currentLocation = "room03";
                    } else if (currentLocation == "room05") {
                        updateScene(background.get(0), 300,300);
                        currentLocation = "room01";
                    }
                }
                break;
            case A:
                player.setImage(playerWalkLeftImage);
                if (player.getX() - velocity >= 0) {
                    player.setX(player.getX() - velocity);
                    if (player.getBoundsInParent().intersects(circle.getBoundsInParent())){
                        container.setVisible(true);
                        container.setTranslateX(player.getX()-(container.getWidth()/2)+25);
                        container.setTranslateY(player.getY()-container.getHeight());
                    } else {
                        container.setVisible(false);
                    }
                } else {
                    if (player.getY() >= 280 && player.getY() <= 440) { // høj /2 + 50% høj af spillen
                        if (currentLocation == "room01") {
                            updateScene(background.get(1), 300,300);
                            currentLocation = "room02";
                        } else if (currentLocation == "room04") {
                            updateScene(background.get(0), 300,300);
                            currentLocation = "room01";
                        }
                    }
                }
                break;
            case D:
                player.setImage(playerWalkRightImage);
                if (player.getX() + player.getBoundsInLocal().getWidth() + velocity <= scene.getWidth()) {
                    player.setX(player.getX() + velocity);
                    if (player.getBoundsInParent().intersects(circle.getBoundsInParent())){
                        container.setVisible(true);
                        container.setTranslateX(player.getX()-(container.getWidth()/2)+25);
                        container.setTranslateY(player.getY()-container.getHeight());
                    } else {
                        container.setVisible(false);
                    }
                } else {
                    if (currentLocation == "room01") {
                        updateScene(background.get(3), 300,300);
                        currentLocation = "room04";
                    } else if (currentLocation == "room02") {
                        updateScene(background.get(0), 300,300);
                        currentLocation = "room01";
                    }
                }
                break;
        }
    }
    public void keyRelease (KeyEvent e, ImageView player) {
        switch (e.getCode()) {
            case W:
                player.setImage(playerUpImage);
                break;
            case D:
                player.setImage(playerRightImage);
                break;
            case S:
                player.setImage(playerDownImage);
                break;
            case A:
                player.setImage(playerLeftImage);
                break;
        }
    }
    public static void main(String[] args) {
        launch();
    }
}