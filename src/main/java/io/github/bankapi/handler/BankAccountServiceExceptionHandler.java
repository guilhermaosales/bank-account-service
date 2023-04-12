package io.github.bankapi.handler;

import io.github.bankapi.exception.ConflictException;
import io.github.bankapi.exception.InternalServerErrorException;
import io.github.bankapi.exception.NotFoundException;
import io.github.bankapi.exception.model.Details;
import io.github.bankapi.exception.model.ErrorDetails;
import jakarta.annotation.Nullable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class BankAccountServiceExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        List<FieldError> listErrors = ex.getBindingResult().getFieldErrors();

        var fields = listErrors.stream().map(FieldError::getField).toList();
        var messages = listErrors.stream().map(FieldError::getDefaultMessage).toList();

        List<String> errors = new ArrayList<>();

        for (int i = 0; i < fields.size(); i++) {
            errors.add(fields.get(i).concat(" ").concat(messages.get(i)));

        }

        return new ResponseEntity<>(
                ErrorDetails.builder()
                        .timestamp(LocalDateTime.now())
                        .title("Bad Request: check documentation for more details")
                        .status(HttpStatus.BAD_REQUEST.value())
                        .details(Details.builder().errors(errors).build())
                        .trace(ex.getClass().getName())
                        .build(),
                HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ErrorDetails> handlerConflictException(Exception ex) {
        return new ResponseEntity<>(ErrorDetails.builder()
                .timestamp(LocalDateTime.now())
                .title("Conflict: check documentation for more details")
                .status(HttpStatus.CONFLICT.value())
                .error(ex.getMessage())
                .trace(ex.getClass().getName())
                .build(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorDetails> handlerNotFoundException(ConflictException ex) {
        return new ResponseEntity<>(ErrorDetails.builder()
                .timestamp(LocalDateTime.now())
                .title("Not found: check documentation for more details")
                .error(ex.getMessage())
                .status(HttpStatus.NOT_FOUND.value())
                .trace(ex.getClass().getName())
                .build(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InternalServerErrorException.class)
    public ResponseEntity<ErrorDetails> handlerInternalServerException(InternalServerErrorException ex) {
        return new ResponseEntity<>(ErrorDetails.builder()
                .error(ex.getMessage())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .trace(ex.getClass().getName())
                .build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    protected ResponseEntity<Object> handleExceptionInternal(
            Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {

        ErrorDetails details = ErrorDetails.builder()
                        .timestamp(LocalDateTime.now())
                        .title("Bad Request: check documentation for more details")
                        .status(status.value())
                        .trace(ex.getClass().getName())
                        .build();

        return new ResponseEntity<>(details, headers, status);
    }

}
