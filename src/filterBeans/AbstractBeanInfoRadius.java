package filterBeans;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;

/**
 * Created by manue on 01.12.2015.
 */
public abstract class AbstractBeanInfoRadius extends AbstractBeanInfo {

    //usedClass wird ben�tigt damit die BeanInfo Klasse wei� f�r welche Klasse die Properties ge�ndert wurden
    private Class usedClass;

    public AbstractBeanInfoRadius(Class usedClass) {
        super(usedClass);
        this.usedClass = usedClass;
    }

    //alle Beans die einen radius als Properties Parameter in der BeanBox ben�tigen k�nnen von dieser Klasse erben, wie zB. ErodeImage, DilateImage, etc.
    @Override
    public PropertyDescriptor[] getPropertyDescriptors(){
        try {
            PropertyDescriptor radius = new PropertyDescriptor("radius", usedClass);
            PropertyDescriptor rv[] = {radius};
            return rv;
        } catch (IntrospectionException e) {
            throw new Error(e.toString());
        }
    }
}
