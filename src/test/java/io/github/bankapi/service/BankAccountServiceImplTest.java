package io.github.bankapi.service;

import io.github.bankapi.model.BankAccount;
import io.github.bankapi.repository.BankAccountRepository;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static io.github.bankapi.mock.BankAccountMocks.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BankAccountServiceImplTest {

    @InjectMocks
    private BankAccountServiceImpl serviceImpl;

    @Mock
    private BankAccountRepository repository;

    @Test
    void shouldCreateABankAccount() {

        when(repository.existsByAccount(bankAccountMock().getAccount())).thenReturn(false);
        when(repository.save(any(BankAccount.class))).thenReturn(bankAccountMock());

        var result = serviceImpl.createBankAccount(bankAccountFormMock());

        verify(repository).existsByAccount(anyString());
        verify(repository).save(any(BankAccount.class));
        assertEquals(result, bankAccountResponseMock());

    }

//    @Test
//    @Ignore
//    void shouldDeleteABankAccountSuccessfully() {
//        when(serviceImpl.getOneBankAccount(bank_id)).thenReturn(bankAccountResponseMock());
//
//        serviceImpl.deleteBankAccount(bank_id);
//        verify(repository, atLeastOnce()).existsByAccount(anyString());
//        verify(repository, atLeastOnce()).delete(any(BankAccount.class));
//
//    }
}
