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
    private BufferedImage originalPicture;

    public BasePicture(File f) {
        picture = null;
        try {
            picture = ImageIO.read(f);
             if (picture == null) {
                createBlankPicture();
            }  else {
            originalPicture = picture;
            }
        } catch (IOException e) {
            createBlankPicture();
        }
    }

    public BasePicture(String FileName) {
        picture = null;
        try {
            picture = ImageIO.read(new File(FileName));
            if (picture == null) {
                createBlankPicture();
            }  else {
            originalPicture = picture;
            }
        } catch (IOException e) {
            System.out.println(e);
            createBlankPicture();
        }
    }

    public BasePicture() {
        createBlankPicture();
    }

    public final void createBlankPicture() {
        File f = new File("blankslide.jpg");
        try {
            picture = ImageIO.read(f);
            originalPicture = picture;
        } catch (IOException e) {
            System.out.println(e);
            System.exit(0);
        }
    }

    public BufferedImage getBasePicture() {
        return picture;
    }

    public BufferedImage getOriginalBasePicture() {
        return originalPicture;
    }

    public boolean setNewBasePicture(File f) {
        BufferedImage tempPic = null;
        try {
            tempPic = ImageIO.read(f);
            originalPicture = picture;
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
            originalPicture = picture;
        } catch (IOException e) {
            System.out.println(e);
            return false; // return joko viesti tai blanko kuva.
        }
        picture = tempPic;
        return true;
    }
}
