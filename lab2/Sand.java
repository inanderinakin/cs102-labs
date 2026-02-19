import java.awt.Color;

public class Sand extends Particle {
    public Sand(int x, int y) {
        super(x, y, Color.decode("#CBBD93"));
        weight = 4;
        canFall = true;
    }
}