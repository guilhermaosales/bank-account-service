package io.github.bankapi.service;

import io.github.bankapi.exception.BankAccountException;
import io.github.bankapi.exception.ConflictException;
import io.github.bankapi.exception.NotFoundException;
import io.github.bankapi.model.BankAccount;
import io.github.bankapi.model.dto.BankAccountDTO;
import io.github.bankapi.model.dto.BankAccountResponse;
import io.github.bankapi.model.mapper.BankAccountMapper;
import io.github.bankapi.repository.BankAccountRepository;
import io.github.bankapi.util.BankAccountBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

@Service
public class BankAccountServiceImpl implements BankAccountService {

    @Autowired
    private BankAccountRepository repository;

    @Override
    public BankAccountResponse createBankAccount(@RequestBody BankAccountDTO bankAccountForm)
            throws BankAccountException {

        accountExists(bankAccountForm.account());

        var newRegistry = BankAccountBuilder.createBankAccount(bankAccountForm);
        var entity = repository.save(newRegistry);

        return BankAccountMapper.INSTANCE.toResponse(entity);

    }

    @Override
    public BankAccountResponse getOneBankAccount(UUID id) throws BankAccountException {
        var res = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Bank account not found!"));
        return new BankAccountResponse(res);
    }

    @Override
    public BankAccountResponse updateBankAccount(UUID id, @RequestBody BankAccountDTO bankAccountForm)
            throws BankAccountException {
        var newBankAccount = BankAccountBuilder.updateBankAccount(bankAccountForm, getBankAccountById(id));
        return BankAccountMapper.INSTANCE.toResponse(repository.save(newBankAccount));
    }

    @Override
    public void deleteBankAccount(UUID id) throws BankAccountException {
        if (repository.findById(id).isPresent()) {
            repository.deleteById(id);
        } else {
            throw new NotFoundException("Bank account not found!");
        }

    }

    @Override
    public List<BankAccount> getAllBankAccounts(Pageable pageable) {
        return repository.findAll(pageable).getContent();
    }

    public BankAccount getBankAccountById(UUID id) throws BankAccountException {

        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Bank account not found!"));
    }

    public void accountExists(String bankAccount) throws BankAccountException {
        if (repository.existsByAccount(bankAccount))
            throw new ConflictException("Account already registered!");
    }
}
