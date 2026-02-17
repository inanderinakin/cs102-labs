import java.awt.Graphics;

import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.event.MouseInputAdapter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class PhysicsPanel extends JPanel{
    String particleMode = "Sand";
    int particleSize = Particle.getSize();
    ArrayList<Particle> particleList = new ArrayList<>(); 
    Timer timer = new Timer(35, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            for (int i = particleList.size() - 1; i >= 0; i--) {
                Particle particle = particleList.get(i);
                int underY = particle.getY() + particleSize;
                if (underY <= getHeight() - particleSize) { //isGrounded, if not
                    boolean blocked = false;
                    for (int j = 0; j < particleList.size() && !blocked; j++) {
                        Particle particle2 = particleList.get(j);
                        if (particle2 != particle && particle.getX() == particle2.getX() && particle2.getY() == underY) {
                            blocked = true;
                        }
                    }
                    if (!blocked) {
                        particle.setY(underY);
                    }
                }
            }
            if (mouseHeld) {
                if (particleMode.equals("Sand") && !isOccupied(mouseX, mouseY))
                particleList.add(new Sand(mouseX, mouseY));
            }
            repaint();
        }
    });

    boolean mouseHeld = false;
    int mouseX = 0;
    int mouseY = 0;

    MouseInputAdapter mouse = new MouseInputAdapter() {
        @Override
        public void mousePressed(MouseEvent e) {
            mouseHeld = true;
            mouseX = (e.getX() / particleSize) * particleSize;
            mouseY = (e.getY() / particleSize) * particleSize;
            if (particleMode.equals("Sand") && !isOccupied(mouseX, mouseY)) {
                particleList.add(new Sand(mouseX, mouseY));
            }
            repaint();
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            mouseHeld = false;
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            mouseX = (e.getX() / particleSize) * particleSize;
            mouseY = (e.getY() / particleSize) * particleSize;
            if (!isOccupied(mouseX, mouseY)) {
                particleList.add(new Sand(mouseX, mouseY));
            }
            repaint();
        }
    };

    boolean isOccupied(int x, int y) {
        for (int i = 0; i < particleList.size(); i++) {
            if (particleList.get(i).getX() == x && particleList.get(i).getY() == y) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Particle particle : particleList) {
            g.setColor(particle.getColor());
            g.fillRect(particle.getX(), particle.getY(), particleSize, particleSize);
        }
    }

    public PhysicsPanel() {
        this.addMouseListener(mouse);
        this.addMouseMotionListener(mouse);
        timer.setRepeats(true);
        timer.start();
    }
}
