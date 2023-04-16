package io.github.bankapi.service;

import io.github.bankapi.model.BankAccount;
import io.github.bankapi.repository.BankAccountRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static io.github.bankapi.mock.BankAccountMocks.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BankAccountServiceTest {

    @InjectMocks
    private BankAccountServiceImpl serviceImpl;

    @Mock
    private BankAccountRepository repository;

    @Test
    void shouldCreateABankAccount() throws Exception {

        when(repository.existsByAccount(bankAccountToBeSaved().getAccount())).thenReturn(false);
        when(repository.save(any(BankAccount.class))).thenReturn(bankAccountToBeSaved());

        var result = serviceImpl.createBankAccount(bankAccountFormMock());

        verify(repository).existsByAccount(anyString());
        verify(repository).save(any(BankAccount.class));
        assertEquals(result, bankAccountResponseMock());
    }

    @Test
    void shouldReturnBankAccountById() throws Exception {
        when(repository.findById(BANK_ID)).thenReturn(Optional.of(bankAccountToBeSaved()));

        var result = serviceImpl.getOneBankAccount(BANK_ID);

        verify(repository).findById(any());
        assertEquals(result, bankAccountResponseMock());
    }

    @Test
    void shouldUpdateBankAccountSuccessfully() throws Exception {
        when(repository.findById(BANK_ID)).thenReturn(Optional.of(bankAccountToBeSaved()));
        when(repository.save(any(BankAccount.class))).thenReturn(bankAccountToBeSaved());

        var result = serviceImpl.updateBankAccount(BANK_ID, bankAccountFormMock());

        verify(repository, atLeastOnce()).findById(any());
        verify(repository).save(any(BankAccount.class));
        assertEquals(result, bankAccountResponseMock());
    }

    @Test
    void shouldDeleteABankAccountSuccessfully() throws Exception {
        when(repository.findById(BANK_ID)).thenReturn(Optional.of(bankAccountToBeSaved()));

        serviceImpl.deleteBankAccount(BANK_ID);

        verify(repository, atLeastOnce()).findById(any());
        verify(repository, atLeastOnce()).deleteById(any());

    }
}
