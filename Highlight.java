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
import javafx.util.Pair;
/**
 *
 * @author kate8210
 */
public class Highlight{
    private Grid grid;
    private boolean colour;
    private static double[] mouseLocation = new double[2];//x, y
    private double[] pieceLocation;//x, y
    private int radius;
    private static int width;
    private static int height;
    private ArrayList<Circle> circles = new ArrayList<>();
    private Circle ghostPiece = new Circle();
    private boolean isThereAPiece = false;

    public Highlight(boolean col, int rad, int w, int h, Grid gr){
        colour = col;
        radius = rad;
        width = w;
        height = h;
        grid = gr;
    }
    
    //call getmousepos first
    public Circle ifDrawGhostPiece(Group group, int[] piecesInColumn){
        //check which column the mouse is in
        //draw a ghost piece in the correct location
        if(isThereAPiece){
            group.getChildren().remove(ghostPiece);
            isThereAPiece = false;
        }
        
        int column = 0;//indicates x coor to draw circle
        int row = 0;//indicates y coor to draw circle
        System.out.println(mouseLocation[0]);
        System.out.println(mouseLocation[1]);
        
        for (int i = 1; i < 8; i++){//check which column
            if (width/7*i > mouseLocation[0] && mouseLocation[0] > width/7*i-1){
                column = i;
                row = piecesInColumn[i - 1] + 1;
                break;
            }
        }
        //column = 2 + 3 pieces 
        
        //draw piece at those coordinates
        //ghostPiece.setCenterX(column);
        //ghostPiece.setCenterY(row);
        //ghostPiece.setRadius(radius);
        System.out.println(column);
        
        ghostPiece.setCenterX(mouseLocation[0]);
        ghostPiece.setCenterY(mouseLocation[1]);
        ghostPiece.setRadius(radius);
        
        if(colour){//pick colour
            ghostPiece.setFill(Color.RED);
        }
        else{
            ghostPiece.setFill(Color.YELLOW);
        }
        setColour();
        ghostPiece.setOpacity(0.25);
        
        isThereAPiece = true;
        
        return ghostPiece;
    }

    public void setColour(){
        colour = !colour;
    }
    
     /**
     * @param pos the position of tiles that create the four-in-a-row.
     * @author josh
     */
    public void addWinHighlight(ArrayList<Pair<Integer, Integer>> pos) {
        pos.forEach((c) -> {
            Circle circle = new Circle(55, grid.getPiece(c.getKey(), c.getValue()).getIsRed() ? Color.PINK : Color.ORANGE);
            circle.setCenterX(c.getKey());
            circle.setCenterY(c.getValue());
        });
    }
    
    //check if the mouse is withing the bounds of the window
    public static boolean checkBoundaries(int[] coords){//done
        return coords[0] > 0 && coords[0] < width && coords[1] > 0 && coords[1] < height; 
    }
    
    public double[] getMousePos(){//done...?
        Pane root = new Pane();
        root.setOnMouseMoved(new EventHandler<MouseEvent>(){
            @Override public void handle(MouseEvent event){
                //mouseLocation[0] = event.getX();
                //mouseLocation[1] = event.getY();
                
                
            }
        });
        mouseLocation[0] = 400;
        mouseLocation[1] = 100;
        return mouseLocation;
    }
    
    
    public boolean checkVisible(int[] coordsToCheck){//check if 
        getMousePos();
        return checkBoundaries(coordsToCheck);
    }
    
     public Circle draw(){
        Circle ghostPiece = new Circle();
        ghostPiece.setCenterX(70);
        ghostPiece.setCenterY(70);
        ghostPiece.setRadius(100); 
        return ghostPiece;
    }
 
}
