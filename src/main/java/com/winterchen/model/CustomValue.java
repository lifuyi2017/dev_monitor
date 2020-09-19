package com.winterchen.model;

import java.io.Serializable;

public class CustomValue implements Serializable {
    private static final long serialVersionUID = 1576519996214051849L;

    private String name;
    private String value;

    public CustomValue() {
    }

    public CustomValue(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
