import java.awt.Button;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class App {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setTitle("Hello, World");
        frame.setSize(800, 400);
        frame.setLayout(new GridLayout(1, 2));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        PhysicsPanel physicsPanel = new PhysicsPanel();
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.gray);

        Button button1 = new Button("Hello");
        buttonPanel.add(button1);

        frame.add(physicsPanel);

        frame.add(buttonPanel);
        frame.setVisible(true);
    }
}
