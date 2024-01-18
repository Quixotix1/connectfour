/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.connect_4;
import java.util.ArrayList;
import javafx.scene.paint.Color;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.Rectangle;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import java.util.Map;
        
/**
 *
 * @author terr3890
 */
public class Grid {
    private int width;
    private int height;
    private Piece[][] grid;
    private ArrayList<ArrayList<Integer>> connectedCoords = new ArrayList<>();
    private ArrayList<Piece> drawPieceList = new ArrayList<>();
    
    public Grid (int w, int h) {
        width = w;
        height = h;
        grid = new Piece[width][height];
    }
    
    public int getHeight() {
        return height;
    }
    
    public int getWidth() {
        return width;
    }
    
    public Piece getPiece(int x, int y) {
        return grid[x][y];
    }
    
    public boolean isTaken(int x, int y) {
        return !(grid[x][y] == null);
    }
    
    public void placePiece(Piece p, int x, int y) {
        if (grid[x][y] == null){
            grid[x][y] = p;   
        } else {
            Error OverlapError = new Error("Overlapping piece");
            throw OverlapError;
        }
    }
    
    public boolean checkConnect4(int x, int y, Piece p) {
        boolean pieceIsRed = true; //p.getIsRed() after or whatever method
    }
    
    private boolean checkRow(int x, int y, Piece p, boolean pieceIsRed) {
        int connected = 1;
        int x1 = x; //checks pieces to left
        int x2 = x; // "" to right
        boolean checkLeft = true; //bool to continue checking left
        boolean checkRight = true; //"" right
        while (true) {
            x1 -= 1;
            x2 += 1;
            if (connected == 4) return true;
            if (x1 >= 0 && checkLeft) {
                if (!(grid[x1][y] == null)) {
                    if (grid[x1][y].getIsRed() == pieceIsRed) {
                        connected +=1;
                        
                    }
                    else checkLeft = false;
                } else checkLeft = false;
            }
            if (x2 < width && checkRight) {
                if (!(grid[x2][y] == null)) {
                    if (grid[x1][y].getIsRed() == pieceIsRed) connected +=1;
                    else checkRight = false;
                } else checkRight = false;
            }
            if (!(checkRight || checkLeft)) break;
        }
        return false;
    }
    
    private boolean checkColumn(int x, int y, Piece p, boolean pieceIsRed) {
        int connected = 1;

        while (true) {
            y -= 1;
            if (connected == 4) return true;
            else if (y >= 0) {
                if (grid[x][y].getIsRed() == pieceIsRed) connected += 1;
                else break;
            } else break;
        }
        return false;
    }
    
    private boolean checkDiagUpRight(int x, int y, Piece p) {
        int connected = 1;
    }
    
    private boolean checkDiagUpLeft(int x, int y, Piece p) {
        int connected = 1;
    }
    
    private void clearConnectedList() {
        connectedCoords = new ArrayList<>();
    }
}
