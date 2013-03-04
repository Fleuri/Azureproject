/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package drawingtable;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Stack;
/**
 *
 * @author Lauri Suomalainen
 */
public class BasePicture {
    
    private BufferedImage picture;
    private Stack layerStack = new Stack<Layer>();
    
    public BasePicture(File f) {
        picture = null;
        try {
            picture = ImageIO.read(f);
        } catch (IOException e) {
            System.out.println(e);
            System.exit(0); // return joko viesti tai blanko kuva.
    }
}
    public BasePicture (String FileName) {
        picture = null;
        try {
            picture = ImageIO.read(new File(FileName));
        } catch (IOException e) {
            System.out.println(e);
            System.exit(0); // return joko viesti tai blanko kuva.
    }
    }
    public BasePicture() {
        //picture = blank.jpg tai jotain
        
    }
    public BufferedImage getBasePicture() {
        return picture;
    }
    public boolean setNewBasePicture(File f) {
        BufferedImage tempPic = null;
        try {
            tempPic = ImageIO.read(f);
        } catch (IOException e) {
            System.out.println(e);
            return false; // return joko viesti tai blanko kuva.
    }
        picture = tempPic;
        return true;
}
    public boolean setNewBasePicture(String fileName) {
        BufferedImage tempPic = null;
        try {
            tempPic = ImageIO.read(new File(fileName));
        } catch (IOException e) {
            System.out.println(e);
            return false; // return joko viesti tai blanko kuva.
    }
        picture = tempPic;
        return true;
}
}


