package oi.github.bankapi.service;

import oi.github.bankapi.dto.BankAccountDTO;
import oi.github.bankapi.model.BankAccount;
import org.springframework.http.ResponseEntity;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

public interface BankAccountService {

    @Transactional
    ResponseEntity<Object> createBankAccount(BankAccountDTO bank);

    ResponseEntity<Object> getOneBankAccount(UUID id);
    ResponseEntity<Object> updateBankAccount(UUID id, BankAccountDTO bank);
    ResponseEntity<List<BankAccount>> getAllBankAccounts();

    @Transactional
    void deleteBankAccount(UUID id);
}
