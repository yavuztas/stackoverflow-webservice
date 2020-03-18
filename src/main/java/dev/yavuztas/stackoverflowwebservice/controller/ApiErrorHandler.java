package dev.yavuztas.stackoverflowwebservice.controller;

import dev.yavuztas.stackoverflowwebservice.exception.QuestionNotFoundException;
import dev.yavuztas.stackoverflowwebservice.exception.UserNotFoundException;
import dev.yavuztas.stackoverflowwebservice.view.ApiErrorView;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Error handler for our api business logic errors.
 *
 * @author Yavuz Tas
 */
@RestControllerAdvice
public class ApiErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(QuestionNotFoundException.class)
    public final ResponseEntity<ApiErrorView> handleQuestionNotFoundException(QuestionNotFoundException exception) {
        ApiErrorView apiError = new ApiErrorView(HttpStatus.NOT_FOUND.getReasonPhrase(), exception.getMessage());
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<ApiErrorView> handleUserNotFoundException(UserNotFoundException exception) {
        ApiErrorView apiError = new ApiErrorView(HttpStatus.NOT_FOUND.getReasonPhrase(), exception.getMessage());
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {

        String message = ex.getMessage();
        if (HttpStatus.NOT_FOUND.equals(status)) {
            message = "Error in API path";
            ApiErrorView apiError = new ApiErrorView(HttpStatus.NOT_FOUND.getReasonPhrase(), message);
            return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
        }

        if (HttpStatus.BAD_REQUEST.equals(status)) {
            message = "Error in parameters";
            ApiErrorView apiError = new ApiErrorView(HttpStatus.BAD_REQUEST.getReasonPhrase(), message);
            return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
        }

        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            message = "Error in server";
            ApiErrorView apiError = new ApiErrorView(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), message);
            return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return super.handleExceptionInternal(ex, body, headers, status, request);
    }
}