package io.github.bankapi.model.dto;

import io.github.bankapi.model.BankAccount;
import io.github.bankapi.model.BankHolder;
import lombok.*;

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
    private String registrationDate;
    private String lastUpdateDate;
    private boolean preferredAccount;

    public BankAccountResponse(BankAccount bankAccount) {
        id = bankAccount.getId();
        agency = bankAccount.getAgency();
        account = bankAccount.getAccount();
        bankNumber = bankAccount.getBankNumber();
        accountType = bankAccount.getAccountType().getType();
        bankHolder = bankAccount.getBankHolder();
        registrationDate = bankAccount.getRegistrationDate().toString();
        lastUpdateDate = bankAccount.getLastUpdateDate().toString();
        preferredAccount = bankAccount.isPreferredAccount();
    }

}
