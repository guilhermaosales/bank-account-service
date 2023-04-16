package io.github.bankapi.service;

import io.github.bankapi.exception.BankAccountException;
import io.github.bankapi.exception.ConflictException;
import io.github.bankapi.exception.NotFoundException;
import io.github.bankapi.model.BankAccount;
import io.github.bankapi.model.dto.BankAccountDTO;
import io.github.bankapi.model.dto.BankAccountResponse;
import io.github.bankapi.model.mapper.BankAccountMapper;
import io.github.bankapi.repository.BankAccountRepository;
import io.github.bankapi.util.BankAccountBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;

import static io.github.bankapi.constant.Constant.BANK_NOT_FOUND;


@Service
@RequiredArgsConstructor
public class BankAccountServiceImpl implements BankAccountService {

    @Autowired
    private final BankAccountRepository repository;

    @Override
    public BankAccountResponse createBankAccount(@RequestBody BankAccountDTO form)
            throws BankAccountException {

        accountExists(form.account());

        var bankAccount = BankAccountMapper.INSTANCE.toEntity(form);
        bankAccount.setRegistrationDate(LocalDateTime.now());
        bankAccount.setLastUpdateDate(LocalDateTime.now());

        var entity = repository.save(bankAccount);

        return BankAccountMapper.INSTANCE.toResponse(entity);

    }

    @Override
    public BankAccountResponse getOneBankAccount(Long id) throws BankAccountException {
        BankAccount res = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(BANK_NOT_FOUND));
        return BankAccountMapper.INSTANCE.toResponse(res);
    }

    @Override
    public BankAccountResponse updateBankAccount(Long id, @RequestBody BankAccountDTO bankAccountForm)
            throws BankAccountException {
        if (repository.findById(id).isPresent()) {
            var newBankAccount = BankAccountBuilder.updateBankAccount(bankAccountForm, repository.findById(id).get());
            return BankAccountMapper.INSTANCE.toResponse(repository.save(newBankAccount));
        } else {
            throw new NotFoundException(BANK_NOT_FOUND);
        }
    }

    @Override
    public void deleteBankAccount(Long id) throws BankAccountException {
        if (repository.findById(id).isPresent()) {
            repository.deleteById(id);
        } else {
            throw new NotFoundException("Bank account not found!");
        }
    }

    public void accountExists(String bankAccount) throws BankAccountException {
        if (repository.existsByAccount(bankAccount))
            throw new ConflictException("Account already registered!");
    }
}
