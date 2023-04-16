package io.github.bankapi.model.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record BankHolderDTO(
        @NotNull(message = "${value} cannot be empty")
        @Size(max = 4) String fullName) {

}
