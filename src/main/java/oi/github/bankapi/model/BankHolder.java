package oi.github.bankapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private UUID id;
    @Column(nullable = false, length = 20)
    private String firstName;
    @Column(nullable = false, length = 40)
    private String secondName;


}
