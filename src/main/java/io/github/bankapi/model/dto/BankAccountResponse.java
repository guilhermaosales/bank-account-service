package io.github.bankapi.model.dto;

import io.github.bankapi.enums.BankAccountTypeEnum;
import io.github.bankapi.model.BankHolder;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class BankAccountResponse implements Serializable {

    private Long id;
    private String agency;
    private String account;
    private String bankNumber;
    private BankAccountTypeEnum accountType;
    private BankHolder bankHolder;
    private LocalDateTime registrationDate;
    private LocalDateTime lastUpdateDate;
    private boolean preferredAccount;

}
