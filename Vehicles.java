import java.awt.Graphics;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JPanel;
import java.util.*;
import javax.swing.*;
import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.*;

public class Vehicles {
    int x;
    int y;
    int width = 0;
    int height = 0;
    int speed = 0;
    int vehlane;

    public Vehicles(int nx, int ny,int nlane) {
        x = nx;
        y = ny;
        vehlane=nlane;
    }

    public void createVehicle(Graphics g) {

    }
    public int getLane(){
        return vehlane;
    }

    public void setLane(int lll){
        vehlane=lll;
    }

    public int getWidth() {
        return width;
    }

    public int getX() {
        return x;
    }

    public void setX(int nx) {
        x = nx;
    }

    public int getSpeed() {
        return speed;
    }

    public int getY() {
        return y;
    }

    public void setY(int ny) {
        y = ny;
    }

    public void setSpeed(int speed) {
        speed = this.speed;
    }

}