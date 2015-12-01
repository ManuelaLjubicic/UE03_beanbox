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
import java.io.BufferedReader;
import java.io.Serializable;

/**
 * Created by manue on 30.11.2015.
 */
public class ROIImage extends AbstractFilterBean implements IFilterEventListener, Serializable {

    private transient FastBitmap fb;
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
                roiImage(fbTemp, x, y, width, height);
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

    public FastBitmap roiImage(FastBitmap fastBitmap, int x, int y, int width, int height) {
        fb = fastBitmap;
        fbTemp = new FastBitmap(fb);
        Crop crop = new Crop(y, x, width, height);
        crop.ApplyInPlace(fb);
        bi = ImageResize.scale(fb.toBufferedImage(), _HEIGHT);
        image = bi;
        repaint();
        fireEvent(fb);
        return fb;
    }

    @Override
    public void handleFilterEvent(FilterEvent event) {
        fb = event.getFb();
        roiImage(fb, x, y, width, height);
    }

    @Override
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    @Override
    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
