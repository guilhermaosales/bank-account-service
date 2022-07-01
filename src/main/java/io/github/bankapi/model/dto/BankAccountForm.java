package io.github.bankapi.model.dto;

import io.github.bankapi.model.BankAccount;
import lombok.*;
import io.github.bankapi.model.BankHolder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class BankAccountForm {

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

    public BankAccountForm(BankAccount bankAccount) {
        agency = bankAccount.getAgency();
        account = bankAccount.getAccount();
        bankNumber = bankAccount.getBankNumber();
        accountType = bankAccount.getAccountType().getType();
        bankHolder = bankAccount.getBankHolder();
    }

    public BankAccount toEntity() {
        return new BankAccount(this);
    }


}
