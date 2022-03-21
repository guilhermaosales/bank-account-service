package oi.github.bankapi.service;

import oi.github.bankapi.dto.BankAccountDTO;
import oi.github.bankapi.model.BankAccount;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface BankAccountService {

    ResponseEntity<BankAccount> createBankAccount(BankAccountDTO bank);
    ResponseEntity<Object> getBankAccount(UUID id);
    ResponseEntity<Object> updateBankAccount(UUID id, BankAccountDTO bank);
    void deleteBankAccount(UUID id);
}
