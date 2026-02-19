import java.awt.Color;

public class Stone extends Particle {

    public Stone(int x, int y) {
        super(x, y, Color.decode("#7b7167"));
        weight = 5;
        canFall = false;
    }
    
}
