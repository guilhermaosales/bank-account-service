package io.github.bankapi.model.dto;

import lombok.Builder;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Builder
public record BankHolderDTO(
        @NotNull(message = "${value} cannot be empty")
        @Size(max = 4) String fullName) {

}
