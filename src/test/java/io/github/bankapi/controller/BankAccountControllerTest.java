package io.github.bankapi.controller;

import io.github.bankapi.mock.BankAccountMocks;
import io.github.bankapi.model.dto.BankAccountDTO;
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

    private static final UUID ID = BankAccountMocks.bankId;
    private static final String BANK_ACCOUNT_URI = "/bank-account";

    @Test
    void shouldCreateBankAccountSuccessfully() throws Exception {

        ArgumentCaptor<BankAccountDTO> bankAccountCaptor = ArgumentCaptor.forClass(BankAccountDTO.class);

        when(service.createBankAccount(bankAccountCaptor.capture())).thenReturn(bankAccountResponseMock());

        mockMvc.perform(post(BANK_ACCOUNT_URI)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(bankAccountFormMock())))
                .andDo(print())
                .andExpect(status().isCreated());

        var actual = controller.createBankAccount(bankAccountFormMock());

        verify(service, atLeastOnce()).createBankAccount(bankAccountCaptor.capture());

        assertEquals(bankAccountFormMock(), bankAccountCaptor.getValue());
        assertEquals(new ResponseEntity<>(bankAccountResponseMock(), HttpStatus.CREATED), actual);
    }

    @Test
    void shouldDeleteBankAccountSuccessfully() throws Exception {

        doNothing().when(service).deleteBankAccount(ID);

        mockMvc.perform(delete(BANK_ACCOUNT_URI.concat("/{id}"), ID)
                .contentType("application/json"))
                .andDo(print())
                .andExpect(status().isAccepted());

        var actual = controller.deleteBankAccount(ID);

        assertEquals(new ResponseEntity<>(HttpStatus.ACCEPTED), actual);

    }

    @Test
    void shouldReturnBankAccountSuccessfully() throws Exception {

        when(service.getOneBankAccount(ID)).thenReturn(bankAccountResponseMock());

        mockMvc.perform(get(BANK_ACCOUNT_URI.concat("/{id}"), ID)
                .contentType("application/json"))
                .andDo(print())
                .andExpect(status().isOk());

        var actual = controller.getOneBankAccount(ID);
        
        verify(service, atLeastOnce()).getOneBankAccount(ID);

        assertEquals(new ResponseEntity<>(bankAccountResponseMock(), HttpStatus.OK), actual);
    }

    @Test
    void shouldUpdateBankAccountSuccessfully() throws Exception {

        when(service.updateBankAccount(ID, bankAccountFormMock())).thenReturn(bankAccountResponseMock());

        mockMvc.perform(put(BANK_ACCOUNT_URI.concat("/{id}"), ID)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(bankAccountFormMock())))
                .andDo(print())
                .andExpect(status().isOk());

        var actual = controller.updateBankAccount(ID, bankAccountFormMock());

        verify(service, atLeastOnce()).updateBankAccount(ID, bankAccountFormMock());

        assertEquals(new ResponseEntity<>(bankAccountResponseMock(), HttpStatus.OK), actual);
    }
}
