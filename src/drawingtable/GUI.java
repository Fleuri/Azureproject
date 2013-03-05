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
        
//        JMenuItem load = new JMenuItem();
//        save.setText("Load");
//        load.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                loadmenu(evt);
//            }
//        });

        frame.setJMenuBar(menu);
        menu.setVisible(true);


        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        addMouseListener(frame.getContentPane().getComponent(0));
        frame.setPreferredSize(new Dimension(1000, 1000));
        frame.pack();
        frame.setVisible(true);



    }

    private void savemenu(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        saveImageActionPerformed(icon.getImage());
    }

//    private Image loadmenu(java.awt.event.ActionEvent evt) {
//        Image img = loadImageActionPerformed();
//        return img;      
//    }

    private void getComponents(Container contentPane) {
        
        File file = loadImageActionPerformed();
        if (file == null) {
            pic = new BasePicture();
        } else {
        pic = new BasePicture(file);
    }
        icon = new ImageIcon(pic.getBasePicture());

        JLabel label = new JLabel(icon);
        contentPane.add(label);


    }

    private void addMouseListener(Component component) {
        listener = new MausListener(component);
        listener.giveImage(icon.getImage());
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

    private File loadImageActionPerformed() {
        JFileChooser chooser = new JFileChooser();
        BufferedImage img;
        
        chooser.showOpenDialog(null);
        File file = chooser.getSelectedFile();

        return file;
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
