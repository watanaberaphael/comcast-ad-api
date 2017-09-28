package com.rnaka.comcast.ad.exception;

import com.rnaka.comcast.ad.enumeration.MessageEnum;

/**
 * Created by Naka on 9/26/17
 * watanabe.raphael@gmail.com
 */
public class ResultNotFoundException extends AdApiRuntimeException {

    public ResultNotFoundException(Throwable throwable) {

        super(throwable);
    }

    public ResultNotFoundException(MessageEnum messageEnum) {
        super(messageEnum.getMessage());
        setId(messageEnum.getId());
    }

    public ResultNotFoundException(String message) {
        super(message);
    }

    public ResultNotFoundException(String message, Throwable throwable) {
        super(message, throwable);
    }


}
