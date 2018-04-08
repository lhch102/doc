package com.jzfq.rms.account.web.requestModel;

import com.jzfq.rms.account.common.Page;

public class DictionaryRequestModel extends BaseRequestModel {

    private String description;//字典描述

    private String label;//字典键

    private String type;//字典类型

    private String dicId;//字典键值id

    private String typeName;//字典类型

    private String id;//字典类型id

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public String getDicId() {
        return dicId;
    }

    public void setDicId(String dicId) {
        this.dicId = dicId;
    }


    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
