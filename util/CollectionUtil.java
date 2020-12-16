package util;

import java.util.Collection;

public class CollectionUtil {
    
    public static Boolean isNullOrEmpty(Collection list) {
        return list == null || list.isEmpty();
    } 
}
