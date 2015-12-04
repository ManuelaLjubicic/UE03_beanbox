package filterBeans;

import Catalano.Imaging.FastBitmap;
import helper.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;

/**
 * Created by Karin on 04.12.2015.
 */
public class CalculateSolderJoint extends AbstractFilterBean implements IFilterEventListener {

    private String fileName;
    private String path;
    private String name;
    private int startPoint;
    private int setValueY;
    private int incrementX;
    private int toleranceX;
    private Point[] solderJoints;
    private CalcCentroids cc= new CalcCentroids();
    private CalculateSolderJointOutput csjo;

    public CalculateSolderJoint(){
        super("CalculateSolderJoint");
    }

    @Override
    void process() {

        BufferedImage bi = ImageResize.scale(fb.toBufferedImage(), _HEIGHT);
        image = bi;
        solderJoints = cc.processFilter(fb);
        csjo = new CalculateSolderJointOutput(fileName, startPoint, setValueY, incrementX, toleranceX,toleranceY);
        fireEvent(fb);
    }

    @Override
    public void handleFilterEvent(FilterEvent event) {
        fb = event.getFb();
        fbCopy = new FastBitmap(fb);
        process();
    }

    //bei Änderung der Werte im Properties Fenster wird im Setter die propertyChange Methode aufgerufen und das Bild wird neu berechnet
//    public int getLowLevel() {return lowLevel;}
//    public void setLowLevel(int lowLevel) {
//        PropertyChangeEvent p = new PropertyChangeEvent(this, "lowLevel", this.lowLevel, lowLevel);
//        this.lowLevel = lowLevel;
//        propertyChange(p);
//    }
//
//    public int getHighLevel() {return highLevel;}
//    public void setHighLevel(int highLevel) {
//        PropertyChangeEvent p = new PropertyChangeEvent(this, "highLevel", this.highLevel, highLevel);
//        this.highLevel = highLevel;
//        propertyChange(p);
//    }



    public String getPath() {return path;}

    public void setPath(String path) {this.path = path;}

    public String getName() {return name;}
    public void setName (String name) {
        this.name = name;
    }

    public int getStartPoint() {return startPoint;}

    public void setStartPoint(int startPoint) {
        this.startPoint = startPoint;
    }

    public int getSetValueY() {
        return setValueY;
    }

    public void setSetValueY(int setValueY) {
        this.setValueY = setValueY;
    }

    public int getIncrementX() {
        return incrementX;
    }

    public void setIncrementX(int incrementX) {
        this.incrementX = incrementX;
    }

    public int getToleranceX() {
        return toleranceX;
    }

    public void setToleranceX(int toleranceX) {
        this.toleranceX = toleranceX;
    }

    public int getToleranceY() {
        return toleranceY;
    }

    public void setToleranceY(int toleranceY) {
        this.toleranceY = toleranceY;
    }

    private int toleranceY;


}
