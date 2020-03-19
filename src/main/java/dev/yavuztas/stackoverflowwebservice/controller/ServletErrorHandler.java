package dev.yavuztas.stackoverflowwebservice.controller;

import dev.yavuztas.stackoverflowwebservice.view.ApiErrorView;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Common error controller to handle servlet errors like 404
 */
@RestController
public class ServletErrorHandler implements ErrorController {

    @RequestMapping("/error")
    public ResponseEntity<ApiErrorView> handleError(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        String message = "Error in API path";
        HttpStatus status = HttpStatus.resolve(statusCode);
        ApiErrorView apiError = new ApiErrorView(status.getReasonPhrase(), message);
        return new ResponseEntity<>(apiError, status);
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
