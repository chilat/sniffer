package com.apptech.newsniffer.model;

/**
 * Created by Emmanuel Rop on 16/09/2018.
 */
public class MessageModel {
    public String sender,message,status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isGenuine() {
        return genuine;
    }

    public void setGenuine(boolean genuine) {
        this.genuine = genuine;
    }

    boolean genuine;

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
