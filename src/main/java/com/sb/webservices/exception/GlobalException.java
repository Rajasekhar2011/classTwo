package com.sb.webservices.exception;

import com.sb.webservices.model.ErrorInfo;
import jakarta.validation.constraints.Null;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(value = NullPointerException.class)
    public ResponseEntity<ErrorInfo> handleNullPointerExcepion(NullPointerException npe){
        ErrorInfo errorInfo = new ErrorInfo();
        errorInfo.setException("NullPointerException");
        errorInfo.setMessage(npe.getMessage());
        return new ResponseEntity<>(errorInfo, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<ErrorInfo> handleUserNotFoundException(UserNotFoundException unfe){
        ErrorInfo errorInfo = new ErrorInfo();
        errorInfo.setException("UserNotFoundException");
        errorInfo.setMessage(unfe.getMessage());
        return new ResponseEntity<>(errorInfo, HttpStatus.NOT_FOUND);
    }
}
