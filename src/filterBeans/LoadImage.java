package filterBeans;

import Catalano.Imaging.FastBitmap;
import helper.ImageResize;

import java.awt.image.BufferedImage;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;

/**
 * Created by manue on 26.11.2015.
 */
public class LoadImage extends AbstractFilterBean implements ActionListener{

    //in path wird der Pfad des Bildes gespeichert
    private String path = "C:\\";

    public LoadImage(){
        super("LoadImage");
    }

    //diese Methode l‰dt das Bild von dem Pfad aus, welcher in den Properties eingegeben wurde,
    //anschlieﬂend wird es auf die vordefinierte Grˆﬂe des Fenster in der BeanBox verkleinert
    @Override
    public void process() {
        FastBitmap fb = new FastBitmap(path);
        BufferedImage bi = ImageResize.scale(fb.toBufferedImage(), _HEIGHT);
        image = bi;
        repaint();
        fireEvent(fb);
    }

    public String getPath() {return path;}
    //sobald sich der Wert von path im Properties Fenster in der BeanBox ‰ndert wird der getter aufgerufen
    //dadurch wird die propertyChange Methode aufgerufen und anschlieﬂen wird das Bild anhand der neu eingelesen Werte berechnet
    public void setPath(String path) {
        PropertyChangeEvent p = new PropertyChangeEvent(this, "path", this.path, path);
        this.path = path;
        propertyChange(p);
    }

    //loadImage ist die einzige Klasse die diese Methode implementiert, LoadImage ist die Source und nur sie kann in der BeanBox mit einem Button verbunden werden
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(path);
        process();
    }
}
