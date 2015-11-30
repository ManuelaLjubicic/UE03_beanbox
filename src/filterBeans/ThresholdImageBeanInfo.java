package filterBeans;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;

/**
 * Created by manue on 30.11.2015.
 */
public class ThresholdImageBeanInfo extends AbstractBeanInfo {

    private final static Class beanClass = ThresholdImage.class;

    public ThresholdImageBeanInfo() {
        super(beanClass);
    }

    @Override
    public PropertyDescriptor[] getPropertyDescriptors(){
        try {
            PropertyDescriptor lowLevel = new PropertyDescriptor("lowLevel", beanClass);
            PropertyDescriptor highLevel = new PropertyDescriptor("highLevel", beanClass);

            PropertyDescriptor rv[] = {lowLevel, highLevel};
            return rv;
        } catch (IntrospectionException e) {
            throw new Error(e.toString());
        }
    }
}
