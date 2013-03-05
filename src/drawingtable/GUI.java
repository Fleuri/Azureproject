/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package drawingtable;

import java.applet.Applet;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.MenuBar;

/**
 *
 * @author Lauri Suomalainen
 */
public class GUI extends JApplet implements Runnable {

    private JFrame frame;
    MausListener listener;
    ImageIcon icon;
    BasePicture pic;

    public GUI() {
    }

    ;

    @Override
    public void run() {
        frame = new JFrame("DrawingTable");
        getComponents(frame.getContentPane());
        
        JMenuBar menu = new JMenuBar();
        JMenu a = new JMenu();
        a.setText("FILE");
        JMenuItem save = new JMenuItem();
        save.setText("Save");
        save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                savemenu(evt);
            }
        });
        menu.add(a);
        a.add(save);
        
        frame.setJMenuBar(menu);
        menu.setVisible(true);


        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        
        addMouseListener(frame.getContentPane().getComponent(0));
        frame.setPreferredSize(new Dimension(pic.getBasePicture().getWidth(), pic.getBasePicture().getHeight()));
        frame.pack();
        frame.setVisible(true);



    }
    
    private void savemenu(java.awt.event.ActionEvent evt) {                                                  
        // TODO add your handling code here:
        saveImageActionPerformed(icon.getImage());
    }

    private void getComponents(Container contentPane) {

        pic = new BasePicture("haisuli.jpg");

        icon = new ImageIcon(pic.getBasePicture());       
        
        
        JLabel label = new JLabel(icon);
        contentPane.add(label);
//        saveB.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                saveImageActionPerformed(icon.getImage());
//            }
//        });


    }

    private void addMouseListener(Component component) {
        listener = new MausListener(component);
        component.addMouseListener(listener);
        component.addMouseMotionListener(listener);
    }

    private void saveImageActionPerformed(Image image) {
        JFileChooser filechooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "JPG Images", "jpg");
        filechooser.setFileFilter(filter);
        int result = filechooser.showSaveDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File saveFile = filechooser.getSelectedFile();
            BufferedImage bufferI = toBufferedImage(image);
            try {
                ImageIO.write(bufferI, "jpg", saveFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static BufferedImage toBufferedImage(Image img) {
        int width = img.getWidth(null);
        int height = img.getHeight(null);
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = bi.getGraphics();
        g.drawImage(img, 0, 0, null);
        g.dispose();
        return bi;
    }
}
