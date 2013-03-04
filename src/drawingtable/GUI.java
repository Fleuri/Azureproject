/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package drawingtable;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import javax.swing.*;

/**
 *
 * @author Lauri Suomalainen
 */
public class GUI implements Runnable{
    
    private JFrame frame;
    
    public GUI(){};

    @Override
    public void run() {
        frame = new JFrame("DrawingTable");
        frame.setPreferredSize(new Dimension(200, 100));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        getComponents(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    private void getComponents(Container contentPane) {
        BasePicture pic = new BasePicture("C:/Users/Acer/Downloads/1360965450088.jpg");
        JLabel label = new JLabel(new ImageIcon(pic.getBasePicture()));
        contentPane.add(label);
        

}
}