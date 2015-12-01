package filterBeans;

import Catalano.Imaging.Concurrent.Filters.Dilatation;
import Catalano.Imaging.FastBitmap;
import helper.FilterEvent;
import helper.IFilterEventListener;
import helper.ImageResize;

import java.awt.image.BufferedImage;

/**
 * Created by manue on 30.11.2015.
 */
public class DilateImage extends AbstractFilterBean implements IFilterEventListener {

    private int radius = 5;
    private FastBitmap fb;

    public DilateImage(){
        super("DilateImage");
    }

    public FastBitmap dilateImage(FastBitmap fastBitmap, int radius){
        fb = fastBitmap;
        Dilatation d = new Dilatation(radius);
        d.applyInPlace(fb);
        BufferedImage bi = ImageResize.scale(fb.toBufferedImage(), _HEIGHT);
        image = bi;
        repaint();
        fireEvent(fb);
        return fb;
//        return resizePicture(fb);
    }


    @Override
    public void handleFilterEvent(FilterEvent event) {
        fb = event.getFb();
        dilateImage(fb, radius);
    }

    public int getRadius() {return radius;}
    public void setRadius(int radius) {this.radius = radius;}
}
