package io.github.bankapi.service;

import io.github.bankapi.model.BankAccount;
import io.github.bankapi.model.dto.BankAccountDTO;
import io.github.bankapi.model.dto.BankAccountResponse;
import org.springframework.data.domain.Pageable;

import javax.transaction.Transactional;

import java.util.List;
import java.util.UUID;

public interface BankAccountService {

    @Transactional
    BankAccountResponse createBankAccount(BankAccountDTO bank);

    BankAccountResponse getOneBankAccount(UUID id);

    BankAccountResponse updateBankAccount(UUID id, BankAccountDTO bank);

    List<BankAccount> getAllBankAccounts(Pageable pageable);

    @Transactional
    void deleteBankAccount(UUID id);
}
