package pl.pjatk.project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Settings implements ActionListener {
    JFrame frame;

    JButton[] speedButtons = new JButton[4];
    JButton[] borderButtons = new JButton[2];
    JButton[] sizeButtons = new JButton[3];
    JButton[] steeringButtons = new JButton[2];
    JButton[] controlButtons = new JButton[2];

    JButton smallSpeedBtn,mediumSpeedBtn,highSpeedBtn,insaneSpeedBtn;
    JButton noBorderBtn, WithBorderBtn;
    JButton smallMapBtn, mediumMapBtn, bigMapBtn;
    JButton startBtn,exitBtn;
    JButton wsadBtn,arrowsBtn;
    JPanel panel1,panel2,panel3,panel4,panel5,speedLabelPanel,borderLabelPanel,sizeLabelPanel,steeringLabPanel;

    Font myFont = new Font("Arial",Font.BOLD,30);

    int speed=75,size=40;
    boolean withBorder=true,arrowSteering=true;

    Settings() {
        frame = new JFrame("SETTINGS");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(750, 1000);
        frame.setLayout(null);

        Label speedLabel = new Label("SPEED");
        Label borderLabel = new Label("MODE");
        Label sizeLabel = new Label("MAPSIZE");
        Label steeringLabel = new Label("STEERING");

        smallSpeedBtn = new JButton("Slow");
        mediumSpeedBtn = new JButton("Medium");
        highSpeedBtn = new JButton("Fast");
        insaneSpeedBtn = new JButton("Insane");

        noBorderBtn = new JButton("With Borders");
        WithBorderBtn = new JButton("No Borders");

        smallMapBtn = new JButton("Small");
        mediumMapBtn = new JButton("Medium");
        bigMapBtn = new JButton("Big");

        wsadBtn = new JButton("WSAD");
        arrowsBtn = new JButton("ARROWS");

        startBtn = new JButton("Start");
        exitBtn = new JButton("Exit");

        speedButtons[0] = smallSpeedBtn;
        speedButtons[1] = mediumSpeedBtn;
        speedButtons[2] = highSpeedBtn;
        speedButtons[3] = insaneSpeedBtn;

        borderButtons[0] = noBorderBtn;
        borderButtons[1] = WithBorderBtn;

        sizeButtons[0] = smallMapBtn;
        sizeButtons[1] = mediumMapBtn;
        sizeButtons[2] = bigMapBtn;

        steeringButtons[0] = wsadBtn;
        steeringButtons[1] = arrowsBtn;

        controlButtons[0] = startBtn;
        controlButtons[1] = exitBtn;

        for(int i =0;i<4;i++) {
            speedButtons[i].addActionListener(this);
            speedButtons[i].setFont(myFont);
            speedButtons[i].setFocusable(false);
        }
        for(int i =0;i<2;i++) {
            borderButtons[i].addActionListener(this);
            borderButtons[i].setFont(myFont);
            borderButtons[i].setFocusable(false);
        }
        for(int i =0;i<3;i++) {
            sizeButtons[i].addActionListener(this);
            sizeButtons[i].setFont(myFont);
            sizeButtons[i].setFocusable(false);
        }
        for(int i =0;i<2;i++) {
            steeringButtons[i].addActionListener(this);
            steeringButtons[i].setFont(myFont);
            steeringButtons[i].setFocusable(false);
        }
        for(int i =0;i<2;i++) {
            controlButtons[i].addActionListener(this);
            controlButtons[i].setFont(myFont);
            controlButtons[i].setFocusable(false);
        }

        speedLabel.setFont(myFont);
        borderLabel.setFont(myFont);
        sizeLabel.setFont(myFont);
        steeringLabel.setFont(myFont);

        //NAPIS SPEED
        speedLabelPanel = new JPanel();
        speedLabelPanel.setBounds(75, 10, 600, 50);
        speedLabelPanel.add(speedLabel);
        //GUZIKI SPEED
        panel1 = new JPanel();
        panel1.setBounds(50, 70, 650, 100);
        panel1.setLayout(new GridLayout(1,4,10,10));
        panel1.add(speedButtons[0]);
        panel1.add(speedButtons[1]);
        panel1.add(speedButtons[2]);
        panel1.add(speedButtons[3]);

        //NAPIS BORDER
        borderLabelPanel = new JPanel();
        borderLabelPanel.setBounds(75, 180, 600, 50);
        borderLabelPanel.add(borderLabel);
        //GUZIKI BORDER
        panel2 = new JPanel();
        panel2.setBounds(50, 240, 650, 100);
        panel2.setLayout(new GridLayout(1,2,10,10));
        panel2.add(borderButtons[0]);
        panel2.add(borderButtons[1]);

        //NAPIS SIZE
        sizeLabelPanel = new JPanel();
        sizeLabelPanel.setBounds(75, 350, 600, 50);
        sizeLabelPanel.add(sizeLabel);
        //GUZIKI SIZE
        panel3 = new JPanel();
        panel3.setBounds(50, 410, 650, 100);
        panel3.setLayout(new GridLayout(1,4,10,10));
        panel3.add(sizeButtons[0]);
        panel3.add(sizeButtons[1]);
        panel3.add(sizeButtons[2]);

        //NAPIS STEERING
        steeringLabPanel = new JPanel();
        steeringLabPanel.setBounds(75, 520, 600, 50);
        steeringLabPanel.add(steeringLabel);
        //GUZIKI STEERING
        panel4 = new JPanel();
        panel4.setBounds(50, 580, 650, 100);
        panel4.setLayout(new GridLayout(1,2,10,10));
        panel4.add( steeringButtons[0]);
        panel4.add( steeringButtons[1]);

        //GUZIKI START EXIT
        panel5 = new JPanel();
        panel5.setBounds(50, 750, 650, 100);
        panel5.setLayout(new GridLayout(1,2,10,10));
        panel5.add( controlButtons[0]);
        panel5.add( controlButtons[1]);

        frame.add(speedLabelPanel);
        frame.add(panel1);
        frame.add(borderLabelPanel);
        frame.add(panel2);
        frame.add(sizeLabelPanel);
        frame.add(panel3);
        frame.add(steeringLabPanel);
        frame.add(panel4);
        frame.add(panel5);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //SPEED SELECTION
        if(e.getSource()==smallSpeedBtn) {
            speed=100;
        }
        if(e.getSource()==mediumSpeedBtn) {
            speed=60;
        }
        if(e.getSource()==highSpeedBtn) {
            speed=35;
        }
        if(e.getSource()==insaneSpeedBtn) {
            speed=15;
        }
        //BORDER SELECTION
        if(e.getSource()==noBorderBtn) {
            withBorder=true;
        }
        if(e.getSource()==WithBorderBtn) {
            withBorder=false;

        }
        //SIZE SELECTION
        if(e.getSource()==smallMapBtn) {
            size=50;

        }
        if(e.getSource()==mediumMapBtn) {
            size=40;

        }
        if(e.getSource()==bigMapBtn) {
            size=25;

        }
        //STEERING SELECTION
        if(e.getSource()==wsadBtn) {
            arrowSteering=false;

        }
        if(e.getSource()==arrowsBtn) {
            arrowSteering=true;
        }

        if(e.getSource()==startBtn) {
            new GameFrame( speed, withBorder, size, arrowSteering);
        }
        if(e.getSource()==exitBtn) {
            System.exit(0);
        }

    }

}
