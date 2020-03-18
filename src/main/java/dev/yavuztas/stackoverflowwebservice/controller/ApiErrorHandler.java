package dev.yavuztas.stackoverflowwebservice.controller;

import dev.yavuztas.stackoverflowwebservice.exception.QuestionNotFoundException;
import dev.yavuztas.stackoverflowwebservice.view.ApiErrorView;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Error handler for our api business logic errors.
 *
 * @author Yavuz Tas
 */
@ControllerAdvice
@RestController
public class ApiErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(QuestionNotFoundException.class)
    public final ResponseEntity<ApiErrorView> handleNotFoundException(QuestionNotFoundException exception) {
        ApiErrorView apiError = new ApiErrorView(HttpStatus.NOT_FOUND.getReasonPhrase(), exception.getMessage());
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

}