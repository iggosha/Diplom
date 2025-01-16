package bsuedu.golovkov.fintracker.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice(basePackages = {"bsuedu.golovkov.fintracker.controller"})
public class GlobalExceptionHandler {

    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ExceptionResponse handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest webRequest) {
        return new ExceptionResponse(webRequest, NOT_FOUND, exception.getMessage());
    }
}
