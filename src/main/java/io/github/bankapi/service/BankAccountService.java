package io.github.bankapi.service;

import io.github.bankapi.dto.BankAccountDTO;
import io.github.bankapi.model.BankAccount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

public interface BankAccountService {

    @Transactional
    ResponseEntity<Object> createBankAccount(BankAccountDTO bank);

    ResponseEntity<Object> getOneBankAccount(UUID id);
    ResponseEntity<Object> updateBankAccount(UUID id, BankAccountDTO bank);
    ResponseEntity<Page<BankAccount>> getAllBankAccounts(Pageable pageable);

    @Transactional
    void deleteBankAccount(UUID id);
}
