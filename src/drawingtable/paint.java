/*
 * Code by NomNom (24 Aug 2010)
 * modified by Bruceoutdoors(2 Aug 2012)
 */
package drawingtable;

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

public class paint {

    public static PaintWindow frame;

    public static void main(String[] args) {
        frame = new PaintWindow();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);

    }

    public PaintWindow getFrame() {
        return this.frame;
    }
}

final class PaintWindow extends JFrame {

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

class PadDraw extends JComponent {
//this is gonna be your image that you draw on

    Image image;
//this is what we'll be using to draw on
    Graphics2D graphics2D;
//these are gonna hold our mouse coordinates
    int currentX, currentY, oldX, oldY;
    int thickness;

    public PadDraw() {
        thickness = 8;
        setDoubleBuffered(false);
        addMouseListener(new MouseAdapter() {
//if the mouse is pressed it sets the oldX & oldY
//coordinates as the mouses x & y coordinates
            public void mousePressed(MouseEvent e) {
                oldX = e.getX();
                oldY = e.getY();
                graphics2D.fillOval(oldX, oldY, thickness, thickness);
                repaint();
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
//while the mouse is dragged it sets currentX & currentY as the mouses x and y
//then it draws a line at the coordinates
//it repaints it and sets oldX and oldY as currentX and currentY
            public void mouseDragged(MouseEvent e) {
                currentX = e.getX();
                currentY = e.getY();

                //drawLinePlz(oldX, oldY, currentX, currentY, thickness);
                graphics2D.fillOval(oldX, oldY, thickness, thickness);


                repaint();

                oldX = currentX;
                oldY = currentY;
            }
        });
    }

    public void paintComponent(Graphics g) {

        if (image == null) {
            image = createImage(getSize().width, getSize().height);
            graphics2D = (Graphics2D) image.getGraphics();
            graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            clear();
        }

        g.drawImage(image, 0, 0, null);
    }

    public void clear() {
        
        Color color = graphics2D.getColor();
        graphics2D.setPaint(Color.white);
        graphics2D.fillRect(0, 0, getSize().width, getSize().height);
        graphics2D.setPaint(color);
        repaint();
    }

    public void changeColor(Color theColor) {
        graphics2D.setPaint(theColor);
        repaint();
    }

    public void drawLinePlz(int oldX, int oldY, int currentX, int currentY, int thickness) {

        int dX = currentX - oldX;
        int dY = currentY - oldY;
        // line length
        double lineLength = Math.sqrt(dX * dX + dY * dY);

        double scale = (double) (thickness) / (2 * lineLength);

        // The x,y increments from an endpoint needed to create a rectangle...
        double ddx = -scale * (double) dY;
        double ddy = scale * (double) dX;
        ddx += (ddx > 0) ? 0.5 : -0.5;
        ddy += (ddy > 0) ? 0.5 : -0.5;
        int dx = (int) ddx;
        int dy = (int) ddy;

        // Now we can compute the corner points...
        int xPoints[] = new int[4];
        int yPoints[] = new int[4];

        xPoints[0] = oldX + dx;
        yPoints[0] = oldY + dy;
        xPoints[1] = oldX - dx;
        yPoints[1] = oldY - dy;
        xPoints[2] = currentX - dx;
        yPoints[2] = currentY - dy;
        xPoints[3] = currentX + dx;
        yPoints[3] = currentY + dy;

        graphics2D.fillPolygon(xPoints, yPoints, 4);
    }

    public void changeBrushSize(int size) {
        thickness = size;
    }

    void setNewImage(BufferedImage tempImage) {
            image = tempImage;
            graphics2D = (Graphics2D) image.getGraphics();
            graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            getGraphics().drawImage(image, 0, 0, null);
            
    }
}
