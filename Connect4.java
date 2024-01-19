package com.mycompany.connectfour;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.shape.Line;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.Screen;
import javafx.scene.shape.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;




/**
 * JavaFX App
 */
public class Connect4 extends Application {
    //below are some constants
    static final int columnNumber = 7;
    static final int rowNumber = 6;
    Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
    final double screenWidth = screenBounds.getWidth();
    final double screenHeight = screenBounds.getHeight();
    @Override
    public void start(Stage primaryStage) {
        ArrayList<Highlight> ghostPieces = new ArrayList<>();
        ArrayList<Line> verticalLines = new ArrayList<>();
        ArrayList<Line> horizontalLines = new ArrayList<>();
        
        for(int i = 0; i < columnNumber; i++) //initialize lines for board
        {
            Line temp = new Line();
            verticalLines.add(temp);
            if (columnNumber - 1 >= 1) //an attempt to be more efficient with initializing the board
            {
                Line temp2 = new Line();
                horizontalLines.add(temp2);
            }
            
        }
        for (int i = 0; i < verticalLines.size(); i++) //vertical lines
        {
            verticalLines.get(i).setStartX((screenHeight / 7) * (i + 1) + screenWidth/7); //change this to col and row num
            verticalLines.get(i).setEndX((screenHeight / 7) * (i + 1) + screenWidth/7); // maybe use a common variable for these two
            verticalLines.get(i).setStartY((screenHeight / 6));
            verticalLines.get(i).setEndY(screenHeight);
            verticalLines.get(i).setStrokeWidth(2.0);
        }
        for (int i = 0; i < horizontalLines.size(); i++)
        {
            horizontalLines.get(i).setStartX(screenBounds.getMinX());
            horizontalLines.get(i).setEndX(screenWidth);
            horizontalLines.get(i).setStartY((screenHeight / 6) * (i));
            horizontalLines.get(i).setEndY((screenHeight / 6) * (i));
            horizontalLines.get(i).setStrokeWidth(2.0);
        }
        for (int i = 0; i < columnNumber; i++) //initialize ghost pieces
        {
            //Highlight temp = new Highlight("Red", "PieceLocation","radius");
            //ghostPieces.add(temp);
        }
        //Grid grid = new Grid(coloumnNumber, rowNumber);
        
       // StackPane root = new StackPane();
        Group root = new Group();
        for (int i = 0; i < verticalLines.size(); i++)
        {
           root.getChildren().add(verticalLines.get(i)); 
        }
        for (int i = 0; i < horizontalLines.size(); i++)
        {
            root.getChildren().add(horizontalLines.get(i));
        }
        
        Scene scene = new Scene(root, 1000, 800);
        primaryStage.setScene(scene);
        primaryStage.setWidth(screenWidth);
        primaryStage.setHeight(screenHeight);
        primaryStage.setX(screenBounds.getMinX());
        primaryStage.setY(screenBounds.getMinY());
        primaryStage.setTitle("Primary Window");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
