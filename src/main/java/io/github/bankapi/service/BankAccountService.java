package io.github.bankapi.service;

import io.github.bankapi.exception.BankAccountException;
import io.github.bankapi.model.dto.BankAccountDTO;
import io.github.bankapi.model.dto.BankAccountResponse;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;


@Component
public interface BankAccountService {

    @Transactional(rollbackOn = Exception.class)
    BankAccountResponse createBankAccount(BankAccountDTO bank) throws BankAccountException;

    BankAccountResponse getOneBankAccount(Long id) throws BankAccountException;

    BankAccountResponse updateBankAccount(Long id, BankAccountDTO bank) throws BankAccountException;

    @Transactional(rollbackOn = Exception.class)
    void deleteBankAccount(Long id) throws BankAccountException;
}
