package oi.github.bankapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import oi.github.bankapi.model.BankHolder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BankAccountDTO {

    @NotBlank
    @Size(max = 4)
    private String agency;
    @NotBlank
    @Size(max = 12)
    private String account;
    @NotBlank
    @Size(max = 3)
    private String bankNumber;
    @NotBlank
    @Size(max = 9)
    private String accountType;
    private BankHolder bankHolder;
}
