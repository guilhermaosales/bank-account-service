package io.github.bankapi.service;

import io.github.bankapi.dto.BankAccountDTO;
import io.github.bankapi.repository.BankAccountRepository;
import io.github.bankapi.util.BankAccountBuilder;
import io.github.bankapi.model.BankAccount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BankAccountServiceImpl implements BankAccountService {

    private final BankAccountRepository repository;

    public BankAccountServiceImpl(BankAccountRepository repository) {
        this.repository = repository;
    }

    @Override
    public ResponseEntity<Object> createBankAccount(@RequestBody BankAccountDTO bankAccountDTO) {

        if (!repository.existsByAccount(bankAccountDTO.getAccount())) {
            var newRegistry = BankAccountBuilder.createBankAccount(bankAccountDTO);
            return ResponseEntity.ok(repository.save(newRegistry));
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Account already registered!");
    }

    @Override
    public void deleteBankAccount(UUID id) {
        getOneBankAccount(id);
        repository.deleteById(id);
    }

    @Override
    @ResponseStatus
    public ResponseEntity<Object> getOneBankAccount(UUID id) {
        Optional<BankAccount> optionalBankAccount = repository.findById(id);
        return repository.findById(id).isPresent() ?
                ResponseEntity.status(HttpStatus.OK).body(optionalBankAccount.get()) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body("Bank account not found!");
    }

    @Override
    public ResponseEntity<Object> updateBankAccount(UUID id, @RequestBody BankAccountDTO bankAccountDTO) {
        Optional<BankAccount> optionalBankAccount = repository.findById(id);

        if (optionalBankAccount.isPresent()) {
            if (!repository.existsByAccount(bankAccountDTO.getAccount()) || bankAccountDTO.getAccount() == null) {
                var newRegistry = BankAccountBuilder.updateBankAccount(bankAccountDTO, optionalBankAccount.get(), optionalBankAccount.get().getBankHolder());
                return ResponseEntity.ok(repository.save(newRegistry));
            }
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Bank account already registered!");
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Bank account not found!");
        }
    }

    @Override
    public ResponseEntity<Page<BankAccount>> getAllBankAccounts(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(repository.findAll(pageable));
    }

}
