package filterBeans;

import Catalano.Imaging.FastBitmap;
import Catalano.Imaging.Filters.Crop;
import helper.FilterEvent;
import helper.IFilterEventListener;
import helper.ImageResize;
import helper.ROIFrame;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.io.BufferedReader;
import java.io.Serializable;

/**
 * Created by manue on 30.11.2015.
 */
public class ROIImage extends AbstractFilterBean implements IFilterEventListener, Serializable {

//    private transient FastBitmap fb;
    private transient FastBitmap fbTemp;
    private int x = 0;
    private int y = 55;
    private int height = 80;
    private int width = 420;
    private transient BufferedImage bi;

    private transient MouseAdapter ma = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getClickCount() == 2) {
                ROIFrame frame = new ROIFrame(null, "ROI Image", true, fbTemp);
                x = (int) frame.getRec().getX();
                y = (int) frame.getRec().getY();
                height = (int) frame.getRec().getHeight();
                width = (int) frame.getRec().getWidth();
                fb = new FastBitmap(fbTemp);
//                roiImage(x, y, width, height);
                process();
                System.out.println("x = " + x);
                System.out.println("y = " + y);
                System.out.println("height = " + height);
                System.out.println("width = " + width);
            }
            super.mouseClicked(e);
        }
    };

    public ROIImage() {
        super("ROIImage");
        addMouseListener(ma);
    }

    @Override
    void process() {
        fbTemp = new FastBitmap(fb);
        Crop crop = new Crop(y, x, width, height);
        crop.ApplyInPlace(fb);
        bi = ImageResize.scale(fb.toBufferedImage(), _HEIGHT);
        image = bi;
        repaint();
        fireEvent(fb);
    }

//    public FastBitmap roiImage(int x, int y, int width, int height) {
//        fbTemp = new FastBitmap(fb);
//        Crop crop = new Crop(y, x, width, height);
//        crop.ApplyInPlace(fb);
//        bi = ImageResize.scale(fb.toBufferedImage(), _HEIGHT);
//        image = bi;
//        repaint();
//        fireEvent(fb);
//        return fb;
//    }

    @Override
    public void handleFilterEvent(FilterEvent event) {
        fb = event.getFb();
        fbCopy = new FastBitmap(fb);
//        roiImage(x, y, width, height);
        process();
    }

    @Override
    public int getX() {
        return x;
    }
    public void setX(int x) {
        PropertyChangeEvent p = new PropertyChangeEvent(this, "x", this.x, x);
        this.x = x;
        propertyChange(p);
    }

    @Override
    public int getY() {
        return y;
    }
    public void setY(int y) {
        PropertyChangeEvent p = new PropertyChangeEvent(this, "y", this.y, y);
        this.y = y;
        propertyChange(p);
    }

    @Override
    public int getHeight() {
        return height;
    }
    public void setHeight(int height) {
        PropertyChangeEvent p = new PropertyChangeEvent(this, "height", this.height, height);
        this.height = height;
        propertyChange(p);
    }

    @Override
    public int getWidth() {
        return width;
    }
    public void setWidth(int width) {
        PropertyChangeEvent p = new PropertyChangeEvent(this, "width", this.width, width);
        this.width = width;
        propertyChange(p);
    }
}
