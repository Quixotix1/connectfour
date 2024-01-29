package com.mycompany.connect4;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.shape.Line;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.stage.Stage;
import javafx.stage.Screen;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;





/**
 * JavaFX App
 */
public class App extends Application {
    //below are some constants
    static final int COLUMN_NUMBER = 7;
    static final int ROW_NUMBER = 6;
    Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
    final double screenWidth = screenBounds.getWidth();
    final double screenHeight = screenBounds.getHeight();
    final double xOffset = (screenWidth / (COLUMN_NUMBER + 4)) * 2;
    final double yOffset = (screenHeight / (ROW_NUMBER + 2));
    final double spaceWidth = (screenWidth / (COLUMN_NUMBER + 4));
    final double spaceHeight = (screenHeight / (ROW_NUMBER + 2));
    final double boardWidth = screenWidth - xOffset * 2;
    final double boardHeight = screenHeight - yOffset * 2;
    boolean redTurn = true;

    Group root = new Group(); //these things must be global otherwise compiler gets mad
    Scene gameScene = null;
    
    EventHandler<MouseEvent> mouseClickHandler;
    
    @Override
    public void start(Stage primaryStage) {
        Scene startScene;
        
        
        
        Group startGroup = new Group();
        ArrayList<Line> verticalLines = new ArrayList<>();
        ArrayList<Line> horizontalLines = new ArrayList<>();
        
        //Initialize other classes
        Grid grid = new Grid(COLUMN_NUMBER, ROW_NUMBER, spaceWidth, spaceHeight, xOffset, yOffset, screenHeight);
        Highlight highlight = new Highlight(true, 50, boardWidth, boardHeight , grid);
        
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
        
        for(int i = 0; i < COLUMN_NUMBER + 1; i++) //initialize lines for board
        {
            verticalLines.add(new Line());
            if (i < COLUMN_NUMBER) 
            {
                horizontalLines.add(new Line());
            }   
        }
        
        initVerticalLines(verticalLines);
        initHorizontalLines(horizontalLines);

        //Root group setup
        for (int i = 0; i < verticalLines.size(); i++)
        {
           root.getChildren().add(verticalLines.get(i)); 
        }
        for (int i = 0; i < horizontalLines.size(); i++)
        {
            root.getChildren().add(horizontalLines.get(i));
        }
        Button restartButton = new Button("Restart");
        restartButton.setLayoutX((screenWidth/2) - 150);
        restartButton.setLayoutY(screenHeight/4*3);
        
         mouseClickHandler = new EventHandler<>() { 
         @Override 
         public void handle(MouseEvent e) {
            int index = findIndex(e.getX());
            try
            {
                root = grid.placePiece(index, redTurn, root);
                if(!grid.checkConnect4(index)) redTurn = highlight.setColour(); //not sure how inefficient the if statement is
                else
                {
                    gameScene.removeEventFilter(MouseEvent.MOUSE_PRESSED, mouseClickHandler); //this will prevent any more pieces from being placed
                }
            } catch(Error OverlapError)
            {
                System.out.println("That column is full.");
            }
            catch(Exception ArraryIndexOutOfBoundsException)
            {
                System.out.println("That move is out of bounds!"); //this doesn't catch perfectly accurately. This is a truncation issue; to be fixed
            }
         }
      };  

         

       
        
        startScene = new Scene(startGroup, screenWidth, screenHeight);
        startScene.setFill(Color.PINK);
        gameScene = new Scene(root, screenWidth, screenHeight);
        gameScene.setFill(Color.LIGHTGREY);
        gameScene.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseClickHandler);
        primaryStage.setScene(startScene);
        primaryStage.setWidth(screenWidth);
        primaryStage.setHeight(screenHeight);
        primaryStage.setX(screenBounds.getMinX());
        primaryStage.setY(screenBounds.getMinY());
        primaryStage.setTitle("Primary Window");
        primaryStage.show();

        //printGhostPieces
        highlight.getMousePos(gameScene, root, grid.getPiecesInColumn(), xOffset, yOffset); //should we change this method name? Kind of misleading
        
        startButton.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){
              startButton.setDefaultButton(false);
              primaryStage.setScene(gameScene);
            }
          });
        
        restartButton.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){
                restartButton.setDefaultButton(true);
                restart(grid);
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
            verticalLines.get(i).setStartX((screenWidth / (COLUMN_NUMBER + 4)) * (i + 2)); //change this to col and row num
            verticalLines.get(i).setEndX((screenWidth / (COLUMN_NUMBER + 4)) * (i + 2)); // maybe use a common variable for these two
            verticalLines.get(i).setStartY(screenHeight / (ROW_NUMBER + 2));
            verticalLines.get(i).setEndY(screenHeight / (ROW_NUMBER + 2) * (ROW_NUMBER + 1));
            verticalLines.get(i).setStrokeWidth(3.0);
        }
    }
    
    private void initHorizontalLines(ArrayList<Line> horizontalLines)
    {
        for (int i = 0; i < horizontalLines.size(); i++)
        {
            horizontalLines.get(i).setStartX((screenWidth / (COLUMN_NUMBER + 4)) * 2); 
            horizontalLines.get(i).setEndX(screenWidth / (COLUMN_NUMBER + 4) * (9));
            horizontalLines.get(i).setStartY((screenHeight / (ROW_NUMBER + 2)) * (i + 1));
            horizontalLines.get(i).setEndY((screenHeight / (ROW_NUMBER + 2)) * (i + 1));
            horizontalLines.get(i).setStrokeWidth(3.0);
        }
    }
    
    private int findIndex(double mouseX)
    {
         return (int) ((mouseX - (screenWidth / (COLUMN_NUMBER + 4) * 2)) / (screenWidth / (COLUMN_NUMBER + 4)));
    }
    
    private void restart(Grid grid)
    {
        grid.restart();
    }

}
