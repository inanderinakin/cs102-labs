import java.awt.Color;

public class Cactus extends Particle{
    int growthLevel = 0;    
    public Cactus(int x, int y, int growthLevel) {
        super(x, y, Color.decode("#2E6021"));
        this.growthLevel = growthLevel;
        weight = 6;
        canFall = false;
    }
    public int getGrowthLevel() {
        return growthLevel;
    }
    public void setGrowthLevel(int growthLevel) {
        this.growthLevel = growthLevel;
    }
    
}
