package br.rafael.creditvalidator.api.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {
    
    @ExceptionHandler(exception = RuntimeException.class)
    public ResponseEntity<?> runTimeExceptionHandler(RuntimeException excep, Throwable cause) {
        return ResponseEntity.badRequest().body(excep.getMessage());
    }

}
