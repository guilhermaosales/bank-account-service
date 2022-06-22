package io.github.bankapi.util;

import io.github.bankapi.dto.BankAccountDTO;
import io.github.bankapi.enums.BankAccountTypeEnum;
import io.github.bankapi.model.BankAccount;
import io.github.bankapi.model.BankHolder;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class BankAccountBuilder {

    public static BankAccount updateBankAccount(BankAccountDTO dto, BankAccount model, BankHolder bh) {

        bh.builder()
                .id(model.getId())
                .firstName(StringUtils.hasLength(dto.getBankHolder().getFirstName()) ?
                        dto.getBankHolder().getFirstName() : model.getBankHolder().getFirstName())
                .secondName(StringUtils.hasLength(dto.getBankHolder().getSecondName()) ?
                        dto.getBankHolder().getSecondName() : model.getBankHolder().getSecondName())
                .build();

        model.builder().bankHolder(bh)
                .account(StringUtils.hasLength(dto.getAccount()) && (dto.getAccount() != null) ? dto.getAccount() : model.getAccount())
                .accountType(StringUtils.hasLength(dto.getAccountType()) ? BankAccountTypeEnum.valueOf(dto.getAccountType()) : model.getAccountType())
                .lastUpdateDate(LocalDateTime.now(ZoneId.of("UTC")))
                .registrationDate(model.getRegistrationDate())
                .preferredAccount(model.isPreferredAccount())
                .build();

        return model;
    }

    public static BankAccount createBankAccount(BankAccountDTO dto) {

        BankAccount bankAccount = BankAccount.builder()
                .accountType(BankAccountTypeEnum.valueOf(dto.getAccountType()))
                .registrationDate(LocalDateTime.now(ZoneId.of("UTC")))
                .lastUpdateDate(LocalDateTime.now(ZoneId.of("UTC")))
                .preferredAccount(Boolean.TRUE)
                .build();

        BeanUtils.copyProperties(dto, bankAccount);

        return bankAccount;
    }
}
