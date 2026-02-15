import java.awt.Color;

public class Sand {
    private int x = 0;
    private int y = 0;
    private static int size = 10;
    Color color = Color.yellow;
    
    public Sand(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public static int getSize() {
        return size;
    }

    public Color getColor() {
        return color;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

}