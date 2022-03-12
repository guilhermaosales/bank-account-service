package oi.github.bankapi.model;

import oi.github.bankapi.enums.BankAccountType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "tb_bank_account")
public class BankAccount implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false, length = 4)
    private String agency;
    @Column(nullable = false, unique = true, length = 8)
    private String account;
    @Column(nullable = false, length = 3)
    private String bankNumber;
    @Column(nullable = false, length = 8)
    private BankAccountType accountType;

    @OneToOne
    @JoinColumn(name = "tb_bank_holder_id")
    private BankHolder bankHolder;


}
