package com.diff.api.errorhandlers;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Holds error information to return as json
 */
@XmlRootElement
public class ErrorMessage {

    @XmlElement(name = "status")
    int status;

    @XmlElement(name = "message")
    String message;

    public ErrorMessage(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
