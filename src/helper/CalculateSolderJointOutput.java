package helper;

import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StreamCorruptedException;
import java.security.InvalidParameterException;

/**
 * Created by manue on 15.11.2015.
 */

//CalculateSolderJointOutput berechnet evtl.
//Abweichungen und gibt die Koordinaten der Lötstellen mit den Abweichungen in einer Textdatei aus.
public class CalculateSolderJointOutput{

    private String _fileName;
    private int _startPoint;
    private int _setValueY;
    private int _incrementX;
    private int _toleranceX;
    private int _toleranceY;

    public CalculateSolderJointOutput(String fileName, int startPoint, int setValueY, int incrementX, int toleranceX, int toleranceY) {

        init(fileName, startPoint, setValueY, incrementX, toleranceX, toleranceY);
    }

    private void init(String fileName, int startPoint, int setValueY, int incrementX, int toleranceX, int toleranceY){
        _fileName = fileName;
        _startPoint = startPoint;
        _setValueY = setValueY;
        _incrementX = incrementX;
        _toleranceX = toleranceX;
        _toleranceY = toleranceY;

    }

    public Object processFilter(Point[] value) {

        try {
            FileWriter fw = new FileWriter(_fileName);

            fw.write("Sollwert_Y = " + _setValueY + "\t\tInkrement_X = " + _incrementX + "\tStartwert_X = " + _startPoint + "\r\n");
            fw.write("Toleranzwert_Y = " + _toleranceY + "\tToleranzwert_X = " + _toleranceX + "\r\n\r\n");
            for(int i = 0; i < value.length; i++){
//                fw.write( i + 1 + " Point: x: " + value[i].x + " Y: " + value[i].y + "\r\n");
                fw.write(checkTolerance(value,i));
            }

            fw.flush();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String checkTolerance(Point[] value, int i){

        int diffX;
        int diffY;
        diffX = (value[i].x - (i * _incrementX + _startPoint));
        diffY = value[i].y - _setValueY;

        //die aktuellen Werte werden gesetzt
        String output = i + 1 + "Point: x: " + value[i].x + " Y: " + value[i].y;
        //Die Abweichungen werden berechnet
        output = output + "\tAbweichung_X = " + diffX + "\tAbweichung_Y = " + diffY;
        if(Math.abs(diffX) > _toleranceX || Math.abs(diffY) > _toleranceY){
            //sollte eine Toleranzüberschreibung auftreten, wird eine Warung ausgegeben
            output = output + " \tToleranzwert wurde ueberschritten";
        }
        output = output + "\r\n";
        return output;
    }
}
