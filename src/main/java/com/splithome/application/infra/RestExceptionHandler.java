package com.splithome.application.infra;

import com.splithome.application.exceptions.DuplicateEmailException;
import com.splithome.application.exceptions.EmailNotFoundException;
import com.splithome.application.exceptions.SimplePasswordException;
import com.splithome.application.exceptions.WrongPasswordException;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DuplicateEmailException.class)
    private ResponseEntity<RestGlobalErrorMessage> DuplicateEmailHandler(DuplicateEmailException exception) {

        RestGlobalErrorMessage response = new RestGlobalErrorMessage(HttpStatus.CONFLICT, exception.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    @ExceptionHandler(EmailNotFoundException.class)
    private ResponseEntity<RestGlobalErrorMessage> EmailNotFoundHandler(EmailNotFoundException exception) {
        RestGlobalErrorMessage response = new RestGlobalErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(SimplePasswordException.class)
    private ResponseEntity<RestGlobalErrorMessage> simplePasswordHandler(SimplePasswordException exception) {
        RestGlobalErrorMessage response = new RestGlobalErrorMessage(HttpStatus.BAD_REQUEST, exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(WrongPasswordException.class)
    private ResponseEntity<RestGlobalErrorMessage> wrongPasswordHandler(WrongPasswordException exception) {
        RestGlobalErrorMessage response = new RestGlobalErrorMessage(HttpStatus.BAD_REQUEST, exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
