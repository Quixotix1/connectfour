/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.connect4;

import javafx.application.Application; 
import javafx.scene.Group; 
import javafx.scene.Scene; 
import javafx.stage.Stage; 
import javafx.scene.paint.*;
import javafx.scene.shape.Circle;
import static javafx.application.Application.launch;
import javafx.event.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.*;
import javafx.scene.layout.Pane;
import java.util.ArrayList;
/**
 *
 * @author kate8210
 */
public class Highlight{
    private boolean colour;
    private static double[] mouseLocation;//x, y
    private double[] pieceLocation;//x, y
    private int radius;
    private static int width;
    private static int height;
    private ArrayList<Circle> circles = new ArrayList<>();
    private Group group;

    public Highlight(boolean col, int rad, int w, int h, Group g){
        colour = col;
        radius = rad;
        width = w;
        height = h;
        group = g;
    }
    
    //call getmousepos first
    public void ifDrawGhostPiece(){
        //check which column the mouse is in
        //draw a ghost piece in the correct location
  
        int column = 0;
        int row = 0;
        for (int i = 1; i < 8; i++){//check which column
            if (width/7*i > mouseLocation[0] && mouseLocation[0] > width/7*i-1){
                column = i;
                break;
            }
        }
        for (int i = 1; i < 7; i++){//check which row
            if (height/6*i > mouseLocation[1] && mouseLocation[1] > height/6*i-1){
                row = i;
                break;
            }
        }
        //draw piece at those coordinates
        Circle ghostPiece = new Circle();
        
        ghostPiece.setCenterX(column);
        ghostPiece.setCenterY(row);
        ghostPiece.setRadius(radius);
        
        if(colour){//pick colour
            ghostPiece.setFill(Color.RED);
        }
        else{
            ghostPiece.setFill(Color.YELLOW);
        }
        setColour();
        ghostPiece.setOpacity(0.5);
    }

    public void setColour(){
        colour = !colour;
    }
    
    //check if the mouse is withing the bounds of the window
    public static boolean checkBoundaries(int[] coords){//done
        return coords[0] > 0 && coords[0] < width && coords[1] > 0 && coords[1] < height; 
    }
    
    public static void getMousePos(){//done...?
        Pane root = new Pane();
        root.setOnMouseMoved(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent event){
                mouseLocation[0] = event.getX();
                mouseLocation[1] = event.getY();
            }
        });
    }
    
    public static boolean checkVisible(int[] coordsToCheck){//check if 
        getMousePos();
        return checkBoundaries(coordsToCheck);
    }
 
}
