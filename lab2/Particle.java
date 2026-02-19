import java.awt.Color;

public abstract class Particle {
    private int x = 0;
    private int y = 0;
    private static int size = 15;
    protected int weight;
    protected boolean canFall;
    protected boolean updated;
    Color color;

    public Particle(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
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

    public int getWeight() {
        return weight;
    }

    public void setUpdated(boolean updated) {
        this.updated = updated;
    }

    public boolean getUpdated() {
        return updated;
    }

    public boolean getCanFall() {
        return canFall;
    }

    public void setX(int x) {
        this.x = x;
        this.setUpdated(true);
    }

    public void setY(int y) {
        this.y = y;
        this.setUpdated(true);
    }
}
