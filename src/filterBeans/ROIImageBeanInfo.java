package filterBeans;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;

/**
 * Created by manue on 30.11.2015.
 */
public class ROIImageBeanInfo extends AbstractBeanInfo {

    private final static Class beanClass = ROIImage.class;

    public ROIImageBeanInfo() {
        super(beanClass);
    }

    @Override
    public PropertyDescriptor[] getPropertyDescriptors(){
        try {
            PropertyDescriptor x = new PropertyDescriptor("x", beanClass);
            PropertyDescriptor y = new PropertyDescriptor("y", beanClass);
            PropertyDescriptor height = new PropertyDescriptor("height", beanClass);
            PropertyDescriptor width = new PropertyDescriptor("width", beanClass);
            PropertyDescriptor rv[] = {x, y, height, width};
            return rv;
        } catch (IntrospectionException e) {
            throw new Error(e.toString());
        }
    }
}
