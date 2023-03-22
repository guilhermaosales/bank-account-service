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

        when(repository.existsByAccount(bankAccountMock().getAccount())).thenReturn(false);
        when(repository.save(any(BankAccount.class))).thenReturn(bankAccountMock());

        var result = serviceImpl.createBankAccount(bankAccountFormMock());

        verify(repository).existsByAccount(anyString());
        verify(repository).save(any(BankAccount.class));
        assertEquals(result, bankAccountResponseMock());
    }

    @Test
    void shouldReturnBankAccountById() throws Exception {
        when(repository.findById(bankId)).thenReturn(Optional.of(bankAccountMock()));

        var result = serviceImpl.getOneBankAccount(bankId);

        verify(repository).findById(any());
        assertEquals(result, bankAccountResponseMock());
    }

    @Test
    void shouldUpdateBankAccountSuccessfully() throws Exception {
        when(repository.findById(bankId)).thenReturn(Optional.of(bankAccountMock()));
        when(repository.save(any(BankAccount.class))).thenReturn(bankAccountMock());

        var result = serviceImpl.updateBankAccount(bankId, bankAccountFormMock());

        verify(repository, atLeastOnce()).findById(any());
        verify(repository).save(any(BankAccount.class));
        assertEquals(result, bankAccountResponseMock());
    }

    @Test
    void shouldDeleteABankAccountSuccessfully() throws Exception {
        when(repository.findById(bankId)).thenReturn(Optional.of(bankAccountMock()));

        serviceImpl.deleteBankAccount(bankId);

        verify(repository, atLeastOnce()).findById(any());
        verify(repository, atLeastOnce()).deleteById(any());

    }
}
