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
    //berechnet die Position der Lötstellen und gibt diese Koordinaten, sowie Abweichungen von Sollwerten in einer Textdatei aus
    //dazu werden die Hilfs-Klassen CalcCentroids und CalculateSolderJointOutput verwendet
    //CalcCentroid berechnet gefundenen Lötstellen   (= weiße "Bälle" im Bild); CalculateSolderJointOutput berechnet evtl.
    //Abweichungen und gibt die Koordinaten der Lötstellen mit den Abweichungen in einer Textdatei aus.

    private String fileName;
    private String path = "C:\\";
    private String name = "TextFile";
    private int startPoint = 6;
    private int setValueY = 25;
    private int incrementX = 65;
    private int toleranceX = 5;
    private int toleranceY = 5;
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

        fileName = path + "\\" + name +".txt";
        csjo = new CalculateSolderJointOutput(fileName, startPoint, setValueY, incrementX, toleranceX,toleranceY);
        csjo.processFilter(solderJoints);
        fireEvent(fb);
    }

    @Override
    public void handleFilterEvent(FilterEvent event) {
        fb = event.getFb();
        fbCopy = new FastBitmap(fb);
        process();
    }

    public String getPath() {return path;}
    public void setPath(String path) {
//        PropertyChangeEvent p = new PropertyChangeEvent(this, "path", this.path, path);
        this.path = path;
//        propertyChange(p);
    }

    public String getName() {return name;}
    public void setName (String name) {
//        PropertyChangeEvent p = new PropertyChangeEvent(this, "name", this.name, name);
        this.name = name;
//        propertyChange(p);
    }

    public int getStartPoint() {return startPoint;}
    public void setStartPoint(int startPoint) {
        PropertyChangeEvent p = new PropertyChangeEvent(this, "startPoint", this.startPoint, startPoint);
        this.startPoint = startPoint;
        propertyChange(p);
    }

    public int getSetValueY() {
        return setValueY;
    }
    public void setSetValueY(int setValueY) {
        PropertyChangeEvent p = new PropertyChangeEvent(this, "setValueY", this.setValueY, setValueY);
        this.setValueY = setValueY;
        propertyChange(p);
    }

    public int getIncrementX() {
        return incrementX;
    }
    public void setIncrementX(int incrementX) {
        PropertyChangeEvent p = new PropertyChangeEvent(this, "incrementX", this.incrementX, incrementX);
        this.incrementX = incrementX;
        propertyChange(p);
    }

    public int getToleranceX() {
        return toleranceX;
    }
    public void setToleranceX(int toleranceX) {
        PropertyChangeEvent p = new PropertyChangeEvent(this, "toleranceX", this.toleranceX, toleranceX);
        this.toleranceX = toleranceX;
        propertyChange(p);
    }

    public int getToleranceY() {return toleranceY;
    }
    public void setToleranceY(int toleranceY) {
        PropertyChangeEvent p = new PropertyChangeEvent(this, "toleranceY", this.toleranceY, toleranceY);
        this.toleranceY = toleranceY;
        propertyChange(p);
    }

}
