/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.spb.petrk.webissues.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author PetrK
 */
public final class Utils {
    
    public static boolean isEmpty(Object object) {
        return object == null ||
               Constants.STRING_EMPTY.equals(object) ||
               Constants.LONG_EMPTY.equals(object);
    }   
    
    
    public static interface Filter<T> {
        
        boolean accept(T element);        
        
    }    
    
    public static <T> List<T> filter(Collection<T> collection, Filter<T> elementsFilter) {
        if (collection != null) {
            List<T> result = new ArrayList<T>();
            
            for (T element : collection) {
                if (elementsFilter.accept(element)) {
                    result.add(element);
                }
            }
            
            return result;                    
        }
        return null;
    }       

            
    private Utils() {
        throw new AssertionError("Not instantiable!");
    }

}
