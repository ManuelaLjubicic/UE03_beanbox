package filterBeans;

import Catalano.Imaging.FastBitmap;
import helper.ImageResize;

import java.awt.image.BufferedImage;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Created by manue on 26.11.2015.
 */
public class LoadImage extends AbstractFilterBean implements ActionListener{

    private String path = "C:\\";

    public LoadImage(){
        super("LoadImage");
    }

    @Override
    public void process() {
        FastBitmap fb = new FastBitmap(path);
        BufferedImage bi = ImageResize.scale(fb.toBufferedImage(), _HEIGHT);
        image = bi;
        repaint();
        fireEvent(fb);
    }

    public String getPath() {return path;}

    public void setPath(String path) {
        PropertyChangeEvent p = new PropertyChangeEvent(this, "path", this.path, path);
        this.path = path;
        propertyChange(p);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(path);
        process();
    }
}
