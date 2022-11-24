package io.github.bankapi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Builder
@Data
@Table(name = "tb_bank_holder")
@AllArgsConstructor
@NoArgsConstructor
public class BankHolder implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false, length = 20)
    private String firstName;
    @Column(nullable = false, length = 40)
    private String secondName;

}
