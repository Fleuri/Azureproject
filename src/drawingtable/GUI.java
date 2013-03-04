/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package drawingtable;

import java.applet.Applet;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.image.BufferedImage;
import javax.swing.*;
import java.awt.Graphics;
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

    public GUI() {
        MouseListener listener;
    }

    ;

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
        
        BasePicture pic = new BasePicture("haisuli.jpg");
        JLabel label = new JLabel(new ImageIcon(pic.getBasePicture()));
        contentPane.add(label);
        
        
        label.addMouseMotionListener(new MouseAdapter() {
            Graphics g = getGraphics();
            private int x, y;
            int lastx, lasty = 0;
            public void mouseDragged(MouseEvent e) {
                x = e.getX();
                y = e.getY();
                g.drawLine(lastx, lasty, x, y);
                System.out.println("LOL");
            }
        });
    }
}
