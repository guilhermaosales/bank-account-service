package oi.github.bankapi.util;

import oi.github.bankapi.dto.BankAccountDTO;
import oi.github.bankapi.enums.BankAccountTypeEnum;
import oi.github.bankapi.model.BankAccount;
import oi.github.bankapi.model.BankHolder;
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
                .accountType(StringUtils.hasLength(dto.getAccountType()) ? BankAccountTypeEnum.valueOf(dto.getAccountType()) : model.getAccountType())
                .lastUpdateDate(LocalDateTime.now(ZoneId.of("UTC")))
                .registrationDate(model.getRegistrationDate())
                .preferredAccount(model.isPreferredAccount())
                .build();

        BeanUtils.copyProperties(dto, model);
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
