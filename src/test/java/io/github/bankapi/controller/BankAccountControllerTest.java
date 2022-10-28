package io.github.bankapi.controller;

import io.github.bankapi.model.dto.BankAccountForm;
import io.github.bankapi.service.BankAccountServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static io.github.bankapi.mock.BankAccountMocks.bankAccountFormMock;
import static io.github.bankapi.mock.BankAccountMocks.bankAccountResponseMock;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BankAccountControllerTest {

    @InjectMocks
    private BankAccountController controller;

    @Mock
    private BankAccountServiceImpl service;

    @Test
    void shouldCreateABankAccountSuccessfully() {

        when(service.createBankAccount(any(BankAccountForm.class))).thenReturn(bankAccountResponseMock());

        var actual = controller.createBankAccount(bankAccountFormMock());

        verify(service, atLeastOnce()).createBankAccount(any(BankAccountForm.class));

        assertEquals(actual, new ResponseEntity<>(bankAccountResponseMock(), HttpStatus.CREATED));
    }
}
