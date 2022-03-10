package oi.github.bankapi.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "tb_bank")
public class Bank implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false, length = 2)
    private String number;
    @Column(nullable = false, length = 60)
    private String name;
}
