package io.github.bankapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode
@Entity
@Table(name = "tb_bank")
public class Bank implements Serializable {

    @Id
    @Column(nullable = false, length = 3)
    private String number;
    @Column(nullable = false, length = 60)
    private String name;
}
