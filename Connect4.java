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
import javafx.geometry.Insets;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;





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
        Scene startScene;
        Scene gameScene;
        Group root = new Group();
        Group startGroup = new Group();
        ArrayList<Highlight> ghostPieces = new ArrayList<>();
        ArrayList<Line> verticalLines = new ArrayList<>();
        ArrayList<Line> horizontalLines = new ArrayList<>();
        
        //Starting group setup
        Label titleLabel = new Label("Connect 4");
        titleLabel.setFont(new Font("Arial", 350));
        //titleLabel.setBackground(new Background(new BackgroundFill(Color.PINK, CornerRadii.EMPTY, Insets.EMPTY)));
        Button startButton = new Button("Start");
        startButton.setDefaultButton(true);
        startButton.setLayoutX((screenWidth/2) - 150);
        startButton.setLayoutY((screenHeight / 3 * 2) - 100);
        startButton.setPrefSize(300, 100);
        startButton.setFont(new Font("Arial", 60));
        
        startGroup.getChildren().add(titleLabel);
        startGroup.getChildren().add(startButton);
        
        for(int i = 0; i < columnNumber + 1; i++) //initialize lines for board
        {
            verticalLines.add(new Line());
            if (i < columnNumber) //an attempt to be more efficient with initializing the board
            {
                horizontalLines.add(new Line());
            }   
        }
        
        initVerticalLines(verticalLines);
        initHorizontalLines(horizontalLines);

        for (int i = 0; i < verticalLines.size(); i++)
        {
           root.getChildren().add(verticalLines.get(i)); 
        }
        for (int i = 0; i < horizontalLines.size(); i++)
        {
            root.getChildren().add(horizontalLines.get(i));
        }
        
        
        
        startScene = new Scene(startGroup, screenWidth, screenHeight);
        startScene.setFill(Color.PINK);
        gameScene = new Scene(root, screenWidth, screenHeight);
        primaryStage.setScene(startScene);
        primaryStage.setWidth(screenWidth);
        primaryStage.setHeight(screenHeight);
        primaryStage.setX(screenBounds.getMinX());
        primaryStage.setY(screenBounds.getMinY());
        primaryStage.setTitle("Primary Window");
        primaryStage.show();
        
        startButton.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){
              primaryStage.setScene(gameScene);
            }
          });
    }

    public static void main(String[] args) {
        launch();
  
    }
    
    private void initVerticalLines(ArrayList<Line> verticalLines)
    {
        for (int i = 0; i < verticalLines.size(); i++) //vertical lines
        {
            verticalLines.get(i).setStartX((screenWidth / (columnNumber + 2)) * (i + 1)); //change this to col and row num
            verticalLines.get(i).setEndX((screenWidth / (columnNumber + 2)) * (i + 1)); // maybe use a common variable for these two
            verticalLines.get(i).setStartY(screenHeight / (rowNumber + 2));
            verticalLines.get(i).setEndY(screenHeight / (rowNumber + 2) * (rowNumber + 1));
            verticalLines.get(i).setStrokeWidth(2.0);
        }
    }
    
    private void initHorizontalLines(ArrayList<Line> horizontalLines)
    {
        for (int i = 0; i < horizontalLines.size(); i++)
        {
            horizontalLines.get(i).setStartX(screenWidth / (columnNumber + 2));
            horizontalLines.get(i).setEndX(screenWidth / (columnNumber + 2) * (columnNumber + 1));
            horizontalLines.get(i).setStartY((screenHeight / (rowNumber + 2)) * (i + 1));
            horizontalLines.get(i).setEndY((screenHeight / (rowNumber + 2)) * (i + 1));
            horizontalLines.get(i).setStrokeWidth(2.0);
        }
    }

}
