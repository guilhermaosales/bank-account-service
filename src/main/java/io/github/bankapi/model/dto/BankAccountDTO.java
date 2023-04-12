package io.github.bankapi.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
@Builder
public record BankAccountDTO(
                @NotBlank @Size(max = 4) String agency,
                @NotBlank @Size(max = 12) String account,
                @NotBlank @Size(max = 3) String bankNumber,
                @NotBlank @Size(max = 9) String accountType,
                BankHolderDTO bankHolder) {

}
