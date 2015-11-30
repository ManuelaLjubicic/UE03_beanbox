package filterBeans;

import Catalano.Imaging.FastBitmap;
import Catalano.Imaging.Filters.Closing;
import helper.FilterEvent;
import helper.IFilterEventListener;
import helper.ImageResize;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by manue on 30.11.2015.
 */
public class ClosingImage extends AbstractFilterBean implements IFilterEventListener {

    private FastBitmap fb;
    private int radius = 5;

    public ClosingImage(){
        super("ClosingImage");
        setSize(_WIDTH, _HEIGHT);
        setBackground(Color.red);
    }

    public FastBitmap closingImage(FastBitmap fastBitmap, int radius){
        fb = fastBitmap;
        Closing c = new Closing(radius);
        c.applyInPlace(fb);
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
        closingImage(fb, radius);
    }

    public int getRadius() {return radius;}
    public void setRadius(int radius) {this.radius = radius;}
}
