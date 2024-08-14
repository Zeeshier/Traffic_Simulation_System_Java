import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.awt.Image;

import javax.imageio.ImageIO;


public class HTV extends Vehicles {

    Image myimage;

    public HTV(int nx, int ny,int nlane) {
        super(nx, ny, nlane);
        width = 240;
        height = 95;
        speed = 1;
        try {
            myimage = ImageIO.read(new File("truck1.png"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void createVehicle(Graphics g) {
        // g.setColor(Color.RED);
        // g.fillRect(x, y, width, height);
        g.drawImage(myimage, x, y, width, height, null);
    }
}