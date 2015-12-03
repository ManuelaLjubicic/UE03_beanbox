package filterBeans;

import Catalano.Imaging.FastBitmap;
import helper.FilterEvent;
import helper.IFilterEventListener;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.Serializable;
import java.util.LinkedList;

/**
 * Created by manue on 29.11.2015.
 */
public abstract class AbstractFilterBean extends Canvas implements Serializable, PropertyChangeListener {
    //Events werden hier gesammelt
    private LinkedList<IFilterEventListener> listeners;
    //hier wurde die height vom BeanBox Fenster f�r alle Filter definiert
    protected static final int _HEIGHT = 150;
    //hier wurde die width vom BeanBox Fenster f�r alle Filter definiert
    protected static final int _WIDTH = 150;
    //dieses Image wird in der Beanbox ausgegeben, sobald es gesetzt wird
    protected transient Image image;
    protected transient FastBitmap fb;
    //eine Kopie der FastBitmap wird gehalten, damit bei �nderung der Properties das Orginalbild wieder verwendet werden kann
    protected transient FastBitmap fbCopy;
    protected String message;

    //leerer Konstruktor wird ben�tigt damit man der Zustand in der Beanbox gespeichert wird. Serializable ben�tigt diesen.
    public AbstractFilterBean(){}

    public AbstractFilterBean(String message){
        listeners = new LinkedList<>();
        this.message = message;
        setSize(_WIDTH, _HEIGHT);
        setBackground(Color.white);
    }

    //wird in allen Filtern implementiert und je nach Filter wird ein anderer Process durchgef�hrt
    abstract void process();

    //remove und add Listener wird ben�tigt um alle Events zu sammeln und wieder zu entfernen
    public void addListener(IFilterEventListener listener){
        listeners.add(listener);
    }
    public void removeListener(IFilterEventListener listener){
        listeners.remove(listener);
    }

    //fireEvent wird am Ende jeder process Methode aufgerufen um das Event abzufeuern, damit erh�lt zB. der nachfolgende Filter das bearbeitete Bild
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
            //f�r die �bersichtlichkeit wird auf dem Fenster solange noch kein Bild sichtbar ist eine Nachricht ausgegeben um welchen Filter es sich handelt
            setSize(_WIDTH, _HEIGHT);
            setBackground(Color.white);
            g.drawString(message, 5, 20);
        }
    }

    //propertyChange wird aufgerufen sobald sich durch die Properties ein Wert �ndert. (Die Methode wird dann im setter aufgerufen)
    //dadurch wird die process Methode nochmal durchgef�hrt
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        //zur�cksetzen vom Bild, da die Properties ge�ndert wurden

        if(!evt.getOldValue().equals(evt.getNewValue())) {
            if (fbCopy != null) {
                fb = new FastBitmap(fbCopy);
            }
            process();
        }
    }

}
