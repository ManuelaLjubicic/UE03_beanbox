package filterBeans;

import Catalano.Imaging.FastBitmap;
import helper.FilterEvent;
import helper.IFilterEventListener;
import helper.ImageResize;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.LinkedList;

/**
 * Created by manue on 29.11.2015.
 */
public class AbstractFilterBean extends Canvas implements Serializable {
    private LinkedList<IFilterEventListener> listeners;
    protected static final int _HEIGHT = 100;
    protected static final int _WIDTH = 150;
    protected Image image;
    String message;

    //leerer Konstruktor wird benötigt damit man der Zustand in der Beanbox gespeichert wird. Serializable benötigt diesen.
    public AbstractFilterBean(){}

    public AbstractFilterBean(String message){
        listeners = new LinkedList<>();
        this.message = message;
    }

    public void addListener(IFilterEventListener listener){
        listeners.add(listener);
    }

    public void removeListener(IFilterEventListener listener){
        listeners.remove(listener);
    }

    protected void fireEvent(FastBitmap fastBitmap){

        FilterEvent filterEvent;
        for(IFilterEventListener l : listeners){
            filterEvent = new FilterEvent(this, fastBitmap);
            l.handleFilterEvent(filterEvent);
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        if(image != null){
            g.drawImage(image, 0, 0, this);
            setSize(_WIDTH, _HEIGHT);
        }else {
            setSize(_WIDTH, _HEIGHT);
            setBackground(Color.red);
            g.drawString(message, 5, 20);
        }
    }

//    public FastBitmap resizePicture(FastBitmap fastbitmap){
//        FastBitmap fb = fastbitmap;
//        int type = fb.toBufferedImage().getType() == 0? BufferedImage.TYPE_INT_ARGB : fb.toBufferedImage().getType();
//        BufferedImage bi = ImageResize.resizeImage(fb.toBufferedImage(), type, _WIDTH, _HEIGHT);
//        image = bi;
//        repaint();
//        fireEvent(fb);
//        return fb;
//    }
}
