package oi.github.bankapi.enums;

public enum BankAccountType {

    CHECKING("Checking"),
    SAVINGS("Savings");

    private String type;

    BankAccountType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
