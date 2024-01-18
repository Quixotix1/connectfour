import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 *
 * @author josh2211
 */
public class Piece extends Circle {
    private final double xLoc;
    private final double yLoc;
    private final boolean isRed;
    
    public Piece(double xLoc, double yLoc, boolean isRed) {
        super(50, isRed ? Color.RED : Color.YELLOW);
        this.xLoc = xLoc;
        this.yLoc = yLoc;
        this.isRed = isRed;
        setCenterX(xLoc);
        setCenterY(yLoc);
    }
    
    public double getX() {
        return xLoc;
    }
    
    public double getY() {
        return yLoc;
    }
    
    public boolean getIsRed() {
        return isRed;
    }
}
