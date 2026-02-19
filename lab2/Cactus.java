import java.awt.Color;

public class Cactus extends Particle{
    int growthLevel = 0;    
    public Cactus(int x, int y) {
        super(x, y, Color.decode("#2E6021"));
        weight = 6;
        canFall = false;
    }
    
}
