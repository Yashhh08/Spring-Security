package com.masai.exception;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<MyErrorDetails> customExceptionHandler(CustomException ce, WebRequest req){

        MyErrorDetails err = new MyErrorDetails();
        err.setLocalDateTime(LocalDateTime.now());
        err.setMessage(ce.getMessage());
        err.setDetails(req.getDescription(false));

        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<MyErrorDetails> NotFoundExceptionHandler(NotFoundException nfe, WebRequest req){

        MyErrorDetails err = new MyErrorDetails();
        err.setLocalDateTime(LocalDateTime.now());
        err.setMessage(nfe.getMessage());
        err.setDetails(req.getDescription(false));

        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<MyErrorDetails> ExceptionHandler(Exception e, WebRequest req){

        MyErrorDetails err = new MyErrorDetails();
        err.setLocalDateTime(LocalDateTime.now());
        err.setMessage(e.getMessage());
        err.setDetails(req.getDescription(false));

        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);

    }

}
