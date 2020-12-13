package com.lifuyi.dev_monitor.model.enterprise;



import lombok.Data;

@Data
public class EnterpriseBinging {
    private String id;
    private String not_service_id;
    private String service_id;

    public EnterpriseBinging(String id, String not_service_id, String service_id) {
        this.id = id;
        this.not_service_id = not_service_id;
        this.service_id = service_id;
    }
}
