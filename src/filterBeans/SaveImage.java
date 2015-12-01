package filterBeans;

import Catalano.Imaging.FastBitmap;
import helper.FilterEvent;
import helper.IFilterEventListener;
import helper.ImageResize;

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
    }

    public FastBitmap saveImage(FastBitmap value, String path, String name) {
        value.saveAsPNG(path + "\\" + name + ".png");
        BufferedImage bi = ImageResize.scale(fb.toBufferedImage(), _HEIGHT);
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
