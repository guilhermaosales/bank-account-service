package io.github.bankapi.repository;

import io.github.bankapi.mock.BankAccountMocks;
import io.github.bankapi.model.BankAccount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
@DisplayName("Tests for BankAccountRepository")
class BankAccountRepositoryTest {

    @Autowired
    private BankAccountRepository repository;

    @Test
    void save_persistBankAccountData_successfully() {
        BankAccount bankAccountToBeSaved = BankAccountMocks.bankAccountMock();
        BankAccount bankAccountSaved = repository.save(bankAccountToBeSaved);

        assertThat(bankAccountToBeSaved)
                .usingRecursiveComparison()
                .ignoringFields("id","bankHolder.id")
                .isEqualTo(bankAccountSaved);
    }

    @Test
    void save_updatesBankAccountData_successfully() {
        BankAccount bankAccountToBeSaved = BankAccountMocks.bankAccountMock();
        BankAccount bankAccountSaved = repository.save(bankAccountToBeSaved);
        bankAccountSaved.setPreferredAccount(true);

        BankAccount bankAccountUpdated = repository.save(bankAccountSaved);

        assertThat(bankAccountUpdated.isPreferredAccount()).isTrue();
    }

    @Test
    void delete_removesBankAccountData_successfully() {
        BankAccount bankAccountToBeSaved = BankAccountMocks.bankAccountMock();
        BankAccount bankAccountSaved = repository.save(bankAccountToBeSaved);

        repository.delete(bankAccountSaved);

        Optional<BankAccount> deletedBankAccount = repository.findById(bankAccountSaved.getId());

        assertThat(deletedBankAccount.isEmpty()).isTrue();

    }

    @Test
    void get_findBankAccountData_successfully() {
        BankAccount bankAccountToBeSaved = BankAccountMocks.bankAccountMock();
        BankAccount bankAccountSaved = repository.save(bankAccountToBeSaved);

        repository.findById(bankAccountSaved.getId());

        Optional<BankAccount> foundBankAccount = repository.findById(bankAccountSaved.getId());

        assertThat(foundBankAccount.isPresent()).isTrue();

    }

    @Test
    void save_throwConstraintValidationExceptionBankAccountData_successfully() {
        BankAccount bankAccountToBeSaved = new BankAccount();
        bankAccountToBeSaved.setAgency("1234");
        bankAccountToBeSaved.setAccount("12343");

        assertThrows(MethodArgumentNotValidException.class, ()-> { repository.save(bankAccountToBeSaved); });

    }
}
