package io.github.bankapi.controller;

import io.github.bankapi.mock.BankAccountMocks;
import io.github.bankapi.model.dto.BankAccountForm;
import io.github.bankapi.service.BankAccountServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import static io.github.bankapi.mock.BankAccountMocks.bankAccountFormMock;
import static io.github.bankapi.mock.BankAccountMocks.bankAccountResponseMock;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.UUID;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
class BankAccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private BankAccountController controller;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private BankAccountServiceImpl service;

    public static UUID ID = BankAccountMocks.bankId;

    @Test
    void shouldCreateABankAccountSuccessfully() throws Exception {

        when(service.createBankAccount(any(BankAccountForm.class))).thenReturn(bankAccountResponseMock());

        mockMvc.perform(post("/bank-account")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(bankAccountFormMock())))
                .andDo(print())
                .andExpect(status().isCreated());

        var actual = controller.createBankAccount(bankAccountFormMock());
        
        ArgumentCaptor<BankAccountForm> bankAccountCaptor = ArgumentCaptor.forClass(BankAccountForm.class);
        verify(service, atLeastOnce()).createBankAccount(bankAccountCaptor.capture());

        assertEquals(bankAccountFormMock(), bankAccountCaptor.getValue());
        assertEquals(actual, new ResponseEntity<>(bankAccountResponseMock(), HttpStatus.CREATED));
    }

    @Test
    void shouldDeleteAccountSuccessfully() throws Exception {

        doNothing().when(service).deleteBankAccount(ID);

        var actual = controller.deleteBankAccount(ID);

        assertEquals(actual, new ResponseEntity<>(HttpStatus.ACCEPTED));

    }
}
