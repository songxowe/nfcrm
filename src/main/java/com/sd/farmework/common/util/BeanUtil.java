package  com.sd.farmework.common.util;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

/**
 * <p>Description: This class is an extension for spring bean utility.</p>
 *
 * @author <a href="mailto:sunyi4j@gmail.com">Roy</a> on Apr 5, 2012
 */
public class BeanUtil {
    /**
     * Copy bean properties. The property which is null value will be ignored.
     * 
     * @param dest Destination object
     * @param source Source object
     */
    public static void copyPropertiesExcludeNull(Object dest, Object source) {
    	copyPropertiesByCase(dest, source, CopyCase.NULL);
    }

    /**
     * Copy bean properties. The property which is empty value will be ignored.
     * 
     * @param dest Destination object
     * @param source Source object
     */
    public static void copyPropertiesExcludeEmpty(Object dest, Object source) {
    	copyPropertiesByCase(dest, source, CopyCase.EMPTY);
    }

    /**
     * Copy bean properties. The property which is empty or null value will be ignored.
     * 
     * @param dest Destination object
     * @param source Source object
     */
    public static void copyPropertiesExcludeNullAndEmpty(Object dest, Object source) {
    	copyPropertiesByCase(dest, source, CopyCase.BOTH);
    }

    /**
     * Just invoke spring's BeanUtils for the convenience.
     * 
     * @param dest Destination object
     * @param source Source object
     */
    public static void copyProperties(Object dest, Object source) {
        BeanUtils.copyProperties(source, dest);
    }

    /**
     * Copy bean properties base on specified copyCase.
     * 
     * @param dest Destination object
     * @param source Source object
     * @param copyCase CopyCase object
     */
    private static void copyPropertiesByCase(Object dest, Object source, CopyCase copyCase) {
        BeanWrapper srcWrapper = new BeanWrapperImpl(source);
        //Iterate all properties
        ArrayList<Object> ignorePropertyList = new ArrayList<Object>();
        for (int i = 0; i < srcWrapper.getPropertyDescriptors().length; i++) {
            PropertyDescriptor sourceDesc = srcWrapper.getPropertyDescriptors()[i];
            String name = sourceDesc.getName();
            Object value = srcWrapper.getPropertyValue(name);
            switch(copyCase) {
            	case NULL:
                    //exclude the null property
                    if(value == null) {
                        ignorePropertyList.add(name);
                    }
            		break;
            	case EMPTY:
                    //exclude the empty property
                    if("".equals(value)) {
                        ignorePropertyList.add(name);
                    }
            		break;
            	case BOTH:
                    //exclude both null and empty property
                    if(value == null || "".equals(value)) {
                        ignorePropertyList.add(name);
                    }
            		break;
            }
        }
        String[] ignoreProperties = new String[ignorePropertyList.size()];
        ignoreProperties = (String[])ignorePropertyList.toArray(ignoreProperties);
        BeanUtils.copyProperties(source, dest, ignoreProperties);
    }

    /**
     * Copy cases. Indicate which case should be ignored when copying.
     */
    private enum CopyCase{
    	NULL,
    	EMPTY,
    	BOTH
    }

}
