package io.github.bankapi.integration;

import io.github.bankapi.mock.BankAccountMocks;
import io.github.bankapi.model.BankAccount;
import io.github.bankapi.model.dto.BankAccountDTO;
import io.github.bankapi.repository.BankAccountRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;


@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
class BankAccountIntegrationTest {

    @Autowired
    TestRestTemplate testRestTemplate;
    @LocalServerPort
    private int port;
    @MockBean
    BankAccountRepository repository;

//    @BeforeEach
//    public void setUp() {
//
//        BDDMockito.when(repository.save(BankAccountMocks.bankAccountToBeSaved()))
//                .thenReturn(BankAccountMocks.bankAccountToBeSaved());
//
//        BDDMockito.when(repository.findById(ArgumentMatchers.anyLong()))
//                .thenReturn(Optional.of(BankAccountMocks.createBankAccount()));
//
//        BDDMockito.doNothing().when(repository).delete(ArgumentMatchers.any(BankAccount.class));
//
//        BDDMockito.when(repository.save(BankAccountMocks.createBankAccount()))
//                .thenReturn(BankAccountMocks.updatedBankAccount());
//    }

    private static final String BANK_ACCOUNT_URI = "/bank-account";

    @Test
    void shouldCreateBankAccountSuccessfully() throws Exception {

        var response = testRestTemplate
                .exchange(BANK_ACCOUNT_URI,
                        HttpMethod.POST,
                        createJsonHttpEntity(BankAccountMocks.bankAccountFormMock()), BankAccount.class);

        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

//    @Test
//    void shouldDeleteBankAccountSuccessfully() throws Exception {
//
//        doNothing().when(service).deleteBankAccount(ID);
//
//        mockMvc.perform(delete(BANK_ACCOUNT_URI.concat("/{id}"), ID)
//                .contentType("application/json"))
//                .andDo(print())
//                .andExpect(status().isAccepted());
//
//        var actual = controller.deleteBankAccount(ID);
//
//        assertEquals(new ResponseEntity<>(HttpStatus.ACCEPTED), actual);
//
//    }
//
//    @Test
//    void shouldReturnBankAccountSuccessfully() throws Exception {
//
//        when(service.getOneBankAccount(ID)).thenReturn(bankAccountResponseMock());
//
//        mockMvc.perform(get(BANK_ACCOUNT_URI.concat("/{id}"), ID)
//                .contentType("application/json"))
//                .andDo(print())
//                .andExpect(status().isOk());
//
//        var actual = controller.getOneBankAccount(ID);
//
//        verify(service, atLeastOnce()).getOneBankAccount(ID);
//
//        assertEquals(new ResponseEntity<>(bankAccountResponseMock(), HttpStatus.OK), actual);
//    }
//
//    @Test
//    void shouldUpdateBankAccountSuccessfully() throws Exception {
//
//        when(service.updateBankAccount(ID, bankAccountFormMock())).thenReturn(bankAccountResponseMock());
//
//        mockMvc.perform(put(BANK_ACCOUNT_URI.concat("/{id}"), ID)
//                .contentType("application/json")
//                .content(objectMapper.writeValueAsString(bankAccountFormMock())))
//                .andDo(print())
//                .andExpect(status().isOk());
//
//        var actual = controller.updateBankAccount(ID, bankAccountFormMock());
//
//        verify(service, atLeastOnce()).updateBankAccount(ID, bankAccountFormMock());
//
//        assertEquals(new ResponseEntity<>(bankAccountResponseMock(), HttpStatus.OK), actual);
//    }

    private static HttpHeaders createJsonHeader() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return httpHeaders;
    }

    private HttpEntity<BankAccountDTO> createJsonHttpEntity(BankAccountDTO bankAccount) {
        return new HttpEntity<>(bankAccount, createJsonHeader());
    }
}
