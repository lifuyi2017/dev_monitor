package com.winterchen.model;


import java.util.Date;

public class DevOneFieldValue {
    private String id;
    private Integer dev_top_id;
    private Integer dev_id;
    private String field_id;
    private String field_string;
    private Date field_date;
    private byte[] field_blob;

    public DevOneFieldValue(String id,Integer dev_top_id, Integer dev_id, String field_id, String field_string, Date field_date, byte[] field_blob) {
        this.id=id;
        this.dev_top_id = dev_top_id;
        this.dev_id = dev_id;
        this.field_id = field_id;
        this.field_string = field_string;
        this.field_date = field_date;
        this.field_blob = field_blob;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public DevOneFieldValue() {
    }

    public Integer getDev_top_id() {
        return dev_top_id;
    }

    public void setDev_top_id(Integer dev_top_id) {
        this.dev_top_id = dev_top_id;
    }

    public Integer getDev_id() {
        return dev_id;
    }

    public void setDev_id(Integer dev_id) {
        this.dev_id = dev_id;
    }

    public String getField_id() {
        return field_id;
    }

    public void setField_id(String field_id) {
        this.field_id = field_id;
    }

    public String getField_string() {
        return field_string;
    }

    public void setField_string(String field_string) {
        this.field_string = field_string;
    }

    public Date getField_date() {
        return field_date;
    }

    public void setField_date(Date field_date) {
        this.field_date = field_date;
    }

    public byte[] getField_blob() {
        return field_blob;
    }

    public void setField_blob(byte[] field_blob) {
        this.field_blob = field_blob;
    }
}
