import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class App {
    public static void main(String[] args) {
        int frameHeight = 800;
        int frameWidth = frameHeight * 4/3;
        JFrame frame = new JFrame();
        frame.setTitle("Cell-Based Particle Simulation");
        frame.setSize(frameWidth, frameHeight);
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        PhysicsPanel physicsPanel = new PhysicsPanel();
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.decode("#d1d1d1"));
        buttonPanel.setPreferredSize(new Dimension(frameWidth / 5, frameHeight));
        buttonPanel.setLayout(new GridLayout(13, 1));

        JButton stepButton = new JButton("Step");
        stepButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!physicsPanel.isTimerEnabled) {
                    physicsPanel.step();
                }
            }
        });

        JButton stopButton = new JButton("Stop");
        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                physicsPanel.editTimer();
                if (stopButton.getText() == "Stop") {
                    stopButton.setText("Start");
                }
                else if (stopButton.getText() == "Start") {
                    stopButton.setText("Stop");
                }
            }
        });

        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                physicsPanel.clearCanvas();
            }
            
        });

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

        JButton seedButton = new JButton("Seed");
        seedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                physicsPanel.setParticleMode("Seed");
                EnableButtons(buttonPanel);
                seedButton.setEnabled(false);
            }
        });

        buttonPanel.add(stepButton);
        buttonPanel.add(stopButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(sandButton);
        buttonPanel.add(stoneButton);
        buttonPanel.add(waterButton);
        buttonPanel.add(smokeButton);
        buttonPanel.add(seedButton);

        frame.add(physicsPanel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.EAST);
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
