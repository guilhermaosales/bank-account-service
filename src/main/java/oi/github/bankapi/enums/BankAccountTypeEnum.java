package oi.github.bankapi.enums;

import lombok.Getter;

@Getter
public enum BankAccountType {

    CHECKING("Checking"),
    SAVINGS("Savings");

    private String type;

    BankAccountType(String type) {
        this.type = type;
    }

}
