/*
 * Code by NomNom (24 Aug 2010)
 * modified by Bruceoutdoors(2 Aug 2012)
 */
package drawingtable;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class paint extends Applet {

    public PaintWindow frame;

    public void run() {
        //main(new String[0]);
        frame = new PaintWindow();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        
    
// Show the frame
        
        frame.setVisible(true);
    }

//    public static void main(String[] args) {
//        
//
//    }

    public PaintWindow getFrame() {
        return this.frame;
    }
}


