package io.github.bankapi.controller;

import io.github.bankapi.model.dto.BankAccountForm;
import io.github.bankapi.model.BankAccount;
import io.github.bankapi.model.dto.BankAccountResponse;
import io.github.bankapi.service.BankAccountServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/bank-account")
public class BankAccountController {

    private BankAccountServiceImpl bankAccountServiceImpl;

    public BankAccountController(BankAccountServiceImpl bankAccountServiceImpl) {
        this.bankAccountServiceImpl = bankAccountServiceImpl;
    }

    @PostMapping
    public ResponseEntity<BankAccountResponse> createBankAccount(@RequestBody @Valid BankAccountForm bankAccountForm) {
        return new ResponseEntity<>(bankAccountServiceImpl.createBankAccount(bankAccountForm), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteBankAccount(@PathVariable UUID id) {
        bankAccountServiceImpl.deleteBankAccount(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<BankAccountResponse> getOneBankAccount(@PathVariable UUID id) {
        return new ResponseEntity<>(bankAccountServiceImpl.getOneBankAccount(id), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<BankAccountResponse> updateBankAccount(@PathVariable UUID id, @RequestBody BankAccountForm bankAccountForm) {
        return new ResponseEntity<>(bankAccountServiceImpl.updateBankAccount(id, bankAccountForm), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<BankAccount>> getAllBankAccounts(Pageable pageable) {
        return new ResponseEntity<>((List<BankAccount>) bankAccountServiceImpl.getAllBankAccounts(pageable), HttpStatus.ACCEPTED);
    }

}
