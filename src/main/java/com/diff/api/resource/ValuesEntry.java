package com.diff.api.resource;

import com.diff.api.resource.enums.Direction;
import com.wordnik.swagger.annotations.ApiModel;

/**
 * Holds the data required to perform an comparision
 */
@ApiModel( value = "ValuesEntry", description = "ValuesEntry resource" )
public class ValuesEntry {

    private int ID;
    private String left;
    private String right;

    public ValuesEntry(int ID) {
        this.ID = ID;
    }

    /**
     * Set value based on direction
     * @param value
     * @param diretion
     */
    public void setValue(String value, Direction diretion){
        if(Direction.LEFT == diretion){
            this.left = value;
        }else{
            this.right = value;
        }
    }

    /**
     * Get value based on direction
     * @param diretion
     */
    public String getValue(Direction diretion){
        if(Direction.LEFT == diretion){
            return left;
        }else{
            return right;
        }
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getLeft() {
        return left;
    }

    public void setLeft(String left) {
        this.left = left;
    }

    public String getRight() {
        return right;
    }

    public void setRight(String right) {
        this.right = right;
    }
}
