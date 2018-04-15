package com.diff.api.resource;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Holds information that users send to api
 * Used to recieve data as json
 */
@XmlRootElement
public class ValueInput {

    @XmlElement(name="value")
    public String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
