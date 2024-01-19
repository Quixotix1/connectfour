package com.mycompany.connect4

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 *
 * @author josh2211
 */
public class Piece extends Circle {
    private final boolean isRed;
    
    public Piece(double xLoc, double yLoc, boolean isRed) {
        super(50, isRed ? Color.RED : Color.YELLOW);
        this.isRed = isRed;
        setCenterX(xLoc);
        setCenterY(yLoc);
    }
    
    public boolean getIsRed() {
        return isRed;
    }
    
    /**
     * getCenterX() and getCenterY() are built in getter methods for x and y coordinates
     */
}
