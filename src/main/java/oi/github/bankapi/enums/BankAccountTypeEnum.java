package oi.github.bankapi.enums;

import lombok.Getter;

@Getter
public enum BankAccountTypeEnum {

    CHECKINGS("Checkings"),
    SAVINGS("Savings");

    private String type;

    BankAccountTypeEnum(String type) {
        this.type = type;
    }

}
