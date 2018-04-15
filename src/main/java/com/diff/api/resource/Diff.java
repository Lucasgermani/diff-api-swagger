package com.diff.api.resource;

import javax.xml.bind.annotation.XmlElement;

/**
 * Holds one difference information of a given string
 */
public class Diff {

    private Integer offset;
    private Integer length;

    public Diff(Integer offset, Integer length) {
        this.offset = offset;
        this.length = length;
    }

    public Diff() {}

    public Integer getOffset() {
        return offset;
    }

    @XmlElement
    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getLength() {
        return length;
    }

    @XmlElement
    public void setLength(Integer length) {
        this.length = length;
    }

    @Override
    public String toString() {
        return String.format("offset = '%s' length = '%s'", offset, length);
    }

    @Override
    public boolean equals(Object object) {
        if(!(object instanceof Diff)){
            return false;
        }
        Diff givenDiff = (Diff) object;
        if((this.length == null && givenDiff.length != null) || (this.length != null && !this.length.equals(givenDiff.length))){
            return false;
        }
        if((this.offset == null && givenDiff.offset != null) || (this.offset != null && !this.offset.equals(givenDiff.offset))){
            return false;
        }
        return true;
    }
}
