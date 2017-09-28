package com.rnaka.comcast.ad.controller.dto;

import java.io.Serializable;

/**
 * Created by Naka on 9/26/17
 * watanabe.raphael@gmail.com
 */
public class MessageDTO implements Serializable{

    private String id;

    private String message;


    public MessageDTO() {

    }

    public MessageDTO(String message) {
        this.message = message;
    }

    public MessageDTO(String message, String id) {
        this.message = message;
        this.id = id;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
