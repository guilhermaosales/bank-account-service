package io.github.bankapi.controller;

import io.github.bankapi.dto.BankAccountDTO;
import io.github.bankapi.model.BankAccount;
import io.github.bankapi.service.BankAccountServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/bank-account")
public class BankAccountController {

    final BankAccountServiceImpl bankAccountServiceImpl;

    public BankAccountController(BankAccountServiceImpl bankAccountServiceImpl) {
        this.bankAccountServiceImpl = bankAccountServiceImpl;
    }

    @PostMapping
    public ResponseEntity<Object> createBankAccount(@RequestBody @Valid BankAccountDTO bankAccountDTO) {
        return bankAccountServiceImpl.createBankAccount(bankAccountDTO);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteBankAccount(@PathVariable UUID id) {
        bankAccountServiceImpl.deleteBankAccount(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getOneBankAccount(@PathVariable UUID id) {
        return bankAccountServiceImpl.getOneBankAccount(id);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> updateBankAccount(@PathVariable UUID id, @RequestBody BankAccountDTO bankAccountDTO) {
        return bankAccountServiceImpl.updateBankAccount(id, bankAccountDTO);
    }

    @GetMapping
    public ResponseEntity<Page<BankAccount>> getAllBankAccounts(Pageable pageable) {
        return bankAccountServiceImpl.getAllBankAccounts(pageable);
    }

}
