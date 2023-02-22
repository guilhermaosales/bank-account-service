package io.github.bankapi.service;

import io.github.bankapi.model.BankAccount;
import io.github.bankapi.model.dto.BankAccountDTO;
import io.github.bankapi.model.dto.BankAccountResponse;
import io.github.bankapi.model.mapper.BankAccountMapper;
import io.github.bankapi.repository.BankAccountRepository;
import io.github.bankapi.util.BankAccountBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class BankAccountServiceImpl implements BankAccountService {

    @Autowired
    private BankAccountRepository repository;

    @Override
    public BankAccountResponse createBankAccount(@RequestBody BankAccountDTO bankAccountForm) {

        accountExists(bankAccountForm.account());

        var newRegistry = BankAccountBuilder.createBankAccount(bankAccountForm);
        var entity = repository.save(newRegistry);

        return BankAccountMapper.INSTANCE.toResponse(entity);

        // return new BankAccountResponse(entity);
    }

    @Override
    public BankAccountResponse getOneBankAccount(UUID id) {
        return new BankAccountResponse(getBankAccount(id));
    }

    @Override
    public BankAccountResponse updateBankAccount(UUID id, @RequestBody BankAccountDTO bankAccountForm) {
        var newBankAccount = BankAccountBuilder.updateBankAccount(bankAccountForm, getBankAccount(id));
        return new BankAccountResponse(repository.save(newBankAccount));
    }

    @Override
    public void deleteBankAccount(UUID id) {
        getOneBankAccount(id);
        repository.deleteById(id);
    }

    @Override
    public List<BankAccount> getAllBankAccounts(Pageable pageable) {
        return repository.findAll(pageable).getContent();
    }

    public BankAccount getBankAccount(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Bank account not found!"));
    }

    public void accountExists(String bankAccount) {
        if (repository.existsByAccount(bankAccount))
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Account already registered!");
    }
}
