package filterBeans;

import Catalano.Core.IntRange;
import Catalano.Imaging.FastBitmap;
import Catalano.Imaging.Filters.ReplaceColor;
import helper.FilterEvent;
import helper.IFilterEventListener;
import helper.ImageResize;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;

/**
 * Created by manue on 30.11.2015.
 */
public class ThresholdImage extends AbstractFilterBean implements IFilterEventListener{

    //für die Berechnung des Thresholds wird ein lowLevel und ein highLevel Wert benötigt
    private int lowLevel = 0;
    private int highLevel = 40;

    public ThresholdImage(){
        super("ThresholdImage");
    }

    @Override
    void process() {
        ReplaceColor rc = new ReplaceColor(new IntRange(lowLevel, highLevel),new IntRange(lowLevel,highLevel),new IntRange(lowLevel,highLevel));
        fb.toRGB();
        rc.ApplyInPlace(fb, 255, 255, 255);
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

    //bei Änderung der Werte im Properties Fenster wird im Setter die propertyChange Methode aufgerufen und das Bild wird neu berechnet
    public int getLowLevel() {return lowLevel;}
    public void setLowLevel(int lowLevel) {
        PropertyChangeEvent p = new PropertyChangeEvent(this, "lowLevel", this.lowLevel, lowLevel);
        this.lowLevel = lowLevel;
        propertyChange(p);
    }

    public int getHighLevel() {return highLevel;}
    public void setHighLevel(int highLevel) {
        PropertyChangeEvent p = new PropertyChangeEvent(this, "highLevel", this.highLevel, highLevel);
        this.highLevel = highLevel;
        propertyChange(p);
    }
}
