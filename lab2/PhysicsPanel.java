import java.awt.Graphics;

import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.event.MouseInputAdapter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class PhysicsPanel extends JPanel{
    String particleMode = "Sand";
    int updateCount = 0;
    int particleSize = Particle.getSize();
    ArrayList<Particle> particleList = new ArrayList<>(); 
    public boolean isTimerEnabled = true;

    Timer timer = new Timer(35, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            step();
            if (mouseHeld) {
                createParticle();
            }
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
            createParticle();
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
            createParticle();
            repaint();
        }
    };

    public void update() {
        ArrayList<Particle> shuffledList = new ArrayList<>(particleList);
        Collections.shuffle(shuffledList);

        for (Particle particle : shuffledList) {
            if (!particle.getUpdated()) {
                int underY = particle.getY() + particleSize;
                boolean isGrounded = underY > getHeight() - particleSize ? true : false;

                if (particle.getCanFall() && !isGrounded) {
                    boolean isBlockedDown = false;
                    boolean isBlockedRightDown = false;
                    boolean isBlockedLeftDown = false;
                    boolean isBlockedRightSide = false;
                    boolean isBlockedLeftSide = false;

                    //Is particle at the ends of the panel?
                    if (particle.getX() + particleSize >= getWidth()) { 
                        isBlockedRightSide = true;
                        isBlockedRightDown = true;
                    }
                    if (particle.getX() - particleSize < 0) {
                        isBlockedLeftSide = true;
                        isBlockedLeftDown = true;
                    }

                    boolean isBlockingSand = false;
                    //Surroundings check
                    for (int j = 0; j < particleList.size(); j++) { 
                        Particle particle2 = particleList.get(j);
                        if (particle2 != particle) {
                            if (particle.getWeight() <= particle2.getWeight()) {
                                if (particle.getX() == particle2.getX() && (particle2.getY() == underY || underY == getHeight())) {
                                    isBlockedDown = true;
                                    if (particle2 instanceof Sand) {
                                        isBlockingSand = true;
                                    }
                                }
                                if (particle.getX() + particleSize == particle2.getX() && underY == particle2.getY()) {
                                    isBlockedRightDown = true;
                                }
                                if (particle.getX() - particleSize == particle2.getX() && underY == particle2.getY()) {
                                    isBlockedLeftDown = true;
                                }
                                if (particle.getX() + particleSize == particle2.getX() && particle.getY() == particle2.getY()) {
                                    isBlockedRightSide = true;
                                }
                                if (particle.getX() - particleSize == particle2.getX() && particle.getY() == particle2.getY()) {
                                    isBlockedLeftSide = true;
                                }
                            }
                        }
                    }
                    boolean canSlideRightDown = !isBlockedRightDown && !isBlockedRightSide;
                    boolean canSlideLeftDown = !isBlockedLeftDown && !isBlockedLeftSide;

                    if (!isBlockedDown) {
                        Particle lighterBelow = null;
                        for (int j = 0; j < particleList.size(); j++) {
                            Particle particle2 = particleList.get(j);
                            if (particle2 != particle && particle.getX() == particle2.getX() && particle2.getY() == underY
                                    && particle.getWeight() > particle2.getWeight() && particle2.getCanFall()) {
                                lighterBelow = particle2;
                                break;
                            }                            
                        }
                        if (lighterBelow != null) {
                            int oldY = particle.getY();
                            particle.setY(underY);
                            lighterBelow.setY(oldY);
                        } 
                        else {
                            particle.setY(underY);
                        }

                        if (particle instanceof Water) {
                            Water water = (Water) particle;
                            water.setFlowDirection(-1);
                        }
                    }
                    else {
                        if (particle instanceof Water && !canSlideLeftDown && !canSlideRightDown) {
                            Water water = (Water) particle;
                            water.updateFlowDirection(isBlockedLeftSide, isBlockedRightSide);
                            int direction = water.getFlowDirection();
                            if (direction == 1) {
                                particle.setX(particle.getX() + particleSize);
                            }
                            else if (direction == 0) {
                                particle.setX(particle.getX() - particleSize);
                            }
                        }
                        else if (particle instanceof Water) {
                            ((Water) particle).setFlowDirection(-1);
                        }

                        if (particle instanceof Seed ) {
                            particleList.remove(particle);
                            if (isBlockingSand) {
                                int seedX = particle.getX();
                                int seedY = particle.getY();
                                particleList.add(new Cactus(seedX, seedY, 0));
                            }
                        }

                        if (canSlideLeftDown && !canSlideRightDown) {
                            particle.setX(particle.getX() - particleSize);
                            particle.setY(underY);
                        }
                        else if (canSlideRightDown && !canSlideLeftDown) {
                            particle.setX(particle.getX() + particleSize);
                            particle.setY(underY);
                        }
                        else if (canSlideLeftDown && canSlideRightDown) {
                            Random random = new Random();
                            if (random.nextInt(0, 100) >= 50) {
                                particle.setX(particle.getX() - particleSize);
                                particle.setY(underY);
                            }
                            else {
                                particle.setX(particle.getX() + particleSize);
                                particle.setY(underY);
                            }
                        }
                    }
                }
                else if (particle instanceof Smoke) {
                    Smoke smoke = (Smoke) particle;

                    int aboveY = particle.getY() - particleSize;

                    Random random = new Random();
                    double prob = random.nextDouble();

                    if (prob < smoke.getRiseProbability()) {
                        //Rise upward
                        boolean isBlockedUp = false;
                        boolean isBlockedRightUp = false;
                        boolean isBlockedLeftUp = false;
                        boolean isBlockedRightSide = false;
                        boolean isBlockedLeftSide = false;

                        //Is particle at the ends of the panel?
                        if (particle.getX() + particleSize >= getWidth()) { 
                            isBlockedRightSide = true;
                            isBlockedRightUp = true;
                        }
                        if (particle.getX() - particleSize < 0) {
                            isBlockedLeftSide = true;
                            isBlockedLeftUp = true;
                        }

                        //Surroundings check
                        for (int j = 0; j < particleList.size(); j++) { 
                            Particle particle2 = particleList.get(j);
                            if (particle2 != particle) {
                                if (particle.getX() == particle2.getX() && particle2.getY() == aboveY) {
                                    isBlockedUp = true;
                                }
                                if (particle.getX() + particleSize == particle2.getX() && aboveY == particle2.getY()) {
                                    isBlockedRightUp = true;
                                }
                                if (particle.getX() - particleSize == particle2.getX() && aboveY == particle2.getY()) {
                                    isBlockedLeftUp = true;
                                }
                                if (particle.getX() + particleSize == particle2.getX() && particle.getY() == particle2.getY()) {
                                    isBlockedRightSide = true;
                                }
                                if (particle.getX() - particleSize == particle2.getX() && particle.getY() == particle2.getY()) {
                                    isBlockedLeftSide = true;
                                }
                            }
                        }

                        if (!isBlockedUp) {
                            particle.setY(aboveY);
                        }
                        else {
                            boolean canSlideRightUp = !isBlockedRightUp && !isBlockedRightSide;
                            boolean canSlideLeftUp = !isBlockedLeftUp && !isBlockedLeftSide;

                            if (canSlideLeftUp && !canSlideRightUp) {
                                particle.setX(particle.getX() - particleSize);
                                particle.setY(aboveY);
                            }
                            else if (canSlideRightUp && !canSlideLeftUp) {
                                particle.setX(particle.getX() + particleSize);
                                particle.setY(aboveY);
                            }
                            else if (canSlideLeftUp && canSlideRightUp) {
                                if (random.nextInt(0, 100) >= 50) {
                                    particle.setX(particle.getX() - particleSize);
                                    particle.setY(aboveY);
                                }
                                else {
                                    particle.setX(particle.getX() + particleSize);
                                    particle.setY(aboveY);
                                }
                            }
                        }
                    }
                    else if (prob < smoke.getRiseProbability() + smoke.getSpreadProbability()) {
                        //Horizontal drift
                        boolean isBlockedRightSide = false;
                        boolean isBlockedLeftSide = false;

                        if (particle.getX() + particleSize >= getWidth()) { 
                            isBlockedRightSide = true;
                        }
                        if (particle.getX() - particleSize < 0) {
                            isBlockedLeftSide = true;
                        }

                        for (int j = 0; j < particleList.size(); j++) { 
                            Particle particle2 = particleList.get(j);
                            if (particle2 != particle) {
                                if (particle.getX() + particleSize == particle2.getX() && particle.getY() == particle2.getY()) {
                                    isBlockedRightSide = true;
                                }
                                if (particle.getX() - particleSize == particle2.getX() && particle.getY() == particle2.getY()) {
                                    isBlockedLeftSide = true;
                                }
                            }
                        }

                        smoke.updateDriftDirection(isBlockedLeftSide, isBlockedRightSide);
                        int direction = smoke.getDriftDirection();
                        if (direction == 1) {
                            particle.setX(particle.getX() + particleSize);
                        }
                        else if (direction == 0) {
                            particle.setX(particle.getX() - particleSize);
                        }
                    }
                }
                if (particle instanceof Cactus) {
                    Cactus cactus = (Cactus) particle;
                    if (updateCount == 10) {
                        Random random = new Random();
                        int prob = random.nextInt(1, 101);

                        int aboveY = particle.getY() - particleSize;
                        boolean isBlockedUp = false;
                        boolean isBlockedRightUp = false;
                        boolean isBlockedLeftUp = false;
                        boolean isBlockedRightSide = false;
                        boolean isBlockedLeftSide = false;

                        //Is particle at the ends of the panel?
                        if (particle.getX() + particleSize >= getWidth()) { 
                            isBlockedRightSide = true;
                            isBlockedRightUp = true;
                        }
                        if (particle.getX() - particleSize < 0) {
                            isBlockedLeftSide = true;
                            isBlockedLeftUp = true;
                        }
                        int surrondingCactusCount = 0;
                        //Surroundings check
                        for (int j = 0; j < particleList.size(); j++) { 
                            Particle particle2 = particleList.get(j);
                            if (particle2 != particle) {
                                if (particle.getX() == particle2.getX() && particle2.getY() == aboveY) {
                                    isBlockedUp = true;
                                    if (particle2 instanceof Cactus) {
                                        surrondingCactusCount++;
                                    }
                                }
                                if (particle.getX() + particleSize == particle2.getX() && aboveY == particle2.getY()) {
                                    isBlockedRightUp = true;
                                    if (particle2 instanceof Cactus) {
                                        surrondingCactusCount++;
                                    }
                                }
                                if (particle.getX() - particleSize == particle2.getX() && aboveY == particle2.getY()) {
                                    isBlockedLeftUp = true;
                                    if (particle2 instanceof Cactus) {
                                        surrondingCactusCount++;
                                    }
                                }
                                if (particle.getX() + particleSize == particle2.getX() && particle.getY() == particle2.getY()) {
                                    isBlockedRightSide = true;
                                    if (particle2 instanceof Cactus) {
                                        surrondingCactusCount++;
                                    }
                                }
                                if (particle.getX() - particleSize == particle2.getX() && particle.getY() == particle2.getY()) {
                                    isBlockedLeftSide = true;
                                    if (particle2 instanceof Cactus) {
                                        surrondingCactusCount++;
                                    }
                                }
                            }
                        }
                        if (cactus.getGrowthLevel() < 8 && surrondingCactusCount <= 2) {
                            if (!isBlockedUp) {
                                if (prob > 90) {
                                    particleList.add(new Cactus(cactus.getX(), cactus.getY() - particleSize, cactus.getGrowthLevel() + 1));
                                    cactus.setGrowthLevel(cactus.growthLevel + 1);
                                }
                            }
                            if (isBlockedUp && !isBlockedLeftUp) {
                                int leftProb = random.nextInt(0, 101);
                                if (leftProb > 95) {
                                    particleList.add(new Cactus(cactus.getX() - particleSize, cactus.getY() - particleSize, cactus.getGrowthLevel() + 1));
                                    cactus.setGrowthLevel(cactus.growthLevel + 1);
                                }
                            }   
                            else if (isBlockedUp && isBlockedLeftUp && !isBlockedRightUp){
                                int rightProb = random.nextInt(0, 101);
                                if (rightProb > 95) {
                                    particleList.add(new Cactus(cactus.getX() + particleSize, cactus.getY() - particleSize, cactus.getGrowthLevel() + 1));
                                    cactus.setGrowthLevel(cactus.growthLevel + 1);
                                }
                            }
                        }
                        
                    }
                }
            }
        }
        if (updateCount == 10) {
            updateCount = 0;
        }
        updateCount++;
    }

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

    public void createParticle() {
        if (!isOccupied(mouseX, mouseY)) {
            CreateParticleAsCircle(mouseX, mouseY, 2);
        }
    }

    public void CreateParticleAsCircle(int centerX, int centerY, int radius) {
        for (int r = centerX - radius; r < centerX + (radius * 2); r++) {
            for (int c = centerY - radius; r < centerY + (radius * 2); c++) {
                if (particleMode.equals("Sand")) {
                    particleList.add(new Sand(r, c));
                }
                else if (particleMode.equals("Stone")) {
                    particleList.add(new Stone(r, c));
                }
                else if (particleMode.equals("Water")) {
                    particleList.add(new Water(r, c));
                }
                else if (particleMode.equals("Smoke")) {
                    particleList.add(new Smoke(r, c));
                }
                else if (particleMode.equals("Seed")) {
                    particleList.add(new Seed(r, c));
                }
            }
        }
    }

    public void step() {
        for (Particle particle : particleList) {
            particle.setUpdated(false);
        }

        for (int i = particleList.size() - 1; i >= 0; i--) {
            Particle p = particleList.get(i);
            if (p instanceof Smoke) {
                Smoke smoke = (Smoke) p;
                smoke.incrementAge();
                if (smoke.getAge() >= smoke.getMaxAge() || smoke.getY() - particleSize < 0) {
                    particleList.remove(i);
                }
            }
        }

        for (int pass = 0; pass < 8; pass++) {
            update();
        }

        repaint();
    }

    public void editTimer() {
        if (isTimerEnabled) {
            timer.stop();
            isTimerEnabled = false;
        }
        else {
            timer.start();
            isTimerEnabled = true;
        }
    }

    public void clearCanvas() {
        particleList.clear();
        repaint();
    }

    public String getParticleMode() {
        return particleMode;
    }

    public void setParticleMode(String particleMode) {
        this.particleMode = particleMode;
    }
}
