/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package drawingtable;

/**
 *
 * @author tanelvir
 */
import java.applet.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Event;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JApplet;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DrawingTest extends Applet {

    protected int lastX = 0, lastY = 0;
    Image draw;
    Image bg;

    public void init() {
        //setBackground(Color.white);
        
    }

    @Override
    public boolean mouseEnter(Event e, int x, int y) {
        return (record(x, y));
    }

    @Override
    public boolean mouseDown(Event e, int x, int y) {
        return (record(x, y));
    }

    @Override
    public boolean mouseDrag(Event e, int x, int y) {
        Graphics g = getGraphics();
        g.drawLine(lastX, lastY, x, y);
        return (record(x, y));
    }


    protected boolean record(int x, int y) {
        lastX = x;
        lastY = y;
        return (true);
    }
}
