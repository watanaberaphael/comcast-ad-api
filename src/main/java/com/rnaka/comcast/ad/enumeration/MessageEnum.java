package com.rnaka.comcast.ad.enumeration;

import org.springframework.util.StringUtils;

import java.io.Serializable;

/**
 * Created by Naka on 9/27/17
 * watanabe.raphael@gmail.com
 */
public enum MessageEnum implements Serializable {

    B1000("B1000", "There is already an active campaign for the partner"),
    B1001("B1001", "No active campaigns for the partner"),

    V3000("V3000", "Ad was created with success.");

    private String id;

    private String message;

    public String getId() {
        return this.id;
    }

    public String getMessage() {
        return this.message;
    }

    MessageEnum(String id, String message) {
        this.id = id;
        this.message = message;
    }

    @Override
    public String toString() {
        return this.id;
    }

    public static MessageEnum getByCode(String codigo) {
        if(!StringUtils.isEmpty(codigo)) {
            for (MessageEnum messageEnum : MessageEnum.values()) {
                if(messageEnum.getId().equals(codigo)){
                    return messageEnum;
                }
            }
        }
        return null;
    }
}
