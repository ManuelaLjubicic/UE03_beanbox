package filterBeans;

import Catalano.Imaging.Concurrent.Filters.Dilatation;
import Catalano.Imaging.FastBitmap;
import helper.FilterEvent;
import helper.IFilterEventListener;
import helper.ImageResize;

import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;

/**
 * Created by manue on 30.11.2015.
 */
public class DilateImage extends AbstractFilterBean implements IFilterEventListener {

    //für die Dilatation wird ein radius benötigt
    private int radius = 5;

    public DilateImage(){
        super("DilateImage");
    }

    //in dieser Methode wird eine Dilatition auf das Bild durchgeführt
    @Override
    void process() {
        Dilatation d = new Dilatation(radius);
        d.applyInPlace(fb);
        BufferedImage bi = ImageResize.scale(fb.toBufferedImage(), _HEIGHT);
        image = bi;
        repaint();
        fireEvent(fb);
    }

    @Override
    public void handleFilterEvent(FilterEvent event) {
        fb = event.getFb();
//        dilateImage(fb, radius);
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
