package filterBeans;

import Catalano.Imaging.FastBitmap;
import helper.ImageResize;

import java.awt.image.BufferedImage;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by manue on 26.11.2015.
 */
public class LoadImage extends AbstractFilterBean implements ActionListener {

    private String path = "C:\\";


    public LoadImage(){
        super("LoadImage");
        setSize(_WIDTH, _HEIGHT);
        setBackground(Color.red);
    }

    public FastBitmap loadImage(String path) {
        FastBitmap fb = new FastBitmap(path);
//        image = fb.toImage();
        int type = fb.toBufferedImage().getType() == 0? BufferedImage.TYPE_INT_ARGB : fb.toBufferedImage().getType();
        BufferedImage bi = ImageResize.resizeImage(fb.toBufferedImage(), type, _WIDTH, _HEIGHT);
        image = bi;
        repaint();
        fireEvent(fb);
        return fb;
    }

    public String getPath() {return path;}
    public void setPath(String path) {this.path = path;}

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(path);
        loadImage(path);
    }

}
