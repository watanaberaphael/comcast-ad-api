package com.rnaka.comcast.ad.exception;

/**
 * Created by Naka on 9/27/17
 * watanabe.raphael@gmail.com
 */
public class AdApiRuntimeException extends RuntimeException {

    private String id;


    public AdApiRuntimeException(Throwable throwable) {

        super(throwable);
    }

    public AdApiRuntimeException(String message) {
        super(message);
    }

    public AdApiRuntimeException(String message, Throwable throwable) {
        super(message, throwable);
    }


    public String getId() {
        return this.id;
    }

    protected void setId(String id) {
        this.id = id;
    }
}
