package io.github.bankapi.mock;

import io.github.bankapi.enums.BankAccountTypeEnum;
import io.github.bankapi.model.BankAccount;
import io.github.bankapi.model.BankHolder;
import io.github.bankapi.model.dto.BankAccountDTO;
import io.github.bankapi.model.dto.BankAccountResponse;
import io.github.bankapi.model.dto.BankHolderDTO;

import java.time.LocalDateTime;
import java.util.UUID;

public class BankAccountMocks {

        public final static UUID bankId = UUID.fromString("3d032ac0-80f9-4938-815f-da4e885eefe2");
        public final static String agency = "1234";
        public final static String account = "1234567";
        public final static String bankNumber = "123";
        public final static String fullName = "Guilherme Alfredo";
        public final static LocalDateTime date = LocalDateTime.now();

        public static BankAccountDTO bankAccountFormMock() {
                return BankAccountDTO.builder()
                                .account(account)
                                .accountType("CHECKINGS")
                                .agency(agency)
                                .bankNumber(bankNumber)
                                .bankHolder(BankHolderDTO.builder()
                                                .fullName(fullName)
                                        .build())
                                .build();
        }

        public static BankAccount bankAccountMock() {
                return BankAccount.builder()
                                .id(bankId)
                                .account(account)
                                .accountType(BankAccountTypeEnum.CHECKINGS)
                                .agency(agency)
                                .bankNumber(bankNumber)
                                .bankHolder(BankHolder.builder()
                                        .id(bankId)
                                        .fullName(fullName)
                                        .build())
                                .registrationDate(date)
                                .lastUpdateDate(date)
                                .preferredAccount(Boolean.FALSE)
                                .build();
        }

        public static BankAccountResponse bankAccountResponseMock() {
                return BankAccountResponse.builder()
                                .id(bankId)
                                .account(account)
                                .accountType(BankAccountTypeEnum.CHECKINGS)
                                .agency(agency)
                                .bankNumber(bankNumber)
                                .bankHolder(BankHolder.builder()
                                        .id(bankId)
                                        .fullName(fullName)
                                        .build())
                                .registrationDate(date)
                                .lastUpdateDate(date)
                                .preferredAccount(Boolean.FALSE)
                                .build();
        }

}
