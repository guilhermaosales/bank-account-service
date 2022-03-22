package oi.github.bankapi.service;

import oi.github.bankapi.dto.BankAccountDTO;
import oi.github.bankapi.enums.BankAccountTypeEnum;
import oi.github.bankapi.model.BankAccount;
import oi.github.bankapi.model.BankHolder;
import oi.github.bankapi.repository.BankAccountRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.time.ZoneId;
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
        var bankAccount = new BankAccount();
        if (!repository.existsByAccount(bankAccountDTO.getAccount())) {
            BeanUtils.copyProperties(bankAccountDTO, bankAccount);
            bankAccount.setAccountType(BankAccountTypeEnum.valueOf(bankAccountDTO.getAccountType()));
            bankAccount.setPreferredAccount(Boolean.TRUE);
            bankAccount.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
            bankAccount.setLastUpdateDate(bankAccount.getRegistrationDate());
            return ResponseEntity.ok(repository.save(bankAccount));
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Account already registered!");
    }

    @Override
    public void deleteBankAccount(UUID id) {
        getOneBankAccount(id);
        repository.deleteById(id);
    }

    @Override
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
            if (!repository.existsByAccount(bankAccountDTO.getAccount())) {
                var bankAccount = new BankAccount();
                var bankHolder = new BankHolder();
                BeanUtils.copyProperties(bankAccountDTO, bankAccount);
                bankAccount.setId(optionalBankAccount.get().getId());
                bankHolder.setId(optionalBankAccount.get().getBankHolder().getId());
                bankAccount.setBankHolder(bankHolder);
                bankAccount.setAccountType(BankAccountTypeEnum.valueOf(bankAccountDTO.getAccountType()));
                bankAccount.setLastUpdateDate(LocalDateTime.now(ZoneId.of("UTC")));
                return ResponseEntity.ok(repository.save(bankAccount));
            }
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Bank account already registered!");
        } else {

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Bank account not found!");
        }
    }

    @Override
    public ResponseEntity<List<BankAccount>> getAllBankAccounts() {
        return ResponseEntity.status(HttpStatus.OK).body(repository.findAll());
    }
}
