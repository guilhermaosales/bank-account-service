package io.github.bankapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.bankapi.mock.BankAccountMocks;
import io.github.bankapi.model.dto.BankAccountDTO;
import io.github.bankapi.service.BankAccountService;
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

import static io.github.bankapi.mock.BankAccountMocks.bankAccountFormMock;
import static io.github.bankapi.mock.BankAccountMocks.bankAccountResponseMock;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
class BankAccountControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @InjectMocks
    private BankAccountController controller;
    @MockBean
    private BankAccountService service;
    @Autowired
    private ObjectMapper objectMapper;


    private static final Long ID = BankAccountMocks.BANK_ID;
    private static final String BANK_ACCOUNT_URI = "/bank-account";

    @Test
    void shouldCreateBankAccountSuccessfully() throws Exception {

        mockMvc.perform(post(BANK_ACCOUNT_URI)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(bankAccountFormMock())))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    void shouldDeleteBankAccountSuccessfully() throws Exception {
        doNothing().when(service).deleteBankAccount(ID);

        mockMvc.perform(delete(BANK_ACCOUNT_URI.concat("/{id}"), ID)
                .contentType("application/json"))
                .andDo(print())
                .andExpect(status().isNoContent());

    }

    @Test
    void shouldReturnBankAccountSuccessfully() throws Exception {

        mockMvc.perform(get(BANK_ACCOUNT_URI.concat("/{id}"), ID)
                .contentType("application/json"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void shouldUpdateBankAccountSuccessfully() throws Exception {

        mockMvc.perform(put(BANK_ACCOUNT_URI.concat("/{id}"), ID)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(bankAccountFormMock())))
                .andDo(print())
                .andExpect(status().isOk());

    }
}
