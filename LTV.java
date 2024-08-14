import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;

public class LTV extends Vehicles {

    Image myimage;

    public LTV(int nx, int ny, int nlane) {
        super(nx, ny, nlane);
        width = 150;
        height = 65;
        speed = 3;
        try {
            myimage = ImageIO.read(new File("car1.png"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void createVehicle(Graphics g) {
        // g.setColor(Color.BLUE);
        // g.fillRect(x, y, width, height);
        g.drawImage(myimage, x, y, width, height, null);
    }

}
