package com.diff.api.resource;

import javax.xml.bind.annotation.XmlElement;

public class Diff {

    private Integer offset;
    private Integer length;

    public Diff(Integer offset, Integer length) {
        this.offset = offset;
        this.length = length;
    }

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
}
