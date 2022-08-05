package io.github.bankapi.mock;

import io.github.bankapi.enums.BankAccountTypeEnum;
import io.github.bankapi.model.BankAccount;
import io.github.bankapi.model.BankHolder;
import io.github.bankapi.model.dto.BankAccountForm;
import io.github.bankapi.model.dto.BankAccountResponse;

import java.util.UUID;

public class BankAccountMocks {

    public static UUID bankId = UUID.fromString("3d032ac0-80f9-4938-815f-da4e885eefe2");
    public static String account = "123434";
    public static String agency = "123434";
    public static String bankNumber = "123434";
    public static String firstName = "123434";
    public static String secondName = "123434";

    public static BankAccountForm bankAccountFormMock() {
        return BankAccountForm.builder()
                .account(account)
                .accountType("CHECKINGS")
                .agency(agency)
                .bankNumber(bankNumber)
                .bankHolder(BankHolder.builder()
                        .firstName(firstName)
                        .secondName(secondName).build())
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
                        .firstName(firstName)
                        .secondName(secondName).build())
                .build();
    }

    public static BankAccountResponse bankAccountResponseMock() {
        return BankAccountResponse.builder()
                .id(bankId)
                .account(account)
                .accountType(BankAccountTypeEnum.CHECKINGS.getType())
                .agency(agency)
                .bankNumber(bankNumber)
                .bankHolder(BankHolder.builder()
                        .firstName(firstName)
                        .secondName(secondName).build())
                .build();
    }

}
