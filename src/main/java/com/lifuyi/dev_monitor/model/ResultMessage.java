package com.lifuyi.dev_monitor.model;

public class ResultMessage<T> {

    private String statuscode;
    private String mesg;
    private T value;

    public ResultMessage() {
    }

    public ResultMessage(String statuscode, String mesg, T value) {
        this.statuscode = statuscode;
        this.mesg = mesg;
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public String getStatuscode() {
        return statuscode;
    }

    public void setStatuscode(String statuscode) {
        this.statuscode = statuscode;
    }

    public String getMesg() {
        return mesg;
    }

    public void setMesg(String mesg) {
        this.mesg = mesg;
    }
}
