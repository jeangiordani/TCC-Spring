package com.backend.portalestuderesponda.exceptions;

public class DataExistsException extends RuntimeException{
    public DataExistsException(String message) {
        super(message);
    }
}
