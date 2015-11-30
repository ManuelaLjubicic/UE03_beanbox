package filterBeans;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;

/**
 * Created by manue on 30.11.2015.
 */
public class MedianImageBeanInfo extends AbstractBeanInfo {

    private final static Class beanClass = MedianImage.class;

    public MedianImageBeanInfo() {
        super(beanClass);
    }

    @Override
    public PropertyDescriptor[] getPropertyDescriptors(){
        try {
            PropertyDescriptor radius = new PropertyDescriptor("radius", beanClass);
            PropertyDescriptor rv[] = {radius};
            return rv;
        } catch (IntrospectionException e) {
            throw new Error(e.toString());
        }
    }
}
