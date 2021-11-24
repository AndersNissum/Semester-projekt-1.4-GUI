package com.example.test;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.Event;
import javafx.scene.Group;
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

public class HelloApplication extends Application {

    Scene scene01, scene02;

    @Override
    public void start(Stage stage) throws IOException {
        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));

        //room backgrounds
        String room01Path = "/img/room01.jpg";
        Image room01Image = new Image(getClass().getResource(room01Path).toExternalForm());
        BackgroundImage room01Background = new BackgroundImage(room01Image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);

        String room02Path = "/img/room02.jpg";
        Image room02Image = new Image(getClass().getResource(room02Path).toExternalForm());
        BackgroundImage room02Background = new BackgroundImage(room02Image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);

        //Player image
        String playerPath = "/img/Player.png";
        Image playerImage = new Image(getClass().getResource(playerPath).toExternalForm());
        ImageView room01Player = new ImageView();
        room01Player.setImage(playerImage);
        room01Player.setFitWidth(100);
        room01Player.setX(300);
        room01Player.setY(300);
        room01Player.setPreserveRatio(true);
        room01Player.setSmooth(true);
        room01Player.setCache(true);

        ImageView room02Player = new ImageView();
        room02Player.setImage(playerImage);
        room02Player.setFitWidth(100);
        room02Player.setX(1180);
        room02Player.setY(300);
        room02Player.setPreserveRatio(true);
        room02Player.setSmooth(true);
        room02Player.setCache(true);

        //Circle
        Circle circle = new Circle();
        circle.setCenterX(500);
        circle.setCenterY(300);
        circle.setRadius(10.0f);
        circle.setFill(Color.PINK);
        circle.setStrokeWidth(1);
        circle.setStroke(Color.BLACK);


        TranslateTransition translateTransition = new TranslateTransition();
        translateTransition.setDuration(Duration.millis(1000));
        translateTransition.setNode(circle);
        translateTransition.setByY(200);
        translateTransition.setByX(200);
        translateTransition.setCycleCount(1000);
        translateTransition.setAutoReverse(true);
        translateTransition.play();


        //Scenes
        //Scene 01
        BorderPane room01 = new BorderPane();
        room01.setMinWidth(1280);
        room01.setMinHeight(720);
        room01.setBackground(new Background(room01Background));
        Group layout01 = new Group();
        layout01.getChildren().addAll(room01, room01Player, circle);
        Scene scene01 = new Scene(layout01,1280,720);

        //Scene 02
        BorderPane room02 = new BorderPane();
        room02.setMinWidth(1280);
        room02.setMinHeight(720);
        room02.setBackground(new Background(room02Background));
        Group layout02 = new Group();
        layout02.getChildren().addAll(room02, room02Player);
        Scene scene02 = new Scene(layout02,1280,720);

        //Keypress
        scene01.setOnKeyPressed(e -> {
            String s = movementHandler(e, room01Player, scene01);
            if (s == "scene01") {
                stage.setScene(scene01);
            } else if (s == "scene02") {
                stage.setScene(scene02);
            }


            /*switch (e.getCode()){
                case DOWN:
                    if (layout01.getChildren().contains(circle)){
                        if (room01Player.getX()<=circle.getCenterX() && room01Player.getX()+100>=circle.getCenterX() && room01Player.getY()<=circle.getCenterY() && room01Player.getY()+100>=circle.getCenterY()){
                            layout01.getChildren().remove(circle);
                            System.out.println("pickUpCircle");
                        }
                    }
                    if (room01Player.getY()+room01Player.getBoundsInLocal().getHeight()+10 <= scene01.getHeight()) {
                        room01Player.setY(room01Player.getY()+10);
                    }
                    break;
                case UP:
                    if (layout01.getChildren().contains(circle)){
                        if (room01Player.getX()<=circle.getCenterX() && room01Player.getX()+100>=circle.getCenterX() && room01Player.getY()<=circle.getCenterY() && room01Player.getY()+100>=circle.getCenterY()){
                            layout01.getChildren().remove(circle);
                            System.out.println("pickUpCircle");
                        }
                    }
                    if (room01Player.getY()-10 >= 0) {
                        room01Player.setY(room01Player.getY()-10);
                    }
                    break;
                case LEFT:
                    if (layout01.getChildren().contains(circle)){
                        if (room01Player.getX()<=circle.getCenterX() && room01Player.getX()+100>=circle.getCenterX() && room01Player.getY()<=circle.getCenterY() && room01Player.getY()+100>=circle.getCenterY()){
                            layout01.getChildren().remove(circle);
                            System.out.println("pickUpCircle");
                        }
                    }
                    if (room01Player.getX()-10 >= 0) {
                        room01Player.setX(room01Player.getX()-10);
                    } else {
                        stage.setScene(scene02);
                    }
                    break;
                case RIGHT:
                    if (layout01.getChildren().contains(circle)){
                        if (room01Player.getX()<=circle.getCenterX() && room01Player.getX()+100>=circle.getCenterX() && room01Player.getY()<=circle.getCenterY() && room01Player.getY()+100>=circle.getCenterY()){
                            layout01.getChildren().remove(circle);
                            System.out.println("pickUpCircle");
                        }
                    }
                    if (room01Player.getX()+room01Player.getBoundsInLocal().getWidth()+10 <= scene01.getWidth()) {
                        room01Player.setX(room01Player.getX()+10);
                    }
                    break;
            }*/
        });
        scene02.setOnKeyPressed(e -> {
            String s = movementHandler(e, room02Player, scene02);
            if (s == "scene01") {
                stage.setScene(scene01);
            } else if (s == "scene02") {
                stage.setScene(scene02);
            }
           /* switch (e.getCode()){
                case DOWN:
                    if (room02Player.getY()+room02Player.getBoundsInLocal().getHeight()+10 <= scene02.getHeight()) {
                        room02Player.setY(room02Player.getY()+10);
                    }
                    break;
                case UP:
                    if (room02Player.getY()-10 >= 0) {
                        room02Player.setY(room02Player.getY()-10);
                    }
                    break;
                case LEFT:
                    if (room02Player.getX()-10 >= 0) {
                        room02Player.setX(room02Player.getX()-10);
                    }
                    break;
                case RIGHT:
                    if (room02Player.getX()+room02Player.getBoundsInLocal().getWidth()+10 <= scene02.getWidth()) {
                        room02Player.setX(room02Player.getX()+10);
                    } else {
                        stage.setScene(scene01);
                    }
                    break;
            }*/
        });
        stage.setTitle("TestForJavaFX");
        stage.setScene(scene01);
        stage.show();
    }
    public String movementHandler (KeyEvent e, ImageView player, Scene scene){
        switch (e.getCode()){
            case DOWN:
                if (player.getY()+player.getBoundsInLocal().getHeight()+10 <= scene.getHeight()) {
                    player.setY(player.getY()+10);
                }
                break;
            case UP:
                if (player.getY()-10 >= 0) {
                    player.setY(player.getY()-10);
                }
                break;
            case LEFT:
                if (player.getX()-10 >= 0) {
                    player.setX(player.getX()-10);
                } else {
                    return "scene02";
                    /*stage.setScene(scene02);*/
                }
                break;
            case RIGHT:
                if (player.getX()+player.getBoundsInLocal().getWidth()+10 <= scene.getWidth()) {
                    player.setX(player.getX()+10);
                } else {
                    return "scene01";
                    //stage.setScene(scene01);
                }
                break;
        }
        return "";
    }

    public static void main(String[] args) {
        launch();
    }
}