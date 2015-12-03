package filterBeans;

import Catalano.Imaging.FastBitmap;
import Catalano.Imaging.Filters.Closing;
import helper.FilterEvent;
import helper.IFilterEventListener;
import helper.ImageResize;

import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;

/**
 * Created by manue on 30.11.2015.
 */
public class ClosingImage extends AbstractFilterBean implements IFilterEventListener {

    //für das CLosing wird ein radius benötigt
    private int radius = 5;

    public ClosingImage(){
        super("ClosingImage");
    }

    //in dieser Methode wird ein Closing auf das Bild durchgeführt
    @Override
    void process() {
        Closing c = new Closing(radius);
        c.applyInPlace(fb);
        BufferedImage bi = ImageResize.scale(fb.toBufferedImage(), _HEIGHT);
        image = bi;
        repaint();
        fireEvent(fb);
    }

    @Override
    public void handleFilterEvent(FilterEvent event) {
        fb = event.getFb();
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
