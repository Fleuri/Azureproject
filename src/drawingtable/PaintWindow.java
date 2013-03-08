/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package drawingtable;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author tanelvir
 */
public class PaintWindow extends JFrame {

    private JPanel panel;
    private PadDraw drawPad;

    public PaintWindow() {
        setTitle("tklDraw");
        setSize(300, 300);

        panel = new JPanel();
        drawPad = new PadDraw();

        panel.setPreferredSize(new Dimension(32, 68));

//Creates a new container
        Container content = this.getContentPane();
        content.setLayout(new BorderLayout());

//sets the panel to the left, padDraw in the center
        content.add(panel, BorderLayout.SOUTH);
        content.add(drawPad, BorderLayout.CENTER);

//add the color buttons:
        makeColorButton(Color.BLUE);
        makeColorButton(Color.MAGENTA);
        makeColorButton(Color.RED);
        makeColorButton(Color.GREEN);
        makeColorButton(Color.BLACK);
        makeColorButton(Color.WHITE);
        makeColorButton(Color.YELLOW);

//creates the clear button
        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                drawPad.clear();
            }
        });

        panel.add(clearButton);
        JMenuBar menu = new JMenuBar();
        JMenu a = new JMenu();
        a.setText("FILE");
        JMenuItem save = new JMenuItem();
        save.setText("Save");
        save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveImage();
            }
        });
        menu.add(a);
        a.add(save);
        JMenuItem load = new JMenuItem();
        load.setText("Load");
        load.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadmenu(evt);
            }
        });
        a.add(load);
        setJMenuBar(menu);
        menu.setVisible(true);
    }

    /*
     * makes a button that changes the color
     * @param color the color used for the button
     */
    public void makeColorButton(final Color color) {
        JButton tempButton = new JButton();
        tempButton.setBackground(color);
        tempButton.setPreferredSize(new Dimension(16, 16));
        panel.add(tempButton);
        tempButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                drawPad.changeColor(color);
            }
        });
    }

    private void loadmenu(java.awt.event.ActionEvent evt) {
        File file = loadImageActionPerformed();
        if (file == null) {
            return;
        } else {
            try {
                BufferedImage tempImage = ImageIO.read(file);
                drawPad.setNewImage(tempImage);
            } catch (IOException e) {
                System.out.println(e);
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

    public RenderedImage convertToRenderedImage(Image image) {

        BufferedImage bImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_RGB);
        Graphics2D bImageGraphics = bImage.createGraphics();
        bImageGraphics.drawImage(image, null, null);
        RenderedImage rImage = (RenderedImage) bImage;
        return rImage;
    }

    public boolean saveImage() {
        JFileChooser filechooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG Images", "jpg");
        filechooser.setFileFilter(filter);
        int result = filechooser.showSaveDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File saveFile = filechooser.getSelectedFile();
            RenderedImage rImage = convertToRenderedImage(drawPad.image);
            try {
                ImageIO.write(rImage, "jpg", saveFile);
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
