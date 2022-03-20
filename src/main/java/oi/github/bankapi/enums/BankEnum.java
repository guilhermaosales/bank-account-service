package oi.github.bankapi.enums;

import lombok.Getter;

@Getter
public enum BankEnum {

    NUBANK("260", "NU PAGAMENTOS"),
    INTER("077", "INTER");

    private String code;
    private String name;

    BankEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }
}
