package com.qsp.UserLoginLogout.Advice;

import com.qsp.UserLoginLogout.exceptionhandling.EmptyInputException;
import com.qsp.UserLoginLogout.exceptionhandling.IdNotfoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MyControllerAdvice {
    @ExceptionHandler(EmptyInputException.class)
    public ResponseEntity<String>handleEmptyinput(EmptyInputException emptyInputException){
        return new ResponseEntity<String>("Input Field is empty ,please look into it", HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(IdNotfoundException.class)
    public ResponseEntity<String>IdNotFound(IdNotfoundException IdNotfoundException){
        return new ResponseEntity<String>("Id Not Present",HttpStatus.NOT_FOUND);
    }

}
