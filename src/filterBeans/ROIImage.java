package filterBeans;

import Catalano.Imaging.FastBitmap;
import Catalano.Imaging.Filters.Crop;
import helper.FilterEvent;
import helper.IFilterEventListener;
import helper.ImageResize;
import helper.ROIFrame;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.io.Serializable;

/**
 * Created by manue on 30.11.2015.
 */
public class ROIImage extends AbstractFilterBean implements IFilterEventListener, Serializable {

    //fbTemp wird bneötigt, damit bei mehrfachen ausschneiden des Bildes das orginal behalten wird
    private transient FastBitmap fbTemp;
    //x, y, height und width sind Werte für den ROI Bereich
    private int x = 0;
    private int y = 55;
    private int height = 80;
    private int width = 420;
    //bei Doppel-Klick auf den ROI Filter in der BeanBox kann das Bild auch ausgeschnitten werden
    private transient MouseAdapter ma = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getClickCount() == 2) {
                ROIFrame frame = new ROIFrame(null, "ROI Image", true, fbTemp);
                //aufgrund von JDialog wird der untere Code erst durchgeführt sobald das frame geschlossen wird
                x = (int) frame.getRec().getX();
                y = (int) frame.getRec().getY();
                height = (int) frame.getRec().getHeight();
                width = (int) frame.getRec().getWidth();
                fb = new FastBitmap(fbTemp);
                process();
            }
            super.mouseClicked(e);
        }
    };

    public ROIImage() {
        super("ROIImage");
        addMouseListener(ma);
    }

    //Das eingehende Bild wird mit der Crop Methode anhand der Werte im Properties Fenster zusammen geschnitten und auf die FensterGröße des Filters in der BeanBos skaliert.
    @Override
    void process() {
        fbTemp = new FastBitmap(fb);
        Crop crop = new Crop(y, x, width, height);
        crop.ApplyInPlace(fb);
        BufferedImage bi = ImageResize.scale(fb.toBufferedImage(), _HEIGHT);
        image = bi;
        repaint();
        fireEvent(fb);
    }

    @Override
    public void handleFilterEvent(FilterEvent event) {
        fb = event.getFb();
        fbCopy = new FastBitmap(fb);
        repaint();
        process();
    }

    //bei Änderung der Werte im Properties Fenster wird im Setter die propertyChange Methode aufgerufen und das Bild wird neu berechnet
    @Override
    public int getX() {
        return x;
    }
    public void setX(int x) {
        PropertyChangeEvent p = new PropertyChangeEvent(this, "x", this.x, x);
        this.x = x;
        fb = new FastBitmap(fbCopy); //neue
        propertyChange(p);
    }

    @Override
    public int getY() {
        return y;
    }
    public void setY(int y) {
        PropertyChangeEvent p = new PropertyChangeEvent(this, "y", this.y, y);
        this.y = y;
        fb = new FastBitmap(fbCopy); //neue
        propertyChange(p);
    }

    @Override
    public int getHeight() {
        return height;
    }
    public void setHeight(int height) {
        PropertyChangeEvent p = new PropertyChangeEvent(this, "height", this.height, height);
        this.height = height;
        fb = new FastBitmap(fbCopy); //neue
        propertyChange(p);
    }

    @Override
    public int getWidth() {
        return width;
    }
    public void setWidth(int width) {
        PropertyChangeEvent p = new PropertyChangeEvent(this, "width", this.width, width);
        this.width = width;
        fb = new FastBitmap(fbCopy); //neue
        propertyChange(p);
    }
}
