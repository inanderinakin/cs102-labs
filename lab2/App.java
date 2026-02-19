import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class App {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setTitle("Cell-Based Particle Simulation");
        frame.setSize(400, 400);
        frame.setLayout(new GridLayout(1, 2));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        PhysicsPanel physicsPanel = new PhysicsPanel();
        JPanel buttonPanel = new JPanel();

        JButton sandButton = new JButton("Sand");
        sandButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                physicsPanel.setParticleMode("Sand");
                EnableButtons(buttonPanel);
                sandButton.setEnabled(false);
            }
        });

        JButton stoneButton = new JButton("Stone");
        stoneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                physicsPanel.setParticleMode("Stone");
                EnableButtons(buttonPanel);
                stoneButton.setEnabled(false);
            }
        });

        JButton waterButton = new JButton("Water");
        waterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                physicsPanel.setParticleMode("Water");
                EnableButtons(buttonPanel);
                waterButton.setEnabled(false);
            }
        });

        JButton smokeButton = new JButton("Smoke");
        smokeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                physicsPanel.setParticleMode("Smoke");
                EnableButtons(buttonPanel);
                smokeButton.setEnabled(false);
            }
        });


        buttonPanel.add(sandButton);
        buttonPanel.add(stoneButton);
        buttonPanel.add(waterButton);
        buttonPanel.add(smokeButton);
        frame.add(physicsPanel);
        frame.add(buttonPanel);
        frame.setVisible(true);
    }

    public static void EnableButtons(JPanel panel) {
        for (Component component : panel.getComponents()) {
            if (component instanceof JButton) {
                JButton button = (JButton) component;
                button.setEnabled(true);
            }
        }
    }
}
