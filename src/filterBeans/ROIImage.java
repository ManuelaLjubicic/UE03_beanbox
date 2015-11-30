package filterBeans;

import Catalano.Imaging.FastBitmap;
import Catalano.Imaging.Filters.Crop;
import helper.FilterEvent;
import helper.IFilterEventListener;
import helper.ImageResize;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by manue on 30.11.2015.
 */
public class ROIImage extends AbstractFilterBean implements IFilterEventListener {

    private FastBitmap fb;
    private int x = 0;
    private int y = 55;
    private int height = 80;
    private int width = _WIDTH;

    public ROIImage(){
        super("ROIImage");
        setSize(_WIDTH, _HEIGHT);
        setBackground(Color.red);
    }


    public FastBitmap roiImage(FastBitmap fastBitmap, int x, int y, int width, int height){
        fb = fastBitmap;
        Crop crop = new Crop(y, x, width, height);
        crop.ApplyInPlace(fb);
        int type = fb.toBufferedImage().getType() == 0? BufferedImage.TYPE_INT_ARGB : fb.toBufferedImage().getType();
        BufferedImage bi = ImageResize.resizeImage(fb.toBufferedImage(), type, _WIDTH, _HEIGHT);
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
    public int getX() {return x;}
    public void setX(int x) {this.x = x;}

    @Override
    public int getY() {return y;}
    public void setY(int y) {this.y = y;}

    @Override
    public int getHeight() {return height;}
    public void setHeight(int height) {this.height = height;}

    @Override
    public int getWidth() {return width;}
    public void setWidth(int width) {this.width = width;}
}
