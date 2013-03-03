/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package drawingtable;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;

/**
 *
 * @author Lauri Suomalainen
 */
public class BasePicture {
    
    private BufferedImage picture;
    
    public BasePicture(File f) {
        picture = null;
        try {
            picture = ImageIO.read(f);
        } catch (IOException e) {
            System.out.println(e);
            System.exit(0); // return joko viesti tai blanko kuva.
    }
}
}


