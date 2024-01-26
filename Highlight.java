/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.connect4;

import javafx.scene.Group; 
import javafx.scene.Scene; 
import javafx.scene.paint.*;
import javafx.scene.shape.Circle;
import java.util.ArrayList;
import javafx.util.Pair;
/**
 *
 * @author kate8210
 */
public class Highlight{
    private Grid grid;
    private static double[] mouseLocation = new double[2];//x, y
    final private double radius;
    private static double width;
    private static double height;
    private boolean colour;
    private Circle ghostPiece = new Circle();
    private boolean isThereAPiece = false;

    public Highlight(boolean col,double rad, double w, double h, Grid gr){//I need the width and height of the board, not the window
        colour = col;
        radius = rad;
        width = w;
        height = h;
        grid = gr;
    }
    
    //call in getMousePos
    public Circle drawGhostPiece( Group group, int[] piecesInColumn, double xOffset, double yOffset){
        //check which column the mouse is in
        //draw a ghost piece in the correct location
        if(isThereAPiece){
            group.getChildren().remove(ghostPiece);
            isThereAPiece = false;
        }
        
        double xCoor = 0;//indicates x coor to draw circle - based off the column its in
        double yCoor = 0;//indicates y coor to draw circle - based off the row its in
        //System.out.println(mouseLocation[0]);
        //System.out.println(mouseLocation[1]);
        
        for (int i = 1; i < 8; i++){//check which column
            //System.out.println("  | " + width/7*i + "?> " + mouseLocation[0]);
            //System.out.println("  | " + (width/7*(i-1)) + "?<" + mouseLocation[0]);
            if (width/7*i + xOffset > mouseLocation[0] && mouseLocation[0] > width/7*(i-1) + xOffset){
                xCoor = (width/7*i + xOffset - width/14); //finding the coordinates 
                //System.out.println("i = " + i);
                yCoor = height/6 * (6 - piecesInColumn[i-1]) + height/12;
                break; 
            }
        }
    
        //set up piece
        ghostPiece.setCenterX(xCoor);
        ghostPiece.setCenterY(yCoor);
        ghostPiece.setRadius(radius);
        
        if(colour){//pick colour
            ghostPiece.setFill(Color.RED);
        }
        else{
            ghostPiece.setFill(Color.YELLOW);
        }
        ghostPiece.setOpacity(0.25);
        
        isThereAPiece = true;
        
        return ghostPiece;
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
    
    public void getMousePos(Scene scene, Group group, int[] piecesInColumn,  double xOffset, double yOffset){
        scene.setOnMouseMoved(event -> {
            mouseLocation[0] = event.getSceneX();
            mouseLocation[1] = event.getSceneY();
            if(mouseLocation[0] > xOffset && mouseLocation[0] < xOffset + width && mouseLocation[1] > yOffset && mouseLocation[1] < yOffset + height){//if the mouse is on the board
                group.getChildren().add(drawGhostPiece(group, piecesInColumn, xOffset, yOffset));
            }
            else{//if not, it won't print anything
                group.getChildren().remove(ghostPiece);
            }
        });
    }

    public boolean setColour(){
        colour = !colour;
        return colour;
    }
   
}
