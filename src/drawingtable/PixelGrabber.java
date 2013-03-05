
package drawingtable;

/**
 *
 * @author tanelvir
 */
import java.awt.Image;
import java.awt.image.PixelGrabber;

class PixeGrabber {

    private int x;
    private int y;
    private int w;
    private int h;
    private PixelGrabber grabber;
    private Image image;

    public PixeGrabber(Image image) {
        this.image = image;
    }

    public void createGrabber(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        grabber = new PixelGrabber(image, x, y, w, h, true);
    }

    public void createGrabber() {
        grabber = new PixelGrabber(image, x, y, w, h, true);
    }

    public int[] getPixel() {
        try {
            grabber.grabPixels();
        } catch (InterruptedException ex) {
        }
        int[] pixArray = (int[])grabber.getPixels();
        return pixArray;

    }
}
