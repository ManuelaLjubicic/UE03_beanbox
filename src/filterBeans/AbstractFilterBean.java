package filterBeans;

import Catalano.Imaging.FastBitmap;
import helper.FilterEvent;
import helper.IFilterEventListener;

import java.awt.*;
import java.io.Serializable;
import java.util.LinkedList;

/**
 * Created by manue on 29.11.2015.
 */
public class AbstractFilterBean extends Canvas implements Serializable {
    private LinkedList<IFilterEventListener> listeners;
    protected static final int _HEIGHT = 150;
    protected static final int _WIDTH = 150;
    protected Image image;
    protected String message;

    //leerer Konstruktor wird benötigt damit man der Zustand in der Beanbox gespeichert wird. Serializable benötigt diesen.
    public AbstractFilterBean(){}

    public AbstractFilterBean(String message){
        listeners = new LinkedList<>();
        this.message = message;
        setSize(_WIDTH, _HEIGHT);
        setBackground(Color.white);
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
            setBackground(Color.white);
            g.drawString(message, 5, 20);
        }
    }

}
