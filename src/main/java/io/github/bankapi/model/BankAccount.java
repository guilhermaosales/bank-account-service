package io.github.bankapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.github.bankapi.enums.BankAccountTypeEnum;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "tb_bank_account")
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 4)
    @NotNull(message = "Agency cannot be empty")
    private String agency;

    @Column(unique = true, length = 12)
    @NotNull(message = "Account cannot be empty")
    private String account;

    @Column(nullable = false, length = 3)
    @NotNull(message = "Bank number cannot be empty")
    private String bankNumber;

    @Column(nullable = false, length = 9)
    @NotNull(message = "Account type cannot be empty")
    private BankAccountTypeEnum accountType;

    @JsonIgnore
    private LocalDateTime registrationDate;

    @JsonIgnore
    private LocalDateTime lastUpdateDate;

    @Column(nullable = false)
    @JsonIgnore
    @Builder.Default
    private boolean preferredAccount = false;

    @OneToOne(fetch = FetchType.LAZY, cascade=CascadeType.PERSIST)
    @JoinColumn(name = "tb_bank_holder_id")
    @NotNull(message = "Bank holder must be filled")
    private BankHolder bankHolder;
}
