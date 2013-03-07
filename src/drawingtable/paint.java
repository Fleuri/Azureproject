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
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class paint
{
public static void main(String[] args)
{
PaintWindow frame = new PaintWindow();
frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
frame.setResizable(true);
frame.setVisible(true);
}

}

class PaintWindow extends JFrame
{
public PaintWindow()
{
setTitle("Paint it");
setSize(300, 300);

panel = new JPanel();
drawPad = new PadDraw();

panel.setPreferredSize(new Dimension(32, 68));

//Creates a new container
Container content = this.getContentPane();
content.setLayout(new BorderLayout());

//sets the panel to the left, padDraw in the center
content.add(panel, BorderLayout.WEST);
content.add(drawPad, BorderLayout.CENTER);

//add the color buttons:
makeColorButton(Color.BLUE);
makeColorButton(Color.MAGENTA);
makeColorButton(Color.RED);
makeColorButton(Color.GREEN);
makeColorButton(Color.BLACK);

//creates the clear button
JButton clearButton = new JButton("Clear");
clearButton.addActionListener(new ActionListener()
{
public void actionPerformed(ActionEvent e)
{
drawPad.clear();
}
});
panel.add(clearButton);
}

/*
* makes a button that changes the color
* @param color the color used for the button
*/
public void makeColorButton(final Color color)
{
JButton tempButton = new JButton();
tempButton.setBackground(color);
tempButton.setPreferredSize(new Dimension(16, 16));
panel.add(tempButton);
tempButton.addActionListener(new ActionListener()
{
public void actionPerformed(ActionEvent e)
{
drawPad.changeColor(color);
}
});
}

private JPanel panel;
private PadDraw drawPad;
}

class PadDraw extends JComponent
{
//this is gonna be your image that you draw on
Image image;
//this is what we'll be using to draw on
Graphics2D graphics2D;
//these are gonna hold our mouse coordinates
int currentX, currentY, oldX, oldY;

public PadDraw()
{
setDoubleBuffered(false);
addMouseListener(new MouseAdapter()
{
//if the mouse is pressed it sets the oldX & oldY
//coordinates as the mouses x & y coordinates
public void mousePressed(MouseEvent e)
{
oldX = e.getX();
oldY = e.getY();
}
});

addMouseMotionListener(new MouseMotionAdapter()
{
//while the mouse is dragged it sets currentX & currentY as the mouses x and y
//then it draws a line at the coordinates
//it repaints it and sets oldX and oldY as currentX and currentY
public void mouseDragged(MouseEvent e)
{
currentX = e.getX();
currentY = e.getY();

graphics2D.drawLine(oldX, oldY, currentX, currentY);
repaint();

oldX = currentX;
oldY = currentY;
}
});
}

//this is the painting bit
//if it has nothing on it then
//it creates an image the size of the window
//sets the value of Graphics as the image
//sets the rendering
//runs the clear() method
//then it draws the image
public void paintComponent(Graphics g) {

if(image == null) {
image = createImage(getSize().width, getSize().height);
graphics2D = (Graphics2D)image.getGraphics();
graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

clear();
}

g.drawImage(image, 0, 0, null);
}

//this is the clear
//it sets the colors as white
//then it fills the window with white
//thin it sets the color back to black
public void clear() {
    
graphics2D.setPaint(Color.white);
graphics2D.fillRect(0, 0, getSize().width, getSize().height);
graphics2D.setPaint(Color.black);
repaint();
}

public void changeColor(Color theColor)
{
graphics2D.setPaint(theColor);
repaint();
}
public RenderedImage convertToRenderedImage(Image image) {

    BufferedImage bImage      = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_RGB);
    Graphics2D bImageGraphics = bImage.createGraphics();
    bImageGraphics.drawImage(image, null, null);
    RenderedImage rImage      = (RenderedImage)bImage;
    return rImage;
}

public boolean saveImage() {
JFileChooser filechooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG Images", "jpg");
        filechooser.setFileFilter(filter);
//        robot  = new Robot();
//        BufferedImage buffer = pic.getBasePicture();
//        Rectangle screenRect = new Rectangle((int)(frame.getLocationOnScreen().getX()), (int)(frame.getLocationOnScreen().getY()), (int)(frame.getSize().getHeight()), (int)(frame.getSize().getWidth()));
//        BufferedImage capture = new Robot().createScreenCapture(screenRect);
        int result = filechooser.showSaveDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File saveFile = filechooser.getSelectedFile();
            RenderedImage rImage = convertToRenderedImage(image);
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



