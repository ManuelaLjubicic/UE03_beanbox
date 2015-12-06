package filterBeans;

import Catalano.Imaging.FastBitmap;
import Catalano.Imaging.Filters.Median;
import helper.FilterEvent;
import helper.IFilterEventListener;
import helper.ImageResize;

import java.beans.PropertyChangeEvent;

/**
 * Created by ${Manuela} on 30.11.2015.
 */

public class MedianImage extends AbstractFilterBean implements IFilterEventListener{

    //für den Median wird ein radius benötigt
    private int radius = 5;

    public MedianImage(){
        super("MedianImage");
    }

    //in dieser Methode wird ein Median auf das Bild durchgeführt
    @Override
    void process() {
        Median m = new Median(radius);
        m.applyInPlace(fb);
        image = ImageResize.scale(fb.toBufferedImage(), _HEIGHT);
        repaint();
        fireEvent(fb);
    }

    @Override
    public void handleFilterEvent(FilterEvent event) {
        fb = event.getFb();
        fbCopy = new FastBitmap(fb);
        process();
    }

    //bei Änderung der Werte im Properties Fenster wird im Setter die propertyChange Methode aufgerufen und das Bild wird neu berechnet
    public int getRadius() {return radius;}
    public void setRadius(int radius) {
        PropertyChangeEvent p = new PropertyChangeEvent(this, "radius", this.radius, radius);
        this.radius = radius;
        propertyChange(p);
    }
}
