package filterBeans;

import Catalano.Core.IntRange;
import Catalano.Imaging.FastBitmap;
import Catalano.Imaging.Filters.ReplaceColor;
import helper.FilterEvent;
import helper.ImageResize;

import java.awt.image.BufferedImage;
import java.beans.IntrospectionException;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyDescriptor;

/**
 * Created by Karin on 04.12.2015.
 */
public class CalculateSolderJointBeanInfo extends AbstractBeanInfo {

    private final static Class beanClass = CalculateSolderJoint.class;

    public CalculateSolderJointBeanInfo(){super(beanClass);}

    @Override
    public PropertyDescriptor[] getPropertyDescriptors(){
        try {
            PropertyDescriptor path = new PropertyDescriptor("path", beanClass);
            PropertyDescriptor name = new PropertyDescriptor("name", beanClass);
            PropertyDescriptor startPoint = new PropertyDescriptor("startPointX", beanClass);
            PropertyDescriptor setValueY = new PropertyDescriptor("setValueY", beanClass);
            PropertyDescriptor incrementX = new PropertyDescriptor("incrementY", beanClass);
            PropertyDescriptor toleranceX = new PropertyDescriptor("toleranceX", beanClass);
            PropertyDescriptor toleranceY = new PropertyDescriptor("toleranceY", beanClass);
            PropertyDescriptor rv[] = {path, name, startPoint, setValueY,incrementX, toleranceX,toleranceY};
            return rv;
        } catch (IntrospectionException e) {
            throw new Error(e.toString());
        }
    }

}
