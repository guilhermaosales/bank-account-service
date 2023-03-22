package io.github.bankapi.service;

import io.github.bankapi.exception.BankAccountException;
import io.github.bankapi.exception.ConflictException;
import io.github.bankapi.exception.NotFoundException;
import io.github.bankapi.model.BankAccount;
import io.github.bankapi.model.dto.BankAccountDTO;
import io.github.bankapi.model.dto.BankAccountResponse;
import org.springframework.data.domain.Pageable;

import javax.transaction.Transactional;

import java.util.List;
import java.util.UUID;

public interface BankAccountService {

    @Transactional(rollbackOn = Exception.class)
    BankAccountResponse createBankAccount(BankAccountDTO bank) throws BankAccountException;

    BankAccountResponse getOneBankAccount(UUID id) throws BankAccountException;

    BankAccountResponse updateBankAccount(UUID id, BankAccountDTO bank) throws BankAccountException;

    @Transactional(rollbackOn = Exception.class)
    void deleteBankAccount(UUID id) throws BankAccountException;
}
