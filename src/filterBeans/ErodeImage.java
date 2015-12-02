package filterBeans;

import Catalano.Imaging.FastBitmap;
import Catalano.Imaging.Filters.Erosion;
import helper.FilterEvent;
import helper.IFilterEventListener;
import helper.ImageResize;

import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;

/**
 * Created by manue on 30.11.2015.
 */
public class ErodeImage extends AbstractFilterBean implements IFilterEventListener {

    //    private transient FastBitmap fb;
    private int radius = 5;

    public ErodeImage(){
        super("ErodeImage");
    }

    @Override
    void process() {
        Erosion e = new Erosion(radius);
        e.applyInPlace(fb);
        BufferedImage bi = ImageResize.scale(fb.toBufferedImage(), _HEIGHT);
        image = bi;
        repaint();
        fireEvent(fb);
    }

//    public FastBitmap erodeImage(FastBitmap fastBitmap, int radius){
//        fb = fastBitmap;
//        Erosion e = new Erosion(radius);
//        e.applyInPlace(fb);
//        BufferedImage bi = ImageResize.scale(fb.toBufferedImage(), _HEIGHT);
//        image = bi;
//        repaint();
//        fireEvent(fb);
//        return fb;
//    }

    @Override
    public void handleFilterEvent(FilterEvent event) {
        fb = event.getFb();
//        erodeImage(fb, radius);
        process();
    }

    public int getRadius() {return radius;}
    public void setRadius(int radius) {
        PropertyChangeEvent p = new PropertyChangeEvent(this, "radius", this.radius, radius);
        this.radius = radius;
        propertyChange(p);
    }
}
