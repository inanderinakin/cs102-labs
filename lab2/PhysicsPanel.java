import java.awt.Graphics;

import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.event.MouseInputAdapter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class PhysicsPanel extends JPanel{
    int sandSize = Sand.getSize();
    ArrayList<Sand> sandList = new ArrayList<>();   
    Timer timer = new Timer(100, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            for (int i = 0; i < sandList.size(); i++) {
                Sand sand = sandList.get(i);
                int underY = sand.getY() + sandSize;
                if (underY <= getHeight() - sandSize) {
                    boolean blocked = false;
                    for (int j = 0; j < sandList.size() && !blocked; j++) {
                        Sand sand2 = sandList.get(j);
                        if (sand2 != sand && sand.getX() == sand2.getX() && sand2.getY() == underY) {
                            blocked = true;
                        }
                    }
                    if (!blocked) {
                        sand.setY(underY);
                    }
                }
            }
            repaint();
        }
    });

    MouseInputAdapter mouse = new MouseInputAdapter() {
        @Override
        public void mousePressed(MouseEvent e) {
            int x = (e.getX() / sandSize) * sandSize;
            int y = (e.getY() / sandSize) * sandSize;
            sandList.add(new Sand(x, y));
            repaint();
        }
    };

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Sand sand : sandList) {
            g.setColor(sand.getColor());
            g.fillRect(sand.getX(), sand.getY(), sandSize, sandSize);
        }
    }

    public PhysicsPanel() {
        this.addMouseListener(mouse);
        timer.setRepeats(true);
        timer.start();
    }
}
