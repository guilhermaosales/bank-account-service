package io.github.bankapi.model.dto;

import io.github.bankapi.model.BankHolder;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Builder
public record BankAccountDTO(
                @NotBlank @Size(max = 4) String agency,
                @NotBlank @Size(max = 12) String account,
                @NotBlank @Size(max = 3) String bankNumber,
                @NotBlank @Size(max = 9) String accountType,
                BankHolder bankHolder) {

}
