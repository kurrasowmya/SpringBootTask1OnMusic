package com.stackroute.MuzixApplicationTask.controller;

import com.stackroute.MuzixApplicationTask.exception.TrackAlreadyExistsException;
import com.stackroute.MuzixApplicationTask.exception.TrackNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandlingController {
    @ExceptionHandler(value = TrackAlreadyExistsException.class)
    public ResponseEntity<Object> exception(TrackAlreadyExistsException exception) {
        return new ResponseEntity<>("Track already Exists", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = TrackNotFoundException.class)
    public ResponseEntity<Object> exception(TrackNotFoundException exception) {
        return new ResponseEntity<>("Track not found", HttpStatus.NOT_FOUND);


    }
}
