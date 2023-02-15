package io.github.bankapi.service;

import io.github.bankapi.model.BankAccount;
import io.github.bankapi.model.dto.BankAccountForm;
import io.github.bankapi.model.dto.BankAccountResponse;
import io.github.bankapi.repository.BankAccountRepository;
import io.github.bankapi.util.BankAccountBuilder;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class BankAccountServiceImpl implements BankAccountService {

    private BankAccountRepository repository;

    public BankAccountServiceImpl(BankAccountRepository repository) {
        this.repository = repository;
    }

    @Override
    public BankAccountResponse createBankAccount(@RequestBody BankAccountForm bankAccountForm) {

        accountExists(bankAccountForm.getAccount());

        var newRegistry = BankAccountBuilder.createBankAccount(bankAccountForm);
        var entity = repository.save(newRegistry);

        return new BankAccountResponse(entity);
    }

    @Override
    public BankAccountResponse getOneBankAccount(UUID id) {
        return new BankAccountResponse(getBankAccount(id));
    }

    @Override
    public BankAccountResponse updateBankAccount(UUID id, @RequestBody BankAccountForm bankAccountForm) {
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
