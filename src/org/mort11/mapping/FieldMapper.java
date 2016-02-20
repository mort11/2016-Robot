package org.mort11.mapping;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class FieldMapper extends JPanel implements ActionListener, KeyListener {
    static final int SCREEN_WIDTH = 800;//Use this value to change the screen size.
    static final double FIELD_SIZE_X = 27;//Feet.
    static final double FIELD_SIZE_Y = 54;//Feet.
    static final double ROBOT_SIZE_X = 2.5;//Feet.
    static final double ROBOT_SIZE_Y = 3.6;//Feet.
    static final int ROBOT_PIXEL_WIDTH = (int) Math.round(SCREEN_WIDTH / FIELD_SIZE_Y * ROBOT_SIZE_X);
    static final int ROBOT_PIXEL_HEIGHT = (int) Math.round(SCREEN_WIDTH / FIELD_SIZE_Y * ROBOT_SIZE_Y);
    Timer t = new Timer(5, this);
    int rPos_x = 0, rPos_y = 0, velx = 0, vely = 0;
    int screenHeight = 0;              //Calculated later.
    private ImageIcon mapImage;
    private ImageIcon robotImage;

    private double robotAttitude = 0;
    private double velA = 0;

    public FieldMapper() {
        t.start();
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);

        String picturePath = "C:/Users/Britney Rivera/Pictures/Robotics/";
        String filename_bg = "CaptureofField2016Robotics.JPG";
        String filestring_bg = picturePath + filename_bg;
        mapImage = new ImageIcon(filestring_bg);
        double Iheight = mapImage.getIconHeight();
        double Iwidth = mapImage.getIconWidth();
        screenHeight = (int) Math.round(Iheight / Iwidth * (double) SCREEN_WIDTH);

        String filename_fg = "colored 2016 Robot1.png";
        String filestring_fg = picturePath + filename_fg;
        robotImage = new ImageIcon(filestring_fg);

    }

    public static void main(String arge[]) {
        JFrame f = new JFrame();
        FieldMapper s = new FieldMapper();
        f.add(s);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(800, 800);
        f.setVisible(true);
    }

    private void RobotToScreenXY() {
        //origVector = (origin,Robot);
        //rotatedVector = originVector.rotatecw(RobotAttitude);
        //offsetVector = subtract(originVector,rotatedVector);
        //screenXY = rotatedxy + offsetVector;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        //g.setColor(Color.RED);
        //g.fillRect(x,y,50,30);

        Graphics2D g2 = (Graphics2D) g;
        g2.rotate(Math.PI / -2, SCREEN_WIDTH / 2, screenHeight / 2);
        g.drawImage(mapImage.getImage(), 0, 0, SCREEN_WIDTH,
                screenHeight, this);
        g2.rotate(robotAttitude, rPos_x + ROBOT_PIXEL_HEIGHT / 2,
                rPos_y + ROBOT_PIXEL_WIDTH / 2);//Math.PI/4
        g.drawImage(robotImage.getImage(), rPos_x, rPos_y,
                ROBOT_PIXEL_HEIGHT, ROBOT_PIXEL_WIDTH, this);
    }

    public void actionPerformed(ActionEvent e) {
        if (rPos_x < 0) {
            velx = 0;
            rPos_x = 0;
        }

        if (rPos_x > 2530) {
            velx = 0;
            rPos_x = 2530;
        }

        if (rPos_y < 0) {
            vely = 0;
            rPos_y = 0;
        }

        if (rPos_y > 2330) {
            vely = 0;
            rPos_y = 2330;
        }

        if (robotAttitude > Math.PI * 2) {
            robotAttitude = 0;
        }

        if (robotAttitude < 0) {
            robotAttitude = Math.PI * 2;
        }

        robotAttitude += velA;
        rPos_x += velx;
        rPos_y += vely;
        repaint();
    }

    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_DOWN) {
            vely = 1;
            velx = 0;

            //vely = 0;
            //velx = -1;
        }
        if (code == KeyEvent.VK_UP) {
            vely = -1;
            velx = 0;

            //vely = 0;
            //velx = 1;
        }
        if (code == KeyEvent.VK_LEFT) {
            vely = 0;
            velx = -1;

            //vely = -1;
            //velx = 0;
        }
        if (code == KeyEvent.VK_RIGHT) {
            vely = 0;
            velx = 1;

            //vely = 1;
            //velx = 0;
        }
        if (code == KeyEvent.VK_A) {
            velA = Math.PI * 2 / 100;
        }
        if (code == KeyEvent.VK_D) {
            velA = Math.PI * 2 / -100;
        }
    }

    public void keyTyped(KeyEvent e) {

    }

    public void keyReleased(KeyEvent e) {
        velx = 0;
        vely = 0;
        velA = 0;
    }
}
