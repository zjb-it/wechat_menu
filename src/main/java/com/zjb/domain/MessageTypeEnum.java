package com.zjb.domain;

/**
 * Created by zjb on 18-7-2.
 */
public enum  MessageTypeEnum {
    vistor(1),audited(2);
    private Integer id;

    MessageTypeEnum(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
