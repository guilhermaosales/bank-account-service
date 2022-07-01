package io.github.bankapi.model.dto;

import io.github.bankapi.model.BankAccount;
import io.github.bankapi.model.BankHolder;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class BankAccountResponse implements Serializable {

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

    public BankAccountResponse(BankAccount bankAccount) {
        agency = bankAccount.getAgency();
        account = bankAccount.getAccount();
        bankNumber = bankAccount.getBankNumber();
        accountType = bankAccount.getAccountType().getType();
        bankHolder = bankAccount.getBankHolder();
    }

}
