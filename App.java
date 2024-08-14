import java.util.*;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.lang.Thread;

public class App extends Thread implements ActionListener {
    JFrame frame;
    JButton start, stop, addhtv, addltv, addbike, l1, l2, l3, red, green;
    JLabel highwayoutput, highwaydensity;
    Container c;
    Road road;
    int running;
    int lane;

    Graphics g;

    int density = 0;

    int CAR_COUNT;
    long starttime = 0;

    public App() {
        frame = new JFrame("Traffic Simulation");
        frame.setBounds(-10, 0, 1600, 750);
        frame.setResizable(false);

        // frame.setLayout(road.BorderLayout.CENTER);
        road = new Road(0,0,120,1370);
        // frame.setLayout(new BorderLayout());
        road.setPreferredSize(new Dimension(1600, 500));
        frame.add(road, BorderLayout.CENTER);

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(null);

        controlPanel.setPreferredSize(new Dimension(1600, 250));

        highwayoutput = new JLabel("Output: " + 0);
        highwayoutput.setBounds(1130, 10, 200, 70);
        controlPanel.add(highwayoutput);

        highwaydensity = new JLabel("Density: " + 0);
        highwaydensity.setBounds(1130, 90, 200, 70);
        controlPanel.add(highwaydensity);

        start = new JButton("Start");
        start.setBounds(0, 10, 150, 70);
        start.addActionListener(this);
        controlPanel.add(start);

        stop = new JButton("Stop");
        stop.setBounds(0, 90, 150, 70);
        stop.addActionListener(this);
        controlPanel.add(stop);

        addhtv = new JButton("Add HTV");
        addhtv.setBounds(170, 10, 200, 70);
        addhtv.addActionListener(this);
        controlPanel.add(addhtv);

        addltv = new JButton("Add LTV");
        addltv.setBounds(170, 90, 200, 70);
        addltv.addActionListener(this);
        controlPanel.add(addltv);

        // addbike = new JButton("Add RB 19");
        addbike = new JButton("Add Bike");
        addbike.setBounds(170, 170, 200, 70);
        addbike.addActionListener(this);
        controlPanel.add(addbike);

        l1 = new JButton("Lane 1");
        l1.setBounds(390, 10, 200, 70);
        l1.addActionListener(this);
        controlPanel.add(l1);

        l2 = new JButton("Lane 2");
        l2.setBounds(390, 90, 200, 70);
        l2.addActionListener(this);
        controlPanel.add(l2);

        l3 = new JButton("Lane 3");
        l3.setBounds(390, 170, 200, 70);
        l3.addActionListener(this);
        controlPanel.add(l3);

        red = new JButton("RED");
        red.setBounds(600, 10, 100, 50);
        red.addActionListener(this);
        controlPanel.add(red);

        green = new JButton("Green");
        green.setBounds(600, 70, 100, 50);
        green.addActionListener(this);
        controlPanel.add(green);

        frame.add(controlPanel, BorderLayout.SOUTH);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == start) {
            if (running == 0) {
                running = 1;
                Thread t = new Thread(this);
                t.start();
                road.setCount();
                starttime = System.currentTimeMillis();
            }
        } else if (e.getSource() == stop) {
            running = 0;
            road.setCount();
        } else if (e.getSource() == l1) {
            lane = 1;
        } else if (e.getSource() == l2) {
            lane = 2;
        } else if (e.getSource() == l3) {
            lane = 3;
        } else if (e.getSource() == addhtv) {
            if (lane == 1) {
                HTV tsthtv = new HTV(10, 15, 1);
                road.addveh(tsthtv);
                density++;

            } else if (lane == 2) {
                HTV tsthtv = new HTV(10, 135, 2);
                road.addveh(tsthtv);
                density++;

            } else if (lane == 3) {
                HTV tsthtv = new HTV(10, 255, 3);
                road.addveh(tsthtv);
                density++;
            }
        } else if (e.getSource() == addltv) {
            if (lane == 1) {
                LTV tstltv = new LTV(10, 30, 1);
                road.addveh(tstltv);
                density++;

            } else if (lane == 2) {
                LTV tstltv = new LTV(10, 150, 2);
                road.addveh(tstltv);
                density++;

            } else if (lane == 3) {
                LTV tstltv = new LTV(10, 270, 3);
                road.addveh(tstltv);
                density++;

            }
        } else if (e.getSource() == addbike) {
            // playSound("Max.wav");
            if (lane == 1) {
                Bike tstbike = new Bike(10, 50, 1);
                road.addveh(tstbike);
                density++;

            } else if (lane == 2) {
                Bike tstbike = new Bike(10, 170, 2);
                road.addveh(tstbike);
                density++;

            } else if (lane == 3) {
                Bike tstbike = new Bike(10, 290, 3);
                road.addveh(tstbike);
                density++;
            }
        } else if (e.getSource() == red) {
            road.trflight = "red";
        } else if (e.getSource() == green) {
            road.trflight = "green";
        }
        frame.repaint();
    }

    private void playSound(String soundFileName) {
        try {
            File soundFile = new File(soundFileName);
            if (soundFile.exists()) {
                AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
                Clip clip = AudioSystem.getClip();
                clip.open(audioIn);
                clip.start();
            } else {
                System.err.println("Sound file not found: " + soundFileName);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void run() {
        while (running == 1) {
            road.step();
            CAR_COUNT = road.getCount();
            double tp = CAR_COUNT / (double) (System.currentTimeMillis() - starttime);
            highwayoutput.setText("Output: " + tp);
            highwaydensity.setText("Density: " + density);
            frame.repaint();
            try {
                Thread.sleep(10);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        App obj = new App();
    }
}
