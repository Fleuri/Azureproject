/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package drawingtable;

import java.applet.Applet;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseMotionAdapter;

/**
 *
 * @author Lauri Suomalainen
 */
public class GUI extends JApplet implements Runnable {

    private JFrame frame;
    MausListener listener;
    
    public GUI() {
        
    }

    ;

    @Override
    public void run() {
        frame = new JFrame("DrawingTable");
        frame.setPreferredSize(new Dimension(200, 100));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        getComponents(frame.getContentPane());
        addMouseListener(frame.getContentPane().getComponent(0));
        frame.pack();
        frame.setVisible(true);

        
    }

    private void getComponents(Container contentPane) {
        
        BasePicture pic = new BasePicture("C:/Users/Acer/Downloads/Aatujakelkka.jpg");
        JLabel label = new JLabel(new ImageIcon(pic.getBasePicture()));
        contentPane.add(label);
    }
    private void addMouseListener(Component component) {
        listener = new MausListener(component);
        component.addMouseListener(listener);
        component.addMouseMotionListener(listener);
    }    
}
