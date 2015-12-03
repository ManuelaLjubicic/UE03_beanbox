package filterBeans;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;

/**
 * Created by manue on 01.12.2015.
 */
public abstract class AbstractBeanInfoRadius extends AbstractBeanInfo {

    //usedClass wird benötigt damit die BeanInfo Klasse weiß für welche Klasse die Properties geändert wurden
    private Class usedClass;

    public AbstractBeanInfoRadius(Class usedClass) {
        super(usedClass);
        this.usedClass = usedClass;
    }

    //alle Beans die einen radius als Properties Parameter in der BeanBox benötigen können von dieser Klasse erben, wie zB. ErodeImage, DilateImage, etc.
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
