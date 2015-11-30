package filterBeans;

import Catalano.Imaging.FastBitmap;
import helper.FilterEvent;
import helper.IFilterEventListener;
import helper.ImageResize;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by manue on 29.11.2015.
 */
public class SaveImage extends AbstractFilterBean implements IFilterEventListener{

    private FastBitmap fb;
    private String path = "C:\\";
    private String name = "Picture";


    public SaveImage(){
        super("SaveImage");
        setSize(_WIDTH, _HEIGHT);
        setBackground(Color.red);
    }

    public FastBitmap saveImage(FastBitmap value, String path, String name) {
        value.saveAsPNG(path + "\\" + name + ".png");
        if(fb == null){
            System.out.println("es ist null");
        }
        int type = fb.toBufferedImage().getType() == 0? BufferedImage.TYPE_INT_ARGB : fb.toBufferedImage().getType();
        BufferedImage bi = ImageResize.resizeImage(fb.toBufferedImage(), type, _WIDTH, _HEIGHT);
        image = bi;
        repaint();
        fireEvent(fb);
        return fb;
    }

    @Override
    public void handleFilterEvent(FilterEvent event) {
        fb = event.getFb();
        saveImage(event.getFb(), path, name);


    }

    public String getPath() {return path;}
    public void setPath(String path) {this.path = path;}

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
}
