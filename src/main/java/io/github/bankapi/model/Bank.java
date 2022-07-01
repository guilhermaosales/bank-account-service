package io.github.bankapi.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@EqualsAndHashCode
@Entity
@Data
@Table(name = "tb_bank")
public class Bank implements Serializable {

    @Id
    @Column(nullable = false, length = 3)
    private String number;
    @Column(nullable = false, length = 60)
    private String name;
}
