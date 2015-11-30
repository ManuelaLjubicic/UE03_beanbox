package filterBeans;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;

/**
 * Created by manue on 30.11.2015.
 */
public class DilateImageBeanInfo extends AbstractBeanInfo {

    private final static Class beanClass = DilateImage.class;

    public DilateImageBeanInfo() {super(beanClass);}

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
