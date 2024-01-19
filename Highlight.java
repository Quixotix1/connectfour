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
public class Highlight extends Connect4{
    private boolean colour;
    private static double[] mouseLocation;//x, y
    private double[] pieceLocation;//x, y
    private int radius;
    private int width;
    private int height;
    //create a circle

    public Highlight(boolean col, int rad, int w, int h){
        colour = col;
        radius = rad;
        width = w;
        height = h;
        //radius: 50
        //amount: 42
    }
    //getmousepos
    public void ifDrawGhostPiece(int[] amntOfPieces){
        //check which column the mouse is in
        //draw a ghost piece in the correct location
        int column;
        int row;
        for (int i = 1; i < 8; i++){
            if (width/7*i > mouseLocation[0] && mouseLocation[0] > width/7*i-1){
                column = i;
            }
        }
        for (int i = 1; i < 7; i++){
            if (height/6*i > mouseLocation[1] && mouseLocation[1] > height/6*i-1){
                row = i;
            }
        }
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public void setColour(){
        
    }
    
    //check if the mouse is withing the bounds of the window
    public static boolean checkBoundaries(int[] coords, int x, int y){//done
        return coords[0] > 0 && coords[0] < x && coords[1] > 0 && coords[1] < y; 
    }
    
    public void shinyVictory(ArrayList<ArrayList<Integer>> coords){
        
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
    
    
    public static boolean checkVisible(int[] coordsToCheck, int width, int height){
        getMousePos();
        return checkBoundaries(coordsToCheck, width, height);
    }
    
    public static boolean isInColumn(){
        
    }

    
}

