package oi.github.bankapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Data
@Table(name = "tb_bank_holder")
public class BankHolder implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private UUID id;
    @Column(nullable = false, length = 20)
    private String firstName;
    @Column(nullable = false, length = 40)
    private String secondName;


}
