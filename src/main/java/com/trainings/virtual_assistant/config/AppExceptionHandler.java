package com.trainings.virtual_assistant.config;


import com.trainings.virtual_assistant.common.AppErrorResponse;
import com.trainings.virtual_assistant.common.InputFieldsValidationException;
import com.trainings.virtual_assistant.users.exception.DuplicateUserException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<AppErrorResponse> handleGenericException(Exception e){
        AppErrorResponse errorResponse = constructErrorResponse(e, 500);
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(DuplicateUserException.class)
    public ResponseEntity<AppErrorResponse> handleAppException(DuplicateUserException e){
        AppErrorResponse errorResponse = constructErrorResponse(e, 400);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<AppErrorResponse> handleAppException(ConstraintViolationException e){
        AppErrorResponse errorResponse = constructErrorResponse(e, 400);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InputFieldsValidationException.class)
    public ResponseEntity<AppErrorResponse> handleAppException(InputFieldsValidationException e){
        AppErrorResponse errorResponse = constructErrorResponse(e, 400);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    private AppErrorResponse constructErrorResponse(Exception e, int statusCode){
        return new AppErrorResponse()
                .setErrorMessage(e.getMessage())
                .setStatusCode(statusCode);
    }

}
