import java.awt.Color;

public class Seed extends Particle {

    public Seed(int x, int y) {
        super(x, y, Color.decode("#5D5040"));
        canFall = true;
        weight = 3;
    }
    
}
