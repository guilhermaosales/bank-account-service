package io.github.bankapi.model.dto;

import io.github.bankapi.model.BankAccount;
import io.github.bankapi.model.BankHolder;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class BankAccountResponse implements Serializable {

    private UUID id;
    private String agency;
    private String account;
    private String bankNumber;
    private String accountType;
    private BankHolder bankHolder;

    public BankAccountResponse(BankAccount bankAccount) {
        id = bankAccount.getId();
        agency = bankAccount.getAgency();
        account = bankAccount.getAccount();
        bankNumber = bankAccount.getBankNumber();
        accountType = bankAccount.getAccountType().getType();
        bankHolder = bankAccount.getBankHolder();
    }

}
