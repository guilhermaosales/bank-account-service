package io.github.bankapi.controller;

import io.github.bankapi.exception.BankAccountException;
import io.github.bankapi.model.dto.BankAccountDTO;
import io.github.bankapi.model.dto.BankAccountResponse;
import io.github.bankapi.service.BankAccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/bank-account")
@RequiredArgsConstructor
public class BankAccountController {

    private final BankAccountService bankAccountService;

    @PostMapping
    public ResponseEntity<BankAccountResponse> createBankAccount(@RequestBody @Valid BankAccountDTO bankAccountForm)
            throws BankAccountException {
        return new ResponseEntity<>(bankAccountService.createBankAccount(bankAccountForm), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteBankAccount(@PathVariable Long id) throws BankAccountException {
        bankAccountService.deleteBankAccount(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<BankAccountResponse> getOneBankAccount(@PathVariable Long id) throws BankAccountException {
        return new ResponseEntity<>(bankAccountService.getOneBankAccount(id), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<BankAccountResponse> updateBankAccount(@PathVariable Long id,
            @RequestBody BankAccountDTO bankAccountForm) throws BankAccountException {
        return new ResponseEntity<>(bankAccountService.updateBankAccount(id, bankAccountForm), HttpStatus.OK);
    }
}
