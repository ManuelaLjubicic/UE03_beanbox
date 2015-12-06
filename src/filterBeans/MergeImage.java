package filterBeans;

import Catalano.Imaging.FastBitmap;
import Catalano.Imaging.Filters.Merge;
import helper.FilterEvent;
import helper.IFilterEventListener;
import helper.ImageResize;

import java.awt.image.BufferedImage;

/**
 * Created by manue on 06.12.2015.
 */
public class MergeImage extends AbstractFilterBean implements IFilterEventListener{

    private FastBitmap fb2;

    public MergeImage(){super("MergeImage");}

    @Override
    void process() {

        Merge m = new Merge(fb);
        m.applyInPlace(fb2);
        BufferedImage bi = ImageResize.scale(fb2.toBufferedImage(), _HEIGHT);
        image = bi;
        repaint();
        fireEvent(fb2);
        fb = null;
        fb2 = null;
    }

    @Override
    public void handleFilterEvent(FilterEvent event) {

        if(fb == null){
            fb = event.getFb();
            fbCopy = new FastBitmap(fb);
        }else{
            fb2 = event.getFb();
            //TODO Copy2 vielleicht??
            process();
        }

    }
}
