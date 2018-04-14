package com.diff.api.archive;

import com.diff.api.resource.ValuesEntry;
import com.diff.api.resource.enums.Direction;

import java.util.HashMap;

public class DataArchive {

    // Holds the ValuesEntry instance
    private static HashMap<Integer, ValuesEntry> map = new HashMap<Integer, ValuesEntry>();

    public static ValuesEntry getInstance(int id) {
        ValuesEntry reg = map.get(id);
        return reg;
    }

    public static void add(int id, String value, Direction direction){
        ValuesEntry reg = getInstance(id);
        if (reg == null) {
            reg = new ValuesEntry(id);
        }
        reg.setValue(value, direction);
        map.put(id, reg);
    }
}
