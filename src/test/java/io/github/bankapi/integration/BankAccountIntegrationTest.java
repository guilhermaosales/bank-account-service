package io.github.bankapi.integration;

import io.github.bankapi.controller.BankAccountController;
import io.github.bankapi.mock.BankAccountMocks;
import io.github.bankapi.model.BankAccount;
import io.github.bankapi.model.dto.BankAccountDTO;
import io.github.bankapi.model.dto.BankAccountResponse;
import io.github.bankapi.repository.BankAccountRepository;
import org.assertj.core.api.Assertions;
import org.junit.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BankAccountIntegrationTest {

    @Autowired
    private TestRestTemplate testRestTemplate;
    @LocalServerPort
    private int port;
    @Mock
    private BankAccountController controller;
    @MockBean
    BankAccountRepository repositoryMock;

    @BeforeEach
    public void setUp() {

        BDDMockito.when(repositoryMock.save(BankAccountMocks.bankAccountToBeSaved()))
                .thenReturn(BankAccountMocks.bankAccountToBeSaved());

        BDDMockito.doNothing().when(repositoryMock).delete(ArgumentMatchers.any(BankAccount.class));

        BDDMockito.when(repositoryMock.save(BankAccountMocks.createBankAccount()))
                .thenReturn(BankAccountMocks.updatedBankAccount());

        BDDMockito.when(repositoryMock.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(BankAccountMocks.createBankAccount()));


    }

    private static final String BANK_ACCOUNT_URI = "/bank-account";

    @Test
    void shouldCreateBankAccountSuccessfully() throws Exception {

        var responseEntity = testRestTemplate
                .exchange(BANK_ACCOUNT_URI,
                        HttpMethod.POST,
                        createJsonHttpEntity(BankAccountMocks.bankAccountFormMock()), BankAccount.class);

        assertThat(responseEntity).isNotNull().satisfies(it -> {
            assertThat(it.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        });
    }

    @Test
    void shouldDeleteBankAccountSuccessfully() throws Exception {

        ResponseEntity<Void> responseEntity = testRestTemplate
                .exchange(BANK_ACCOUNT_URI.concat("/1"), HttpMethod.DELETE, null, Void.class);

        assertThat(responseEntity).isNotNull().satisfies(it -> {
            assertThat(it.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
            assertThat(it.getBody()).isNull();
        });

    }

    @Test
    void shouldUpdateBankAccountSuccessfully() throws Exception {

        ResponseEntity<Void> responseEntity = testRestTemplate
                .exchange(BANK_ACCOUNT_URI.concat("/1"),
                        HttpMethod.PUT,
                        createJsonHttpEntity(BankAccountMocks.bankAccountFormMock()), Void.class);

        assertThat(responseEntity).isNotNull().satisfies(it -> {
            assertThat(it.getStatusCode()).isEqualTo(HttpStatus.OK);
            assertThat(it.getBody()).isNull();
        });
    }

    @Ignore
    void shouldGetBankAccountSuccessfully() throws Exception {

        var expectedId = BankAccountMocks.bankAccountResponseMock().getId();
        var bankAccount = testRestTemplate.getForObject(BANK_ACCOUNT_URI.concat("/1"), BankAccountResponse.class);

        Assertions.assertThat(bankAccount).isNotNull();
        Assertions.assertThat(bankAccount.getId()).isNotNull();
        Assertions.assertThat(bankAccount.getId()).isEqualTo(expectedId);
    }

    private HttpEntity<BankAccountDTO> createJsonHttpEntity(BankAccountDTO bankAccount) {
        return new HttpEntity<>(bankAccount, createJsonHeader());
    }

    private static HttpHeaders createJsonHeader() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON_VALUE);
        return httpHeaders;
    }

}
