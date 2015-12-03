package filterBeans;

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

    //f�r die Erosion wird ein Radius ben�tigt
    private int radius = 5;

    public ErodeImage(){
        super("ErodeImage");
    }

    //in dieser Methode wird auf das Bild eine Erosions-Bildbearbeitung durchgef�hrt
    @Override
    void process() {
        Erosion e = new Erosion(radius);
        e.applyInPlace(fb);
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

    //bei �nderung der Werte im Properties Fenster wird im Setter die propertyChange Methode aufgerufen und das Bild wird neu berechnet
    public int getRadius() {return radius;}
    public void setRadius(int radius) {
        PropertyChangeEvent p = new PropertyChangeEvent(this, "radius", this.radius, radius);
        this.radius = radius;
        propertyChange(p);
    }
}
