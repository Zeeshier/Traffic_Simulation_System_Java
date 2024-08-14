import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;

public class Bike extends Vehicles {

    Image myimage;

    public Bike(int nx, int ny, int nlane) {
        super(nx, ny, nlane);
        width = 100;
        height = 40;
        speed = 5;
        // width = 170;
        // height = 65;
        // speed = 8;
        try {
            myimage = ImageIO.read(new File("bike1.png"));
            // myimage = ImageIO.read(new File("1rb19.png"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void createVehicle(Graphics g) {
        // g.setColor(Color.green);
        // g.fillRect(x, y, width, height);
        g.drawImage(myimage, x, y - 5, width, height, null);
        // g.drawImage(myimage, x, y - 20, width, height, null);
    }

}
