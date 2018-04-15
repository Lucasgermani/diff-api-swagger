package com.diff.api.resource.enums;

import org.apache.commons.lang.StringUtils;

import java.util.Arrays;
import java.util.List;

public enum Direction{
    LEFT("left"),
    RIGHT("right");

    Direction(String side) {
        this.side = side;
    }

    private String side;

    public String getSide() {
        return side;
    }

    public static Direction findBySideString(String desc){
        List<Direction> list = Arrays.asList(Direction.values());
        for (Direction enumEntry : list){
            if(StringUtils.equals(enumEntry.getSide(), desc)){
                return enumEntry;
            }
        }
        return null;
    }
}
