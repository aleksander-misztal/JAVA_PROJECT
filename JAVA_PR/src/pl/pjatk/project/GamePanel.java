package pl.pjatk.project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener {
    //WYMIARY PLANSZY
    static final int SCREEN_WIDTH = 1000;
    static final int SCREEN_HEIGHT = 1000;
    int UNIT_SIZE = 25;
    int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT) / (UNIT_SIZE * UNIT_SIZE);
    //PREDKOSC GRY
    int GAMESPEED;
    //STEROWANIE
    boolean arrowSteering;
    //DANE WĘŻA
    final int x[] = new int[GAME_UNITS];
    final int y[] = new int[GAME_UNITS];
    int bodySize = 6;
    //JABLKO
    int applesEaten;
    int appleX;
    int appleY;
    //SUPERJABLKO
    int bonusAppleX;
    int bonusAppleY;
    //SPEEDYJABLKO
    int speedyAppleX;
    int speedyAppleY;
    //POCZATKOWY KIERUNEK
    char direction = 'R';

    boolean running = false;
    Timer timer;
    Random random;
    boolean withBorder;


    GamePanel(int delay, boolean withBorders, int mapSize, boolean arrowSteering) {
        //IMPORT USTAWIEN
        this.GAMESPEED = delay;
        this.withBorder = withBorders;
        this.UNIT_SIZE = mapSize;
        this.arrowSteering = arrowSteering;

        random = new Random();

        //USTAWIENIE OKNA I START GRY
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        startGame();
    }

    //START GRY
    public void startGame() {
        newApple();
        running = true;
        timer = new Timer(GAMESPEED, this);
        timer.start();
    }

    //Malowanie komponentow
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    //NANOSZENIE ELEMENTOW NA PLANSZE WAZ JABLKA POZYCJE I KOLORY
    public void draw(Graphics g) {
        if (running) {
            g.setColor(Color.red);
            g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);

            if (applesEaten % 15 == 0 && applesEaten > 14) {
                g.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
                g.fillOval(bonusAppleX, bonusAppleY, UNIT_SIZE, UNIT_SIZE);
            }
            if (applesEaten % 20 == 0 && applesEaten > 19) {
                g.setColor(new Color(random.nextInt(255), 255, 255));
                g.fillOval(speedyAppleX, speedyAppleY, UNIT_SIZE, UNIT_SIZE);
            }

            for (int i = 0; i < bodySize; i++) {
                if (i == 0) {
                    g.setColor(Color.white);
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                } else {
                    g.setColor(new Color(233, 233, 233));
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                }
            }
            g.setColor(Color.blue);
            g.setFont(new Font("Arial", Font.BOLD, 40));
            FontMetrics metrics = getFontMetrics(g.getFont());
            g.drawString("Score: " + applesEaten, (SCREEN_WIDTH - metrics.stringWidth("Score: " + applesEaten)) / 2, g.getFont().getSize());
        }
        //JEZELI KONIEC GRY WYSWIETLA INNE WARTOSCI
        else {
            gameOver(g);
        }
    }

    //GENERUJE KOORDYNATY NOWEGO JABLKA
    public void newApple() {
        appleX = random.nextInt((int) (SCREEN_WIDTH / UNIT_SIZE)) * UNIT_SIZE;
        appleY = random.nextInt((int) (SCREEN_HEIGHT / UNIT_SIZE)) * UNIT_SIZE;
    }

    //GENERUJE KOORDYNATY NOWEGO SUPERJABLKA
    public void newBonusApple() {
        bonusAppleX = random.nextInt((int) (SCREEN_WIDTH / UNIT_SIZE)) * UNIT_SIZE;
        bonusAppleY = random.nextInt((int) (SCREEN_HEIGHT / UNIT_SIZE)) * UNIT_SIZE;
    }

    //GENERUJE KOORDYNATY NOWEGO SPEEDYJABLKA
    public void newSpeedyApple() {
        speedyAppleX = random.nextInt((int) (SCREEN_WIDTH / UNIT_SIZE)) * UNIT_SIZE;
        speedyAppleY = random.nextInt((int) (SCREEN_HEIGHT / UNIT_SIZE)) * UNIT_SIZE;
    }

    //FUNKCJA ODPOWIADA ZA MECHANIZM PORUSZANIA SIE W ZALEZNOSCI OD WYBRANEGO KIERUNKU
    public void move() {
        for (int i = bodySize; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }

        switch (direction) {
            case 'U':
                y[0] = y[0] - UNIT_SIZE;
                break;
            case 'D':
                y[0] = y[0] + UNIT_SIZE;
                break;
            case 'L':
                x[0] = x[0] - UNIT_SIZE;
                break;
            case 'R':
                x[0] = x[0] + UNIT_SIZE;
                break;
        }
    }

    //W ZALEZNOSCI OD PRZYCISKU INNA WARTOSC DIRECTION CO MA WPLYW NA KLASE MOVE
    public class MyKeyAdapter extends KeyAdapter {
        int pauseChecker = -1;

        @Override
        public void keyPressed(KeyEvent e) {
            if (arrowSteering == true) {//STEROWANIE STRZALKI
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        if (direction != 'R') {
                            direction = 'L';
                        }
                        break;
                    case KeyEvent.VK_RIGHT:
                        if (direction != 'L') {
                            direction = 'R';
                        }
                        break;
                    case KeyEvent.VK_UP:
                        if (direction != 'D') {
                            direction = 'U';
                        }
                        break;
                    case KeyEvent.VK_DOWN:
                        if (direction != 'U') {
                            direction = 'D';
                        }
                        break;
                    //PAUZA
                    case KeyEvent.VK_P:
                        if (pauseChecker == -1) {
                            timer.stop();
                            pauseChecker *= -1;
                        } else {
                            timer.start();
                            pauseChecker *= -1;
                        }
                        break;
                }

            } else {//STEROWANIE WSAD
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_A:
                        if (direction != 'R') {
                            direction = 'L';
                        }
                        break;
                    case KeyEvent.VK_D:
                        if (direction != 'L') {
                            direction = 'R';
                        }
                        break;
                    case KeyEvent.VK_W:
                        if (direction != 'D') {
                            direction = 'U';
                        }
                        break;
                    case KeyEvent.VK_S:
                        if (direction != 'U') {
                            direction = 'D';
                        }
                        break;
                    //PAUZA
                    case KeyEvent.VK_P:
                        if (pauseChecker == -1) {
                            timer.stop();
                            pauseChecker *= -1;
                        } else {
                            timer.start();
                            pauseChecker *= -1;
                        }
                        break;
                }
            }
        }
    }

    //SPRAWDZA CZY ZJADLEM JABLKO DOLiCZA STATY I GENERUJE NOWE JABLKO A CZASAMI SUPERJABLKO LUB SPEEDYJABLKO
    public void checkApple() {
        if ((x[0] == appleX) && (y[0] == appleY)) {
            bodySize++;
            applesEaten++;
            newApple();

            if (applesEaten % 15 == 0 && applesEaten > 9) {
                newBonusApple();
            }
            if (applesEaten % 20 == 0 && applesEaten > 9) {
                newSpeedyApple();
            }
        }
    }

    //SPRAWDZA CZY ZJADLEM SUPERJABLKO I DOLICZA STATY
    public void checkBonusApple() {
        if ((x[0] == bonusAppleX) && (y[0] == bonusAppleY)) {
            bodySize += 5;
            applesEaten += 5;
        }
    }

    public void checkSpeedyApple() {
        if ((x[0] == speedyAppleX) && (y[0] == speedyAppleY)) {
            bodySize += 1;
            applesEaten += 1;
            GAMESPEED -= 5;
            timer.stop();
            timer = new Timer(GAMESPEED, this);
            timer.start();
        }
    }

    //SPRAWDZANIE KOLIZJI
    public void checkCollisions() {
        //CZY ZJADLEM WLASNE CIALO
        for (int i = bodySize; i > 0; i--) {
            if ((x[0] == x[i]) && (y[0] == y[i])) {
                running = false;
            }
        }
        //CZY UDEZYLEM W BORDER W ZALEZNOSCI OD USTAWIEN PRZERYWA LUB TELEPORTUJE NA DRUGA STRONE MAPY
        if (x[0] < 0) {
            if (withBorder == false) {
                x[0] = SCREEN_WIDTH - UNIT_SIZE;
            } else {
                running = false;
            }
        }
        if (x[0] > SCREEN_WIDTH) {
            if (withBorder == false) {
                x[0] = 0;
            } else {
                running = false;
            }
        }
        if (y[0] < 0) {
            if (withBorder == false) {
                y[0] = SCREEN_HEIGHT;
            } else {
                running = false;
            }
        }
        if (y[0] > SCREEN_HEIGHT) {
            if (withBorder == false) {
                y[0] = 0;
            } else {
                running = false;
            }
        }
        if (!running) {
            timer.stop();
        }
    }

    public void gameOver(Graphics g) {
        //WYSWIETLA WYNIK PO GRZE
        g.setColor(Color.white);
        g.setFont(new Font("Arial", Font.BOLD, 75));
        FontMetrics metrics1 = getFontMetrics(g.getFont());
        g.drawString("Score: " + applesEaten, (SCREEN_WIDTH - metrics1.stringWidth("Score: " + applesEaten)) / 2, 3 * SCREEN_HEIGHT / 5);

        //WYSWIETLA GAMEOVER PO GRZE
        g.setColor(Color.white);
        g.setFont(new Font("Arial", Font.BOLD, 75));
        FontMetrics metrics2 = getFontMetrics(g.getFont());
        g.drawString("Game Over", (SCREEN_WIDTH - metrics2.stringWidth("Game Over")) / 2, SCREEN_HEIGHT / 2);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (running) {
            move();
            checkApple();
            checkBonusApple();
            checkSpeedyApple();
            checkCollisions();

        }
        repaint();
    }

}
