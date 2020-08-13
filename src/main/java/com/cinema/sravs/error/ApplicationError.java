package com.cinema.sravs.error;

public class ApplicationError extends RuntimeException {
    public ApplicationError(String errorMessage){
        super(errorMessage);
    }

}
