/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package drawingtable;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.Graphics;
import javax.swing.event.MouseInputListener;

/**
 *
 * @author Lauri Suomalainen
 */
public class MausListener implements MouseInputListener {

    int lastX;
    int lastY;
    Component component;

    public MausListener(Component component) {
        this.component = component;


    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Graphics g = component.getGraphics();
        g.drawLine(lastX, lastY, lastX, lastY);

    }

    @Override
    public void mousePressed(MouseEvent e) {
        draw(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        lastX = e.getX();
        lastY = e.getY();
//        System.out.println(lastX + ": " + lastY);

    }

    @Override
    public void mouseExited(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        draw(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        lastX = e.getX();
        lastY = e.getY();
//        System.out.println(lastX + ": " + lastY);

    }

    private void draw(MouseEvent e) {
        if (e.getModifiers() == 16) {
            Graphics g = component.getGraphics();
            g.drawLine(lastX, lastY, e.getX(), e.getY());
            lastX = e.getX();
            lastY = e.getY();
        } else if (e.getModifiers() == 4) {

        }
    }
}
