package filterBeans;

import Catalano.Imaging.FastBitmap;
import Catalano.Imaging.Filters.Opening;
import helper.FilterEvent;
import helper.IFilterEventListener;
import helper.ImageResize;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;

/**
 * Created by manue on 30.11.2015.
 */
public class OpeningImage extends AbstractFilterBean implements IFilterEventListener {

    //private transient FastBitmap fb;
    private int radius = 5;

    public OpeningImage(){
            super("OpeningImage");
            setSize(_WIDTH, _HEIGHT);
            setBackground(Color.red);
    }

    @Override
    void process() {
        Opening o = new Opening(radius);
        o.applyInPlace(fb);
        BufferedImage bi = ImageResize.scale(fb.toBufferedImage(), _HEIGHT);
        image = bi;
        repaint();
        fireEvent(fb);
    }

//    public FastBitmap openingImage(FastBitmap fastBitmap, int radius){
//        fb = fastBitmap;
//        Opening o = new Opening(radius);
//        o.applyInPlace(fb);
//        BufferedImage bi = ImageResize.scale(fb.toBufferedImage(), _HEIGHT);
//        image = bi;
//        repaint();
//        fireEvent(fb);
//        return fb;
//    }

    @Override
    public void handleFilterEvent(FilterEvent event) {
        fb = event.getFb();
//        openingImage(fb, radius);
        process();
    }

    public int getRadius() {return radius;}
    public void setRadius(int radius) {
        PropertyChangeEvent p = new PropertyChangeEvent(this, "radius", this.radius, radius);
        this.radius = radius;
        propertyChange(p);
    }
}
