package com.cinema.sravs.error;

import java.net.UnknownHostException;
import java.util.List;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.support.WebExchangeBindException;

import javax.naming.ServiceUnavailableException;


@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class DefaultExceptionHandler  {
    
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(WebExchangeBindException.class)
    public ErrorResponse webExchangeBindException(WebExchangeBindException ex) {
        
        BindingResult result = ex.getBindingResult();
        List<org.springframework.validation.FieldError> fieldErrors = result.getFieldErrors();
        return processFieldErrors(fieldErrors);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ErrorResponse httpMessageNotReadableException(HttpMessageNotReadableException  ex) {
        
        return new ErrorResponse(HttpStatus.BAD_REQUEST, "Invalid Input");
    }

    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    @ResponseBody
    @ExceptionHandler(ServiceUnavailableException.class)
    public ErrorResponse serviceNotAvailableException(ServiceUnavailableException ex) {
        
        return new ErrorResponse(HttpStatus.SERVICE_UNAVAILABLE, "Service is not available");
    }


    @ResponseStatus(HttpStatus.PRECONDITION_FAILED)
    @ResponseBody
    @ExceptionHandler(ApplicationError.class)
    public ErrorResponse serviceNotAvailableException1(ApplicationError ex) {

        return new ErrorResponse(HttpStatus.PRECONDITION_FAILED, "Invalid Input, Please Check ApiKey, SearchTerm");
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    @ExceptionHandler(UnknownHostException.class)
    public ErrorResponse unauthorizedException(UnknownHostException ex) {

        return new ErrorResponse(HttpStatus.UNAUTHORIZED, "Invalid API Key");
    }

    private ErrorResponse processFieldErrors(List<org.springframework.validation.FieldError> fieldErrors) {

        ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST, "validation error");
        return error;
    }
}