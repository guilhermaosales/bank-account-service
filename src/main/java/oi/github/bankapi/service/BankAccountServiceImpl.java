package oi.github.bankapi.service;

import oi.github.bankapi.dto.BankAccountDTO;
import oi.github.bankapi.enums.BankAccountTypeEnum;
import oi.github.bankapi.model.BankAccount;
import oi.github.bankapi.repository.BankAccountRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

@Service
public class BankAccountServiceImpl implements BankAccountService {

    private final BankAccountRepository repository;

    public BankAccountServiceImpl(BankAccountRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public ResponseEntity<BankAccount> createBankAccount(@RequestBody BankAccountDTO bankAccountDTO) {
        var bankAccount = new BankAccount();
        BeanUtils.copyProperties(bankAccountDTO, bankAccount);
        bankAccount.setAccountType(BankAccountTypeEnum.valueOf(bankAccountDTO.getAccountType()));
        bankAccount.setPreferredAccount(Boolean.TRUE);
        bankAccount.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
        bankAccount.setLastUpdateDate(bankAccount.getRegistrationDate());
        return ResponseEntity.ok(repository.save(bankAccount));
    }

    @Override
    @Transactional
    public void deleteBankAccount(UUID id) {
        repository.deleteById(id);
    }

//    @Override
//    public ResponseEntity<BankAccount> getBankAccount(UUID id) throws Exception {
//        return repository.findById(id);
//    }
//
//    @Override
//    public ResponseEntity<?> updateBankAccount(UUID id, @RequestBody BankAccount bank) {
//        return ResponseEntity.ok(repository.save(bank));
//    }
//
//    @Override
//    public ResponseEntity<?> deleteBankAccount(UUID id) {
//        repository.findById(id).map( repository.delete(id))
//        return ResponseEntity.noContent();
//    }
}
