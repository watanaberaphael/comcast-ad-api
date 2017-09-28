package com.rnaka.comcast.ad;

import com.rnaka.comcast.ad.controller.dto.MessageDTO;
import com.rnaka.comcast.ad.exception.BusinessValidationException;
import com.rnaka.comcast.ad.exception.ResultNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Naka on 9/26/17
 * watanabe.raphael@gmail.com
 */
@ControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {

    private final Logger LOG = Logger.getLogger(this.getClass().getName());

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleConflict(Exception ex, WebRequest request) {
        LOG.log(Level.SEVERE, String.format("Error: %s", ex.getMessage()));
        LOG.log(Level.SEVERE, "Error stacktrace: ", ex);
        return handleExceptionInternal(ex, "Internal Error", new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @ExceptionHandler(BusinessValidationException.class)
    public ResponseEntity<Object> handleConflict(BusinessValidationException ex, WebRequest request) {
        MessageDTO messageDTO = new MessageDTO(ex.getMessage(), ex.getId());

        return handleExceptionInternal(ex, messageDTO, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(ResultNotFoundException.class)
    public ResponseEntity<Object> handleConflict(ResultNotFoundException ex, WebRequest request) {
        MessageDTO messageDTO = new MessageDTO(ex.getMessage(), ex.getId());

        return handleExceptionInternal(ex, messageDTO, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}
