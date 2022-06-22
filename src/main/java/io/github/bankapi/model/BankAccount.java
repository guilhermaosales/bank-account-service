package io.github.bankapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.github.bankapi.enums.BankAccountTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

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
    private String agency;
    @Column(nullable = false, unique = true, length = 12)
    private String account;
    @Column(nullable = false, length = 3)
    private String bankNumber;
    @Column(nullable = false, length = 9)
    private BankAccountTypeEnum accountType;
    @JsonIgnore
    private LocalDateTime registrationDate;
    @JsonIgnore
    private LocalDateTime lastUpdateDate;
    @Column(nullable = false)
    @JsonIgnore
    private boolean preferredAccount;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "tb_bank_holder_id")
    private BankHolder bankHolder;

}
