package com.rnaka.comcast.ad.exception;

import com.rnaka.comcast.ad.enumeration.MessageEnum;

/**
 * Created by Naka on 9/26/17
 * watanabe.raphael@gmail.com
 */
public class BusinessValidationException extends AdApiRuntimeException {

    public BusinessValidationException(Throwable throwable) {
        super(throwable);
    }

    public BusinessValidationException(MessageEnum messageEnum) {
        super(messageEnum.getMessage());
        setId(messageEnum.getId());
    }

    public BusinessValidationException(String message) {
        super(message);
    }

    public BusinessValidationException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
