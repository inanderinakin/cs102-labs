import java.awt.Color;
import java.util.Random;

public class Smoke extends Particle{
    private int age = 0;
    private int maxAge = 100;
    private int driftDirection = -1;

    public Smoke(int x, int y) {
        super(x, y, Color.decode("#939393"));
        weight = 1;
        canFall = false;
    }

    public int getAge() {
        return age;
    }

    public int getMaxAge() {
        return maxAge;
    }

    public void incrementAge() {
        age++;
        int gray = 147 + (int)((255 - 147) * ((double) age / maxAge)); //When age is 0, gray; When age is 100, white.
        if (gray > 255) {
            gray = 255;
        }
        color = new Color(gray, gray, gray);
    }

    public double getRiseProbability() {
        return 1 - 0.8 * ((double) age / maxAge);
    }

    public double getSpreadProbability() {
        return 0.2 + 0.5 * ((double) age / maxAge);
    }

    public int getDriftDirection() {
        return driftDirection;
    }

    public void setDriftDirection(int direction) {
        this.driftDirection = direction;
    }

    public void updateDriftDirection(boolean blockedLeft, boolean blockedRight) {
        if (blockedLeft && blockedRight) {
            driftDirection = -1;
        }
        else if (blockedLeft) {
            driftDirection = 1;
        }
        else if (blockedRight) {
            driftDirection = 0;
        }
        else {
            if (driftDirection == -1) {
                driftDirection = new Random().nextInt(2);
            }
        }
    }
}
