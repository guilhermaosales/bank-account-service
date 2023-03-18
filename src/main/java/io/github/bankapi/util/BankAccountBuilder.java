package io.github.bankapi.util;

import io.github.bankapi.enums.BankAccountTypeEnum;
import io.github.bankapi.model.BankAccount;
import io.github.bankapi.model.BankHolder;
import io.github.bankapi.model.dto.BankAccountDTO;
import lombok.experimental.UtilityClass;

import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.time.ZoneId;

@UtilityClass
public class BankAccountBuilder {

        public static BankAccount updateBankAccount(BankAccountDTO dto, BankAccount model) {

                var bh = BankHolder.builder()
                                .id(model.getId())
                                .fullName(StringUtils.hasLength(dto.bankHolder().getFullName())
                                                ? dto.bankHolder().getFullName()
                                                : model.getBankHolder().getFullName())
                                .build();

                BankAccount.builder().bankHolder(bh)
                                .account(model.getAccount())
                                .accountType(StringUtils.hasLength(dto.accountType())
                                                ? BankAccountTypeEnum.valueOf(dto.accountType())
                                                : model.getAccountType())
                                .lastUpdateDate(LocalDateTime.now(ZoneId.of("UTC")))
                                .registrationDate(model.getRegistrationDate())
                                .preferredAccount(model.isPreferredAccount())
                                .build();

                return model;
        }

        public static BankAccount createBankAccount(BankAccountDTO dto) {

                BankAccount bankAccount = BankAccount.builder()
                                .accountType(BankAccountTypeEnum.valueOf(dto.accountType()))
                                .registrationDate(LocalDateTime.now(ZoneId.of("UTC")))
                                .build();

                bankAccount.setLastUpdateDate(bankAccount.getRegistrationDate());

                BeanUtils.copyProperties(dto, bankAccount);

                return bankAccount;

        }
}
