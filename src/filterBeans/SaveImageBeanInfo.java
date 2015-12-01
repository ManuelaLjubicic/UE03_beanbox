package filterBeans;

import java.beans.*;

/**
 * Created by manue on 29.11.2015.
 */
public class SaveImageBeanInfo extends AbstractBeanInfo {

    private final static Class beanClass = SaveImage.class;

    public SaveImageBeanInfo(){super(beanClass);}

    @Override
    public PropertyDescriptor[] getPropertyDescriptors(){
        try {
            PropertyDescriptor path = new PropertyDescriptor("path", beanClass);
            PropertyDescriptor name = new PropertyDescriptor("name", beanClass);
            PropertyDescriptor rv[] = {path, name};
            return rv;
        } catch (IntrospectionException e) {
            throw new Error(e.toString());
        }
    }



}
