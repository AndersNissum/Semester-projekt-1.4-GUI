package com.example.test;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.Event;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.IOException;

/*TODO
- refactoring
- pick up items
- chat bubbles
- collision detection
- a way to interact with with the questions and NPC
- Find the final assets we need for the game */

public class GameZuulApplication extends Application {

    //Here we declared the different scenes. we do that so we can call then in the metdes later
    Scene scene01, scene02, scene03, scene04, scene05;

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
        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));

        //room backgrounds
        String room01Path = "/img/room01.jpg";
        Image room01Image = new Image(getClass().getResource(room01Path).toExternalForm());
        BackgroundImage room01Background = new BackgroundImage(room01Image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);// this is just the Properties

        String room02Path = "/img/room02.jpg";
        Image room02Image = new Image(getClass().getResource(room02Path).toExternalForm());
        BackgroundImage room02Background = new BackgroundImage(room02Image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);

        String room03Path = "/img/room03.jpg";
        Image room03Image = new Image(getClass().getResource(room03Path).toExternalForm());
        BackgroundImage room03Background = new BackgroundImage(room03Image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);

        String room04Path = "/img/room04.jpg";
        Image room04Image = new Image(getClass().getResource(room04Path).toExternalForm());
        BackgroundImage room04Background = new BackgroundImage(room04Image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);

        String room05Path = "/img/room05.jpg";
        Image room05Image = new Image(getClass().getResource(room05Path).toExternalForm());
        BackgroundImage room05Background = new BackgroundImage(room05Image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);

        //Player image
        ImageView room01Player = new ImageView();
        room01Player.setImage(playerDownImage);
        room01Player.setFitWidth(50);
        room01Player.setX(300);
        room01Player.setY(300);
        room01Player.setPreserveRatio(true); // here we preserve ratio of the image
        room01Player.setSmooth(true); // this is just the way to make the image looks smooth (it's not needed)
        room01Player.setCache(true); // stores the image in memory

        ImageView room02Player = new ImageView();
        room02Player.setImage(playerDownImage);
        room02Player.setFitWidth(50);
        room02Player.setX(1180);
        room02Player.setY(300);
        room02Player.setPreserveRatio(true);
        room02Player.setSmooth(true);
        room02Player.setCache(true);

        ImageView room03Player = new ImageView();
        room03Player.setImage(playerDownImage);
        room03Player.setFitWidth(50);
        room03Player.setX(640);
        room03Player.setY(600);
        room03Player.setPreserveRatio(true);
        room03Player.setSmooth(true);
        room03Player.setCache(true);

        ImageView room04Player = new ImageView();
        room04Player.setImage(playerDownImage);
        room04Player.setFitWidth(50);
        room04Player.setX(0);
        room04Player.setY(300);
        room04Player.setPreserveRatio(true);
        room04Player.setSmooth(true);
        room04Player.setCache(true);

        ImageView room05Player = new ImageView();
        room05Player.setImage(playerDownImage);
        room05Player.setFitWidth(50);
        room05Player.setX(640);
        room05Player.setY(0);
        room05Player.setPreserveRatio(true);
        room05Player.setSmooth(true);
        room05Player.setCache(true);

        //Circle: this is just a Circle for testing
        Circle circle = new Circle();
        circle.setCenterX(500);
        circle.setCenterY(300);
        circle.setRadius(10.0f);
        circle.setFill(Color.PINK);
        circle.setStrokeWidth(1);
        circle.setStroke(Color.BLACK);

        //Animation for the circle
        /*TranslateTransition translateTransition = new TranslateTransition();
        translateTransition.setDuration(Duration.millis(1000));
        translateTransition.setNode(circle);
        translateTransition.setByY(200);
        translateTransition.setByX(200);
        translateTransition.setCycleCount(1000);
        translateTransition.setAutoReverse(true);
        translateTransition.play();*/

        // We are using borderPane for scaling purposes even though we're not using scaling yet
        //Scene 01
        BorderPane room01 = new BorderPane();
        room01.setMinWidth(1280);
        room01.setMinHeight(720);
        room01.setBackground(new Background(room01Background));
        Group layout01 = new Group(); // here we make a group to make it easy adding elements to a scene
        layout01.getChildren().addAll(room01, room01Player, circle);
        scene01 = new Scene(layout01,1280,720);

        //Scene 02
        BorderPane room02 = new BorderPane();
        room02.setMinWidth(1280);
        room02.setMinHeight(720);
        room02.setBackground(new Background(room02Background));
        Group layout02 = new Group();
        layout02.getChildren().addAll(room02, room02Player);
        scene02 = new Scene(layout02,1280,720);

        //Scene 03
        BorderPane room03 = new BorderPane();
        room03.setMinWidth(1280);
        room03.setMinHeight(720);
        room03.setBackground(new Background(room03Background));
        Group layout03 = new Group();
        layout03.getChildren().addAll(room03, room03Player);
        scene03 = new Scene(layout03,1280,720);

        //Scene 04
        BorderPane room04 = new BorderPane();
        room04.setMinWidth(1280);
        room04.setMinHeight(720);
        room04.setBackground(new Background(room04Background));
        Group layout04 = new Group();
        layout04.getChildren().addAll(room04, room04Player);
        scene04 = new Scene(layout04,1280,720);

        //Scene 05
        BorderPane room05 = new BorderPane();
        room05.setMinWidth(1280);
        room05.setMinHeight(720);
        room05.setBackground(new Background(room05Background));
        Group layout05 = new Group();
        layout05.getChildren().addAll(room05, room05Player);
        scene05 = new Scene(layout05,1280,720);

        // Arrow functions vi har ikke se det før men det er super godt. https://www.youtube.com/watch?v=57zkzBE0g48&ab_channel=ProgrammingKnowledge
        // e = event
        //KeyPress for Scene 01
        scene01.setOnKeyPressed(e -> { movementHandler(e, room01Player, scene01); });
        scene01.setOnKeyReleased(e -> {
            switch (e.getCode()) {
                case UP:
                    room01Player.setImage(playerUpImage);
                    break;
                case RIGHT:
                    room01Player.setImage(playerRightImage);
                    break;
                case DOWN:
                    room01Player.setImage(playerDownImage);
                    break;
                case LEFT:
                    room01Player.setImage(playerLeftImage);
                    break;
            }
        });

        //KeyPress for Scene 02
        scene02.setOnKeyPressed(e -> { movementHandler(e, room02Player, scene02); });
        scene02.setOnKeyReleased(e -> {
            switch (e.getCode()) {
                case UP:
                    room02Player.setImage(playerUpImage);
                    break;
                case RIGHT:
                    room02Player.setImage(playerRightImage);
                    break;
                case DOWN:
                    room02Player.setImage(playerDownImage);
                    break;
                case LEFT:
                    room02Player.setImage(playerLeftImage);
                    break;
            }
        });
        //KeyPress for Scene 03
        scene03.setOnKeyPressed(e -> { movementHandler(e, room03Player, scene03); });
        scene03.setOnKeyReleased(e -> {
            switch (e.getCode()) {
                case UP:
                    room03Player.setImage(playerUpImage);
                    break;
                case RIGHT:
                    room03Player.setImage(playerRightImage);
                    break;
                case DOWN:
                    room03Player.setImage(playerDownImage);
                    break;
                case LEFT:
                    room03Player.setImage(playerLeftImage);
                    break;
            }
        });
        //KeyPress for Scene 04
        scene04.setOnKeyPressed(e -> { movementHandler(e, room04Player, scene04); });
        scene04.setOnKeyReleased(e -> {
            switch (e.getCode()) {
                case UP:
                    room04Player.setImage(playerUpImage);
                    break;
                case RIGHT:
                    room04Player.setImage(playerRightImage);
                    break;
                case DOWN:
                    room04Player.setImage(playerDownImage);
                    break;
                case LEFT:
                    room04Player.setImage(playerLeftImage);
                    break;
            }
        });
        //KeyPress for Scene 05
        scene05.setOnKeyPressed(e -> { movementHandler(e, room05Player, scene05); });
        scene05.setOnKeyReleased(e -> {
            switch (e.getCode()) {
                case UP:
                    room05Player.setImage(playerUpImage);
                    break;
                case RIGHT:
                    room05Player.setImage(playerRightImage);
                    break;
                case DOWN:
                    room05Player.setImage(playerDownImage);
                    break;
                case LEFT:
                    room05Player.setImage(playerLeftImage);
                    break;
            }
        });


        stage.setTitle("World of Zuul");
        stage.setScene(scene01);
        stage.show();
    }
    //this is the metode for movementHandler.
    public void movementHandler (KeyEvent e, ImageView player, Scene scene){
        Scene currentScene = ((Scene)e.getSource()); // here we get the scene by casting the response event as scene
        Stage currentStage = (Stage)(currentScene).getWindow(); //  here we are casting the response as a stage
        switch (e.getCode()){ // get key code from the event handler
            case DOWN:
                player.setImage(playerWalkDownImage);
                if (player.getY()+player.getBoundsInLocal().getHeight()+10 <= scene.getHeight()) { //here we get the position of the player + the height + how far we're going to move player and test against the bottom of the screen
                    player.setY(player.getY()+10); // If I'm not going to hit the bottom of the screen please move me
                } else {
                    if (player.getX() >= 590 && player.getX() <= 690){ // NOTE && means that we want both conditions to be met. Extra NOTE || Means OR so we just want one of the conditions
                        if (currentScene == scene01){
                            currentStage.setScene(scene05);
                        } else if (currentScene == scene03){
                            currentStage.setScene(scene01);
                        }
                    }
                }
                break;
            case UP:
                player.setImage(playerWalkUpImage);
                if (player.getY()-10 >= 0) {
                    player.setY(player.getY()-10);
                } else {
                    if (currentScene == scene01){
                        currentStage.setScene(scene03);
                    } else if (currentScene == scene05){
                        currentStage.setScene(scene01);
                    }
                }
                break;
            case LEFT:
                player.setImage(playerWalkLeftImage);
                if (player.getX()-10 >= 0) {
                    player.setX(player.getX()-10);
                } else {
                    if (player.getY() >= 280 && player.getY() <= 440){ // høj /2 + 50% høj af spillen
                        if (currentScene == scene01){
                            currentStage.setScene(scene02);
                        } else if (currentScene == scene04){
                            currentStage.setScene(scene01);
                        }
                    }
                }
                break;
            case RIGHT:
                player.setImage(playerWalkRightImage);
                if (player.getX()+player.getBoundsInLocal().getWidth()+10 <= scene.getWidth()) {
                    player.setX(player.getX()+10);
                } else {
                    if (currentScene == scene01){
                        currentStage.setScene(scene04);
                    } else if (currentScene == scene02){
                        currentStage.setScene(scene01);
                    }
                }
                break;
        }
    }
    public static void main(String[] args) {
        launch();
    }
}