package pl.pjatk.project;

import javax.swing.*;

public class GameFrame extends JFrame {
    GameFrame(int delay,boolean withBorder,int mapSize, boolean arrowSteering){
        this.add(new GamePanel(delay,withBorder,mapSize,arrowSteering));
        this.setTitle("Snake");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);

    }
}
