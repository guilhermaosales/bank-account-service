package oi.github.bankapi.controller;

import oi.github.bankapi.dto.BankAccountDTO;
import oi.github.bankapi.enums.BankAccountTypeEnum;
import oi.github.bankapi.model.BankAccount;
import oi.github.bankapi.service.BankAccountService;
import oi.github.bankapi.service.BankAccountServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneId;
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
    public ResponseEntity<BankAccount> createBankAccount(@RequestBody @Valid BankAccountDTO bankAccountDTO) {
        return bankAccountServiceImpl.createBankAccount(bankAccountDTO);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteBankAccount(@PathVariable UUID id) {
        bankAccountServiceImpl.deleteBankAccount(id);
        return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
    }
}
