package io.github.bankapi.controller;

import io.github.bankapi.exception.BankAccountException;
import io.github.bankapi.model.dto.BankAccountDTO;
import io.github.bankapi.model.dto.BankAccountResponse;
import io.github.bankapi.service.BankAccountServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/bank-account")
public class BankAccountController {

    private BankAccountServiceImpl bankAccountServiceImpl;

    public BankAccountController(BankAccountServiceImpl bankAccountServiceImpl) {
        this.bankAccountServiceImpl = bankAccountServiceImpl;
    }

    @PostMapping
    public ResponseEntity<BankAccountResponse> createBankAccount(@RequestBody @Valid BankAccountDTO bankAccountForm)
            throws BankAccountException {
        return new ResponseEntity<>(bankAccountServiceImpl.createBankAccount(bankAccountForm), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteBankAccount(@PathVariable Long id) throws BankAccountException {
        bankAccountServiceImpl.deleteBankAccount(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<BankAccountResponse> getOneBankAccount(@PathVariable Long id) throws BankAccountException {
        return new ResponseEntity<>(bankAccountServiceImpl.getOneBankAccount(id), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<BankAccountResponse> updateBankAccount(@PathVariable Long id,
            @RequestBody BankAccountDTO bankAccountForm) throws BankAccountException {
        return new ResponseEntity<>(bankAccountServiceImpl.updateBankAccount(id, bankAccountForm), HttpStatus.OK);
    }
}
