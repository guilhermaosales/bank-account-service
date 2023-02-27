package io.github.bankapi.exception.model;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class ErrorDetails {
    private String title;
    private int status;
    private String details;
    private String trace;
    private LocalDateTime timestamp;
}
