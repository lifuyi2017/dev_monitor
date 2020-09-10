package com.winterchen.model;

public class FieldValue {

    /**
     * 1文本、2日期、3图片
     */
    private String valueType;

    private String value;

    public FieldValue() {
    }

    public FieldValue(String valueType, String value) {
        this.valueType = valueType;
        this.value = value;
    }

    public String getValueType() {
        return valueType;
    }

    public void setValueType(String valueType) {
        this.valueType = valueType;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
