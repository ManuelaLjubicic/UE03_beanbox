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

    //xPos, yPos, heightROI und widthROI sind Werte für den ROI Bereich
    private int xPos = 0;
    private int yPos = 55;
    private int heightROI = 80;
    private int widthROI = 420;


    //bei Doppel-Klick auf den ROI Filter in der BeanBox kann das Bild auch ausgeschnitten werden
    private transient MouseAdapter ma = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getClickCount() == 2) {
                ROIFrame frame = new ROIFrame(null, "ROI Image", true, fbCopy);
                //aufgrund von JDialog wird der untere Code erst durchgeführt sobald das frame geschlossen wird

                if(frame.getRec() != null) {
                    setAllValues((int) frame.getRec().getX(), (int) frame.getRec().getY(),
                            (int) frame.getRec().getWidth(), (int) frame.getRec().getHeight());
                }
            }
        }
    };

    public ROIImage() {
        super("ROIImage");
        addMouseListener(ma);
    }

    //Das eingehende Bild wird mit der Crop Methode anhand der Werte im Properties Fenster zusammen geschnitten und auf die FensterGröße des Filters in der BeanBos skaliert.
    @Override
    void process() {
        Crop crop = new Crop(yPos, xPos, widthROI, heightROI);
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
        process();
    }

    private void setAllValues(int x, int y, int width, int height) {
        PropertyChangeEvent p = new PropertyChangeEvent(this, "all", 0, 1);
        this.xPos = x;
        this.yPos = y;
        this.widthROI = width;
        this.heightROI = height;
        propertyChange(p);
    }

    //bei Änderung der Werte im Properties Fenster wird im Setter die propertyChange Methode aufgerufen und das Bild wird neu berechnet
    public int getXPos() {
        return xPos;
    }
    public void setXPos(int xPos) {
        PropertyChangeEvent p = new PropertyChangeEvent(this, "xPos", this.xPos, xPos);
        this.xPos = xPos;
        propertyChange(p);
    }

    public int getYPos() {
        return yPos;
    }
    public void setYPos(int yPos) {
        PropertyChangeEvent p = new PropertyChangeEvent(this, "yPos", this.yPos, yPos);
        this.yPos = yPos;
        propertyChange(p);
    }

    public int getHeightROI() {
        return heightROI;
    }
    public void setHeightROI(int heightROI) {
        PropertyChangeEvent p = new PropertyChangeEvent(this, "heightROI", this.heightROI, heightROI);
        this.heightROI = heightROI;
        propertyChange(p);
    }

    public int getWidthROI() { return widthROI; }
    public void setWidthROI(int widthROI) {
        PropertyChangeEvent p = new PropertyChangeEvent(this, "widthROI", this.widthROI, widthROI);
        this.widthROI = widthROI;
        propertyChange(p);
    }
}
