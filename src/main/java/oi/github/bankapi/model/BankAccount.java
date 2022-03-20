package oi.github.bankapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import oi.github.bankapi.enums.BankAccountTypeEnum;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Table(name = "tb_bank_account")
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
    @Column(nullable = false)
    @JsonIgnore
    private LocalDateTime registrationDate;
    @Column(nullable = false)
    @JsonIgnore
    private LocalDateTime lastUpdateDate;
    @Column(nullable = false)
    @JsonIgnore
    private boolean preferredAccount;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "tb_bank_holder_id")
    private BankHolder bankHolder;

}
