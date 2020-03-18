package dev.yavuztas.stackoverflowwebservice.exception;

/**
 * Exception for when pagesize parameter is not provided
 */
public class NoPageSizeException extends RuntimeException {

    private static final String MESSAGE = "Parameter pageSize must be provided";

    public NoPageSizeException() {
        super(MESSAGE);
    }

}
