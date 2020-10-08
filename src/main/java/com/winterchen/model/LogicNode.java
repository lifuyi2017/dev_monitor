package com.winterchen.model;

import java.io.Serializable;
import java.util.List;

public class LogicNode implements Serializable {
    private static final long serialVersionUID = -4118684387863013764L;

    private String logic_id;
    private String logic_code;
    private String logic_name;
    private String enterprise_id;

    private String enterprise_name;

    public String getEnterprise_name() {
        return enterprise_name;
    }

    public void setEnterprise_name(String enterprise_name) {
        this.enterprise_name = enterprise_name;
    }

    private List<LogicRelation> logicRelationList;

    public LogicNode() {
    }

    public LogicNode(String logic_id, String logic_code, String logic_name, String enterprise_id, List<LogicRelation> logicRelationList) {
        this.logic_id = logic_id;
        this.logic_code = logic_code;
        this.logic_name = logic_name;
        this.enterprise_id = enterprise_id;
        this.logicRelationList = logicRelationList;
    }

    public String getLogic_id() {
        return logic_id;
    }

    public void setLogic_id(String logic_id) {
        this.logic_id = logic_id;
    }

    public String getLogic_code() {
        return logic_code;
    }

    public void setLogic_code(String logic_code) {
        this.logic_code = logic_code;
    }

    public String getLogic_name() {
        return logic_name;
    }

    public void setLogic_name(String logic_name) {
        this.logic_name = logic_name;
    }

    public String getEnterprise_id() {
        return enterprise_id;
    }

    public void setEnterprise_id(String enterprise_id) {
        this.enterprise_id = enterprise_id;
    }

    public List<LogicRelation> getLogicRelationList() {
        return logicRelationList;
    }

    public void setLogicRelationList(List<LogicRelation> logicRelationList) {
        this.logicRelationList = logicRelationList;
    }
}
