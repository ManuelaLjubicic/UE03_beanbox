package filterBeans;

import java.awt.event.ActionEvent;
import java.beans.*;

/**
 * Created by manue on 28.11.2015.
 */
public class LoadImageBeanInfo extends AbstractBeanInfo {

    private final static Class beanClass = LoadImage.class;

    public LoadImageBeanInfo() {
        super(beanClass);
    }

    @Override
    public PropertyDescriptor[] getPropertyDescriptors() {
        try {
            PropertyDescriptor path = new PropertyDescriptor("path", beanClass);
            PropertyDescriptor rv[] = {path};
            return rv;
        } catch (IntrospectionException e) {
            throw new Error(e.toString());
        }
    }

    @Override
    public MethodDescriptor[] getMethodDescriptors() {
        MethodDescriptor md = null;
        try {
            md = new MethodDescriptor(beanClass.getMethod("actionPerformed", ActionEvent.class));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return new MethodDescriptor[]{md};
    }
}
