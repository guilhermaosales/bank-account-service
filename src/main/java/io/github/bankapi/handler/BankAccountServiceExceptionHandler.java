package io.github.bankapi.handler;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import io.github.bankapi.exception.ConflictException;
import io.github.bankapi.exception.InternalServerErrorException;
import io.github.bankapi.exception.NotFoundException;
import io.github.bankapi.exception.model.ErrorDetails;

@ControllerAdvice
public class BankAccountServiceExceptionHandler {

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ErrorDetails> handlerConflictException(Exception ex) {
        return new ResponseEntity<>(ErrorDetails.builder()
                .timestamp(LocalDateTime.now())
                .title("Conflict: check documentation for more details")
                .status(HttpStatus.CONFLICT.value())
                .details(ex.getMessage())
                .trace(ex.getClass().getName())
                .build(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorDetails> handlerNotFoundException(ConflictException ex) {
        return new ResponseEntity<>(ErrorDetails.builder()
                .timestamp(LocalDateTime.now())
                .title("Not found: check documentation for more details")
                .details(ex.getMessage())
                .status(HttpStatus.NOT_FOUND.value())
                .trace(ex.getClass().getName())
                .build(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InternalServerErrorException.class)
    public ResponseEntity<ErrorDetails> handlerInternalServerException(InternalServerErrorException ex) {
        return new ResponseEntity<>(ErrorDetails.builder()
                .details(ex.getMessage())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .trace(ex.getClass().getName())
                .build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
