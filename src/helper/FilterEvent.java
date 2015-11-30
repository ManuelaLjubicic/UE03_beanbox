package helper;

import Catalano.Imaging.FastBitmap;

import java.util.EventObject;

/**
 * Created by manue on 29.11.2015.
 */
public class FilterEvent extends EventObject {

    private FastBitmap fb;
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public FilterEvent(Object source, FastBitmap fastbitmap) {
        super(source);
        fb = fastbitmap;
    }

    public FastBitmap getFb() {
        return fb;
    }
}
