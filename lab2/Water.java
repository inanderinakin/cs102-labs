import java.awt.Color;
import java.util.Random;

public class Water extends Particle{
    private int flowDirection = -1;

    public Water(int x, int y) {
        super(x, y, Color.decode("#2140A3"));
        weight = 2;
        canFall = true;
    }

    public int getFlowDirection() {
        return flowDirection;
    }

    public void setFlowDirection(int direction) {
        this.flowDirection = direction;
    }

    public void updateFlowDirection(boolean blockedLeft, boolean blockedRight) {
        if (blockedLeft && blockedRight) {
            flowDirection = -1;
        } 
        else if (blockedLeft) {
            flowDirection = 1;
        } 
        else if (blockedRight) {
            flowDirection = 0;
        } 
        else {
            if (flowDirection == -1) {
                flowDirection = new Random().nextInt(2);
            }
        }
    }
}
