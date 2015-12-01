package filterBeans;

/**
 * Created by manue on 30.11.2015.
 */
public class ErodeImageBeanInfo extends AbstractBeanInfoRadius {

    private final static Class beanClass = ErodeImage.class;

    public ErodeImageBeanInfo() {
        super(beanClass);
    }

//    @Override
//    public PropertyDescriptor[] getPropertyDescriptors(){
//        try {
//            PropertyDescriptor radius = new PropertyDescriptor("radius", beanClass);
//
//            PropertyDescriptor rv[] = {radius};
//            return rv;
//        } catch (IntrospectionException e) {
//            throw new Error(e.toString());
//        }
//    }
}
