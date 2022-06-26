package io.github.bankapi.service;

import io.github.bankapi.dto.BankAccountDTO;
import io.github.bankapi.enums.BankAccountTypeEnum;
import io.github.bankapi.model.BankAccount;
import io.github.bankapi.model.BankHolder;
import io.github.bankapi.repository.BankAccountRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BankAccountServiceImplTest {

    @InjectMocks
    private BankAccountServiceImpl serviceImpl;

    @Mock
    private BankAccountRepository repository;

    @Test
    void shouldCreateABankAccount() {

        BankAccountDTO realBankAccount = BankAccountDTO.builder()
                .account("123434")
                .accountType("CHECKINGS")
                .agency("123")
                .bankNumber("1233")
                .bankHolder(BankHolder.builder()
                        .firstName("Alfredo")
                        .secondName("Sales").build())
                .build();

        BankAccount bankAccountMock = BankAccount.builder()
                .account("123434")
                .accountType(BankAccountTypeEnum.CHECKINGS)
                .agency("123")
                .bankNumber("1233")
                .bankHolder(BankHolder.builder()
                        .firstName("Alfredo")
                        .secondName("Sales").build())
                .build();

        when(repository.existsByAccount(realBankAccount.getAccount())).thenReturn(false);
        when(repository.save(any(BankAccount.class))).thenReturn(bankAccountMock);

        var result = serviceImpl.createBankAccount(realBankAccount);

        verify(repository).existsByAccount(anyString());
        verify(repository).save(any(BankAccount.class));
        assertEquals(result.getBody(), bankAccountMock);

    }
}
