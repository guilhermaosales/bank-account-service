package io.github.bankapi.exception.model;

import java.time.LocalDateTime;

import javax.annotation.Nullable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@JsonInclude(Include.NON_NULL)
public class ErrorDetails {
    private String title;
    private int status;
    private String error;
    private Details details;
    private String trace;
    private LocalDateTime timestamp;
}
