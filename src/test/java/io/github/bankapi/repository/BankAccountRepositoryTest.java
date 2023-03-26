package io.github.bankapi.repository;

import io.github.bankapi.mock.BankAccountMocks;
import io.github.bankapi.model.BankAccount;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.validation.ConstraintViolationException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@DataJpaTest
@DisplayName("Tests for BankAccountRepository")
@Log4j2
class BankAccountRepositoryTest {

    @Autowired
    private BankAccountRepository repository;

    @Test
    void save_persistBankAccountData_successfully() {
        BankAccount bankAccountToBeSaved = BankAccountMocks.bankAccountForRepositoryMock();
        BankAccount bankAccountSaved = repository.save(bankAccountToBeSaved);

        assertThat(bankAccountToBeSaved)
                .usingRecursiveComparison()
                .isEqualTo(bankAccountSaved);
    }

    @Test
    void save_updatesBankAccountData_successfully() {
        BankAccount bankAccountToBeSaved = BankAccountMocks.bankAccountForRepositoryMock();
        BankAccount bankAccountSaved = repository.save(bankAccountToBeSaved);
        bankAccountSaved.setPreferredAccount(true);

        BankAccount bankAccountUpdated = repository.save(bankAccountSaved);

        assertThat(bankAccountUpdated.isPreferredAccount()).isTrue();
    }

    @Test
    void delete_removesBankAccountData_successfully() {
        BankAccount bankAccountToBeSaved = BankAccountMocks.bankAccountForRepositoryMock();
        BankAccount bankAccountSaved = repository.save(bankAccountToBeSaved);

        repository.delete(bankAccountSaved);

        Optional<BankAccount> deletedBankAccount = repository.findById(bankAccountSaved.getId());

        assertThat(deletedBankAccount.isEmpty()).isTrue();

    }

    @Test
    void get_findBankAccountData_successfully() {
        BankAccount bankAccountToBeSaved = BankAccountMocks.bankAccountForRepositoryMock();
        BankAccount bankAccountSaved = repository.save(bankAccountToBeSaved);

        repository.findById(bankAccountSaved.getId());

        Optional<BankAccount> foundBankAccount = repository.findById(bankAccountSaved.getId());

        assertThat(foundBankAccount.isPresent()).isTrue();

    }

    @Test
    void save_throwConstraintValidationExceptionBankAccountData_successfully() {
        BankAccount bankAccountToBeSaved = new BankAccount();

        assertThatThrownBy(
                ()-> repository.save(bankAccountToBeSaved))
                .isInstanceOf(ConstraintViolationException.class)
                .hasMessageContaining("Account cannot be empty");

    }
}
