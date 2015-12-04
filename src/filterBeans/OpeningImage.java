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

    //für das Opening wird ein radius benötigt
    private int radius = 5;

    public OpeningImage(){super("OpeningImage");}

    //in dieser Methode wird das Bild mit einem Opening aus dem Catalano Framework bearbeitet
    @Override
    void process() {

        Opening o = new Opening(radius);
        o.applyInPlace(fb);
        BufferedImage bi = ImageResize.scale(fb.toBufferedImage(), _HEIGHT);
        image = bi;
        repaint();
        fireEvent(fb);
    }

    @Override
    public void handleFilterEvent(FilterEvent event) {
        fb = event.getFb();
        fbCopy = new FastBitmap(fb);
        process();
    }

    public int getRadius() {return radius;}
    public void setRadius(int radius) {
        PropertyChangeEvent p = new PropertyChangeEvent(this, "radius", this.radius, radius);
        System.out.println("this.Radius radius " + this.radius +" " + radius);
        this.radius = radius;
        System.out.println("this.Radius radius " + this.radius +" " + radius);
        propertyChange(p);
    }
}
