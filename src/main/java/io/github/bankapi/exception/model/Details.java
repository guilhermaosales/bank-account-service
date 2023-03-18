package io.github.bankapi.exception.model;

import java.util.List;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class Details {

    private final List<String> errors;
}
