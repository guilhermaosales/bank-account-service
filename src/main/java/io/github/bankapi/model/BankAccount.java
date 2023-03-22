package io.github.bankapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.github.bankapi.enums.BankAccountTypeEnum;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@EqualsAndHashCode
@Entity
@Table(name = "tb_bank_account")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BankAccount implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

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

    @OneToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    @JoinColumn(name = "tb_bank_holder_id")
    @NotNull(message = "Bank holder must be filled")
    private BankHolder bankHolder;
}
