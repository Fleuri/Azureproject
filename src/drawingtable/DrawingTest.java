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
import java.awt.Color;
import java.awt.Event;
import java.awt.Graphics;


public class DrawingTest extends Applet {

    protected int lastX = 0, lastY = 0;

    public void init() {
        setBackground(Color.white);
    }

    public boolean mouseEnter(Event e, int x, int y) {
        return (record(x, y));
    }

    public boolean mouseDown(Event e, int x, int y) {
        return (record(x, y));
    }

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
