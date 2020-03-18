package dev.yavuztas.stackoverflowwebservice.view;

/**
 * Basic view for api business errors
 *
 * @author Yavuz Tas
 */
public class ApiErrorView {
 
    private final String status;
    private final String message;

    public ApiErrorView(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}