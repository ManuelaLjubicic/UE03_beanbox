package filterBeans;

import Catalano.Core.IntRange;
import Catalano.Imaging.FastBitmap;
import Catalano.Imaging.Filters.ReplaceColor;
import helper.FilterEvent;
import helper.IFilterEventListener;
import helper.ImageResize;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by manue on 30.11.2015.
 */
public class ThresholdImage extends AbstractFilterBean implements IFilterEventListener{

    private FastBitmap fb;
    private int lowLevel = 0;
    private int highLevel = 40;
    private FastBitmap copyFB;

    public ThresholdImage(){
        super("ThresholdImage");
        setSize(_WIDTH, _HEIGHT);
        setBackground(Color.red);
    }

    public FastBitmap thresholdImage(FastBitmap fastBitmap, int lowLevel, int highLevel){
        fb = fastBitmap;
        copyFB = fb;
        ReplaceColor rc = new ReplaceColor(new IntRange(lowLevel, highLevel),new IntRange(lowLevel,highLevel),new IntRange(lowLevel,highLevel));
        fb.toRGB();
        rc.ApplyInPlace(fb, 255, 255, 255);
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
        thresholdImage(fb, lowLevel, highLevel);
    }

    public int getLowLevel() {return lowLevel;}
    public void setLowLevel(int lowLevel) {this.lowLevel = lowLevel;}

    public int getHighLevel() {return highLevel;}
    public void setHighLevel(int highLevel) {this.highLevel = highLevel;}
}
