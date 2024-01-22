/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.connect_4;
import java.util.ArrayList;
import javafx.util.Pair;
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
    private double spaceWidth;
    private double spaceHeight;
    private double offset;
    private Piece[][] grid;
    private ArrayList<Pair<Integer, Integer>> connectedCoords = new ArrayList<>();
    private ArrayList<Piece> drawPieceList = new ArrayList<>();
    
    public Grid (int w, int h, double spaceW, double spaceH, double offset) {
        width = w;
        height = h;
        spaceWidth = spaceW;
        spaceHeight = spaceH;
        this.offset = offset;
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
    
    public void placePiece(int x, boolean isRed) {
        boolean spaceFound = false;
        int y = -1;
        for (int i = 0; i < height; i++) {
            if (grid[x][i] == null) {
                y = i;
                spaceFound = true;
                break;
            }
        }
        if (!spaceFound) {
            Error OverlapError = new Error("Overlapping piece");
            throw OverlapError;
        } else grid[x][y] = new Piece(x * spaceWidth + offset + 25, y * spaceHeight + 25, isRed); //25 is the current radius, could use variable to replace it
        
    }
    
    public boolean checkConnect4(int x, int y, Piece p) {
        boolean pieceIsRed = true; //p.getIsRed() after or whatever method
        return checkRow(x, y, p, pieceIsRed) || checkColumn(x, y, p, pieceIsRed) || checkDiagUpRight(x, y, p, pieceIsRed) || checkDiagUpLeft(x, y, p, pieceIsRed);
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
                        connected += 1;
                        connectedCoords.add(new Pair<>(x1, y));
                    }
                    else checkLeft = false;
                } else checkLeft = false;
            }
            if (x2 < width && checkRight) {
                if (!(grid[x2][y] == null)) {
                    if (grid[x2][y].getIsRed() == pieceIsRed) {
                        connected += 1;
                        connectedCoords.add(new Pair<>(x2, y));
                    }
                    else checkRight = false;
                } else checkRight = false;
            }
            if (!(checkRight || checkLeft)) break;
        }
        clearConnectedList();
        return false;
    }
    
    private boolean checkColumn(int x, int y, Piece p, boolean pieceIsRed) {
        int connected = 1;

        while (true) {
            y -= 1;
            if (connected == 4) return true;
            else if (y >= 0) {
                if (grid[x][y].getIsRed() == pieceIsRed) {
                    connected += 1;
                    connectedCoords.add(new Pair<>(x, y));
                }
                else break;
            } else break;
        }
        clearConnectedList();
        return false;
    }
    
    private boolean checkDiagUpRight(int x, int y, Piece p, boolean pieceIsRed) {
        int connected = 1;
        int x1 = x; //checks pieces to left
        int x2 = x; // "" to right
        int y1 = y;
        int y2 = y;
        boolean checkLeft = true; //bool to continue checking left
        boolean checkRight = true; //"" right
        while (true) {
            x1 -= 1;
            x2 += 1;
            y1 -= 1;
            y2 += 1;
            
            if (connected == 4) return true;
            if (x1 >= 0 && checkLeft) {
                if (!(grid[x1][y1] == null)) {
                    if (grid[x1][y1].getIsRed() == pieceIsRed) {
                        connected += 1;
                        connectedCoords.add(new Pair<>(x1, y1));
                    }
                    else checkLeft = false;
                } else checkLeft = false;
            }
            if (x2 < width && checkRight) {
                if (!(grid[x2][y2] == null)) {
                    if (grid[x2][y2].getIsRed() == pieceIsRed) {
                        connected += 1;
                        connectedCoords.add(new Pair<>(x2, y2));
                    }
                    else checkRight = false;
                } else checkRight = false;
            }
            if (!(checkRight || checkLeft)) break;
        }
        clearConnectedList();
        return false;
    }
    
    private boolean checkDiagUpLeft(int x, int y, Piece p, boolean pieceIsRed) {
        int connected = 1;
        int x1 = x; //checks pieces to left
        int x2 = x; // "" to right
        int y1 = y;
        int y2 = y;
        boolean checkLeft = true; //bool to continue checking left
        boolean checkRight = true; //"" right
        while (true) {
            x1 -= 1;
            x2 += 1;
            y1 -= 1;
            y2 += 1;
            
            if (connected == 4) return true;
            if (x1 >= 0 && checkLeft) {
                if (!(grid[x1][y1] == null)) {
                    if (grid[x1][y1].getIsRed() == pieceIsRed) {
                        connected += 1;
                        connectedCoords.add(new Pair<>(x1, y1));
                    }
                    else checkLeft = false;
                } else checkLeft = false;
            }
            if (x2 < width && checkRight) {
                if (!(grid[x2][y2] == null)) {
                    if (grid[x2][y2].getIsRed() == pieceIsRed) {
                        connected += 1;
                        connectedCoords.add(new Pair<>(x2, y2));
                    }
                    else checkRight = false;
                } else checkRight = false;
            }
            if (!(checkRight || checkLeft)) break;
        }
        clearConnectedList();
        return false;
    }
    
    private void clearConnectedList() {
        connectedCoords = new ArrayList<>();
    }
    
    public ArrayList<Pair<Integer, Integer>> getConnectedCoords() {
        return connectedCoords;
    }
    
    public void drawPiece() {
            
    }
}
