package oi.github.bankapi.enums;

public enum Bank {

    NUBANK("260", "NUBANK PAGAMENTOS"),
    INTER("077", "INTER");

    private String code;
    private String name;

    private void getCode() {
        this.code = code;
    }

    private void getName() {
        this.name = name;
    }

    Bank(String code, String name) {
        this.code = code;
        this.name = name;
    }
}
