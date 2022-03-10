package oi.github.bankapi.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "tb_bank_holder")
public class BankHolder implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false, length = 20)
    private String firstName;
    @Column(nullable = false, length = 40)
    private String secondName;


}
