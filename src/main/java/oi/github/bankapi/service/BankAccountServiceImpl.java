package oi.github.bankapi.service;

import oi.github.bankapi.dto.BankAccountDTO;
import oi.github.bankapi.enums.BankAccountTypeEnum;
import oi.github.bankapi.model.BankAccount;
import oi.github.bankapi.repository.BankAccountRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.UUID;

@Service
public class BankAccountServiceImpl implements BankAccountService {

    private final BankAccountRepository repository;

    public BankAccountServiceImpl(BankAccountRepository repository) {
        this.repository = repository;
    }

    @Override
    public ResponseEntity<BankAccount> createBankAccount(@RequestBody BankAccountDTO bankAccountDTO) {
        var bankAccount = new BankAccount();
        // TODO: add a validation in case of account already exists
        BeanUtils.copyProperties(bankAccountDTO, bankAccount);
        bankAccount.setAccountType(BankAccountTypeEnum.valueOf(bankAccountDTO.getAccountType()));
        bankAccount.setPreferredAccount(Boolean.TRUE);
        bankAccount.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
        bankAccount.setLastUpdateDate(bankAccount.getRegistrationDate());
        return ResponseEntity.ok(repository.save(bankAccount));
    }

    @Override
    public void deleteBankAccount(UUID id) {
        getBankAccount(id);
        repository.deleteById(id);
    }

    @Override
    public ResponseEntity<Object> getBankAccount(UUID id) {
        Optional<BankAccount> optionalBankAccount = repository.findById(id);
        return repository.findById(id).isPresent() ?
                ResponseEntity.status(HttpStatus.OK).body(optionalBankAccount.get()) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body("Bank account not found!");
    }

    @Override
    public ResponseEntity<Object> updateBankAccount(UUID id, @RequestBody BankAccountDTO bankAccountDTO) {
        Optional<BankAccount> optionalBankAccount = repository.findById(id);

        if (optionalBankAccount.isPresent()) {
            var bankAccount = new BankAccount();
            // TODO: add a validation in case of account already exists
            BeanUtils.copyProperties(bankAccountDTO, bankAccount);
            bankAccount.setAccountType(BankAccountTypeEnum.valueOf(bankAccountDTO.getAccountType()));
            bankAccount.setPreferredAccount(optionalBankAccount.get().isPreferredAccount());
            bankAccount.setRegistrationDate(optionalBankAccount.get().getRegistrationDate());
            bankAccount.setLastUpdateDate(LocalDateTime.now(ZoneId.of("UTC")));
            return ResponseEntity.ok(repository.save(bankAccount));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Bank account not found!");

    }
}
