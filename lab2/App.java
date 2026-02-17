import java.awt.GridLayout;
import javax.swing.JFrame;

public class App {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setTitle("Cell-Based Particle Simulation");
        frame.setSize(400, 400);
        frame.setLayout(new GridLayout(1, 1));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        PhysicsPanel physicsPanel = new PhysicsPanel();
        frame.add(physicsPanel);
        frame.setVisible(true);
    }
}
