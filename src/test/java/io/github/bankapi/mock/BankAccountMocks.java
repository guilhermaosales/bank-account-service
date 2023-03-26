package io.github.bankapi.mock;

import io.github.bankapi.enums.BankAccountTypeEnum;
import io.github.bankapi.model.BankAccount;
import io.github.bankapi.model.BankHolder;
import io.github.bankapi.model.dto.BankAccountDTO;
import io.github.bankapi.model.dto.BankAccountResponse;
import io.github.bankapi.model.dto.BankHolderDTO;

import java.time.LocalDateTime;

public class BankAccountMocks {

        public final static Long BANK_ID = 1000L;
        public final static String AGENCY = "1234";
        public final static String ACCOUNT = "1234567";
        public final static String BANK_NUMBER = "123";
        public final static String FULL_NAME = "Guilherme Alfredo";
        public final static LocalDateTime DATE = LocalDateTime.now();

        public static BankAccountDTO bankAccountFormMock() {
                return BankAccountDTO.builder()
                                .account(ACCOUNT)
                                .accountType("CHECKINGS")
                                .agency(AGENCY)
                                .bankNumber(BANK_NUMBER)
                                .bankHolder(BankHolderDTO.builder()
                                                .fullName(FULL_NAME)
                                        .build())
                                .build();
        }

        public static BankAccount bankAccountMock() {
                return BankAccount.builder()
                                .id(BANK_ID)
                                .account(ACCOUNT)
                                .accountType(BankAccountTypeEnum.CHECKINGS)
                                .agency(AGENCY)
                                .bankNumber(BANK_NUMBER)
                                .bankHolder(BankHolder.builder()
                                        .id(BANK_ID)
                                        .fullName(FULL_NAME)
                                        .build())
                                .registrationDate(DATE)
                                .lastUpdateDate(DATE)
                                .preferredAccount(Boolean.FALSE)
                                .build();
        }

        public static BankAccount bankAccountForRepositoryMock() {
                return BankAccount.builder()
                        .account(ACCOUNT)
                        .accountType(BankAccountTypeEnum.CHECKINGS)
                        .agency(AGENCY)
                        .bankNumber(BANK_NUMBER)
                        .bankHolder(BankHolder.builder()
                                .fullName(FULL_NAME)
                                .build())
                        .registrationDate(DATE)
                        .lastUpdateDate(DATE)
                        .preferredAccount(Boolean.FALSE)
                        .build();
        }

        public static BankAccountResponse bankAccountResponseMock() {
                return BankAccountResponse.builder()
                                .id(BANK_ID)
                                .account(ACCOUNT)
                                .accountType(BankAccountTypeEnum.CHECKINGS)
                                .agency(AGENCY)
                                .bankNumber(BANK_NUMBER)
                                .bankHolder(BankHolder.builder()
                                        .id(BANK_ID)
                                        .fullName(FULL_NAME)
                                        .build())
                                .registrationDate(DATE)
                                .lastUpdateDate(DATE)
                                .preferredAccount(Boolean.FALSE)
                                .build();
        }

}
