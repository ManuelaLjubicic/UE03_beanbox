package filterBeans;

import helper.FilterEvent;
import helper.IFilterEventListener;

import java.beans.EventSetDescriptor;
import java.beans.IntrospectionException;
import java.beans.MethodDescriptor;
import java.beans.SimpleBeanInfo;
import java.io.Serializable;

/**
 * Created by manue on 29.11.2015.
 */
public class AbstractBeanInfo extends SimpleBeanInfo{

    private Class usedClass;

    public AbstractBeanInfo(Class usedClass){
        this.usedClass = usedClass;
    }

    @Override
    public MethodDescriptor[] getMethodDescriptors() {
        MethodDescriptor md = null;
        try {
            md = new MethodDescriptor(usedClass.getMethod("handleFilterEvent", FilterEvent.class));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return new MethodDescriptor[]{md};
    }

    @Override
    public EventSetDescriptor[] getEventSetDescriptors() {

        String[] listenerMethod = new String[]{"handleFilterEvent"};
        try {
            EventSetDescriptor e = new EventSetDescriptor
                    (usedClass, "FilterEvent", IFilterEventListener.class, listenerMethod, "addListener", "removeListener");
            EventSetDescriptor[] events = {e};
            return events;

        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        return super.getEventSetDescriptors();
    }

}
