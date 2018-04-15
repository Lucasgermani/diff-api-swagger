package com.diff.api.resource.utils;

import java.util.List;

public class CollectionUtil {

    /**
     * Determines if the given lists contain the same elements.
     * @param   firstList
     * @param   secondList
     * @return  True if the given lists contain the same elements, false otherwise.
     */
    public static boolean sameElements(List firstList, List secondList){
        if (firstList.size() != secondList.size()){
            return false;
        }
        // Iterate over the elements of the first list.
        for (int index = 0; index < firstList.size(); index++){
            // Check if the element is also in the second list.
            if (!secondList.contains(firstList.get(index))){
                return false;
            }
        }
        return true;
    }
}
