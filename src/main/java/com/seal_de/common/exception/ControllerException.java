package com.seal_de.common.exception;

public class ControllerException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    private Object errorStatus;
    private String message;

    public ControllerException(String message){
        super(message);
    }

    public ControllerException(Object errorStatus, String message) {
        this.errorStatus = errorStatus;
        this.message = message;
    }

    public Object getErrorStatus(){
        return errorStatus;
    }

    public String getMessage() {
        return message;
    }
}
