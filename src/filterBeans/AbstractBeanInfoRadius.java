package filterBeans;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;

/**
 * Created by manue on 01.12.2015.
 */
public abstract class AbstractBeanInfoRadius extends AbstractBeanInfo {

    private Class usedClass;

    public AbstractBeanInfoRadius(Class usedClass) {
        super(usedClass);
        this.usedClass = usedClass;
    }


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
